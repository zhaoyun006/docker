package com.cloud.sms.docker.service.thread;

import com.cloud.sms.docker.container.controller.ContainerController;

import javax.servlet.http.HttpSession;

/**
 * Created by zhaoyun on 2017/10/4.
 */
public class DeleteContainerThread extends Thread {
    private String id;
    private String host;
    private ContainerController containerController;
    private HttpSession session;

    public DeleteContainerThread(String id, String host, ContainerController containerController,HttpSession session) {
        this.id = id;
        this.host = host;
        this.session = session;
        this.containerController = containerController;
        super.setName("DeleteContainerThread");
    }


    public void run(){
        containerController.containerOper(id, host, "remove", session);
        this.interrupt();
    }
}
