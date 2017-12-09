package com.cloud.sms.docker.ci.controller;

import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchMap;
import com.asura.framework.dao.mybatis.paginator.domain.PageBounds;
import com.cloud.configure.RedisKey;
import com.cloud.sms.docker.ci.entity.*;
import com.cloud.sms.docker.ci.service.*;
import com.cloud.sms.docker.common.response.PageResponse;
import com.cloud.sms.docker.common.response.ResponseVo;
import com.cloud.sms.docker.images.controller.RegistryServerController;
import com.cloud.sms.docker.images.entity.DockerCloudImagesBuildTemplateEntity;
import com.cloud.sms.docker.images.service.DockerCloudImagesBuildTemplateService;
import com.cloud.sms.docker.log.controller.LogController;
import com.cloud.sms.docker.server.entity.DockerCloudEntEntity;
import com.cloud.sms.docker.server.entity.DockerCloudServerEntity;
import com.cloud.sms.docker.server.service.DockerCloudEntService;
import com.cloud.sms.docker.server.service.DockerCloudGroupsService;
import com.cloud.sms.docker.server.service.DockerCloudServerService;
import com.cloud.sms.docker.util.CheckUtil;
import com.cloud.sms.docker.util.DateUtil;
import com.cloud.sms.docker.util.PermissionsCheck;
import com.cloud.sms.docker.util.RedisUtil;
import com.cloud.util.HttpUtil;
import com.google.gson.Gson;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ci/")
public class CiController {

    @Autowired
    private DockerCloudGroupsService groupsService;
    @Autowired
    private PermissionsCheck permissionsCheck;

    @Autowired
    private DockerCloudCiEnvService envService;

    @Autowired
    private RegistryServerController registryServerController;

    @Autowired
    private DockerCloudServerService serverService;

    @Autowired
    private DockerCloudImagesBuildLogService buildLogService;

    @Autowired
    private EnvController envController;

    @Autowired
    private DockerCloudImagesBuildTemplateService buildTemplateService;

    @Autowired
    private CiPermissionsService permissionsService;

    @Autowired
    private DockerCloudCiAutoBuildService autoBuildService;

    @Autowired
    private LogController logController;

    @Autowired
    private DockerCloudEnvFlowService envFlowService;

    /**
     * 镜像列表
     *
     * @return
     */
    @RequestMapping("images/list")
    public String list(ModelMap modelMap) {
        modelMap.addAttribute("groups", groupsService.getListData(new SearchMap<>(), "selectGroups"));
        return "/ci/images/list";
    }

    /**
     * 镜像tag详细入口
     *
     * @return
     */
    @RequestMapping("images/tags/list/{envId}")
    public String tagsList(@PathVariable int envId, ModelMap modelMap) {
        modelMap.addAttribute("envId", envId);
        return "/ci/images/tags/list";
    }

    /**
     * 根据构建日志，自动生成tag
     * tag按照 年月日-00001，构建次数
     * 例子 2017-10-22 00001
     *
     * @param imagesBuildInfoEntity
     * @return
     */
    String makeTag(ImagesBuildInfoEntity imagesBuildInfoEntity) {
        String tag;
        if (null != imagesBuildInfoEntity) {
            tag = imagesBuildInfoEntity.getVersion();
        } else {
            tag = "19701010-00001";
        }
        String[] tags = tag.split("-");
        String dates = DateUtil.getDate(DateUtil.DATE_FORMAT).replace("-", "");
        if (!tags[0].equals(dates)) {
            return dates + "-" + "00001";
        }
        int number = Integer.valueOf(tags[1]);
        number = number + 1;
        String s = "";
        if (number < 10000) {
            s = "0";
        }
        if (number < 1000) {
            s = "00";
        }
        if (number < 100) {
            s = "000";
        }
        if (number < 10) {
            s = "0000";
        }
        tag = dates + "-" + s + number;
        return tag;
    }

