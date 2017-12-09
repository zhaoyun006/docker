package com.cloud.agent.scheduler;

import com.cloud.agent.configure.RedisKey;
import com.cloud.agent.entity.DockerServiceEntity;
import com.cloud.agent.scheduler.entity.ContainerMonitorEntity;
import com.cloud.agent.util.CheckUtil;
import com.cloud.agent.util.LogUtil;
import com.cloud.agent.util.RedisUtil;
import com.cloud.agent.util.RunCommandUtil;
import com.cloud.agent.util.docker.DockerApi;
import com.cloud.agent.util.docker.DockerGetUtil;
import com.cloud.agent.util.docker.DockerServiceCmd;
import com.cloud.agent.util.docker.ServiceInfoUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaoyun on 2017/10/1. 17:56
 * 监控docker容器信息
 * 并进行扩容
 * @author zhaoyun
 */
@RestController
@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
public class ScaleScheduler {

    private static Map<String, Map<String, String>> servcieScaleMap;
    private static Map<String, Long> scaleLock;

    /**
     * 每俩分钟更新一次数据
     * 自动扩容
     * 通过监控信息判断是否扩容
     * 扩容节点数据来自服务配置
     */
    @Scheduled(cron = "0 * * * * ?")
    static void setServcieScaleMap() {
        DockerGetUtil dockerGetUtil = new  DockerGetUtil();
        ArrayList<String > services = DockerScheduler.getServices();
        if (null == services){
            return;
        }
        if (null == servcieScaleMap){
            servcieScaleMap = new HashMap<>();
            scaleLock = new HashMap();
        }
        LogUtil.info("开始设置配置信息");
        Map<String, Object> data;
        Map spec;
        Map labels;
        String maxReplicas;
        String maxCpu;
        String maxMem;
        Map<String, String> scaleMap;
        for (String service:services){
            data = dockerGetUtil.services(service);
            if (null == data){
                continue;
            }
            scaleMap = new HashMap<>();
            spec = (Map<String, Object>) data.get("Spec");
            labels = (Map<String, String>) spec.get("Labels");
            if (null == labels){
                continue;
            }
            maxReplicas = (String) labels.get("max-replicas");
            maxCpu = (String) labels.get("max-cpu");
            maxMem = (String) labels.get("max-mem");
            LogUtil.info("maxReplicas" + maxReplicas);
            LogUtil.info("maxCpu" + maxCpu);
            LogUtil.info("maxMem" + maxMem);
            if (CheckUtil.checkString(maxReplicas)){
                scaleMap.put("max-replicas", maxReplicas);
                scaleMap.put("max-cpu", maxCpu);
                scaleMap.put("max-mem", maxMem);
                servcieScaleMap.put(service, scaleMap);
                LogUtil.info(new Gson().toJson(servcieScaleMap));
            }
        }
    }

    /**
     * 从监控中获取服务的使用率
     */
    @Scheduled(cron = "*/10 * * * * ?")
    static void autoScale() {
        if (null == DockerScheduler.getServices()){
            return;
        }
        if (null == servcieScaleMap ){
            setServcieScaleMap();
        }
        LogUtil.info("开始检查扩容");
        DockerGetUtil dockerGetUtil = new  DockerGetUtil();
        String service;
        String cpu;
        int maxReplicas = 0;
        String mem;
        Map<String, Object> serviceInfoMap;
        Gson gson = new Gson();
        Map<String, String> scaleMap;
        ArrayList<ContainerMonitorEntity> arrayList;
        ContainerMonitorEntity containerMonitorEntity;
        if (null == servcieScaleMap){
            return;
        }
        DockerServiceEntity serviceEntity;
        RedisUtil redisUtil = new RedisUtil();
        for (Map.Entry<String,Map<String,String>> entry: servcieScaleMap.entrySet()){
            service = entry.getKey();
            String  oldData = redisUtil.get(RedisKey.serviceStats.concat(service));
            if (!CheckUtil.checkString(oldData)) {
                 continue;
            }
            Type type = new TypeToken<ArrayList<ContainerMonitorEntity>>() {
                }.getType();
            arrayList = gson.fromJson(oldData, type);
            containerMonitorEntity = arrayList.get(arrayList.size() -1);
            cpu = containerMonitorEntity.getCpu();
            mem = containerMonitorEntity.getMemory();
            scaleMap = servcieScaleMap.get(service);
            if (null == scaleMap){
                continue;
            }
            if (null == cpu || mem == null){
                LogUtil.info("service null data" + gson.toJson(containerMonitorEntity) + " "+service);
                continue;
            }
            LogUtil.info("scaleMap" + gson.toJson(scaleMap));
            LogUtil.info("scaleMap cpu" + scaleMap.get("max-cpu") +" " + cpu);
            LogUtil.info("scaleMap mem" + scaleMap.get("max-mem") + " " + mem);
            LogUtil.info("scaleMap replicas" + scaleMap.get("max-replicas"));
            LogUtil.info("scaleMap service" + service);
            if (Double.valueOf(cpu) > Integer.valueOf(scaleMap.get("max-cpu"))){
                maxReplicas = Integer.valueOf(scaleMap.get("max-replicas"));
            }
            if (Double.valueOf(mem) > Integer.valueOf(scaleMap.get("max-mem"))){
                maxReplicas = Integer.valueOf(scaleMap.get("max-replicas"));
            }
            if (maxReplicas > 0){
                if (scaleLock.containsKey(service)) {
                    if(System.currentTimeMillis()/1000 - scaleLock.get(service) < 50){
                        LogUtil.info("扩容间隔太短,跳出扩容");
                        continue;
                    }
                }
                scaleLock.put(service, System.currentTimeMillis() / 1000);
                serviceInfoMap = dockerGetUtil.services(service);
                serviceEntity = ServiceInfoUtil.getServiceInfo(null,serviceInfoMap);
                LogUtil.info("扩容State" + serviceEntity.getState());
                if (null != serviceEntity.getState() && serviceEntity.getState().toLowerCase().contains("upd")){
                    LogUtil.info("正在扩容中，不能重复扩容");
                    continue;
                }
                if (Double.valueOf(serviceEntity.getReplicas()) < maxReplicas){
                    String scale = service + "="+ (new Double(serviceEntity.getReplicas()).intValue() + 1);
                    String result =  RunCommandUtil.runScript(  DockerServiceCmd.scaleService.replace("{0}", scale));
                    DockerApi.queue("扩缩容操作"+service, "system", result);
                }
            }
        }
    }
}
