package com.cloud.agent.scheduler.entity;

/**
 * Created by zhaoyun on 2017/10/1.\
 * 容器监控数据格式
 */
public class ContainerMonitorEntity {

    private String name;
    private String memory;
    private String cpu;
    // 网络INPUT，MB
    private String netInput;
    private String netOutput;
    // 磁盘操作M
    private String blockInput;
    private String blockOutput;
    private String time;

    public String getBlockOutput() {
        return blockOutput;
    }

    public void setBlockOutput(String blockOutput) {
        this.blockOutput = blockOutput;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getNetInput() {
        return netInput;
    }

    public void setNetInput(String netInput) {
        this.netInput = netInput;
    }

    public String getNetOutput() {
        return netOutput;
    }

    public void setNetOutput(String netOutput) {
        this.netOutput = netOutput;
    }

    public String getBlockInput() {
        return blockInput;
    }

    public void setBlockInput(String blockInput) {
        this.blockInput = blockInput;
    }


}
