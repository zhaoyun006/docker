package com.cloud.sms.docker.ci.controller;


import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchMap;
import com.asura.framework.dao.mybatis.paginator.domain.PageBounds;
import com.cloud.sms.docker.ci.entity.*;
import com.cloud.sms.docker.ci.service.*;
import com.cloud.sms.docker.common.entity.ParamEntity;
import com.cloud.sms.docker.common.response.PageResponse;
import com.cloud.sms.docker.common.response.ResponseVo;
import com.cloud.sms.docker.images.entity.DockerCloudImageMergerRecordEntity;
import com.cloud.sms.docker.images.entity.DockerCloudImagesEntity;
import com.cloud.sms.docker.images.entity.DockerCloudRegistryServerEntity;
import com.cloud.sms.docker.images.service.DockerCloudRegistryServerService;
import com.cloud.sms.docker.log.controller.LogController;
import com.cloud.sms.docker.server.entity.DockerCloudEntEntity;
import com.cloud.sms.docker.server.entity.DockerCloudGroupsEntity;
import com.cloud.sms.docker.server.entity.DockerCloudServerEntity;
import com.cloud.sms.docker.server.service.DockerCloudEntService;
import com.cloud.sms.docker.server.service.DockerCloudGroupsService;
import com.cloud.sms.docker.server.service.DockerCloudServerService;
import com.cloud.sms.docker.service.controller.ServiceController;
import com.cloud.sms.docker.service.entity.DockerCloudServiceEntity;
import com.cloud.sms.docker.service.service.DockerCloudServiceService;
import com.cloud.sms.docker.service.service.DockerServiceEntity;
import com.cloud.sms.docker.util.CheckUtil;
import com.cloud.sms.docker.util.DateUtil;
import com.cloud.sms.docker.util.PermissionsCheck;
import com.cloud.util.HttpUtil;
import com.google.gson.Gson;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.jgss.GSSCaller;

import javax.security.auth.callback.CallbackHandler;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 发布管理
 * Created by zhaoyun on 2017/10/10.
 *
 * @author zhaoyun
 */
@Controller
@RequestMapping("/ci/flow/")
public class FlowController {


    @Autowired
    private DockerCloudCiEnvService envService;

    @Autowired
    private DockerCloudImagesBuildLogService buildLogService;

    @Autowired
    private PermissionsCheck permissionsCheck;

    @Autowired
    private CiPermissionsService ciPermissionsService;

    @Autowired
    private DockerCloudEnvFlowService envFlowService;

    @Autowired
    private DockerCloudReleaseRecordService releaseRecordService;

    @Autowired
    private DockerCloudEntService entService;

    @Autowired
    private LogController logController;

    @Autowired
    private DockerCloudServiceService serviceService;

    @Autowired
    private DockerCloudServiceService dockerCloudServiceService;

    @Autowired
    private ServiceController serviceController;

    @Autowired
    private DockerCloudServerService dockerCloudServerService;

    @Autowired
    private DockerCloudImagePullLogService pullLogService;

    @Autowired
    private DockerCloudGroupsService groupsService;

    @Autowired
    private DockerCloudRegistryServerService registryServerService;


    /**
     * 发布入口
     *
     * @return
     */
    @RequestMapping("list")
    public String list(ModelMap modelMap) {
        return "/ci/flow/list";
    }

    /**
     * @param modelMap
     * @param envId
     */
    void getFlowMap(ModelMap modelMap, int envId) {
        modelMap.put("envId", envId);
        modelMap.addAttribute("configs", new DockerCloudGroupsEntity());
        DockerCloudCiEnvEntity envEntity = envService.findById(envId, DockerCloudCiEnvEntity.class);
        modelMap.addAttribute("serviceName", envEntity.getServiceName());
        modelMap.addAttribute("domain", envEntity.getDomain());
        SearchMap searchMap = new SearchMap();
        searchMap.put("envId", envId);
        List<DockerCloudEnvFlowEntity> envFlowEntities = envFlowService.getListData(searchMap, "selectByAll");
        String[] ents;
        String entOrder;
        if (envFlowEntities.size() > 0) {
            entOrder = envFlowEntities.get(0).getReleaseOrder();
            ents = entOrder.split(",");

        } else {
            searchMap.remove("envId");
            envFlowEntities = envFlowService.getListData(searchMap, "selectByAll");
            entOrder = envFlowEntities.get(0).getReleaseOrder();
            ents = envFlowEntities.get(0).getReleaseOrder().split(",");
        }
        modelMap.addAttribute("envOrder", entOrder.split(","));
        searchMap.put("ids", ents);
        List<DockerCloudEntEntity> envEntities = entService.getListData(searchMap, "selectByAll");
        modelMap.addAttribute("entnames", envEntities);
        searchMap.put("envId", envId);
        List<DockerCloudImagesBuildLogEntity> buildLogEntities = buildLogService.getListData(searchMap, "selectPushImageTag");
        modelMap.addAttribute("imageTags", buildLogEntities);
        if (buildLogEntities.size() > 0) {
            modelMap.addAttribute("version", buildLogEntities.get(0).getTag());
        } else {
            modelMap.addAttribute("未知");
        }
        modelMap.addAttribute("groups", groupsService.getListData(searchMap, "selectByAll"));
    }

