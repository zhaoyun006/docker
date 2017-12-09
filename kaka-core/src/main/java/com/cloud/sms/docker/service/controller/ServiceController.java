package com.cloud.sms.docker.service.controller;

import com.asura.framework.base.paging.SearchMap;
import com.cloud.configure.RedisKey;
import com.cloud.sms.docker.app.entity.DockerContainerEntity;
import com.cloud.sms.docker.ci.entity.DockerCloudCiEnvEntity;
import com.cloud.sms.docker.ci.service.DockerCloudCiEnvService;
import com.cloud.sms.docker.common.response.PageResponse;
import com.cloud.sms.docker.common.response.ResponseVo;
import com.cloud.sms.docker.container.controller.ContainerController;
import com.cloud.sms.docker.log.controller.LogController;
import com.cloud.sms.docker.log.entity.DockerCloudLogEntity;
import com.cloud.sms.docker.network.controller.NetworkController;
import com.cloud.sms.docker.network.entity.DockerCloudNetworkEntity;
import com.cloud.sms.docker.network.service.DockerCloudNetworkService;
import com.cloud.sms.docker.server.entity.DockerCloudEntEntity;
import com.cloud.sms.docker.server.entity.DockerCloudGroupsEntity;
import com.cloud.sms.docker.server.entity.DockerCloudServerEntity;
import com.cloud.sms.docker.server.service.DockerCloudEntService;
import com.cloud.sms.docker.server.service.DockerCloudGroupsService;
import com.cloud.sms.docker.server.service.DockerCloudServerService;
import com.cloud.sms.docker.service.entity.DockerCloudServiceEntity;
import com.cloud.sms.docker.service.entity.GetContainersEntity;
import com.cloud.sms.docker.service.service.DockerCloudServiceService;
import com.cloud.sms.docker.service.service.DockerServiceEntity;
import com.cloud.sms.docker.service.thread.DeleteContainerThread;
import com.cloud.sms.docker.service.util.ServiceInfoUtil;
import com.cloud.sms.docker.util.CheckUtil;
import com.cloud.sms.docker.util.DateUtil;
import com.cloud.sms.docker.util.PermissionsCheck;
import com.cloud.sms.docker.util.RedisUtil;
import com.cloud.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyun on 2017/9/25.
 * 创建服务
 * @author zhaoyun
 */
@Controller
@RequestMapping("/service/")
public class ServiceController {

    @Autowired
    private DockerCloudServiceService serviceService;

    @Autowired
    private PermissionsCheck permissionsCheck;

    @Autowired
    private LogController logController;

    @Autowired
    private ContainerController containerController;

    @Autowired
    private DockerCloudNetworkService networkService;

    @Autowired
    private DockerCloudEntService dockerCloudEntService;

    @Autowired
    private DockerCloudGroupsService groupsService;

    @Autowired
    private NetworkController networkController;

    @Autowired
    private DockerCloudServerService serverService;

    @Autowired
    private DockerCloudCiEnvService ciEnvService;

    private final String errorMsg = "Error response from daemon";
    private final String KUBERNETES = "kubernetes";
    private final String SWARM = "swarm";
    private Logger logger = LoggerFactory.getLogger(ServiceController.class);

    /**
     * @param modelMap
     * @param appName
     * @return
     */
    @RequestMapping("add")
    public String add(ModelMap modelMap, String appName, HttpSession session) {
        modelMap.addAttribute("readonly", null);
        modelMap.addAttribute("appName", appName);
        modelMap.addAttribute("service", null);
        modelMap.addAttribute("serviceName", null);
        modelMap.addAttribute("containers", null);
        modelMap.addAttribute("networkName", null);
        modelMap.addAttribute("operUser",  permissionsCheck.getLoginUser(session));
        List<DockerCloudNetworkEntity> networks = networkService.getListData(new SearchMap(), "selectByAll");
        if (networks.size() < 1){
            return "/network/subnet";
        }
        modelMap.addAttribute("networks",  networks);
        return "service/add";
    }

