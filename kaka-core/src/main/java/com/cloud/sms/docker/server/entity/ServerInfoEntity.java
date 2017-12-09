package com.cloud.sms.docker.server.entity;

/**
 * Created by zhaoyun on 2017/10/9.
 */
public class ServerInfoEntity {

    private String cpu;
    private String memory;

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }
}