    /**
     * 推送镜像入口
     *
     * @return
     */
    @RequestMapping("pushImage/{envId}")
    public String pushImage(ModelMap modelMap, @PathVariable int envId) {
        getFlowMap(modelMap, envId);
        return "/ci/flow/pushImage";
    }

    /**
     * 获取回滚的版本号，最近一次发布的
     *
     * @param envId
     * @param entId
     * @return
     */
    @RequestMapping("rollback/version")
    @ResponseBody
    public String getRollbackVersion(String envId, String entId) {
        SearchMap searchMap = new SearchMap();
        searchMap.put("entId", entId);
        searchMap.put("envId", envId);
        List<DockerCloudReleaseRecordEntity> releaseRecordEntities = releaseRecordService.getListData(searchMap, "selectByAll");
        if (releaseRecordEntities.size() > 0) {
            DockerCloudReleaseRecordEntity releaseRecordEntity = releaseRecordEntities.get(0);
            return releaseRecordEntity.getLastVersion() + "</td><td>&nbsp;&nbsp;<span class='text-info'>" + releaseRecordEntity.getCreateTime() + "</span>";
        } else {
            return null;
        }
    }


    /**
     * 镜像推送
     *
     * @param version
     * @param envId
     * @param entId
     * @return
     */
    @RequestMapping("image/push/{envId}/{entId}")
    @ResponseBody
    public ResponseVo push(String version, @PathVariable int envId, @PathVariable int entId, HttpServletRequest request) {
        SearchMap searchMap = new SearchMap();
        Gson gson = new Gson();
        searchMap.put("envId", envId);
        searchMap.put("entId", entId);
        searchMap.put("tag", version);
        DockerCloudImagesBuildLogEntity buildLogEntities = buildLogService.getBuildLog(searchMap, "selectByAll");
        String param = buildLogEntities.getBuildParam();
        Map map = gson.fromJson(param, HashMap.class);
        String registry = map.get("registry").toString();
        String tag = buildLogEntities.getTag();
        String serviceName = map.get("serviceName").toString();
        String image = registry.concat("/").concat(serviceName).concat(":").concat(tag);
        List<DockerCloudServiceEntity> serviceEntities = serviceService.getListData(searchMap, "selectByAll");
        if (serviceEntities.size() < 1) {
            return ResponseVo.responseError("没有对应的服务");
        }
        DockerCloudServiceEntity dockerCloudServiceEntity = serviceEntities.get(0);
        int groupsId = dockerCloudServiceEntity.getGroupsId();
        searchMap.put("groupsId", groupsId);
        String url = "http://{0}:{1}/api/images/commands?commands=pullImage";
        String api;
        ImagesBuildInfoEntity imagesEntity = new ImagesBuildInfoEntity();
        imagesEntity.setRegistry(image);
        imagesEntity.setEnvId(envId);
        imagesEntity.setServiceName(serviceName);
        String user = permissionsCheck.getLoginUser(request.getSession());
        imagesEntity.setUser(user);
        imagesEntity.setEntId(entId + "");
        String params = gson.toJson(imagesEntity);
        List<DockerCloudServerEntity> serverEntities = dockerCloudServerService.getListData(searchMap, "selectByAll");
        for (DockerCloudServerEntity serverEntity : serverEntities) {
            try {
                api = url.replace("{0}", serverEntity.getServerAddress()).replace("{1}", serverEntity.getApiPort());
                HttpUtil.sendPost(api, "param=" + params);
            } catch (Exception e) {
                logController.saveOperLog(request, "下载镜像失败" + e.toString());
            }
        }
        logController.saveOperLog(request, "下载镜像:" + image);
        return ResponseVo.responseOk("ok");
    }