    /**
     * @param serviceName
     * @return
     */
    public List<DockerContainerEntity> getContainers(String serviceName, boolean isRuning, String clusterName) {
        Gson gson = new Gson();
        RedisUtil redisUtil = new RedisUtil();
        List<DockerContainerEntity> continersRuning = new ArrayList<>();
        List<DockerContainerEntity> continers = new ArrayList<>();
        String containerData = redisUtil.get(RedisKey.serviceContainers.concat(clusterName).concat(serviceName));
        if (CheckUtil.checkString(containerData)) {
            Type containerType = new TypeToken<ArrayList<DockerContainerEntity>>() {
            }.getType();
            continers = gson.fromJson(containerData, containerType);
                for (DockerContainerEntity entity:continers){
                    if (entity.getState().equals("running")){
                        continersRuning.add(entity);
                    }
                }
        }
        if (isRuning){
            return continersRuning;
        }
        for (DockerContainerEntity entity:continers){
            if (!entity.getState().equals("running")){
                continersRuning.add(entity);
            }
        }
        return continersRuning;
    }

    /**
     *
     * @param serviceName
     * @param redisUtil
     * @param gson
     * @return
     */
    long getServiceContainerSize(String serviceName, RedisUtil redisUtil, Gson gson){
        List<DockerContainerEntity> continers;
        String containerData = redisUtil.get(RedisKey.serviceContainers.concat(serviceName));
        if (CheckUtil.checkString(containerData)) {
            Type containerType = new TypeToken<ArrayList<DockerContainerEntity>>() {
            }.getType();
            continers = gson.fromJson(containerData, containerType);
            return continers.size();
        }
        return 0;
    }

    /**
     *
     * @return
     */
    public Map getContainerTotle(){
        RedisUtil redisUtil = new RedisUtil();
        Map map = new HashMap();
        long runing = 0L;
        long totle = 0L;
        Gson gson = new Gson();

        List<DockerCloudServiceEntity> entityList = serviceService.getListData(new SearchMap(), "selectByAll");
        for (DockerCloudServiceEntity entity: entityList){
            runing += getContainers(entity.getServiceName(), true, entity.getGroupsName()+entity.getEntId()).size();
            totle += getServiceContainerSize(entity.getServiceName(), redisUtil, gson);
        }
        map.put("runing", runing);
        map.put("totle", totle);
        map.put("shutdown", totle - runing);
        return map;
    }

    /**
     * @param serviceName
     * @return
     */
    @RequestMapping("containers")
    @ResponseBody
    public String containers(String serviceName, int draw, int start, int length, String clusterName) {
        List<DockerContainerEntity> containerEntities = getContainers(serviceName,false, clusterName);
        GetContainersEntity getContainersEntity  = ServiceInfoUtil.getContainers(new Gson().toJson(containerEntities), start, length);
        return PageResponse.getList(getContainersEntity.getDockerContainerEntity(), draw, getContainersEntity.getSize());
    }

    /**
     * @param serviceName
     * @return
     */
    @RequestMapping("scale")
    @ResponseBody
    public String scale(String serviceName, int number, String action, HttpSession session) {
        if (number < 1) {
            return "最小是1个";
        }
        String user = permissionsCheck.getLoginUser(session);
        HttpUtil.sendGet("http://10.16.55.6:9999/services/scaleService/" + serviceName + "/" + number+"?user="+user);
        return action;
    }



    /**
     * 服务升级 20170928 14:44
     *
     * @param appName
     * @param serviceName
     * @return
     */
    @RequestMapping("update")
    public String update(String appName, String serviceName, ModelMap modelMap, HttpSession session, String entId) {
        SearchMap searchMap = new SearchMap();
        searchMap.put("appName", appName);
        searchMap.put("entId", entId);
        searchMap.put("serviceName", serviceName);
        List<DockerCloudServiceEntity> entityList = serviceService.getListData(searchMap, "selectByAll");
        if (entityList.size() < 1) {
            return "error/404";
        }

        DockerServiceEntity dockerServiceEntity = ServiceInfoUtil.getServiceInfo(serviceName, serviceService, entId);
        searchMap.put("groupsId", dockerServiceEntity.getGroupsId());
        List<DockerCloudNetworkEntity> networks = networkService.getListData(searchMap, "selectByAll");
        if (networks.size() < 1){
            return "/network/subnet";
        }

        List<DockerCloudCiEnvEntity> envEntities =  ciEnvService.getListData(searchMap, "selectByAll");
        if (envEntities.size() < 1){
            return "/error/404";
        }
        DockerCloudCiEnvEntity envEntity = envEntities.get(0);
        String gsonData =  envEntity.getGsonData();
        Map map = new Gson().fromJson(gsonData, HashMap.class);
        modelMap.addAttribute("networks",  networks);
        modelMap.addAttribute("networkName", dockerServiceEntity.getNetwork());
        dockerServiceEntity.setLimitCpu(map.get("cpu"+entId).toString());
        dockerServiceEntity.setMemory(map.get("memory"+entId).toString());
        dockerServiceEntity.setScaleMin(Integer.valueOf(map.get("containerMin"+entId).toString()));
        dockerServiceEntity.setScaleMax(Integer.valueOf(map.get("containerMax"+entId).toString()));
        modelMap.addAttribute("service", dockerServiceEntity);
        modelMap.addAttribute("readonly", "readonly");
        modelMap.addAttribute("appName", appName);
        modelMap.addAttribute("operUser",  permissionsCheck.getLoginUser(session));
        modelMap.addAttribute("serviceName", serviceName);
        return "service/add";
    }