    /**
     * @param entity
     * @param user
     * @param buildParam
     */
    void setAutoBuild(ImagesBuildInfoEntity entity, String user, String buildParam) {

        // 设置自动编译
        DockerCloudCiAutoBuildEntity dockerCloudCiAutoBuildEntity = new DockerCloudCiAutoBuildEntity();
        dockerCloudCiAutoBuildEntity.setEnvId(entity.getEnvId());
        if (CheckUtil.checkString(entity.getAutoBuild())) {
            SearchMap searchMap = new SearchMap();
            searchMap.put("envId", entity.getEnvId());
            dockerCloudCiAutoBuildEntity.setBuildParam(buildParam);
            dockerCloudCiAutoBuildEntity.setCreateTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
            dockerCloudCiAutoBuildEntity.setCreteUser(user);
            List<DockerCloudCiAutoBuildEntity> ciAutoBuildEntity = autoBuildService.getListData(searchMap, "selectByAll");
            if (ciAutoBuildEntity.size() > 0){
                autoBuildService.update(dockerCloudCiAutoBuildEntity);
            }else {
                autoBuildService.save(dockerCloudCiAutoBuildEntity);
            }
        } else {
            // 没有开关的话就删除掉
            autoBuildService.delete(dockerCloudCiAutoBuildEntity);
        }
    }

    /**
     * 构建镜像
     *
     * @return
     */
    @RequestMapping("images/compile")
    @ResponseBody
    public String compile(ImagesBuildInfoEntity entity, HttpSession session) {
        SearchMap searchMap = new SearchMap();
        searchMap.put("envId", entity.getEnvId());

        boolean perm = permissionsService.checkPermissions(entity.getEnvId(), session);
        if (!perm) {
            return "你没有权限操作!";
        }
        int entId = getOrderEnt(searchMap);
        if (entId == 0){
            searchMap.remove("envId");
            entId = getOrderEnt(searchMap);
        }
        searchMap.put("envId", entity.getEnvId());
        searchMap.put("entId", entId);
        List<DockerCloudServerEntity> servers = serverService.getListData(searchMap, "selectCompileServer");
        if (servers.size() < 0) {
            return "请添加服务器";
        }
        String user = permissionsCheck.getLoginUser(session);

        // 排除add命令
        String add = "add ";
        String dockerFile = entity.getDockerFile();
        if (CheckUtil.checkString(dockerFile)) {
            if (dockerFile.toLowerCase().contains(add)) {
                return "docker内容不能包含ADD命令";
            }
        }
        // 更新编译状态
        String compile = "1";
        DockerCloudCiEnvEntity envEntity = envService.findById(entity.getEnvId(), DockerCloudCiEnvEntity.class);
        if (compile.equals(envEntity.getBuildStatus())) {
            return "正在构建中,请稍候重试";
        }

        String buildTp1 = "1";
        // 根据模板进行编译
        String auto = "auto";
        String custom = "custom";
        if (auto.equals(entity.getDockerFileSource()) && buildTp1.equals(entity.getBuildTp()) && CheckUtil.checkString(entity.getTemplateId()) ) {
            DockerCloudImagesBuildTemplateEntity buildTemplateEntity = buildTemplateService.findById(Integer.valueOf(entity.getTemplateId()), DockerCloudImagesBuildTemplateEntity.class);
            entity.setDockerFile(buildTemplateEntity.getDockerFile());
        }

        // 根据代码中的Dockerfile进行编译构建
        if (custom.equals(entity.getDockerFileSource())){
            String filePath = entity.getDockerFilePath();
            if (!CheckUtil.checkString(filePath)){
                entity.setDockerFilePath("Dockerfile");
            }else {
                if (filePath.startsWith("/")){
                    entity.setDockerFilePath(filePath.substring(1, filePath.length()));
                }
            }
        }

        DockerCloudServerEntity dockerCloudServerEntity = servers.get(0);

        entity.setServiceName(dockerCloudServerEntity.getServiceName());
        entity.setUser(user);
        Gson gson = new Gson();

        // 设置是否变更过代码地址,如果有改动就删除机器上已经有的目录，重新拉取
        searchMap.put("createUser", user);
        DockerCloudImagesBuildLogEntity buildLogEntity = buildLogService.getBuildLog(searchMap, "selectServerApi");
        if (buildLogEntity != null) {
            String param = buildLogEntity.getBuildParam();
            ImagesBuildInfoEntity buildInfoEntity = gson.fromJson(param, ImagesBuildInfoEntity.class);
            if (null != buildInfoEntity) {
                if (CheckUtil.checkString(entity.getSvnAddress())) {
                    if (!buildInfoEntity.getSvnAddress().equals(entity.getSvnAddress())) {
                        entity.setNewGit("1");
                    }
                }
                if (CheckUtil.checkString(entity.getGitAddress())) {
                    if (!buildInfoEntity.getGitAddress().equals(entity.getGitAddress())) {
                        entity.setNewGit("1");
                    }
                }
            }
        }


        String url = "http://".concat(dockerCloudServerEntity.getServerAddress()).concat(":").concat(dockerCloudServerEntity.getApiPort());
        url = url.concat("/api/images/commands?commands=build");
        entity.setBuildApiServer(url);
        String entitys = gson.toJson(entity);
        setAutoBuild(entity, user, entitys);
        HttpUtil.sendPost(url, "param=".concat(entitys));
        envEntity.setBuildStatus("1");
        envService.update(envEntity);
        DockerCloudImagesBuildLogEntity dockerCloudImagesBuildLogEntity = new DockerCloudImagesBuildLogEntity();
        dockerCloudImagesBuildLogEntity.setCreateTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
        dockerCloudImagesBuildLogEntity.setCreateUser(user);
        dockerCloudImagesBuildLogEntity.setEnvId(entity.getEnvId());
        dockerCloudImagesBuildLogEntity.setMessages("开始构建");
        dockerCloudImagesBuildLogEntity.setBuildStatus("2");
        dockerCloudImagesBuildLogEntity.setBuildServerApi(url);
        dockerCloudImagesBuildLogEntity.setBuildParam(entitys);
        dockerCloudImagesBuildLogEntity.setTag(entity.getVersion());
        buildLogService.save(dockerCloudImagesBuildLogEntity);
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setex(RedisKey.imagesBuildStatus, 1800, "1");
        return "ok";
    }