    /**
     * 合并镜像入口
     *
     * @return
     */
    @RequestMapping("mergerImage/{envId}")
    public String mergerImage(ModelMap modelMap, @PathVariable int envId) {
        getFlowMap(modelMap, envId);
        return "/ci/flow/mergerImage";
    }


    /**
     *
     * @param entId
     * @param groupsId
     * @param envId
     * @param version
     * @param serviceName
     * @param request
     * @return
     */
    @RequestMapping("image/merger/{entId}")
    @ResponseBody
    public ResponseVo mergerImageSave(@PathVariable int entId,String groupsId, int envId, String version, String serviceName, HttpServletRequest request) {
        SearchMap searchMap = new SearchMap();
        searchMap.put("serviceName", serviceName);
        searchMap.put("entId", entId);
        searchMap.put("tag", version);
        searchMap.put("envId", envId);
        if (CheckUtil.checkString(groupsId)){
            searchMap.put("groupsId", groupsId);
        }
        Map map = getBuildLogEntity(searchMap);
        String user = permissionsCheck.getLoginUser(request.getSession());

        // 编译镜像的机器
        String pushImagesServer = map.get("buildServerApi").toString();
        String apiUrl = pushImagesServer.replace("build", "merger");

        // 老的地址
        String registry = map.get("registry").toString() + "/" + serviceName;

        // 生成新的镜像地址
        List<DockerCloudRegistryServerEntity> registryServerEntities = registryServerService.getListData(searchMap, "selectByAll");
        if (registryServerEntities.size() > 0){
            DockerCloudRegistryServerEntity dockerCloudRegistryServerEntity = registryServerEntities.get(0);
            // 合并后的名字
            String imageName = dockerCloudRegistryServerEntity.getServerDomain() +"/" + dockerCloudRegistryServerEntity.getPrefix();
            imageName = imageName+"/" + serviceName + ":" + version;
            ImagesBuildInfoEntity imagesBuildInfoEntity = new ImagesBuildInfoEntity();
            imagesBuildInfoEntity.setRegistry(imageName);
            imagesBuildInfoEntity.setUser(user);
            imagesBuildInfoEntity.setEntId(entId+"");
            imagesBuildInfoEntity.setEnvId(envId);
            imagesBuildInfoEntity.setVersion(version);
            imagesBuildInfoEntity.setServiceName(serviceName);
            imagesBuildInfoEntity.setImagesId(registry.concat(":").concat(version));
            HttpUtil.sendPost(apiUrl, "param="+ new Gson().toJson(imagesBuildInfoEntity));
        }else{
            return ResponseVo.responseError("仓库地址不存在");
        }

        return ResponseVo.responseOk("ok");
    }

    /**
     *
     * @param version
     * @param envId
     * @param entId
     * @return
     */
    String getVersionIsRelease(String version, int envId, int entId) {
        SearchMap searchMap = new SearchMap();
        searchMap.put("version", version);
        searchMap.put("envId", envId);
        searchMap.put("entId", entId);
        List<DockerCloudReleaseRecordEntity> entities = releaseRecordService.getListData(searchMap, "selectLastVersion");
        if (entities.size() > 0) {
            return "已发布";
        } else {
            return null;
        }
    }

    /**
     *
     * @param envId
     * @param version
     * @param entId
     * @param request
     * @return
     */
    @RequestMapping("saveRollbackService")
    @ResponseBody
    public ResponseVo saveRollbackService(int envId, String version, int entId, HttpServletRequest request) {
        saveUpdateService(envId, version, entId, request,"0");
        return ResponseVo.responseOk("操作中");
    }

    /**
     *
     * @param searchMap
     * @return
     */
    Map  getBuildLogEntity(SearchMap searchMap ){
        DockerCloudImagesBuildLogEntity buildLogEntity = buildLogService.getBuildLog(searchMap, "selectUpdateParam");
        String paramData = buildLogEntity.getBuildParam();
        Map map = new Gson().fromJson(paramData, HashMap.class);
        map.put("buildServerApi", buildLogEntity.getBuildServerApi());
        return map;
    }

