package com.cloud.sms.docker.container.controller;

import com.cloud.sms.docker.app.entity.DockerContainerEntity;
import com.cloud.sms.docker.common.response.PageResponse;
import com.cloud.sms.docker.container.util.ContainerUtil;
import com.cloud.sms.docker.service.controller.ServiceController;
import com.cloud.sms.docker.service.entity.GetContainersEntity;
import com.cloud.sms.docker.service.service.DockerServiceEntity;
import com.cloud.sms.docker.service.util.ServiceInfoUtil;
import com.cloud.sms.docker.util.PermissionsCheck;
import com.cloud.util.HttpUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyun on 2017/10/2.
 *  @author zhaoyun
 */
@Controller
@RequestMapping("/container/")
public class ContainerController {

    @Autowired
    private PermissionsCheck permissionsCheck;

    @Autowired
    private ServiceController serviceController;

    /**
     *
     * @return
     */
    @RequestMapping("add")
    public String add(){
        return "/containers/add";
    }


    /**
     *
     * @return
     */
    @RequestMapping("list")
    public String list(){
       return "/containers/list";
    }

    /**
     * @param serviceName
     * @return
     */
    @RequestMapping("data")
    @ResponseBody
    public String containers(String serviceName, int draw, int start, int length) {
        return "";
    }

    /**
     *
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping("/detail/{host}/{id}")
    public String detail(@PathVariable String host, @PathVariable String id, ModelMap modelMap, HttpSession session,String name,String clusterName, String serviceName){
        List<DockerContainerEntity> containerEntities = serviceController.getContainers(serviceName, false, clusterName);
        String result = containerOper(id, host,"info", session);
        ArrayList containers = new ArrayList();
        for (DockerContainerEntity entity:containerEntities){
            if (entity.getState().equals("running")){
                containers.add(entity);
            }
        }

        DockerServiceEntity serviceEntity = ContainerUtil.info(result);
        System.out.println(new Gson().toJson(serviceEntity));
        modelMap.addAttribute("data", serviceEntity);
        modelMap.addAttribute("createdAt", serviceEntity.getCreatedAt());
        modelMap.addAttribute("host", host);
        modelMap.addAttribute("serviceName", serviceName);
        modelMap.addAttribute("name", name);
        modelMap.addAttribute("containers", containers);
       return "/containers/detail";
    }

    /**
     * @param id
     * @param host
     * @param action
     * @param session
     * @return
     */
    @RequestMapping("{host}/{id}/{action}")
    @ResponseBody
    public String containerOper(@PathVariable String id, @PathVariable String host, @PathVariable String action, HttpSession session) {
        host = host.split("\\(")[1].replace(")","");
        String user = permissionsCheck.getLoginUser(session);
        String url = "http://"+host.trim()+":9999/containers/"+id+"/"+action+"?user="+user;
        String  result = "";
        if ("remove".equals(action)) {
            result = HttpUtil.httpPostJson(url, "", "DELETE");
        }
        if ("start".equals(action)){
            result = HttpUtil.httpPostJson(url, "", "GET");
        }
        if ("stop".equals(action)){
            result = HttpUtil.httpPostJson(url, "", "GET");
        }
        if ("info".equals(action)){
            result = HttpUtil.httpPostJson(url, "", "GET");
            return result;
        }
        return "操作结果"+result;
    }
}
