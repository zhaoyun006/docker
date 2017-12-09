package com.cloud.util;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyun on 2017/9/24.
 */
public class DockerDeleteUtil {


    /**
     * 获取swarm 集群信息
     * @return
     */
    public String nodes(String id){
        String data = DockerApi.dockerDelete("nodes/".concat(id), "");
        return data;
    }

    /**
     * 删除容器
     * @return
     */
    public String images(String id){
        String data = DockerApi.dockerDelete("images/".concat(id), "");
        return data;
    }

    /**
     * 删除网络
     */
    public String networks(String id){
        String data = DockerApi.dockerDelete("networks/".concat(id), "");
        return data;
    }

    /**
     * 删除服务
     */
    public String services(String id){
        String data = DockerApi.dockerDelete("services/".concat(id),"");
        return data;
    }



    /**
     * 删除单个
     * @param id
     * @return
     */
    public String container(String id){
        String data = DockerApi.dockerDelete("containers/".concat(id), "");
        return data;
    }
}