    /**
     *
     * @param envId
     * @param version
     * @param entId
     * @param request
     * @param resume
     * @return
     */
    @RequestMapping("saveUpdateService")
    @ResponseBody
    public ResponseVo saveUpdateService(int envId, String version, int entId,  HttpServletRequest request,String resume) {
        boolean check = ciPermissionsService.checkPermissions(envId, request.getSession());
        if (!check) {
            return ResponseVo.responseError("没有权限");
        }
        String isRelease = getVersionIsRelease(version, envId, entId);
        if (CheckUtil.checkString(isRelease) && resume.equals("0")) {
            return ResponseVo.responseError("该项目已经发布完成,不能重复发布<br>如果需要重新发布,点击&nbsp;重新发布&nbsp;按钮");
        }
        DockerCloudCiEnvEntity envEntity = envService.findById(envId, DockerCloudCiEnvEntity.class);

        SearchMap searchMap = new SearchMap();
        searchMap.put("serviceName", envEntity.getServiceName());
        searchMap.put("entId", entId);
        searchMap.put("tag", version);
        searchMap.put("envId", envId);

        Gson gson = new Gson();

        // 发布服务其实就是更新docker的service，替换镜像
        List<DockerCloudServiceEntity> envServices = dockerCloudServiceService.getListData(searchMap, "selectByAll");
        String gsonData = envServices.get(0).getGsonData();
        DockerServiceEntity serviceEntity = gson.fromJson(gsonData, DockerServiceEntity.class);

        String envEntityGsonData = envEntity.getGsonData();
        Map envMap = gson.fromJson(envEntityGsonData, HashMap.class);
        Map map = getBuildLogEntity(searchMap);

        serviceEntity.setEntId(entId);
        serviceEntity.setClusterTp(envMap.get("cluterTp").toString());
        serviceEntity.setEntname(String.valueOf(entId));
        String registry = map.get("registry").toString() + "/" + envEntity.getServiceName();
        serviceEntity.setImage(registry + ":" + version);
        String result = serviceController.save(serviceEntity, request, "1", null);

        DockerCloudReleaseRecordEntity entity = new DockerCloudReleaseRecordEntity();
        entity.setEntId(entId);
        entity.setEnvId(envId);
        entity.setCreateTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
        entity.setCreateUser(permissionsCheck.getLoginUser(request.getSession()));
        entity.setCurrentVersion(version);
        entity.setStatus("开始更新");
        entity.setRegistry(registry);
        // 获取上次发布记录,没有的话就是用当前的版本号
        try {
            entity.setLastVersion(releaseRecordService.getListData(searchMap, "").get(0).getLastVersion());
        } catch (Exception e) {
            entity.setLastVersion(version);
        }
        releaseRecordService.save(entity);
        logController.saveOperLog(request, "发布服务" + new Gson().toJson(entity));
        return ResponseVo.responseOk(result);
    }

    /**
     * 更新服务入口
     * @param modelMap
     * @param envId
     * @param version
     * @return
     */
    @RequestMapping("updateService/{envId}")
    public String updateService(ModelMap modelMap, @PathVariable int envId, String version) {
        getFlowMap(modelMap, envId);
        if (!CheckUtil.checkString(version)) {
            version = modelMap.get("version").toString();
        }
        modelMap.put("selectVersion", version);
        String[] orderEnvs = (String[]) modelMap.get("envOrder");
        List<DockerCloudEntEntity> entities = (List) modelMap.get("entnames");
        ArrayList<DockerCloudEntEntity> arrayList = new ArrayList<>();
        for (int i = 0; i < orderEnvs.length; i++) {
            for (DockerCloudEntEntity entity : entities) {
                if (orderEnvs[i].equals(entity.getEntId() + "")) {
                    entity.setVersion(version);
                    entity.setDisabled("");
                    entity.setRelease(getVersionIsRelease(version, envId, entity.getEntId()));
                    entity.setTitle("可更新,可更新版本为".concat(version));
                    if (i == 0 && !CheckUtil.checkString(entity.getRelease())) {
                        entity.setChecked("checked");
                    } else if (i > 0 && CheckUtil.checkString(arrayList.get(i - 1).getRelease())) {
                        entity.setChecked("checked");
                    } else if (i > 0) {
                        entity.setTitle("不可更新,按发布流程顺序更新&nbsp;&nbsp;".concat("需要先更新&nbsp;&nbsp;").concat(arrayList.get(i - 1).getEntname()));
                        entity.setDisabled("disabled");
                    }
                    if (CheckUtil.checkString(entity.getRelease()) && entity.getRelease().equals("已发布")) {
                        entity.setTitle("已更新,请更新下一个环境");
                        entity.setDisabled("disabled");
                    }
                    arrayList.add(entity);
                }
            }
        }
        modelMap.addAttribute("entnames", arrayList);
        return "/ci/flow/updateService";
    }

