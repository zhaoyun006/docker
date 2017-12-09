package com.cloud.util;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyun on 2017/9/24.
 */
public class DockerCreateUtil {

    private static  final Gson GSON = new Gson();

    /**
     * 获取swarm信息
     * @return
     */
    public Map swarm(){
        String data = DockerApi.dockerCreate("swarm/init", "");
        return GSON.fromJson(data, HashMap.class);
    }


    /**
     * 获取镜像信息
     * @return
     */
    public Map<String, Object> images(String json){
        String data = DockerApi.dockerCreate("images/create", json);
        return GSON.fromJson(data, HashMap.class);
    }

    /**
     * 获取网络信息
     */
    public Map<String, Object> networks(String json){
        String data = DockerApi.dockerCreate("networks/create", json);
        return GSON.fromJson(data, HashMap.class);
    }

    /**
     * 获取服务信息
     */
    public String services() throws Exception{
        String json = "{\n" +
                "  \"Name\": \"web\",\n" +
                "  \"TaskTemplate\": {\n" +
                "    \"ContainerSpec\": {\n" +
                "      \"Image\": \"nginx:latest\",\n" +
                "      \"DNSConfig\": {\n" +
                "        \"Nameservers\": [\n" +
                "          \"10.16.25.126\",\n" +
                "\t  \"10.16.25.127\"\n" +
                "        ],\n" +
                "        \"Options\": [\n" +
                "          \"timeout:3\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    \"LogDriver\": {\n" +
                "      \"Name\": \"json-file\",\n" +
                "      \"Options\": {\n" +
                "        \"max-file\": \"3\",\n" +
                "        \"max-size\": \"10M\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"Placement\": {},\n" +
                "    \"Resources\": {\n" +
                "      \"Limits\": {\n" +
                "        \"MemoryBytes\": 104857600\n" +
                "      },\n" +
                "      \"Reservations\": {}\n" +
                "    },\n" +
                "    \"RestartPolicy\": {\n" +
                "      \"Condition\": \"on-failure\",\n" +
                "      \"Delay\": 10000000000,\n" +
                "      \"MaxAttempts\": 10\n" +
                "    }\n" +
                "  },\n" +
                "  \"Mode\": {\n" +
                "    \"Replicated\": {\n" +
                "      \"Replicas\": 4\n" +
                "    }\n" +
                "  },\n" +
                "  \"UpdateConfig\": {\n" +
                "    \"Parallelism\": 2,\n" +
                "    \"Delay\": 1000000000,\n" +
                "    \"FailureAction\": \"pause\",\n" +
                "    \"Monitor\": 15000000000,\n" +
                "    \"MaxFailureRatio\": 0.15\n" +
                "  },\n" +
                "  \"RollbackConfig\": {\n" +
                "    \"Parallelism\": 1,\n" +
                "    \"Delay\": 1000000000,\n" +
                "    \"FailureAction\": \"pause\",\n" +
                "    \"Monitor\": 15000000000,\n" +
                "    \"MaxFailureRatio\": 0.15\n" +
                "  },\n" +
                "  \"EndpointSpec\": {\n" +
                "    \"Ports\": [\n" +
                "      {\n" +
                "        \"Protocol\": \"tcp\",\n" +
                "        \"PublishedPort\": 8080,\n" +
                "        \"TargetPort\": 80\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"Labels\": {\n" +
                "    \"foo\": \"nginx-test\"\n" +
                "  }\n" +
                "}";
        return HttpUtil.httpPostJson("http://10.16.55.101:2375/services/create", json, "POST");
    }



    /**
     * 创建单个
     * @param json
     * @return
     */
    public Map<String, Object> container(String json){
        String data = DockerApi.dockerCreate("containers/create", json);
        return GSON.fromJson(data, Map.class);
    }

}
