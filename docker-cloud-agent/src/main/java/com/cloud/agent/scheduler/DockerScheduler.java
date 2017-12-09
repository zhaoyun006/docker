package com.cloud.agent.scheduler;

import com.cloud.agent.configure.Configure;
import com.cloud.agent.configure.RedisKey;
import com.cloud.agent.entity.*;
import com.cloud.agent.scheduler.entity.ContainerMonitorEntity;
import com.cloud.agent.util.*;
import com.cloud.agent.util.docker.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.util.*;
import java.util.List;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author zhaozq
 * @version 1.0
 * @since 1.0
 */
@RestController
@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
public class DockerScheduler {

    private static String hostname;
    public static String ip;
    private static ArrayList<String> services;
    private static Long nodeStatus;
    public static Map containerServiceMap;
    private static HashSet<String> networks;
    private static String clusterName;
    private static HashSet pushServer;
    private static String clusterTp;
    static final String KUBERNETES = "kubernetes";
    static final String SWARM = "swarm";

    @Autowired
    private DockerGetUtil dockerGetUtil;

    static void setHostname() {
        if (hostname == null) {
            pushServer = new HashSet();
            containerServiceMap = new HashMap();
            hostname = HostNameUtil.getHostName("hostname");
            ip = HostNameUtil.getHostName("ip");
            LogUtil.info("获取到主机名和IP地址" + hostname + " " + ip);
            registerServer();
        }
    }

    public static String getClusterTp() {
        return clusterTp;
    }

    public static HashSet getPushServer() {
        return pushServer;
    }


    public static ArrayList<String> getServices() {
        return services;
    }


    /**
     * 注册master服务到cache
     * 一个集群中就一个master
     */
    @Scheduled(cron = "0/10 * * * * ?")
    void registerMaster() {
        if (null == clusterTp) {
            return;
        }
        RedisUtil redisUtil = new RedisUtil();
        String key = RedisKey.masterServersList.concat(clusterName);
        if (clusterTp.equals(SWARM)) {
            List<Map<String, Object>> nodes = dockerGetUtil.nodes();
            if (nodes == null) {
                return;
            }
        }
        if (clusterTp.equals(KUBERNETES)) {
            String r = HttpUtil.sendGet("http://127.0.0.1:8080/healthz/ping");
            if (!CheckUtil.checkString(r)) {
                return;
            }
        }
        if (null != clusterName) {
            redisUtil.setex(key, 20, ip);
            redisUtil.setex(RedisKey.serverIsMaster.concat(ip),50, "1");
        }
    }

    /**
     * 生产service名称列表
     */
    @Scheduled(cron = "0/10 * * * * ?")
    static void setServiceName() throws Exception {
        if (clusterTp.equals(KUBERNETES)){
            return;
        }
        if (services == null) {
            services = new ArrayList();
            setHostname();
            nodeStatus = 1L;
        }
        if (nodeStatus > 10) {
            LogUtil.info("无法获取服务信息,退出获取");
            return;
        }
        DockerServiceEntity dockerServiceEntity;
        RedisUtil redisUtil = new RedisUtil();
        DockerGetUtil dockerGetUtil = new DockerGetUtil();
        List<Map<String, Object>> data = dockerGetUtil.services();
        if (data == null) {
            nodeStatus += 1;
            return;
        } else {
            nodeStatus = 0L;
        }
        Map<String, Object> dataMap;
        String name;
        for (Map<String, Object> map : data) {
            dataMap = (Map<String, Object>) map.get("Spec");
            name = (String) dataMap.get("Name");
            dockerServiceEntity = ServiceInfoUtil.getServiceInfo(null, map);
            ObjectMapper mapper = new ObjectMapper();
            redisUtil.setex(RedisKey.serviceInfo.concat(clusterName).concat(name), 30, mapper.writeValueAsString(dockerServiceEntity));
            if (!services.contains(name)) {
                services.add(name);
            }
        }
    }

    /**
     * @param ip
     * @param update
     * @param serverEntities
     * @return
     */
    static ArrayList<PushServerEntity> setPushServer(String ip, Long update, ArrayList<PushServerEntity> serverEntities) {
        PushServerEntity pushServerEntity = new PushServerEntity();
        pushServerEntity.setIp(ip);
        pushServerEntity.setUpdate(update);
        serverEntities.add(pushServerEntity);
        return serverEntities;
    }

