package com.cloud.sms.docker.report.controller;

import com.cloud.sms.docker.common.response.PageResponse;
import com.cloud.sms.docker.report.entity.ReportEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyun on 2017/10/10.
 */
@Controller
@RequestMapping("/report")
public class ReportContoller {

    /**
     * 资源使用页面
     * @return
     */
    @RequestMapping("/used")
    public String used(){
        return "/report/used";
    }

    /**
     * 服务器数据
     * @return
     */
    @RequestMapping(value = "data", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String data(int draw, int start, int length, HttpServletRequest request, String groups) {
        List<ReportEntity> list = new ArrayList<>();
        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setContainerNumber("10");
        reportEntity.setGroups("基础平台研发部");
        reportEntity.setCpu("20%");
        reportEntity.setMemory("40%");
        reportEntity.setDisk("70%");
        reportEntity.setTrafficOutput("90011337.11");
        reportEntity.setTrafficInput("700113336.31");
        list.add(reportEntity);
        reportEntity = new ReportEntity();
        reportEntity.setContainerNumber("90");
        reportEntity.setGroups("生活服务平台");
        reportEntity.setCpu("26%");
        reportEntity.setMemory("80%");
        reportEntity.setDisk("73%");
        reportEntity.setTrafficOutput("80011336.33");
        reportEntity.setTrafficInput("600113335.14");
        list.add(reportEntity);
        reportEntity = new ReportEntity();
        reportEntity.setContainerNumber("120");
        reportEntity.setGroups("后台系统研发部");
        reportEntity.setCpu("56%");
        reportEntity.setMemory("75%");
        reportEntity.setDisk("33%");
        reportEntity.setTrafficOutput("1800113313.33");
        reportEntity.setTrafficInput("1600113331.14");
        list.add(reportEntity);
        reportEntity = new ReportEntity();
        reportEntity.setContainerNumber("120");
        reportEntity.setGroups("互联网研发部");
        reportEntity.setCpu("46%");
        reportEntity.setMemory("70%");
        reportEntity.setDisk("43%");
        reportEntity.setTrafficOutput("380011332.73");
        reportEntity.setTrafficInput("360011334.34");
        list.add(reportEntity);
        return PageResponse.getList(list, draw, 2);
    }
}
