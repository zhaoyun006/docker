package com.cloud.sms.docker.ci.controller;

import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchMap;
import com.asura.framework.dao.mybatis.paginator.domain.PageBounds;
import com.cloud.sms.docker.ci.entity.DockerCloudCiEnvEntity;
import com.cloud.sms.docker.ci.entity.DockerCloudEnvFlowEntity;
import com.cloud.sms.docker.ci.service.CiPermissionsService;
import com.cloud.sms.docker.ci.service.DockerCloudCiEnvService;
import com.cloud.sms.docker.ci.service.DockerCloudEnvFlowService;
import com.cloud.sms.docker.common.response.PageResponse;
import com.cloud.sms.docker.common.response.ResponseVo;
import com.cloud.sms.docker.log.controller.LogController;
import com.cloud.sms.docker.server.entity.DockerCloudEntEntity;
import com.cloud.sms.docker.server.service.DockerCloudEntService;
import com.cloud.sms.docker.util.DateUtil;
import com.cloud.sms.docker.util.PermissionsCheck;
import com.google.gson.Gson;
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
 * Created by zhaoyun on 2017/11/3.
 *
 * @author zhaoyun
 */
@RequestMapping("/ci/")
@Controller
@SuppressWarnings("unused")
public class EnvFlowController {

    @Autowired
    private DockerCloudEnvFlowService flowService;

    @Autowired
    private LogController logController;

    @Autowired
    private PermissionsCheck permissionsCheck;

    @Autowired
    private DockerCloudCiEnvService ciEnvService;

    @Autowired
    private CiPermissionsService ciPermissionsService;

    @Autowired
    private DockerCloudEntService entService;

    /**
     * 流程列表
     *
     * @return
     */
    @RequestMapping("env/flow/list")
    public String list(ModelMap modelMap) {
        return "/ci/env/flow/list";
    }

    /**
     * 流程添加
     *
     * @return
     */
    @RequestMapping("env/flow/add")
    public String add(ModelMap modelMap, int flowId, HttpServletRequest request) {
        modelMap.addAttribute("configs", new DockerCloudEnvFlowEntity());
        SearchMap searchMap = new SearchMap();
        if (flowId > 0) {
            searchMap.put("flow", 1);
            searchMap.put("flowId", flowId);
            DockerCloudEnvFlowEntity dockerCloudEnvFlowEntity ;
            List<DockerCloudEnvFlowEntity> envFlowEntity = flowService.getListData(searchMap, "findByIdList");
            if (envFlowEntity.size() < 1){
                searchMap.remove("flow");
                envFlowEntity = flowService.getListData(searchMap, "findByIdList");
                envFlowEntity.get(0).setEnvId(0);
                dockerCloudEnvFlowEntity = envFlowEntity.get(0);
                dockerCloudEnvFlowEntity.setEnvId(0);
                dockerCloudEnvFlowEntity.setServiceName("--默认流程--");
            }else{
                dockerCloudEnvFlowEntity = envFlowEntity.get(0);
            }
            modelMap.addAttribute("configs", dockerCloudEnvFlowEntity);
        }
        String user = permissionsCheck.getLoginUser(request.getSession());
        ArrayList list = ciPermissionsService.getUeerEnv(user);

        searchMap.put("ids", list);
        List<DockerCloudCiEnvEntity> envEntities = ciEnvService.getListData(searchMap, "selectServiceName");
        if (envEntities.size() < 0) {
            return "/ci/env/add";
        }
        modelMap.addAttribute("services", envEntities);
        return "/ci/env/flow/add";
    }

    /**
     * 删除环境流程信息
     *
     * @return
     */
    @RequestMapping("env/flow/delete/{flowId}")
    @ResponseBody
    public ResponseVo delete(@PathVariable int flowId, HttpServletRequest request) {
        String user = permissionsCheck.getLoginUser(request.getSession());
        DockerCloudEnvFlowEntity flowEntity = flowService.findById(flowId, DockerCloudEnvFlowEntity.class);
        flowService.delete(flowEntity);
        if (!user.equals(flowEntity.getCreateUser())) {
            return ResponseVo.responseError("无权限");
        }
        logController.saveOperLog(request, "删除环境信息" + new Gson().toJson(flowEntity));
        return ResponseVo.responseOk("ok");
    }

    /**
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping("env/flow/save")
    @ResponseBody
    public ResponseVo save(DockerCloudEnvFlowEntity entity, HttpServletRequest request) {

        SearchMap searchMap = new SearchMap();
        searchMap.put("envId", entity.getEnvId());
        List<DockerCloudEnvFlowEntity> flowEntity = flowService.getListData(searchMap, "selectExistsEnv");
        if (flowEntity != null && flowEntity.size() > 0 && entity.getFlowId() == 0) {
            return ResponseVo.responseError("该环境已存在流程,不能重复创建");
        }

        String user = permissionsCheck.getLoginUser(request.getSession());
        entity.setLastModifyUser(user);
        entity.setLastModifyTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
        if (entity.getFlowId() != null && entity.getFlowId() > 0) {
            flowService.update(entity);
        } else {
            entity.setCreateTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
            entity.setCreateUser(user);
            flowService.save(entity);
        }
        logController.saveOperLog(request, "添加环境流程" + new Gson().toJson(entity));
        return ResponseVo.responseOk("添加环境流程成功");
    }

    /**
     * @param entIds
     * @return
     */
    String getEntName(String entIds) {
        Map map = new HashMap<>();
        String data = "";
        String[] ids = entIds.split(",");
        SearchMap searchMap = new SearchMap();
        searchMap.put("ids", ids);
        List<DockerCloudEntEntity> entEntities = entService.getListData(searchMap, "selectByAll");
        for (DockerCloudEntEntity entEntity : entEntities) {
            map.put(entEntity.getEntId()+"", entEntity.getEntname());
        }
        for (int i = 0; i < ids.length; i++) {
            data += map.get(ids[i]) + "<br>";
        }
        return data;
    }

    /**
     * 流程数据
     *
     * @return
     */
    @RequestMapping(value = "env/flow/data", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String data(int draw, int start, int length, HttpServletRequest request, String groups) {
        PageBounds pageBounds = PageResponse.getPageBounds(length, start);
        SearchMap searchMap = new SearchMap();
        PagingResult<DockerCloudEnvFlowEntity> result = flowService.findAll(searchMap, pageBounds, "selectByAll");
        ArrayList list = new ArrayList();
        for (DockerCloudEnvFlowEntity envFlowEntity : result.getRows()) {
            envFlowEntity.setReleaseOrder(getEntName(envFlowEntity.getReleaseOrder()));
            list.add(envFlowEntity);
        }
        return PageResponse.getList(list, draw, result.getTotal());
    }
}
