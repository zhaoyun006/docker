package com.cloud.agent.thread;

import com.cloud.agent.configure.RedisKey;
import com.cloud.agent.scheduler.entity.ContainerMonitorEntity;
import com.cloud.agent.util.CheckUtil;
import com.cloud.agent.util.RedisUtil;
import com.cloud.agent.util.RunCommandUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by zhaoyun on 2017/10/1.
 */
public class MonitorThread extends Thread{

    private String dockerCmd;

    Logger logger = LoggerFactory.getLogger(MonitorThread.class);

    public MonitorThread(String dockerCmd) {
        this.dockerCmd = dockerCmd;
        super.setName("MonitorThread");
    }

    /**
     *
     * @param v
     * @return
     */
    static String convert(String v){
        Double cv;
        if (v.contains("MB")){
            return v.replace("MB", "");
        }
        if (v.contains("GB")){
            v =  v.replace("GB", "");
            cv = Double.valueOf(v);
            return RunCommandUtil.decimalFormat(cv * 1024) +"";
        }
        if (v.contains("TB")){
            v =  v.replace("TB", "");
            cv = Double.valueOf(v);
            return RunCommandUtil.decimalFormat(cv * 1024 * 1024) +"";
        }
        if (v.contains("KB")){
            v =  v.replace("KB", "");
            cv = Double.valueOf(v);
            return RunCommandUtil.decimalFormat(cv / 1024) +"";
        }
        if (v.contains("kB")){
            v =  v.replace("kB", "");
            cv = Double.valueOf(v);
            return RunCommandUtil.decimalFormat(cv / 1024 / 1024) +"";
        }
        if (v.contains("B")){
            v =  v.replace("B", "");
            cv = Double.valueOf(v);
            return RunCommandUtil.decimalFormat(cv / 1024 / 1024) +"";
        }
        return "0";
    }

    @Override
    public void run(){
        RedisUtil redisUtil = new RedisUtil();
        String result = RunCommandUtil.runScript(dockerCmd);
        if (!CheckUtil.checkString(result)){return;}
        ArrayList arrayList = new ArrayList();
        ArrayList scaleArrayList ;
        String[] list = result.split("\n");
        Gson gson = new Gson();
        String[] info;
        String netInput;
        String netOutput;
        String blockInput;
        String blockOutput;
        ContainerMonitorEntity containerMonitorEntity;
        for (String c:list){
            info = c.split(" ");
            if (info.length< 6){
                logger.info("获取到监控数据"+ gson.toJson(info));
                continue;
            }
            containerMonitorEntity = new ContainerMonitorEntity();
            containerMonitorEntity.setName(info[0]);
            containerMonitorEntity.setCpu(info[1].replace("%",""));
            containerMonitorEntity.setMemory(info[2].replace("%",""));
            netInput = convert(info[3]);
            netOutput = convert(info[4]);
            blockInput = convert(info[5]);
            blockOutput = convert(info[6]);
            containerMonitorEntity.setBlockOutput(blockOutput);
            containerMonitorEntity.setBlockInput(blockInput);
            containerMonitorEntity.setNetOutput(netOutput);
            containerMonitorEntity.setNetInput(netInput);
            logger.info("获取到监控数据" + gson.toJson(containerMonitorEntity));
            containerMonitorEntity.setTime(System.currentTimeMillis() / 1000 +"");
            String key = RedisKey.containerStats.concat(containerMonitorEntity.getName());
            String  oldData = redisUtil.get(key);
            if (CheckUtil.checkString(oldData)){
                arrayList = gson.fromJson(oldData, ArrayList.class);
                if (arrayList.size() > 300){
                    scaleArrayList = new ArrayList();
                    for (int i=arrayList.size() - 300; i<300;i++){
                        scaleArrayList.add(arrayList.get(i));
                    }
                    arrayList = scaleArrayList;
                }
            }
            arrayList.add(containerMonitorEntity);
            redisUtil.setex(key, 30, gson.toJson(arrayList));
        }
        this.interrupt();
    }
}
