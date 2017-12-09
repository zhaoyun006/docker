package com.cloud.sms.docker.app.controller;

import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchMap;
import com.asura.framework.dao.mybatis.paginator.domain.PageBounds;
import com.cloud.configure.RedisKey;
import com.cloud.sms.docker.app.entity.DockerCloudAppEntity;
import com.cloud.sms.docker.app.service.DockerCloudAppService;
import com.cloud.sms.docker.ci.entity.DockerCloudCiEnvEntity;
import com.cloud.sms.docker.ci.service.DockerCloudCiEnvService;
import com.cloud.sms.docker.common.entity.ParamEntity;
import com.cloud.sms.docker.common.response.PageResponse;
import com.cloud.sms.docker.common.response.ResponseVo;
import com.cloud.sms.docker.log.entity.DockerCloudLogEntity;
import com.cloud.sms.docker.log.service.DockerCloudLogService;
import com.cloud.sms.docker.service.controller.ServiceController;
import com.cloud.sms.docker.service.entity.DockerCloudServiceEntity;
import com.cloud.sms.docker.service.service.DockerCloudServiceService;
import com.cloud.sms.docker.service.service.DockerServiceEntity;
import com.cloud.sms.docker.service.util.ServiceInfoUtil;
import com.cloud.sms.docker.util.CheckUtil;
import com.cloud.sms.docker.util.RedisUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyun on 2017/9/21.
 * 应用管理
 *  @author zhaoyun
 */
@Controller
@RequestMapping("/app/")
public class AppController {

    @Autowired
    private DockerCloudAppService cloudAppService;

    @Autowired
    private DockerCloudServiceService serviceService;

    @Autowired
    private DockerCloudLogService logService;

    @Autowired
    private ServiceController serviceController;

    @Autowired
    private DockerCloudCiEnvService ciEnvService;

    private Logger logger = LoggerFactory.getLogger(AppController.class);

    /**
     * @return
     */
    @RequestMapping("list")
    public String list(ModelMap modelMap, String appName, String envId, String entname) {
        SearchMap searchMap = new SearchMap();
        List<DockerCloudAppEntity> data = cloudAppService.getListData(searchMap, "selectByAll");
        if (CheckUtil.checkString(appName)) {
            modelMap.addAttribute("appName", appName);
        } else {
            if (data.size() > 0) {
                modelMap.addAttribute("appName", data.get(0).getAppName());
            }else{
                modelMap.addAttribute("appName", "");
            }
        }
        modelMap.addAttribute("envId",envId);
        modelMap.addAttribute("entname",entname);
        modelMap.addAttribute("datas", data);
        System.out.println("data" + data.size());
        System.out.println(new Gson().toJson(data));

        return "app/list";
    }

    /**
     * @param modelMap
     * @param appName
     * @return
     */
    @RequestMapping("detail")
    public String detail(ModelMap modelMap, String appName, String serviceName, String entId, String groupsName) {
        modelMap.addAttribute("appName", appName);
        SearchMap searchMap = new SearchMap();
        String clusterName = groupsName+entId;
        List<DockerCloudAppEntity> data = cloudAppService.getListData(searchMap, "selectByAll");
        searchMap.put("appName", appName);

        modelMap.addAttribute("clusterName", clusterName);
        List<DockerCloudAppEntity> app = cloudAppService.getListData(searchMap, "selectByAll");
        modelMap.addAttribute("appData", app.get(0));
        modelMap.addAttribute("data", data);
        modelMap.addAttribute("containers", 1);
        modelMap.addAttribute("containerNumber", serviceController.getContainers(serviceName, true, clusterName).size());
        List<DockerCloudServiceEntity> serviceEntities = serviceService.getListData(searchMap, "selectByAll");
        modelMap.addAttribute("services", serviceEntities);
        modelMap.addAttribute("serviceName", serviceName);
        DockerServiceEntity dockerServiceEntity = ServiceInfoUtil.getServiceInfo(serviceName, serviceService, clusterName);
        modelMap.addAttribute("service", dockerServiceEntity);
        List<DockerCloudLogEntity> logs = logService.getListData(searchMap, "last10");
        modelMap.addAttribute("logs", logs);
        return "app/detail";
    }

    /**
     *
     * @return
     */
    @RequestMapping("detailData")
    @ResponseBody
    public String detailData(String appName, ParamEntity paramEntity, String entname, String envId){
        SearchMap searchMap = new SearchMap();
        searchMap.put("list", "1");
        searchMap.put("appName", appName);
        if (CheckUtil.checkString(envId)){
            DockerCloudCiEnvEntity dockerCloudCiEnvEntity =  ciEnvService.findById(Integer.valueOf(envId), DockerCloudCiEnvEntity.class);
            searchMap.put("serviceName", dockerCloudCiEnvEntity.getServiceName());
        }
        if (CheckUtil.checkString(entname)){
            searchMap.put("entname", entname.trim());
        }
        PageBounds pageBounds = PageResponse.getPageBounds(paramEntity.getLength(), paramEntity.getStart());
        PagingResult<DockerCloudServiceEntity> serviceServiceListData = serviceService.findAll(searchMap, pageBounds, "selectByAll");
        List<DockerServiceEntity> entities = new ArrayList<>();
        Gson gson = new Gson();
        RedisUtil redisUtil = new RedisUtil();
        Jedis jedis = redisUtil.getJedis();
        String serviceInfo;
        DockerServiceEntity serviceEntityInfo;
        DockerServiceEntity serviceEntity;
        for (DockerCloudServiceEntity entity : serviceServiceListData.getRows()) {
            serviceEntity = new DockerServiceEntity();
            serviceEntity.setImage("未知");
            serviceEntity.setContainerNumber(0);
            if (CheckUtil.checkString(entity.getGsonData())) {
                String clusterName = entity.getGroupsName()+entity.getEntId();
                serviceEntity = gson.fromJson(entity.getGsonData(), DockerServiceEntity.class);
                serviceInfo = jedis.get(RedisUtil.app + "_" + RedisKey.serviceInfo.concat(clusterName).concat(entity.getServiceName()));
                System.out.println("serviceInfo" + serviceInfo);
                if (CheckUtil.checkString(serviceInfo)) {
                    serviceEntityInfo = gson.fromJson(serviceInfo, DockerServiceEntity.class);
                    serviceEntity.setImage(serviceEntityInfo.getImage());
                    serviceEntity.setContainerNumber(serviceController.getContainers(entity.getServiceName(), true, clusterName).size());
                    if (CheckUtil.checkString(serviceEntityInfo.getState())) {
                        serviceEntity.setState(serviceEntityInfo.getState());
                    }else{
                        serviceEntity.setState("未更新");
                    }
                }
            }
            serviceEntity.setStatus(0);
            serviceEntity.setEntname(entity.getEntname());
            serviceEntity.setGroupsName(entity.getGroupsName());
            serviceEntity.setEntId(entity.getEntId());
            serviceEntity.setGroupsId(entity.getGroupsId());
            serviceEntity.setServiceTp(entity.getServiceTp());
            entities.add(serviceEntity);
        }
        jedis.close();
        System.out.println(gson.toJson(entities));
        return PageResponse.getList(entities, paramEntity.getDraw(), entities.size());
    }


    /**
     * @return
     */
    @RequestMapping("add")
    public String add() {
        return "app/app_add";
    }

    /**
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResponseVo save(DockerCloudAppEntity entity) {
        cloudAppService.save(entity);
        return ResponseVo.responseOk("创建应用栈成功");
    }
}
