package com.cloud.sms.docker.ci.controller;

import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchMap;
import com.asura.framework.dao.mybatis.paginator.domain.PageBounds;
import com.cloud.sms.docker.authority.entity.DockerCloudAuthorityUserEntity;
import com.cloud.sms.docker.authority.service.DockerCloudAuthorityUserService;
import com.cloud.sms.docker.ci.entity.DockerCloudCiEnvEntity;
import com.cloud.sms.docker.ci.entity.DockerCloudCiEnvPermissionsEntity;
import com.cloud.sms.docker.ci.entity.DockerCloudCiEnvPermissionsGroupsEntity;
import com.cloud.sms.docker.ci.service.DockerCloudCiEnvPermissionsGroupsService;
import com.cloud.sms.docker.ci.service.DockerCloudCiEnvPermissionsService;
import com.cloud.sms.docker.ci.service.DockerCloudCiEnvService;
import com.cloud.sms.docker.common.response.PageResponse;
import com.cloud.sms.docker.common.response.ResponseVo;
import com.cloud.sms.docker.log.controller.LogController;
import com.cloud.sms.docker.util.CheckUtil;
import com.cloud.sms.docker.util.DateUtil;
import com.cloud.sms.docker.util.PermissionsCheck;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by zhaoyun on 2017/10/23.
 * @author zhaoyun
 */
@RequestMapping("/ci/env/permissions/")
@Controller
public class EnvPermissionController {

    @Autowired
    private DockerCloudCiEnvPermissionsGroupsService permissionsGroupsService;

    @Autowired
    private DockerCloudCiEnvPermissionsService permissionsService;

    @Autowired
    private PermissionsCheck permissionsCheck;

    @Autowired
    private LogController logController;

    @Autowired
    private DockerCloudCiEnvService envService;

    @Autowired
    private DockerCloudAuthorityUserService authorityUserService;

    /**
     * 某个环境的权限列表
     * @return
     */
    @RequestMapping("list")
    public String list(ModelMap modelMap, String envId, String serviceName){
        modelMap.addAttribute("envId", envId);
        modelMap.addAttribute("serviceName", serviceName);
        return "/ci/env/permissions/list";
    }


    /**
     *
     * @param modelMap
     * @param envId
     * @return
     */
    @RequestMapping("groups/list")
    public String groupsList(ModelMap modelMap, String envId){
        modelMap.addAttribute("envId", envId);
        return "/ci/env/permissions/groups/list";
    }

    /**
     *
     * @param modelMap
     * @param permissionsId
     * @param envId
     * @return
     */
    @RequestMapping("add/{envId}")
    public String add(ModelMap modelMap,@PathVariable int envId, int permissionsId){
        DockerCloudCiEnvPermissionsEntity envPermissionsEntity = new DockerCloudCiEnvPermissionsEntity();
        DockerCloudCiEnvEntity envEntity = envService.findById(envId, DockerCloudCiEnvEntity.class);
        envPermissionsEntity.setEnvId(envId);
        envPermissionsEntity.setServiceName(envEntity.getServiceName());
        envPermissionsEntity.setPermissionsId(permissionsId);
        modelMap.addAttribute("configs", envPermissionsEntity);
        if (permissionsId > 0 ){
            modelMap.addAttribute("configs", permissionsService.findById(permissionsId, DockerCloudCiEnvPermissionsEntity.class));
        }
        return "/ci/env/permissions/add";
    }

    /**
     *
     * @param modelMap
     * @param groupsId
     * @return
     */
    @RequestMapping("groups/add")
    public String groupsAdd(ModelMap modelMap, int groupsId){
        modelMap.addAttribute("configs",new DockerCloudCiEnvPermissionsGroupsEntity());
        if (groupsId > 0 ){
            modelMap.addAttribute("configs", permissionsGroupsService.findById(groupsId, DockerCloudCiEnvPermissionsGroupsEntity.class));
        }
        return "/ci/env/permissions/groups/add";
    }