    /**
     *
     * @param entity
     * @param gson
     * @return
     */
    String getClusterTp(DockerCloudServiceEntity entity, Gson gson){
        String gsonData = entity.getGsonData();
        DockerServiceEntity serviceEntity = gson.fromJson(gsonData, DockerServiceEntity.class);
        return serviceEntity.getClusterTp();
    }

    /**
     *
     * @param serviceName
     * @return
     */
    @RequestMapping("delete/{serviceName}")
    @ResponseBody
    public ResponseVo delete(@PathVariable String serviceName, String entId){
        SearchMap searchMap = new SearchMap();
        if (!CheckUtil.checkString(serviceName)){
            return ResponseVo.responseError("服务名不能为空");
        }
        if (CheckUtil.checkString(entId)){
            searchMap.put("entId", Integer.valueOf(entId));
        }
        searchMap.put("serviceName", serviceName);
        List<DockerCloudServiceEntity> entities =  serviceService.getListData(searchMap, "selectByAll");
        String apiPort ;
        String clusterTp;
        String removeApi = "";
        Gson gson = new Gson();
        for (DockerCloudServiceEntity entity:entities){
            apiPort = getApiPort(entity.getGroupsName(), entity.getEntId());
            clusterTp = getClusterTp(entity, gson);
            if (SWARM.equals(clusterTp)){
                removeApi = "/api/docker/service?commands=removeService";
            }
            if (KUBERNETES.equals(clusterTp)){
                removeApi = "/api/k8s?commands=deleteRc";
            }
            if (CheckUtil.checkString(apiPort)){
                apiPort = apiPort.concat(removeApi);
                HttpUtil.sendPost(apiPort, "param="+entity.getGsonData());
            }
            entity.setTableName("docker_cloud_service");
            serviceService.delete(entity);
            entity.setTableName("docker_cloud_service_rollback");
            // 备份一个
            try {
                serviceService.save(entity);
            }catch (Exception e){
            }
        }
        return ResponseVo.responseOk("操作中");
    }

    /**
     * 回滚完成后删除回滚数据
     * @param serviceName
     * @param session
     * @return
     */
    @RequestMapping("delete/rollback/{serviceName}")
    @ResponseBody
    public String rollback(@PathVariable String serviceName , HttpSession session){
        String user = permissionsCheck.getLoginUser(session);
        DockerCloudServiceEntity dockerCloudServiceEntity = new DockerCloudServiceEntity();
        dockerCloudServiceEntity.setTableName("docker_cloud_service_rollback");
        dockerCloudServiceEntity.setServiceName(serviceName);
        dockerCloudServiceEntity.setCreateUser(user);
        serviceService.delete(dockerCloudServiceEntity);
        return "回滚完成";
    }

    /**
     * 回滚服务
     * 通过数据库备份的参数信息回滚
     * @param session
     * @param serviceName
     * @return
     */
    @RequestMapping("rollback/{serviceName}")
    @ResponseBody
    public String rollback( HttpSession session, @PathVariable  String serviceName) {
        String user = permissionsCheck.getLoginUser(session);
        SearchMap searchMap = new SearchMap();
        searchMap.put("serviceName", serviceName);
        searchMap.put("createUser", user);
        List<DockerCloudServiceEntity> serviceEntities =  serviceService.getListData(searchMap, "selectByAll");
        DockerCloudServiceEntity dockerCloudServiceEntity = serviceEntities.get(0);
        System.out.println(dockerCloudServiceEntity.getGsonData());
        HttpUtil.sendPost("http://10.16.55.6:9999/api/docker/service?commands=rollbackService", "param=" + dockerCloudServiceEntity.getGsonData());
        return "正在回滚中";
    }

