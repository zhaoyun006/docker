package com.cloud.sms.docker.ci.controller;

import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchMap;
import com.asura.framework.dao.mybatis.paginator.domain.PageBounds;
import com.cloud.sms.docker.app.entity.DockerCloudAppEntity;
import com.cloud.sms.docker.app.service.DockerCloudAppService;
import com.cloud.sms.docker.ci.entity.DockerCloudCiEnvEntity;
import com.cloud.sms.docker.ci.service.DockerCloudCiEnvService;
import com.cloud.sms.docker.common.response.PageResponse;
import com.cloud.sms.docker.common.response.ResponseVo;
import com.cloud.sms.docker.log.controller.LogController;
import com.cloud.sms.docker.server.controller.EntController;
import com.cloud.sms.docker.server.controller.GroupsController;
import com.cloud.sms.docker.server.entity.DockerCloudEntEntity;
import com.cloud.sms.docker.server.entity.DockerCloudGroupsEntity;
import com.cloud.sms.docker.server.service.DockerCloudEntService;
import com.cloud.sms.docker.server.service.DockerCloudGroupsService;
import com.cloud.sms.docker.service.controller.ServiceController;
import com.cloud.sms.docker.service.service.DockerServiceEntity;
import com.cloud.sms.docker.util.CheckUtil;
import com.cloud.sms.docker.util.DateUtil;
import com.cloud.sms.docker.util.PermissionsCheck;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by zhaoyun on 2017/10/12.
 * @author zhaoyun
 */
@RequestMapping("/ci/")
@Controller
public class EnvController {

    private final Logger LOGGER = LoggerFactory.getLogger(EnvController.class);

    @Autowired
    private DockerCloudGroupsService groupsService;

    @Autowired
    private DockerCloudCiEnvService envService;

    @Autowired
    private PermissionsCheck permissionsCheck;

    @Autowired
    private LogController logController;

    @Autowired
    private ServiceController serviceController;

    @Autowired
    private DockerCloudAppService cloudAppService;

    @Autowired
    private DockerCloudEntService dockerCloudEntService;

    @Autowired
    private EntController entController;

    @Autowired
    private GroupsController groupsController;

    @Autowired
    private DockerCloudEntService entService;

    private final String SWARM = "swarm";

    /**
     * 环境列表
     * @return
     */
    @RequestMapping("env/list")
    public String list(ModelMap modelMap){
        modelMap.addAttribute("groups", groupsService.getListData(new SearchMap<>(), "selectGroups"));
        return "/ci/env/list";
    }

    /**
     *
     * @param gsonData
     * @param entEntity
     * @param key
     * @param dockerCloudCiEnvEntity
     * @return             String[] key = "containerMax,containerMin,cpu,memory,network".split(",");

     */
    DockerCloudEntEntity getCloudEnt(Map gsonData, DockerCloudEntEntity entEntity, String key, DockerCloudEntEntity dockerCloudCiEnvEntity ){
        if (gsonData != null && CheckUtil.checkString(gsonData.get(key+entEntity.getEntId()).toString())){
            switch (key){
                case "containerMax":
                    dockerCloudCiEnvEntity.setContainerMax(Integer.valueOf(gsonData.get(key+ entEntity.getEntId()).toString()));
                    break;
                case "containerMin":
                    dockerCloudCiEnvEntity.setContainerMin(Integer.valueOf(gsonData.get(key+ entEntity.getEntId()).toString()));
                    break;
                case "cpu":
                    dockerCloudCiEnvEntity.setCpu(Integer.valueOf(gsonData.get(key+ entEntity.getEntId()).toString()));
                    break;
                case "memory":
                    dockerCloudCiEnvEntity.setMemory(Integer.valueOf(gsonData.get(key+ entEntity.getEntId()).toString()));
                    break;
                default:
                    break;
            }
        }
        return dockerCloudCiEnvEntity;
    }