    /**
     *  服务回滚入口
     * @param modelMap
     * @param envId
     * @return
     */
    @RequestMapping("rollbackService/{envId}")
    public String rollbackService(ModelMap modelMap, @PathVariable int envId) {
        getFlowMap(modelMap, envId);
        SearchMap searchMap = new SearchMap();
        List<DockerCloudEntEntity> envEntities = entService.getListData(searchMap, "selectByAll");
        for (DockerCloudEntEntity dockerCloudEntEntity : envEntities) {
            dockerCloudEntEntity.setVersion(getRollbackVersion(envId + "", dockerCloudEntEntity.getEntId() + ""));
        }
        modelMap.addAttribute("entnames", envEntities);
        return "/ci/flow/rollbackService";
    }


    /**
     *
     * @param modelMap
     * @param request
     */
    void getModelMap(ModelMap modelMap, HttpServletRequest request) {
        String user = permissionsCheck.getLoginUser(request.getSession());
        SearchMap searchMap = new SearchMap();
        searchMap.put("createUser", user);
        modelMap.addAttribute("envs", envService.getListData(searchMap, "selectByAll"));
        modelMap.addAttribute("ents", entService.getListData(searchMap, "selectByAll"));
    }

    /**
     * @param envId
     * @param entId
     * @return
     */
    SearchMap getSearchMap(String envId, String entId) {
        SearchMap searchMap = new SearchMap();
        if (CheckUtil.checkString(envId)) {
            searchMap.put("envId", envId);
        }
        if (CheckUtil.checkString(entId)) {
            searchMap.put("entId", entId);
        }
        return searchMap;
    }

    /**
     * 镜像获取日志
     *
     * @return
     */
    @RequestMapping("image/push/list")
    public String pullList(ModelMap modelMap, HttpServletRequest request) {
        getModelMap(modelMap, request);
        return "/ci/flow/imagePullLog";
    }

    /**
     * 获取发布历史信息
     *
     * @return
     */
    @RequestMapping("release/list")
    public String releaseList(ModelMap modelMap, HttpServletRequest request) {
        getModelMap(modelMap, request);
        return "/ci/flow/releaseLog";
    }

    /**
     *
     * @param paramEntity
     * @param envId
     * @param entId
     * @return
     */
    @RequestMapping(value = "releaseLogData", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String releaseLogData(ParamEntity paramEntity, String envId, String entId) {
        SearchMap searchMap = getSearchMap(envId, entId);
        PageBounds pageBounds = PageResponse.getPageBounds(paramEntity.getLength(), paramEntity.getStart());
        PagingResult<DockerCloudReleaseRecordEntity> releaseLogData = releaseRecordService.findAll(searchMap, pageBounds, "selectByAll");
        return PageResponse.getMap(releaseLogData, paramEntity.getDraw());
    }

    /**
     * @param paramEntity
     * @param envId
     * @param entId
     * @return
     */
    @RequestMapping(value = "images/pullLogData", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String pullLogData(ParamEntity paramEntity, String envId, String entId) {
        SearchMap searchMap = getSearchMap(envId, entId);
        PageBounds pageBounds = PageResponse.getPageBounds(paramEntity.getLength(), paramEntity.getStart());
        PagingResult<DockerCloudImagePullLogEntity> pullLogEntityPagingResult = pullLogService.findAll(searchMap, pageBounds, "selectByAll");
        return PageResponse.getMap(pullLogEntityPagingResult, paramEntity.getDraw());
    }

    /**
     * @param paramEntity
     * @return
     */
    @RequestMapping(value = "data", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String data(ParamEntity paramEntity, HttpServletRequest request) {
        String user = permissionsCheck.getLoginUser(request.getSession());
        ArrayList list = ciPermissionsService.getUeerEnv(user);
        SearchMap searchMap = new SearchMap();
        searchMap.put("ids", list);
        searchMap.put("createUser", user);
        PageBounds pageBounds = PageResponse.getPageBounds(paramEntity.getLength(), paramEntity.getStart());
        PagingResult<DockerCloudCiEnvEntity> result = envService.findAll(searchMap, pageBounds, "selectUserEnv");
        List<DockerCloudCiEnvEntity> envEntities = new ArrayList<>();
        DockerCloudImagesBuildLogEntity buildLogEntity;
        for (DockerCloudCiEnvEntity envEntity : result.getRows()) {
            searchMap.put("envId", envEntity.getEnvId());
            buildLogEntity = buildLogService.getBuildLog(searchMap, "selectServerApi");
            if (null != buildLogEntity) {
                envEntity.setBuildTp(buildLogEntity.getBuildTp());
                envEntity.setTag(buildLogEntity.getTag());
            }
            envEntities.add(envEntity);
        }
        return PageResponse.getMap(result, paramEntity.getDraw());
    }
}