    /**
     *
     * @param groupsName
     * @param entId
     * @return
     */
    String getApiPort(String groupsName,int entId){
        SearchMap searchMap = new SearchMap();
        RedisUtil redisUtil = new RedisUtil();
        String groups =  groupsName + entId;
        String master = redisUtil.get(RedisKey.masterServersList.concat(groups));
        if (CheckUtil.checkString(master)){
            searchMap.put("serverAddress", master.trim());
            List<DockerCloudServerEntity> serverEntities = serverService.getListData(searchMap, "selectByAll");
            DockerCloudServerEntity dockerCloudServerEntity = serverEntities.get(0);
            return  "http://".concat(master.trim()).concat(":").concat(dockerCloudServerEntity.getApiPort());
        }
        return null;
    }


    /**
     *
     * @param dockerServiceEntity
     * @return
     */
    public DockerServiceEntity getEntMaster(DockerServiceEntity dockerServiceEntity){
        SearchMap searchMap = new SearchMap();
        searchMap.put("entId", dockerServiceEntity.getEntId());
        List<DockerCloudGroupsEntity> groupsEntities =  groupsService.getListData(searchMap, "selectIdleGroups");
        for (DockerCloudGroupsEntity entity:groupsEntities){
           dockerServiceEntity.setEntname(entity.getEntname());
           String apiPort = getApiPort(entity.getGroupsName() , dockerServiceEntity.getEntId());
           if (CheckUtil.checkString(apiPort)){
               dockerServiceEntity.setGroupsName(entity.getGroupsName());
               dockerServiceEntity.setGroupsId(entity.getGroupsId());
               dockerServiceEntity.setApiUrl(apiPort);
           }
        }
        if (!CheckUtil.checkString(dockerServiceEntity.getEntname())){
            DockerCloudEntEntity entEntity = dockerCloudEntService.findById(dockerServiceEntity.getEntId(), DockerCloudEntEntity.class);
            dockerServiceEntity.setEntname(entEntity.getEntname());
        }
        return dockerServiceEntity;
    }

    /**
     *
     * @param serviceName
     * @param entId
     * @return
     */
    @RequestMapping("/rebuild/{serviceName}/{entId}")
    @ResponseBody
    public ResponseVo rebuild(@PathVariable String serviceName, @PathVariable int entId, HttpServletRequest request){
        SearchMap searchMap = new SearchMap();
        searchMap.put("serviceName", serviceName);
        searchMap.put("entId", entId);
        List<DockerCloudServiceEntity> result = serviceService.getListData(searchMap, "selectByAll");
        if (result.size() < 1){
            return ResponseVo.responseError("没有获取到可用服务");
        }

        DockerCloudServiceEntity serviceEntity = result.get(0);
        int groupsId = serviceEntity.getGroupsId();
        // 删除服务
        delete(serviceName, entId+"");
        DockerServiceEntity dockerServiceEntity = new Gson().fromJson(serviceEntity.getGsonData(), DockerServiceEntity.class);
        String network = dockerServiceEntity.getNetwork();

        searchMap.put("name", network);
        searchMap.put("groupsId", groupsId);
        List<DockerCloudNetworkEntity> networkEntity = networkService.getListData(searchMap, "selectByAll");
        if (networkEntity.size() < 1 ){
            return ResponseVo.responseError("该服务网络没有找到, 或已被删除 " + network);
        }

        // 创建网络
        networkController.save(networkEntity.get(0), request,"1");

        // 创建服务
        save(dockerServiceEntity, request, null, "1");
        return ResponseVo.responseOk("ok");
    }