    /**
     * 注册服务器
     */
    @Scheduled(cron = "0/5 * * * * ?")
    static void registerServer() {
        if (ip == null) {
            setHostname();
        }
        ArrayList<PushServerEntity> serverEntities = new ArrayList<>();
        RedisUtil redisUtil = new RedisUtil();
        // 生产集群名
        if (null == clusterName) {
            clusterName = redisUtil.get(RedisKey.serverClueterName.concat(ip));
            clusterTp = redisUtil.get(RedisKey.serverClusterTp.concat(ip));
        }
        String key = RedisKey.cachePushServer.concat(clusterName);
        String addrs = redisUtil.get(key);
        if (addrs != null && addrs.length() > 8) {
            Type type = new TypeToken<ArrayList<PushServerEntity>>() {
            }.getType();
            List<PushServerEntity> list = new Gson().fromJson(addrs, type);
            Long update;
            for (PushServerEntity entity : list) {
                update = entity.getUpdate();
                // 排除5分钟没有更新的机器
                if (System.currentTimeMillis() / 1000 - update > 50) {
                    continue;
                }
                if (entity.getIp().equals(ip)) {
                    continue;
                }
                setPushServer(entity.getIp(), entity.getUpdate(), serverEntities);
            }
        }
        setPushServer(ip, System.currentTimeMillis() / 1000, serverEntities);
        for (PushServerEntity entity : serverEntities) {
            pushServer.add(entity.getIp());
        }
        Gson gson = new Gson();
        String serverData = gson.toJson(serverEntities);
        redisUtil.setex(key, 180, serverData);
    }

    /**
     * @param name
     * @param gson
     * @return
     */
    static ContainerMonitorEntity getContainerMonitor(String name, Gson gson) {
        RedisUtil redisUtil = new RedisUtil();
        String key = RedisKey.containerStats.concat(name);
        String oldData = redisUtil.get(key);
        ContainerMonitorEntity containerMonitorEntity;
        if (CheckUtil.checkString(oldData)) {
            Type type = new TypeToken<ArrayList<ContainerMonitorEntity>>() {
            }.getType();
            ArrayList<ContainerMonitorEntity> arrayList = gson.fromJson(oldData, type);
            containerMonitorEntity = arrayList.get(arrayList.size() - 1);
            return containerMonitorEntity;
        }
        containerMonitorEntity = new ContainerMonitorEntity();
        return containerMonitorEntity;
    }

    /**
     * @param name
     * @param redisUtil
     * @param gson
     * @param containerMonitorEntity
     */
    static ArrayList<ContainerMonitorEntity> setServiceMonitorData(String name, RedisUtil redisUtil, Gson gson, ContainerMonitorEntity containerMonitorEntity) {
        String key = RedisKey.serviceStats.concat(name);
        ArrayList scaleArrayList;
        ArrayList arrayList = new ArrayList();
        String oldData = redisUtil.get(key);
        if (CheckUtil.checkString(oldData)) {
            Type type = new TypeToken<ArrayList<ContainerMonitorEntity>>() {
            }.getType();
            arrayList = gson.fromJson(oldData, type);
            if (arrayList.size() > 301) {
                scaleArrayList = new ArrayList();
                for (int i = arrayList.size() - 301; i < 301; i++) {
                    scaleArrayList.add(arrayList.get(i));
                }
                arrayList = scaleArrayList;
            }
        }
        arrayList.add(containerMonitorEntity);
        return arrayList;
    }

    /**
     * @param monitorEntity
     * @param containerMonitorEntity
     * @return
     */
    static ContainerMonitorEntity getContainerMonitor(ContainerMonitorEntity monitorEntity, ContainerMonitorEntity containerMonitorEntity) {
        if (CheckUtil.checkString(monitorEntity.getCpu())) {
            monitorEntity.setCpu((RunCommandUtil.decimalFormat(Double.valueOf(containerMonitorEntity.getCpu()) + Double.valueOf(monitorEntity.getCpu()))));
        } else {
            monitorEntity.setCpu(containerMonitorEntity.getCpu());
        }
        if (CheckUtil.checkString(monitorEntity.getMemory())) {
            monitorEntity.setMemory((RunCommandUtil.decimalFormat(Double.valueOf(containerMonitorEntity.getMemory()) + Double.valueOf(monitorEntity.getMemory()))));
        } else {
            monitorEntity.setMemory(containerMonitorEntity.getMemory());
        }

        monitorEntity.setNetInput(containerMonitorEntity.getNetInput());
        monitorEntity.setNetOutput(containerMonitorEntity.getNetOutput());
        monitorEntity.setBlockInput(containerMonitorEntity.getBlockInput());
        monitorEntity.setBlockOutput(containerMonitorEntity.getBlockOutput());
        return monitorEntity;
    }

