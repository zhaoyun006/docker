package com.cloud.sms.docker.container.util;

import com.cloud.sms.docker.service.service.DockerServiceEntity;
import com.cloud.sms.docker.util.DateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaoyun on 2017/10/2.
 */
public class ContainerUtil {

    /**
     *
     * @param data
     * @return
     */
    public static DockerServiceEntity info(String data){
        System.out.println(data);
        Map<String,Object> map ;
        DockerServiceEntity serviceEntity = new DockerServiceEntity();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(data, Map.class);
        }catch (Exception e){
            map = new HashMap();
        }
        if (map == null){
            return null;
        }
        if (map.size() < 1){
            return serviceEntity;
        }
        Map State = (Map<String,Object>) map.get("State");
        serviceEntity.setStartedAt(DateUtil.utc2Date(State.get("StartedAt").toString()));
        Map Config;
        Map HostConfig;
        HostConfig = (Map<String,Object>) map.get("HostConfig");
        Config = (Map<String,Object>) map.get("Config");
        try {
            serviceEntity.setLimitCpu(HostConfig.get("CpuQuota").toString());
        }catch (Exception e){
            serviceEntity.setLimitCpu("无限制");
        }
        System.out.println(StringUtils.join(map.get("Args")," "));
        serviceEntity.setCmd(StringUtils.join(map.get("Args")," "));
        try {
            serviceEntity.setLimitMemory(HostConfig.get("Memory").toString());
        }catch (Exception e){
            serviceEntity.setLimitMemory("无限制");
        }
        try {
            serviceEntity.setImage(Config.get("Image").toString());
        }catch (Exception e){
            serviceEntity.setImage("无");
        }
        serviceEntity.setCommands("");
        serviceEntity.setStatus(1);
        serviceEntity.setCreatedAt(DateUtil.utc2Date(map.get("Created").toString()));
        try {
            System.out.println(mapper.writeValueAsString(serviceEntity));
        }catch (Exception e){

        }
        return serviceEntity;
    }
}