    /**
     * @param serviceEntity
     * @param request
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public String save(DockerServiceEntity serviceEntity, HttpServletRequest request, String update, String create) {

        Gson gson = new Gson();
        System.out.println(gson.toJson(serviceEntity));
        String user = permissionsCheck.getLoginUser(request.getSession());
        String data = gson.toJson(serviceEntity);
        String result;
        serviceEntity = getEntMaster(serviceEntity);
        logger.info("api接口"+ serviceEntity.getApiUrl());
        if (!CheckUtil.checkString(serviceEntity.getApiUrl())){
            logger.error("创建服务失败,没有可用的服务器");
            logController.saveOperLog(request, "创建服务失败,没有可用的服务器"+serviceEntity.getName() + " " + serviceEntity.getEntname());
            return "服务操作失败:没有可用的服务器";
        }
        String createApi = "";
        String updateApi = "";
        if (KUBERNETES.equals(serviceEntity.getClusterTp())){
            createApi = "/api/k8s?commands=createRc";
            updateApi = "/api/k8s?commands=updateRc";
        }
        if (SWARM.equals(serviceEntity.getClusterTp())){
            createApi = "/api/docker/service?commands=createService";
            updateApi = "/api/docker/service?commands=updateService";
        }
        createApi = serviceEntity.getApiUrl().concat(createApi);
        updateApi = serviceEntity.getApiUrl().concat(updateApi);
        // 服务升级
        if (CheckUtil.checkString(update)) {
            result = HttpUtil.sendPost(updateApi, "param=" + data);
            result = HttpUtil.sendPost(updateApi, "param=" + data);
            logger.info("更新服务" + data);
        } else {
            // 服务创建
            result = HttpUtil.sendPost(createApi, "param=" + data);
            logger.info("创建服务" + data);
        }
        if (result.contains(errorMsg)) {
            logController.saveOperLog(request,"服务操作失败"+result+serviceEntity.getEntname()+" "+ serviceEntity.getGroupsName());
            return "服务操作失败:".concat(result);
        }
        if (!CheckUtil.checkString(result)){
            logController.saveOperLog(request, "创建服务失败,响应异常"+serviceEntity.getApiUrl()+" " + serviceEntity.getEntname()+" "+ serviceEntity.getGroupsName());
            return "创建服务失败";
        }

        if (CheckUtil.checkString(create)){
            logController.saveOperLog(request, "重建服务成功 "+ serviceEntity.getName());
            return "ok";
        }
        // 数据库存留信息
        DockerCloudServiceEntity entity = serviceEntity;
        entity.setServiceName(serviceEntity.getName());
        entity.setImage(serviceEntity.getImage());

        // 自动扩容参数
        if (serviceEntity.getScaleOn() != null && serviceEntity.getScaleOn() > 0) {
            entity.setScaleCpu(serviceEntity.getScaleCpu());
            entity.setScaleMem(serviceEntity.getScaleMem());
            entity.setScaleMax(serviceEntity.getScaleMax());
            entity.setScaleMin(serviceEntity.getScaleMin());
        }

        entity.setServiceTp(1);
        entity.setGsonData(new Gson().toJson(serviceEntity));
        entity.setCreateUser(user);
        entity.setAppName(serviceEntity.getAppName());
        entity.setCreateTime(DateUtil.getDate(DateUtil.TIME_FORMAT));

        DockerCloudLogEntity logEntity = new DockerCloudLogEntity();
        logEntity.setAppName(serviceEntity.getAppName());
        logEntity.setServiceName(serviceEntity.getName());
        if (CheckUtil.checkString(update)) {
            // 备份数据
            SearchMap searchMap = new SearchMap();
            searchMap.put("serviceName",  serviceEntity.getName());

            // 更新数据
            entity.setTableName("docker_cloud_service");
            serviceService.update(entity);

            entity.setTableName("docker_cloud_service_rollback");
            serviceService.update(entity);

            logEntity.setMessages("更新服务 ".concat(logEntity.getAppName()).concat(" ").concat(logEntity.getServiceName()));
            logController.save(logEntity, request);
            return "服务更新成功";
        } else {
            entity.setTableName("docker_cloud_service");
            serviceService.save(entity);
            try {
                entity.setTableName("docker_cloud_service_rollback");
                serviceService.save(entity);
            }catch (Exception e){
            }
            logEntity.setMessages("创建服务 ".concat(logEntity.getAppName()).concat(" ").concat(logEntity.getServiceName()));
            logController.save(logEntity, request);
            return "服务创建成功";
        }
    }

    /**
     * 删除已经停止的服务
     * @param serviceName
     * @return
     */
    @RequestMapping("/{serviceName}/invalid/delete")
    @ResponseBody
    public String deleteInvalidContainer(@PathVariable String serviceName, HttpSession session){
        String clusterName = "";
        List<DockerContainerEntity> containerEntities = getContainers(serviceName, false, clusterName);
        for (DockerContainerEntity entity:containerEntities){
            if (!entity.getState().equals("running")){
                DeleteContainerThread thread = new DeleteContainerThread(entity.getId(), entity.getHost(), containerController,session);
                thread.start();
            }
        }
        return "正在删除中<br>";
    }
}
