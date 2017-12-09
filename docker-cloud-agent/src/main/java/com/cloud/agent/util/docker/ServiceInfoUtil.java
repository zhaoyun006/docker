package com.cloud.agent.util.docker;

import com.cloud.agent.entity.DockerServiceEntity;
import com.cloud.agent.util.CheckUtil;
import com.cloud.agent.util.DateUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyun on 2017/10/2.
 */
public class ServiceInfoUtil {

    private static Logger logger = LoggerFactory.getLogger(ServiceInfoUtil.class);

    /**
     * 获取端口信息
     * @param serviceInfoMap
     * @return
     */
    public static String getPublishPort(Map serviceInfoMap, String sp){
        try {
            String portData = "";
            Map<String, ArrayList> endpointSpec = (Map<String, ArrayList>) serviceInfoMap.get("Endpoint");
            ArrayList<Map> ports = endpointSpec.get("Ports");
            for (Map port : ports) {
                portData += port.get("PublishedPort") + sp + port.get("TargetPort") + ",";
            }
            return portData;
        }catch (Exception e){
            return null;
        }
    }

    /**
     *
     * @param serviceInfoMap
     * @return
     */
    public static String getPort(Map serviceInfoMap){
        String port = getPublishPort(serviceInfoMap, ":");
        if (CheckUtil.checkString(port)){
           return port.split(":")[0];
        }
        return null;
    }

    /**
     *
     * @param service
     * @param serviceInfoMap
     * @return
     */
    public static DockerServiceEntity getServiceInfo(String service, Map serviceInfoMap){
        if (null == serviceInfoMap){
            DockerGetUtil dockerGetUtil = new DockerGetUtil();
            serviceInfoMap = dockerGetUtil.services(service);
        }
        DockerServiceEntity dockerServiceEntity = new DockerServiceEntity();
        Map space = (Map<String, Object>) serviceInfoMap.get("Spec");
        Map mode = (Map<String, String>) space.get("Mode");
        Map taskTemplate = (Map<String, String>) space.get("TaskTemplate");
        Map containerSpec =  (Map<String, String>) taskTemplate.get("ContainerSpec");
        Map resources = (Map<String,Object>) taskTemplate.get("Resources");
        Map limits = (Map<String,Object>)resources.get("Limits");
        Map replicated = (Map<String, String>) mode.get("Replicated");
        ArrayList<Map<String,String>> networks = (ArrayList<Map<String,String>>) taskTemplate.get("Networks");
        Map updateStatus =  (Map<String, String>) serviceInfoMap.get("UpdateStatus");
        dockerServiceEntity.setReplicas(replicated.get("Replicas").toString());
        dockerServiceEntity.setCreatedAt(DateUtil.utc2Date(serviceInfoMap.get("CreatedAt").toString()));
        if (null != limits) {
            try {
                dockerServiceEntity.setLimitMemory(limits.get("MemoryBytes").toString());
            }catch (Exception e){
                dockerServiceEntity.setLimitMemory("无");
            }
            try {
                dockerServiceEntity.setLimitCpu(limits.get("NanoCPUs").toString());
            }catch (Exception e){
                dockerServiceEntity.setLimitCpu("无");
            }
        }
        try {
            dockerServiceEntity.setState(new Gson().toJson(updateStatus));
        }catch (Exception e){
            dockerServiceEntity.setState("");
        }
        dockerServiceEntity.setImage(containerSpec.get("Image").toString().split("@")[0]);
        try {
            String mountData = "";
            ArrayList<Map> mounts = (ArrayList) containerSpec.get("Mounts");
            for (Map mount:mounts){
                mountData += mount.get("Source").toString() +":" + mount.get("Target").toString()+",";
            }
            dockerServiceEntity.setMount(mountData.substring(0, mountData.length()-1));
        }catch (Exception e){
            dockerServiceEntity.setMount(null);
        }

        try {
            Map labels = (Map<String, String>) space.get("Labels");
            String domain = (String) labels.get("domain");
            dockerServiceEntity.setDomain(domain);
        }catch (Exception e){
            dockerServiceEntity.setDomain(null);
        }

       dockerServiceEntity.setPort(getPublishPort(containerSpec,":"));

        try{
            DockerGetUtil dockerGetUtil = new DockerGetUtil();
            String networkData = "";
            for (Map<String, String> map:networks){
                networkData += dockerGetUtil.networks(map.get("Target").toString()).get("Name")+",";
            }
            dockerServiceEntity.setNetwork(networkData);
        }catch (Exception e){
            dockerServiceEntity.setNetwork(null);
        }
        try{
            String envData = "";
            ArrayList<String>  envs = (ArrayList<String>)containerSpec.get("Env");
            for (String env:envs){
                envData += env+",";
            }
            dockerServiceEntity.setEnv(envData);
        }catch (Exception e){
            dockerServiceEntity.setEnv(null);
        }
        logger.info("serviceUtil"+ new Gson().toJson(dockerServiceEntity));
        dockerServiceEntity.setName(space.get("Name").toString());
        return dockerServiceEntity;

    }
}