    /**
     *
     * @param searchMap
     * @return
     */
    int getOrderEnt(SearchMap searchMap){
        List<DockerCloudEnvFlowEntity> datas = envFlowService.getListData(searchMap,"selectByAll");
        if (datas.size() < 1){
            return 0;
        }
        String order = datas.get(0).getReleaseOrder();
        String[] orders = order.split(",");
        return Integer.valueOf(orders[0]);
    }

    /**
     * 获取仓库是否存在
     * @param envId
     * @return
     */
    List<DockerCloudCiEnvEntity> getRegistry(int envId){
        SearchMap searchMap = new SearchMap();
        searchMap.put("envId", envId);
        // 看是否配置过流程信息
        // 没有的话走默认配置,必须添加一个默认的配置
        int orderEntId = getOrderEnt(searchMap);
        if (orderEntId == 0 ) {
            searchMap.remove("envId");
            orderEntId = getOrderEnt(searchMap);
        }
        searchMap.put("entId", orderEntId);
        searchMap.put("envId", envId);
        // 先找自己环境的仓库,没有的话就找
        List<DockerCloudCiEnvEntity> dockerCloudCiEnvEntity = envService.getListData(searchMap, "selectRegistry");
        System.out.println("dockerCloudCiEnvEntity 1"+ new Gson().toJson(dockerCloudCiEnvEntity));
        if (dockerCloudCiEnvEntity.size() < 1){
            searchMap = new SearchMap();
            dockerCloudCiEnvEntity = envService.getListData(searchMap, "selectRegistry");
            System.out.println("dockerCloudCiEnvEntity 2"+ new Gson().toJson(dockerCloudCiEnvEntity));
        }
        return dockerCloudCiEnvEntity;
    }

    /**
     *
     * @param envId
     * @return
     */
    @RequestMapping("images/deleteLock/{envId}")
    @ResponseBody
    public ResponseVo deleteLock(@PathVariable  int envId, HttpServletRequest  request){
        SearchMap searchMap = new SearchMap();
        searchMap.put("envId", envId);
        envService.getListData(searchMap, "deleteLock");
        logController.saveOperLog(request, "更新发布状态");
        return ResponseVo.responseOk("删除成功");
    }