    /**
     * 环境添加
     * @return
     */
    @RequestMapping("env/add")
    public String add(ModelMap modelMap, int envId){
        DockerCloudCiEnvEntity ciEnvEntity = new DockerCloudCiEnvEntity();
        ciEnvEntity.setContainerMaxNumber(3);
        ciEnvEntity.setMemory(2048);
        ciEnvEntity.setCpu(2);
        ciEnvEntity.setContainerNumber(1);
        ciEnvEntity.setDept("");
        modelMap.addAttribute("configs", ciEnvEntity);
        modelMap.addAttribute("gsonData", new HashMap<>());
        modelMap.addAttribute("cluterTp", "kubernetes");

        List<DockerCloudEntEntity> entEntities;
        Map gsonData = new HashMap();
        if (envId > 0){
            DockerCloudCiEnvEntity dockerCloudCiEnvEntity = envService.findById(envId, DockerCloudCiEnvEntity.class);
            modelMap.addAttribute("configs", dockerCloudCiEnvEntity);
            gsonData = new Gson().fromJson(dockerCloudCiEnvEntity.getGsonData(), HashMap.class);
            modelMap.addAttribute("gsonData", gsonData);
            if (gsonData.containsKey("cluterTp")) {
                modelMap.addAttribute("cluterTp", gsonData.get("cluterTp"));
            }
        }
        SearchMap searchMap = new SearchMap();
        List<DockerCloudGroupsEntity> groups = groupsService.getListData(searchMap, "selectGroups");
        if (groups.size() < 1){
            return groupsController.add(modelMap, 0);
        }
        entEntities =  dockerCloudEntService.getListData(searchMap, "selectByAll");
        if (entEntities.size() < 1){
            return entController.add(modelMap,0);
        }

        if (null != gsonData && gsonData.size() > 0){
            String[] key = "containerMax,containerMin,cpu,memory".split(",");
            List<DockerCloudEntEntity> entities = new ArrayList<>();
            DockerCloudEntEntity cloudEntEntity;
            for (DockerCloudEntEntity entEntity:entEntities){
                cloudEntEntity = entEntity;
                for (int i=0; i<key.length; i++){
                    cloudEntEntity = getCloudEnt(gsonData, entEntity,  key[i],  cloudEntEntity);
                }
                entities.add(cloudEntEntity);
            }
            entEntities = entities;
        }
        modelMap.addAttribute("itemTps", envService.getListData(searchMap, "selectItemTp"));
        modelMap.addAttribute("depts", envService.getListData(searchMap, "selectDept"));
        modelMap.addAttribute("groups", groups);
        modelMap.addAttribute("ents", entEntities);
        return "/ci/env/add";
    }

    /**
     *
     * @param name
     * @return
     */
    @RequestMapping("env/check/{name}")
    @ResponseBody
    public String check(@PathVariable String name){
        SearchMap searchMap = new SearchMap();
        searchMap.put("serviceName", name);
        if(envService.getListData(searchMap, "selectExistsName").size() > 0){
            return "error";
        }
        return "ok";
    }

    /**
     *
     * @param entity
     * @return
     */
    ResponseVo checkParam(DockerCloudCiEnvEntity entity){

        if (!CheckUtil.checkString(entity.getServiceName()) || entity.getServiceName().length() < 6){
            if (entity.getServiceName().contains(".") || entity.getServiceName().contains("_")){
                return ResponseVo.responseError("服务名不能包含特殊字符");
            }
            return ResponseVo.responseError("服务名为空,至少6个字符");
        }
        if (!CheckUtil.checkString(entity.getDomain()) || entity.getDomain().length() < 6){
            return ResponseVo.responseError("应用域名不能为空");
        }
        if (!CheckUtil.checkString(entity.getDept())){
            return ResponseVo.responseError("部门必须填写和配置");
        }
        if (entity.getServiceName().contains(".")){
            return ResponseVo.responseError("服务名不能包含 . ");
        }
        if (CheckUtil.checkString(entity.getPort())){
            try {
                Integer.valueOf(entity.getPort());
            }catch (Exception e){
                return ResponseVo.responseError("端口必须为数字 ");
            }
        }else{
            return ResponseVo.responseError("端口必须填写 ");

        }
        boolean checkServiceName = "error".equals(check(entity.getServiceName())) && (entity.getEnvId() == null || entity.getEnvId() == 0);
        if (checkServiceName){
            return ResponseVo.responseError("服务名已经存在");
        }
        return  null;
    }