    /**
     *
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResponseVo save(DockerCloudCiEnvPermissionsEntity entity, HttpServletRequest request){
        String user = permissionsCheck.getLoginUser(request.getSession());
        DockerCloudCiEnvEntity dockerCloudCiEnvEntity = envService.findById(entity.getEnvId(), DockerCloudCiEnvEntity.class);
        if (!user.equals(dockerCloudCiEnvEntity.getCreateUser())){
            return ResponseVo.responseError("只能由创建用户 "+dockerCloudCiEnvEntity.getCreateUser()+"保存更改");
        }
        entity.setUsername(uniqueData(entity.getUsername()));
        entity.setGroupsName(uniqueData(entity.getGroupsName()));
        if (entity.getPermissionsId() != null && entity.getPermissionsId() > 0){
            permissionsService.update(entity);
        }else {
            entity.setCreateTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
            entity.setCreateUser(user);
            permissionsService.save(entity);
        }
        logController.saveOperLog(request, "保存用户权限" + new Gson().toJson(entity));
        return ResponseVo.responseOk("保存用户权限成功");
    }

    /**
     *
     * @param data
     * @return
     */
    String uniqueData(String data){
        String[] s= data.split(",");
        List list = Arrays.asList(s);
        Set set = new HashSet(list);
        String[] username =(String [])set.toArray(new String[0]);
        return StringUtils.join(username,",");
    }
    /**
     *
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping("groups/save")
    @ResponseBody
    public ResponseVo groupsSave(DockerCloudCiEnvPermissionsGroupsEntity entity, HttpServletRequest request){
        entity.setUsername(uniqueData(entity.getUsername()));
        String user = permissionsCheck.getLoginUser(request.getSession());
        entity.setLastModifyTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
        entity.setLastModifyUser(user);
        if (entity.getGroupsId() != null && entity.getGroupsId() > 0){
            DockerCloudCiEnvPermissionsGroupsEntity envPermissionsGroupsEntity = permissionsGroupsService.findById(entity.getGroupsId(), DockerCloudCiEnvPermissionsGroupsEntity.class);
            if (!user.equals(envPermissionsGroupsEntity.getCreateUser())){
                return ResponseVo.responseError("只能由创建用户 "+envPermissionsGroupsEntity.getCreateUser()+"保存更改");
            }
            permissionsGroupsService.update(entity);
        }else {
            entity.setCreateTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
            entity.setCreateUser(user);
            permissionsGroupsService.save(entity);
        }
        logController.saveOperLog(request, "保存应用权限组" + new Gson().toJson(entity));
        return ResponseVo.responseOk("保存应用权限组成功");
    }


    /**
     *
     * @param permissionsId
     * @param request
     * @return
     */
    @RequestMapping("delete/{permissionsId}")
    @ResponseBody
    public ResponseVo delete(@PathVariable int permissionsId, HttpServletRequest request){
        String user = permissionsCheck.getLoginUser(request.getSession());
        DockerCloudCiEnvPermissionsEntity envPermissionsEntity = permissionsService.findById(permissionsId, DockerCloudCiEnvPermissionsEntity.class);
        if (!user.equals(envPermissionsEntity.getCreateUser())){
            return ResponseVo.responseError("只能由创建用户 "+envPermissionsEntity.getCreateUser()+"删除该权限");
        }
        permissionsService.delete(envPermissionsEntity);
        logController.saveOperLog(request, "删除环境权限用户"+ new Gson().toJson(envPermissionsEntity));
        return ResponseVo.responseOk("ok");
    }

    /**
     *
     * @param groupsId
     * @param request
     * @return
     */
    @RequestMapping("groups/delete/{groupsId}")
    @ResponseBody
    public ResponseVo groupsDelete(@PathVariable int groupsId, HttpServletRequest request){
        String user = permissionsCheck.getLoginUser(request.getSession());
        DockerCloudCiEnvPermissionsGroupsEntity groupsEntity = permissionsGroupsService.findById(groupsId, DockerCloudCiEnvPermissionsGroupsEntity.class);
        if (!user.equals(groupsEntity.getCreateUser())){
            return ResponseVo.responseError("只能由创建用户 "+groupsEntity.getCreateUser()+"删除该组");
        }
        SearchMap searchMap = new SearchMap();
        searchMap.put("groupsId", groupsId);
        List<DockerCloudCiEnvPermissionsEntity> used = permissionsService.getListData(searchMap, "selectUseGroup");
        if (used.size() > 0){
            return ResponseVo.responseError("该组还在使用,不能删除,正在使用的组 ".concat(used.get(0).getServiceName()));
        }
        permissionsGroupsService.delete(groupsEntity);
        logController.saveOperLog(request, "删除环境权限组"+ new Gson().toJson(groupsEntity));
        return ResponseVo.responseOk("ok");
    }