    /**
     * 构建镜像
     *
     * @return
     */
    @RequestMapping("images/build")
    public String build(ModelMap modelMap, HttpSession session, int envId) {
        SearchMap searchMap = new SearchMap<>();
        searchMap.put("envId", envId);
        boolean perm = permissionsService.checkPermissions(envId, session);
        if (!perm) {
            return "/ci/images/403";
        }
        List<DockerCloudCiEnvEntity> server = getRegistry(envId);
        if (server.size() < 1) {
            return registryServerController.add(modelMap, 0, null,null);
        }
        String user = permissionsCheck.getLoginUser(session);
        DockerCloudCiEnvEntity dockerCloudCiEnvEntity = server.get(0);
        if (CheckUtil.checkString(dockerCloudCiEnvEntity.getServerDomain())) {
            modelMap.addAttribute("registry", server.get(0).getServerDomain().concat("/").concat(user));
        } else {
            modelMap.addAttribute("registry", server.get(0).getServerAddress().concat("/").concat(user));
        }
        modelMap.addAttribute("serviceName", dockerCloudCiEnvEntity.getServerDomain());
        modelMap.addAttribute("user", user);
        searchMap.put("createUser", user);
        DockerCloudImagesBuildLogEntity buildLogEntity = buildLogService.getBuildLog(searchMap, "selectServerApi");
        System.out.println("configs 1 " + new Gson().toJson(buildLogEntity));
        if (null != buildLogEntity && CheckUtil.checkString(buildLogEntity.getTag())) {
            ImagesBuildInfoEntity imagesBuildInfoEntity = new Gson().fromJson(buildLogEntity.getBuildParam(), ImagesBuildInfoEntity.class);
            modelMap.addAttribute("tag", makeTag(imagesBuildInfoEntity));
            modelMap.addAttribute("configs", imagesBuildInfoEntity);
        } else {
            modelMap.addAttribute("tag", makeTag(null));
            DockerCloudCiEnvEntity envEntity = envService.findById(envId, DockerCloudCiEnvEntity.class);
            if (!CheckUtil.checkString(envEntity.getBuildTp())){
                envEntity.setBuildTp("1");
            }
            modelMap.addAttribute("configs", envEntity);
        }
        System.out.println("configs 4" + new Gson().toJson(modelMap.get("configs")));
        modelMap.addAttribute("groups", groupsService.getListData(new SearchMap<>(), "selectGroups"));
        return "/ci/images/build";
    }

    /**
     * 我的应用数据
     *
     * @return
     */
    @RequestMapping(value = "images/data", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String data(int draw, int start, int length, HttpServletRequest request, String groups) {
        String user = permissionsCheck.getLoginUser(request.getSession());
        PageBounds pageBounds = PageResponse.getPageBounds(length, start);
        SearchMap searchMap = envController.getEnvSearchMap(request, groups);
        searchMap.put("createUser", user);
        PagingResult<DockerCloudCiEnvEntity> result = envService.findAll(searchMap, pageBounds, "selectByAll");
        return PageResponse.getMap(result, draw);
    }

    /**
     * @param logId
     * @return
     */
    @RequestMapping(value = "images/log/{logId}")
    @ResponseBody
    public String imagesLog(@PathVariable int logId) {
        SearchMap searchMap = new SearchMap();
        searchMap.put("logId", logId);
        DockerCloudImagesBuildLogEntity logEntity = buildLogService.getBuildLog(searchMap, "selectByAll");
        return logEntity.getMessages().replace("\n", "<br>").replace(" ", "&nbsp;");
    }

    /**
     * @param draw
     * @param start
     * @param length
     * @param envId
     * @return
     */
    @RequestMapping(value = "images/tags/data", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String tagsData(int draw, int start, int length, int envId) {
        DockerCloudCiEnvEntity envEntity = envService.findById(envId, DockerCloudCiEnvEntity.class);
        PageBounds pageBounds = PageResponse.getPageBounds(length, start);
        SearchMap searchMap = new SearchMap();
        searchMap.put("envId", envId);
        List list = new ArrayList();
        DockerCloudCiEnvEntity dockerCloudCiEnvEntity;
        PagingResult<DockerCloudImagesBuildLogEntity> result = buildLogService.findAll(searchMap, pageBounds, "selectImagesTag");
        for (DockerCloudImagesBuildLogEntity entity : result.getRows()) {
            dockerCloudCiEnvEntity = new DockerCloudCiEnvEntity();
            dockerCloudCiEnvEntity.setServiceName(envEntity.getServiceName());
            dockerCloudCiEnvEntity.setDomain(envEntity.getDomain());
            dockerCloudCiEnvEntity.setDescription(envEntity.getDescription());
            dockerCloudCiEnvEntity.setBuildStatus(entity.getBuildStatus());
            dockerCloudCiEnvEntity.setTag(entity.getTag());
            dockerCloudCiEnvEntity.setEnvId(entity.getLogId());
            dockerCloudCiEnvEntity.setCreateTime(entity.getCreateTime());
            dockerCloudCiEnvEntity.setBuildStatus(entity.getBuildStatus());
            dockerCloudCiEnvEntity.setBuildTp(entity.getBuildTp());
            list.add(dockerCloudCiEnvEntity);
        }
        return PageResponse.getList(list, draw, result.getTotal());
    }
}
