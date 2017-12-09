package com.cloud.agent.util.docker;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyun on 2017/9/24.
 */
@Component
public class DockerGetUtil {

    private static  final Gson GSON = new Gson();

    /**
     * 获取swarm信息
     * @return
     */
    public Map swarm(){
        String data = DockerApi.dockerInfo("swram");
        return GSON.fromJson(data, HashMap.class);
    }

    /**
     * 获取swarm 集群信息
     * @return
     */
    public List<Map<String, Object>> nodes(){
        String data = DockerApi.dockerInfo("nodes");
        return GSON.fromJson(data, List.class);
    }

    /**
     * 获取docker info 信息
     * @return
     */
    public Map<String, Object> info(){
        try {
            String data = DockerApi.dockerInfo("info");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(data, Map.class);
        }catch (Exception e){
            return null;
        }
    }


    /**
     *
     * @param id
     * @return
     */
    public Map<String, Object> nodes(String id){
        String data = DockerApi.dockerInfo("nodes/" +id);
        return GSON.fromJson(data, Map.class);
    }

    /**
     * 获取镜像信息
     * @return
     */
    public List<Map<String, Object>> images(){
        try {
            String data = DockerApi.dockerInfo("images/json");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(data, List.class);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 获取镜像信息
     * @return
     */
    public Map<String, Object> images(String id){
        String data = DockerApi.dockerInfo("images/"+id+"/json");
        return GSON.fromJson(data, Map.class);
    }

    /**
     * 获取网络信息
     */
    public List<Map<String, Object>> networks(){
        String data = DockerApi.dockerInfo("networks");
        return GSON.fromJson(data, List.class);
    }

    /**
     * 获取网络信息
     */
    public Map<String, Object> networks(String id){
        String data = DockerApi.dockerInfo("networks/"+id);
        return GSON.fromJson(data, Map.class);
    }

    /**
     * 获取服务信息
     */
    public List<Map<String, Object>> services(){
        try {
            String data = DockerApi.dockerInfo("services");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(data, List.class);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 获取服务信息
     */
    public Map<String, Object> services(String id){
        try {
            String data = DockerApi.dockerInfo("services/" + id);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(data, HashMap.class);
        }catch (Exception e){
            Map map = new HashMap();
            return map;
        }
    }

    /**
     * 获取容器信息获取所有
     */
    public List<Map<String,Object>> containers(){
        try {
            String data = DockerApi.dockerInfo("containers/json?all=true");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(data, List.class);
        }catch (Exception e){

        }
        ArrayList list = new ArrayList();
        return list;
    }

    /**
     * 获取单个
     * @param container
     * @return
     */
    public Map<String, Object> containers(String container){
        String data = DockerApi.dockerInfo("containers/"+container+"/json");
        return GSON.fromJson(data, Map.class);
    }

}
