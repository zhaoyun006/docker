package com.cloud.agent.entity;

/**
 * Created by zhaoyun on 2017/10/8.
 */
public class DockerNetworkEntity {

    private String name;
    private String networkIp;
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetworkIp() {
        return networkIp;
    }

    public void setNetworkIp(String networkIp) {
        this.networkIp = networkIp;
    }
}