    /**
     * 在审核成功后,自动创建服务
     * @param entity
     * @param request
     * @param user
     */
    void initService(DockerCloudCiEnvEntity entity, HttpServletRequest request, String user, String update){
        String gsonData = entity.getGsonData();
        Map map = new Gson().fromJson(gsonData, HashMap.class);

        if (map.containsKey("clusterTp") && CheckUtil.checkString(map.get("clusterTp").toString())){
            entity.setClusterTp(map.get("clusterTp").toString());
        }

        // 不需要负载均衡配置,swarm排除掉
        if (!CheckUtil.checkString(map.get("loadblanceTp").toString())){
            LOGGER.info("没有配置负载" + map.get("loadblanceTp").toString());
            if (SWARM.equals(entity.getClusterTp())) {
                entity.setDomain("");
            }
        }

        List<DockerCloudEntEntity> result = entService.getListData(new SearchMap(), "selectByAll");
        for (DockerCloudEntEntity entEntity:result) {
            if (map.containsKey("containerMin"+entEntity.getEntId()) && Integer.valueOf(map.get("containerMin"+entEntity.getEntId()).toString()) > 0) {
                entity.setMemory(Integer.valueOf(map.get("memory"+entEntity.getEntId()).toString()));
                entity.setCpu(Integer.valueOf(map.get("cpu"+entEntity.getEntId()).toString()));
                entity.setContainerNumber(Integer.valueOf(map.get("containerMin"+entEntity.getEntId()).toString()));
                entity.setContainerMaxNumber(Integer.valueOf(map.get("containerMax"+entEntity.getEntId()).toString()));
                entity.setDefaultNetwork(map.get("network"+entEntity.getEntId()).toString());
                entity.setEntname(entEntity.getEntId()+"");
                entity.setClusterTp(map.get("cluterTp").toString());
                entity.setPort(map.get("port").toString());
                entity.setLoadblanceTp(map.get("loadblanceTp").toString());
                try {
                    saveService(entity, request, user, update);
                }catch (Exception e){
                    LOGGER.error("保存服务器失败", e);
                }
            }
        }
    }

    /**
     * 初始化完成后，需要去服务配置
     * @param entity
     * @param request
     */
    void saveService(DockerCloudCiEnvEntity entity, HttpServletRequest request, String user, String update){

        // 初始化app库
        SearchMap searchMap = new SearchMap();
        searchMap.put("appName", "default");
        if(cloudAppService.getListData(searchMap, "selectByAll").size() == 0){
            DockerCloudAppEntity appEntity = new DockerCloudAppEntity();
            appEntity.setAppName("default");
            appEntity.setCreateTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
            appEntity.setCreateUser(user);
            cloudAppService.save(appEntity);
        }

        DockerServiceEntity dockerServiceEntity = new DockerServiceEntity();
        dockerServiceEntity.setClusterTp(entity.getClusterTp());
        dockerServiceEntity.setMemory(String.valueOf(entity.getMemory()));
        dockerServiceEntity.setAppDescription(entity.getDescription());
        dockerServiceEntity.setContainerNumber(entity.getContainerNumber());
        dockerServiceEntity.setScaleMax(entity.getContainerMaxNumber());
        dockerServiceEntity.setCpu(String.valueOf(entity.getCpu()));
        dockerServiceEntity.setName(entity.getServiceName());
        dockerServiceEntity.setCreateUser(user);
        dockerServiceEntity.setServiceName(entity.getServiceName());
        dockerServiceEntity.setCreateTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
        dockerServiceEntity.setReplicas(String.valueOf(entity.getContainerNumber()));
        dockerServiceEntity.setImage("");
        dockerServiceEntity.setLoadblanceTp(entity.getLoadblanceTp());
        dockerServiceEntity.setDomain(entity.getDomain());
        dockerServiceEntity.setNetwork(entity.getDefaultNetwork());
        dockerServiceEntity.setAppName("default");
        dockerServiceEntity.setOperUser(user);
        dockerServiceEntity.setPort(entity.getPort());
        dockerServiceEntity.setEntId(Integer.valueOf(entity.getEntname()));
        dockerServiceEntity.setLimitMemory((Long.valueOf(entity.getMemory()) * 1024 * 1024)+"");
        dockerServiceEntity.setLimitCpu(String.valueOf(entity.getCpu()));
        dockerServiceEntity.setGsonData(new Gson().toJson(dockerServiceEntity));
        System.out.println(new Gson().toJson(dockerServiceEntity));
        serviceController.save(dockerServiceEntity, request,update, null);
    }

