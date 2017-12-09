package com.cloud.sms.docker.log.controller;

import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchMap;
import com.asura.framework.dao.mybatis.paginator.domain.PageBounds;
import com.cloud.sms.docker.ci.entity.DockerCloudCiEnvEntity;
import com.cloud.sms.docker.common.response.PageResponse;
import com.cloud.sms.docker.log.entity.DockerCloudLogEntity;
import com.cloud.sms.docker.log.entity.DockerCloudOperLogEntity;
import com.cloud.sms.docker.log.service.DockerCloudLogService;
import com.cloud.sms.docker.log.service.DockerCloudOperLogService;
import com.cloud.sms.docker.util.CheckUtil;
import com.cloud.sms.docker.util.DateUtil;
import com.cloud.sms.docker.util.HttpClientIpAddress;
import com.cloud.sms.docker.util.PermissionsCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 操作日志信息
 * Created by zhaoyun on 2017/9/24.
 */
@Controller
@RequestMapping("/log/")
public class LogController {


    @Autowired
    private DockerCloudLogService logService;

    @Autowired
    private PermissionsCheck permissionsCheck;

    @Autowired
    private DockerCloudOperLogService operLogService;



    /**
     *
     * @param appName
     * @param serviceName
     * @return
     */
    @RequestMapping("service")
    @ResponseBody
    public String service(String appName, String serviceName){
        SearchMap searchMap = new SearchMap();
        searchMap.put("appName", appName);
        if (CheckUtil.checkString(serviceName)) {
            searchMap.put("serviceName", serviceName);
        }
        List<DockerCloudLogEntity> logs = logService.getListData(searchMap, "last10");
        return PageResponse.getList(logs, 1, logs.size());
    }


    /**
     *
     * @param entity
     * @param request
     */
    public void save(DockerCloudLogEntity entity, HttpServletRequest request){
        String user;
        if (null != request){
            user = permissionsCheck.getLoginUser(request.getSession());
            entity.setUserName(user);
        }
        entity.setTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
        logService.save(entity);
    }

    /**
     * 保存操作日志
     * @param request
     * @param messages
     */
    public void saveOperLog(HttpServletRequest request, String messages){
        try {
            HttpSession session = request.getSession();
            String user = permissionsCheck.getLoginUser(session);
            String clientIp = HttpClientIpAddress.getIpAddr(request);
            DockerCloudOperLogEntity entity = new DockerCloudOperLogEntity();
            entity.setUser(user);
            entity.setIp(clientIp);
            entity.setMessages(messages);
            entity.setTime(String.valueOf(DateUtil.getDate(DateUtil.TIME_FORMAT)));
            operLogService.save(entity);
        }catch (Exception e){
        }
    }


    /**
     * 操作日志
     * @return
     */
    @RequestMapping("operation/list")
    public String list() {
        return "/log/operation/list";
    }


    /**
     * 容器日志
     * @return
     */
    @RequestMapping("container/list")
    public String containerList() {
        return "/log/container/list";
    }


    /**
     * 操作日志
     * @param draw
     * @param start
     * @param length
     * @param request
     * @return
     */
    @RequestMapping(value = "operation/data", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String data(int draw, int start, int length, HttpServletRequest request) {
        String user = permissionsCheck.getLoginUser(request.getSession());
        SearchMap searchMap = new SearchMap();
        String search = request.getParameter("search[value]");
        searchMap.put("search", search);
        searchMap.put("userName", user);
        PageBounds pageBounds = PageResponse.getPageBounds(length, start);
        PagingResult<DockerCloudOperLogEntity> result = operLogService.findAll(searchMap, pageBounds, "selectByAll");
        return PageResponse.getMap(result, draw);
    }

    /**
     *
     * @param draw
     * @param start
     * @param length
     * @return
     */
    @RequestMapping(value = "container/data", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String containerData(int draw, int start, int length, HttpServletRequest request) {
        String user = permissionsCheck.getLoginUser(request.getSession());
        SearchMap searchMap = new SearchMap();
        searchMap.put("userName", user);
        PageBounds pageBounds = PageResponse.getPageBounds(length, start);
        PagingResult<DockerCloudLogEntity> result = logService.findAll(searchMap, pageBounds, "selectByAll");
        return PageResponse.getMap(result, draw);
    }

}
