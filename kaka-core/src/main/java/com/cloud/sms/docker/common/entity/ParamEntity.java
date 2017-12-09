package com.cloud.sms.docker.common.entity;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhaoyun on 2017/11/2.
 */
public class ParamEntity {
    private int start;
    private int length;
    private int draw;
    private HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }
}
