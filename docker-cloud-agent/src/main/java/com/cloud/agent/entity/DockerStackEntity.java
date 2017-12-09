package com.cloud.agent.entity;

/**
 * Created by zhaoyun on 2017/9/30.
 */
public class DockerStackEntity {

    private String composeFile;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComposeFile() {
        return composeFile;
    }

    public void setComposeFile(String composeFile) {
        this.composeFile = composeFile;
    }
}
