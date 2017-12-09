package com.cloud.sms.docker.service.entity;

import com.cloud.sms.docker.app.entity.DockerContainerEntity;

import java.util.ArrayList;

/**
 * Created by zhaoyun on 2017/10/8.
 */
public class GetContainersEntity {
    private int size;

    public ArrayList<DockerContainerEntity> getDockerContainerEntity() {
        return dockerContainerEntity;
    }

    public void setDockerContainerEntity(ArrayList<DockerContainerEntity> dockerContainerEntity) {
        this.dockerContainerEntity = dockerContainerEntity;
    }

    private ArrayList<DockerContainerEntity> dockerContainerEntity;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


}