    /**
     *
     * @param usernames
     * @return
     */
    String getUsername(String usernames){
        String username = "";
        String[] users = usernames.split(",");
        SearchMap searchMap = new SearchMap();
        searchMap.put("users", users);
        List<DockerCloudAuthorityUserEntity> userEntities = authorityUserService.getListData(searchMap, "selectUsername");
        for (DockerCloudAuthorityUserEntity entity:userEntities){
            username += entity.getUserName().concat("<br>");
        }
        return username;
    }

    /**
     *
     * @param groupsNames
     * @return
     */
    String getGroupsName(String groupsNames){
        String group = "";
        String[] groups = groupsNames.split(",");
        SearchMap searchMap = new SearchMap();
        searchMap.put("ids", groups);
        List<DockerCloudCiEnvPermissionsGroupsEntity> userEntities = permissionsGroupsService.getListData(searchMap, "selectGroupsName");
        for (DockerCloudCiEnvPermissionsGroupsEntity entity:userEntities){
            group += entity.getGroupsName().concat("<br>");
        }
        return group;
    }


    /**
     * 环境权限信息
     * @return
     */
    @RequestMapping(value = "data", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String data(int draw, int start, int length, HttpServletRequest request, int envId) {
        String user = permissionsCheck.getLoginUser(request.getSession());
        PageBounds pageBounds = PageResponse.getPageBounds(length, start);
        SearchMap searchMap = new SearchMap();
        searchMap.put("createUser", user);
        searchMap.put("envId", envId);
        ArrayList list = new ArrayList();
        PagingResult<DockerCloudCiEnvPermissionsEntity> result = permissionsService.findAll(searchMap, pageBounds, "selectByAll");
        for (DockerCloudCiEnvPermissionsEntity envPermissionsEntity:result.getRows()){
            envPermissionsEntity.setUsername(getUsername(envPermissionsEntity.getUsername()));
            envPermissionsEntity.setGroupsName(getGroupsName(envPermissionsEntity.getGroupsName()));
            list.add(envPermissionsEntity);
        }
        return PageResponse.getList(list, draw, result.getTotal());
    }


    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "groups/select", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String select(String id) {
        SearchMap searchMap = new SearchMap();
        String defS = "0";
        if (CheckUtil.checkString(id) &&  !defS.equals(id)){
            String[] ids = id.split(",");
            searchMap.put("ids",ids);
        }
        List<DockerCloudCiEnvPermissionsGroupsEntity> result = permissionsGroupsService.getListData(searchMap, "selectGroupsName");
        return PageResponse.getList(result, 1, result.size());
    }

        /**
         * 我的应用数据
         * @return
         */
    @RequestMapping(value = "groups/data", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String groupsData(int draw, int start, int length, HttpServletRequest request) {
        String user = permissionsCheck.getLoginUser(request.getSession());
        PageBounds pageBounds = PageResponse.getPageBounds(length, start);
        SearchMap searchMap = new SearchMap();
        searchMap.put("createUser", user);
        ArrayList list = new ArrayList();
        PagingResult<DockerCloudCiEnvPermissionsGroupsEntity> result = permissionsGroupsService.findAll(searchMap, pageBounds, "selectByAll");
        for (DockerCloudCiEnvPermissionsGroupsEntity envPermissionsEntity:result.getRows()){
            envPermissionsEntity.setUsername(getUsername(envPermissionsEntity.getUsername()));
            list.add(envPermissionsEntity);
        }
        return PageResponse.getList(list, draw, result.getTotal());

    }

}