    /**
     * 设置
     */
    @Scheduled(cron = "0/15 * * * * ?")
    void setServerContainerInfo() {
        if (clusterTp.equals(KUBERNETES)){
            return;
        }
        if (null == ip) {
            return;
        }
        Map<String, Object> map = dockerGetUtil.info();
        if (null == map) {
            return;
        }
        Map<String, Object> swarm = (Map<String, Object>) map.get("Swarm");
        DockerCloudServerEntity dockerCloudServerEntity = new DockerCloudServerEntity();
        dockerCloudServerEntity.setImages(map.get("Images").toString());
        dockerCloudServerEntity.setInstance(map.get("ContainersRunning").toString());
        dockerCloudServerEntity.setNoRunning(map.get("ContainersStopped").toString());
        dockerCloudServerEntity.setCpuNumber(map.get("NCPU").toString());
        dockerCloudServerEntity.setPaused(map.get("ContainersPaused").toString());
        dockerCloudServerEntity.setHostname(map.get("Name").toString());
        dockerCloudServerEntity.setDockerVersion(map.get("ServerVersion").toString());
        dockerCloudServerEntity.setStatus(swarm.get("LocalNodeState").toString());
        String mem = map.get("MemTotal").toString();
        dockerCloudServerEntity.setMemSize(Long.valueOf(mem) / 1024 / 1024 / 1024 + "GB");
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setex(RedisKey.serverContainerInfo.concat(ip), 50, new Gson().toJson(dockerCloudServerEntity));
    }


    /**
     * 将多个服务器上的服务的容器组合起来
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public static void setServices() {
        if (null == services) {
            try {
                setServiceName();
            } catch (Exception e) {

            }
        }
        if (null == services || services.size() < 1) {
            return;
        }
        DockerGetUtil getUtil = new DockerGetUtil();
        Gson gson = new Gson();
        RedisUtil redisUtil = new RedisUtil();
        String key = RedisKey.cachePushServer.concat(clusterName);
        String addrs = redisUtil.get(key);
        String containerData;
        Map serviceMap;
        ContainerMonitorEntity monitorEntity;
        ContainerMonitorEntity containerMonitorEntity;
        LogUtil.info("现在有服务名" + gson.toJson(services));
        if (addrs != null && addrs.length() > 8) {
            LogUtil.info("获取到服务器地址" + addrs);
            Type type = new TypeToken<ArrayList<PushServerEntity>>() {
            }.getType();
            List<PushServerEntity> list = new Gson().fromJson(addrs, type);
            for (String service : services) {
                serviceMap = getUtil.services(service);
                LogUtil.info("获取到服务" + service);
                monitorEntity = new ContainerMonitorEntity();
                monitorEntity.setName(service);
                monitorEntity.setTime(System.currentTimeMillis() / 1000 + "");
                int scaleCounter = 0;
                ArrayList<DockerContainerEntity> dockerContainerEntityArrayList = new ArrayList<>();
                List<DockerContainerEntity> dockerContainerEntityList;
                for (PushServerEntity entity : list) {
                    containerData = redisUtil.get(RedisKey.serviceContainers.concat(entity.getIp()).concat(service));
                    if (CheckUtil.checkString(containerData)) {
                        Type containerType = new TypeToken<ArrayList<DockerContainerEntity>>() {
                        }.getType();
                        dockerContainerEntityList = gson.fromJson(containerData, containerType);
                        for (DockerContainerEntity containerEntity : dockerContainerEntityList) {
                            if (null == containerEntity.getPort()) {
                                containerEntity.setPort("无");
                            }
                            containerMonitorEntity = getContainerMonitor(containerEntity.getId().substring(0, 12), gson);
                            if (null != containerEntity && CheckUtil.checkString(containerMonitorEntity.getCpu())) {
                                monitorEntity = getContainerMonitor(monitorEntity, containerMonitorEntity);
                                scaleCounter += 1;
                            }
                            containerEntity.setPort(ServiceInfoUtil.getPublishPort(serviceMap, "->"));
                            dockerContainerEntityArrayList.add(containerEntity);
                        }
                    }
                }
                if (CheckUtil.checkString(monitorEntity.getCpu())) {
                    monitorEntity.setCpu((RunCommandUtil.decimalFormat(Double.valueOf(monitorEntity.getCpu()) / scaleCounter)));
                    monitorEntity.setMemory(RunCommandUtil.decimalFormat(Double.valueOf(monitorEntity.getMemory()) / scaleCounter));
                    redisUtil.setex(RedisKey.serviceStats.concat(service), 300, gson.toJson(setServiceMonitorData(service, redisUtil, gson, monitorEntity)));
                }
                redisUtil.setex(RedisKey.serviceContainers.concat(clusterName).concat(service), 300, gson.toJson(dockerContainerEntityArrayList));
            }
        }
    }

    /**
     *
     */
    @Scheduled(cron = "0/5 * * * * ?")
    void setContainers() {
        setHostname();
        if (clusterTp.equals(KUBERNETES)){
            return;
        }
        RedisUtil redisUtil = new RedisUtil();
        Gson gson = new Gson();
        DockerGetUtil dockerGetUtil = new DockerGetUtil();
        List<Map<String, Object>> data = dockerGetUtil.containers();
        Map<String, String> dataMap;
        ArrayList containers;
        Map<String, ArrayList<Map>> containerMap = new HashMap<>();
        String name;
        Map<String, Object> container;
        Map<String, Map> networks;
        Map networks1;
        for (Map<String, Object> map : data) {
            dataMap = (Map<String, String>) map.get("Labels");
            name = dataMap.get("com.docker.swarm.service.name");
            if (containerMap.containsKey(name)) {
                containers = containerMap.get(name);
            } else {
                containers = new ArrayList();
            }
            container = new HashMap<>();
            container.put("service", name);
            List names = (List) map.get("Names");
            String containerName = (String) names.get(0);
            containerName = containerName.replace("/", "");
            if (containerName.length() > 17) {
                container.put("name", containerName.substring(0, containerName.length() - 17));
            } else {
                container.put("name", containerName);
            }
            container.put("port", gson.toJson(map.get("Ports")));
            container.put("state", map.get("State"));
            networks1 = (Map) map.get("NetworkSettings");
            networks = (Map) networks1.get("Networks");
            for (Map.Entry<String, Map> maps : networks.entrySet()) {
                container.put("network", maps.getKey() + ":" + networks.get(maps.getKey()).get("IPAddress"));
            }
            container.put("id", map.get("Id"));
            container.put("image", map.get("Image").toString().split("@")[0]);
            container.put("host", "{0}({1}) ".replace("{0}", hostname).replace("{1}", ip));
            container.put("created", DateUtil.timeStamp2Date(map.get("Created") + "000", DateUtil.TIME_FORMAT));
            containers.add(container);
            containerMap.put(name, containers);
            // 存储容器对应的服务名
            if (!containerServiceMap.containsKey(container.get("id"))) {
                containerServiceMap.put(map.get("Id").toString().substring(0, 12), name);
            }
        }
        for (Map.Entry<String, ArrayList<Map>> entry : containerMap.entrySet()) {
            if (null == entry.getKey()) {
                continue;
            }
            redisUtil.setex(RedisKey.serviceContainers.concat(ip).concat(entry.getKey()), 10, gson.toJson(entry.getValue()));
        }
    }

