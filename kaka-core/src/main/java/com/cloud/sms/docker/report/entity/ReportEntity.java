package com.cloud.sms.docker.report.entity;

/**
 * Created by zhaoyun on 2017/10/10.
 */
public class ReportEntity {

    private String groups;
    private String containerNumber;
    private String cpu;
    private String memory;
    private String disk;
    private String trafficInput;
    private String trafficOutput;

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getContainerNumber() {
        return containerNumber;
    }

    public void setContainerNumber(String containerNumber) {
        this.containerNumber = containerNumber;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }


    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String getTrafficInput() {
        return trafficInput;
    }

    public void setTrafficInput(String trafficInput) {
        this.trafficInput = trafficInput;
    }

    public String getTrafficOutput() {
        return trafficOutput;
    }

    public void setTrafficOutput(String trafficOutput) {
        this.trafficOutput = trafficOutput;
    }
}