    /**
     *
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping("env/save")
    @ResponseBody
    public ResponseVo save(DockerCloudCiEnvEntity entity, HttpServletRequest request){
        String user = permissionsCheck.getLoginUser(request.getSession());
        entity.setLastModifyUser(user);
        entity.setBuildStatus("0");
        entity.setLastModifyTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
        ResponseVo responseVo = checkParam(entity);
        if (null != responseVo){
            return responseVo;
        }
        entity.setApprove(null);
        entity.setApproveTime(null);
        entity.setApproveUser(null);
        if (entity.getEnvId() != null && entity.getEnvId() > 0){
            DockerCloudCiEnvEntity envEntity = envService.findById(entity.getEnvId(), DockerCloudCiEnvEntity.class);
            if (CheckUtil.checkString(envEntity.getApprove())){
                entity.setApprove("1");
            }
            Gson gson = new Gson();
            String gsonData = envEntity.getGsonData();
            Map map = gson.fromJson(gsonData,HashMap.class);
            map.put("loadblanceTp", entity.getLoadblanceTp());
            map.put("domain", entity.getDomain());
            map.put("port", entity.getPort());
            gsonData = gson.toJson(map);
            entity.setGsonData(gsonData);
            envEntity.setGsonData(gsonData);
            // 服务更新
            initService(envEntity, request, user, "1");
            envService.update(entity);
        }else {
            entity.setCreateTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
            entity.setCreateUser(user);
            envService.save(entity);
        }

        logController.saveOperLog(request, "添加环境信息" + new Gson().toJson(entity));
        return ResponseVo.responseOk("添加环境成功");
    }


    /**
     * 删除环境
     * @return
     */
    @RequestMapping("env/delete/{serviceName}")
    @ResponseBody
    public ResponseVo delete(@PathVariable String serviceName, HttpServletRequest request){
        DockerCloudCiEnvEntity dockerCloudCiEnvEntity = new DockerCloudCiEnvEntity();
        dockerCloudCiEnvEntity.setServiceName(serviceName);
        serviceController.delete(serviceName, null);
        envService.delete(dockerCloudCiEnvEntity);
        logController.saveOperLog(request, "删除环境信息" + new Gson().toJson(dockerCloudCiEnvEntity));
        return ResponseVo.responseOk("ok");
    }

    /**
     * 审核环境
     * @return
     */
    @RequestMapping("env/verify/{envId}")
    @ResponseBody
    public ResponseVo verify(@PathVariable int envId, HttpServletRequest request){
        String user = permissionsCheck.getLoginUser(request.getSession());
        DockerCloudCiEnvEntity dockerCloudCiEnvEntity = envService.findById(envId, DockerCloudCiEnvEntity.class);
        if (CheckUtil.checkString(dockerCloudCiEnvEntity.getApprove()) && dockerCloudCiEnvEntity.getApprove().equals("1")){
            return ResponseVo.responseError("该申请已经审核完成,不能重复审核");
        }
        dockerCloudCiEnvEntity.setApprove("1");
        dockerCloudCiEnvEntity.setApproveUser(user);
        dockerCloudCiEnvEntity.setApproveTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
        envService.update(dockerCloudCiEnvEntity);
        // 初始化服务器
        initService(dockerCloudCiEnvEntity, request, user, null);
        logController.saveOperLog(request, "审核环境:" + dockerCloudCiEnvEntity.getServiceName());
        return ResponseVo.responseOk("ok");
    }

    /**
     *
     * @param request
     * @param groups
     * @return
     */
    public SearchMap getEnvSearchMap(HttpServletRequest request, String groups){
        SearchMap searchMap = new SearchMap();
        String search = request.getParameter("search[value]");
        if (CheckUtil.checkString(search)){
            searchMap.put("key", search);
        }
        if (CheckUtil.checkString(groups)) {
            searchMap.put("groups", groups);
        }
        return searchMap;
    }

    /**
     * 获取每个环境的容器数量
     * @param entEntities
     * @param map
     * @return
     */
    String getEntContainerNumber(List<DockerCloudEntEntity> entEntities, Map map){
        String data = "";
        for (DockerCloudEntEntity entEntity:entEntities){
            if (map.containsKey("containerMin"+entEntity.getEntId())) {
                data += entEntity.getEntname() + ":<b><span class='text-primary pull-right'>" + map.get("containerMin" + entEntity.getEntId()).toString()+"</span></b><br>";
            }
        }
        return data;
    }

    /**
     * 我的应用数据
     * @return
     */
    @RequestMapping(value = "env/data", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String data(int draw, int start, int length, HttpServletRequest request, String groups) {
        PageBounds pageBounds = PageResponse.getPageBounds(length, start);
                PagingResult<DockerCloudCiEnvEntity> result = envService.findAll(getEnvSearchMap(request, groups), pageBounds, "selectByAll");
        ArrayList list = new ArrayList();
        Map map ;
        Gson gson = new Gson();
        List<DockerCloudEntEntity> entEntities = entService.getListData(new SearchMap(), "selectByAll");

        for (DockerCloudCiEnvEntity envEntity:result.getRows()){
            map = gson.fromJson(envEntity.getGsonData(), HashMap.class);
            envEntity.setContainerInfo(getEntContainerNumber(entEntities, map));
            if (map.containsKey("cluterTp")) {
                envEntity.setClusterTp(map.get("cluterTp").toString());
            }
            list.add(envEntity);
        }
        return PageResponse.getList(list,  draw, result.getTotal());
    }
}