    /**
     * 将网络名称添加到变量中
     * 每59秒执行一次
     */
    @Scheduled(cron = "0/59 * * * * ?")
    public static void setNetworks() {
        if (null == networks) {
            networks = new HashSet<>();
        }
        RedisUtil redisUtil = new RedisUtil();
        String[] netTemp;
        String result = RunCommandUtil.runScript(DockerNetworkCmd.getNetwork);
        if (CheckUtil.checkString(result)) {
            String[] results = result.split("\n");
            for (String network : results) {
                netTemp = network.split(" ");
                if (netTemp.length < 2) {
                    continue;
                }
                networks.add(netTemp[1]);
                redisUtil.setex(RedisKey.networkInfo.concat(netTemp[1]), 120, netTemp[0]);
            }
        }
    }

    /**
     * 设置网络信息 2017-10-08
     * 每15秒更新一次
     */
    @Scheduled(cron = "0/15 * * * * ?")
    void setNetworksScheduled() {
        if (null == networks) {
            networks = new HashSet<>();
            setNetworks();
        }
        if (null == hostname) {
            return;
        }

        RedisUtil redisUtil = new RedisUtil();
        Map<String, HashSet> networkMap = new HashMap();
        Map<String, Object> map;
        Map<String, Map> containers;
        Map<String, String> container;
        String containerId;
        DockerGetUtil getUtil = new DockerGetUtil();
        DockerContainerEntity dockerContainerEntity;
        HashSet hashSet;
        for (String network : networks) {
            map = getUtil.networks(network);
            if (null != map) {
                containers = (Map<String, Map>) map.get("Containers");
                if (null == containers) {
                    continue;
                }
                for (Map.Entry<String, Map> entry : containers.entrySet()) {
                    containerId = entry.getKey().substring(0, 12);
                    container = entry.getValue();
                    dockerContainerEntity = new DockerContainerEntity();
                    dockerContainerEntity.setId(containerId);
                    dockerContainerEntity.setName(container.get("Name"));
                    dockerContainerEntity.setNetworkId(map.get("Id").toString().substring(0, 12));
                    dockerContainerEntity.setNetworkName(network);
                    dockerContainerEntity.setNetwork(container.get("IPv4Address"));
                    dockerContainerEntity.setHost("{0}({1}) ".replace("{0}", hostname).replace("{1}", ip));
                    if (networkMap.containsKey(network)) {
                        hashSet = networkMap.get(network);
                    } else {
                        hashSet = new HashSet();
                    }
                    hashSet.add(dockerContainerEntity);
                    networkMap.put(network, hashSet);
                }
            }
        }
        Gson gson = new Gson();
        for (String network : networks) {
            redisUtil.setex(RedisKey.networkContainers.concat(ip).concat(network), 50, gson.toJson(networkMap.get(network)));
        }
    }

    /**
     * 将多个服务器上的网络的容器组合起来
     */
    @Scheduled(cron = "0/9 * * * * ?")
    void mergerNetworkContainer() {
        if (null == networks || networks.size() < 1) {
            return;
        }
        Gson gson = new Gson();
        RedisUtil redisUtils = new RedisUtil();
        Jedis redisUtil = redisUtils.getJedis();
        String app = RedisUtil.app + "_";
        // 获取锁
        String lock = redisUtil.get(app + RedisKey.networkSetLock);
        if (CheckUtil.checkString(lock)) {
            LogUtil.info("network 获取到锁文件,退出 " + lock);
            return;
        }
        redisUtil.setex(app + RedisKey.serviceSetLock, 8, DateUtil.getCurrTime() + " " + ip);
        String key = app + RedisKey.cachePushServer.concat(clusterName);
        String addrs = redisUtil.get(key);
        String containerData;
        LogUtil.info("现在有网络名" + gson.toJson(networks));
        if (addrs != null && addrs.length() > 8) {
            Type type = new TypeToken<ArrayList<PushServerEntity>>() {
            }.getType();
            List<PushServerEntity> list = new Gson().fromJson(addrs, type);
            List<DockerContainerEntity> dockerContainerEntityList;
            for (String network : networks) {
                HashSet<DockerContainerEntity> dockerContainerEntityArrayList = new HashSet<>();
                for (PushServerEntity entity : list) {
                    containerData = redisUtil.get(app + RedisKey.networkContainers.concat(entity.getIp()).concat(network));
                    if (CheckUtil.checkString(containerData)) {
                        Type containerType = new TypeToken<ArrayList<DockerContainerEntity>>() {
                        }.getType();
                        dockerContainerEntityList = gson.fromJson(containerData, containerType);
                        if (null == dockerContainerEntityList) {
                            continue;
                        }
                        for (DockerContainerEntity containerEntity : dockerContainerEntityList) {
                            dockerContainerEntityArrayList.add(containerEntity);
                        }
                    }
                }
                redisUtil.setex(app + RedisKey.networkContainers.concat(clusterName).concat(network), 200, gson.toJson(dockerContainerEntityArrayList));
            }
        }
    }

    /**
     * 设置集群镜像
     */
    @Scheduled(cron = "0/40 * * * * ?")
    private void setImages() {
        if (services == null || services.size() < 1) {
            return;
        }
        ImagesEnttiy imagesEnttiy;
        ArrayList imagesList = new ArrayList();
        List<Map<String, Object>> list = dockerGetUtil.images();
        for (Map<String, Object> objectMap : list) {
            imagesEnttiy = new ImagesEnttiy();
            imagesEnttiy.setCreateTime(DateUtil.timeStamp2Date(objectMap.get("Created").toString().concat("000"), null));
            imagesEnttiy.setSize((Long.valueOf(objectMap.get("Size").toString()) / 1000 / 1000) + "MB");
            imagesEnttiy.setRegistry(StringUtils.join(objectMap.get("RepoDigests"), "<br>"));
            imagesEnttiy.setFrom(ip);
            imagesEnttiy.setTag(StringUtils.join(objectMap.get("RepoTags"), "<br>"));
            imagesEnttiy.setName("None");
            imagesList.add(imagesEnttiy);
        }
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setex(RedisKey.serverImages.concat(clusterName), 300, new Gson().toJson(imagesList));
    }
}
