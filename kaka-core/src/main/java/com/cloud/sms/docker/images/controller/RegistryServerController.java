package com.cloud.sms.docker.images.controller;

import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchMap;
import com.asura.framework.dao.mybatis.paginator.domain.PageBounds;
import com.cloud.sms.docker.common.response.PageResponse;
import com.cloud.sms.docker.common.response.ResponseVo;
import com.cloud.sms.docker.images.entity.DockerCloudRegistryServerEntity;
import com.cloud.sms.docker.images.service.DockerCloudRegistryServerService;
import com.cloud.sms.docker.images.util.GetImagesUtil;
import com.cloud.sms.docker.log.controller.LogController;
import com.cloud.sms.docker.server.entity.DockerCloudGroupsEntity;
import com.cloud.sms.docker.server.service.DockerCloudGroupsService;
import com.cloud.sms.docker.util.CheckUtil;
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
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyun on 2017/10/10.
 * @author zhaoyun
 */
@Controller
@RequestMapping("/registry/server/")
public class RegistryServerController {

    @Autowired
    private DockerCloudRegistryServerService registryServerService;

    @Autowired
    private PermissionsCheck permissionsCheck;

    @Autowired
    private DockerCloudGroupsService groupsService;

    @Autowired
    private LogController logController;

    /**
     * 镜像服务器列表
     * @return
     */
    @RequestMapping("list")
    public String list(ModelMap modelMap){
        modelMap.addAttribute("groups", groupsService.getListData(new SearchMap(), "selectGroups"));
        return "/registry/server/list";
    }

    /**
     * 镜像服务器添加
     * @return
     */
    @RequestMapping("add")
    public String add(ModelMap modelMap, int serverId, String groupsName, String entId){
        List<DockerCloudGroupsEntity> groups;
        SearchMap searchMap = new SearchMap();
        DockerCloudRegistryServerEntity dockerCloudRegistryServerEntity = new DockerCloudRegistryServerEntity();
        if (CheckUtil.checkString(groupsName)) {
            searchMap.put("groupsName", groupsName);
            searchMap.put("entId",entId);
            groups = groupsService.getListData(searchMap, "selectByAll");
            DockerCloudGroupsEntity dockerCloudGroupsEntity = groups.get(0);
            dockerCloudRegistryServerEntity.setGroupsId(dockerCloudGroupsEntity.getGroupsId());
            dockerCloudRegistryServerEntity.setGroups(dockerCloudGroupsEntity.getGroupsName());
        }else{
            groups = groupsService.getListData(new SearchMap<>(), "selectGroups");
            if (groups.size() < 1) {
                return "/groups/add";
            }
        }
        modelMap.addAttribute("configs", dockerCloudRegistryServerEntity);
        if (serverId > 0){
            modelMap.addAttribute("configs", registryServerService.findById(serverId, DockerCloudRegistryServerEntity.class));
        }

        modelMap.addAttribute("groups", groups);
        return "/registry/server/add";
    }



    /**
     * 注册服务器数据
     * @return
     */
    @RequestMapping(value = "data", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String data(int draw, int start, int length, HttpServletRequest request, String groups) {

        PageBounds pageBounds = PageResponse.getPageBounds(length, start);
        SearchMap searchMap = new SearchMap();
        String search = request.getParameter("search[value]");
        if (CheckUtil.checkString(search)){
            searchMap.put("key", search);
        }
        if (CheckUtil.checkString(groups)){
            searchMap.put("groups", groups);
        }
        ArrayList list = new ArrayList();
        PagingResult<DockerCloudRegistryServerEntity> result = registryServerService.findAll(searchMap, pageBounds, "selectByAll");
        for (DockerCloudRegistryServerEntity entity:result.getRows()){
            entity.setImagesNumber(GetImagesUtil.getRegistryImagesNumber(entity.getServerAddress(), entity.getServerDomain()));
            list.add(entity);
        }
        return PageResponse.getMap(result, draw);
    }

    /**
     *
     * @return
     */
    @RequestMapping("delete/{serverId}")
    @ResponseBody
    public ResponseVo delete(@PathVariable int serverId, HttpServletRequest request){
        DockerCloudRegistryServerEntity registryServerEntity = new DockerCloudRegistryServerEntity();
        registryServerEntity.setServerId(serverId);
        registryServerService.delete(registryServerEntity);
        logController.saveOperLog(request, "删除仓库配置" + new Gson().toJson(registryServerEntity));
        return ResponseVo.responseOk("ok");
    }

    /**
     *
     * @param entity
     * @param session
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResponseVo save(DockerCloudRegistryServerEntity entity, HttpSession session){
        String user = permissionsCheck.getLoginUser(session);
        entity.setLastModifyUser(user);
        entity.setLastModifyTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
        if (entity.getServerId() != null && entity.getServerId() > 0){
            registryServerService.update(entity);
        }else {
            entity.setCreateTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
            entity.setCreateUser(user);
            entity.setImagesNumber(0);
            registryServerService.save(entity);
        }
        return ResponseVo.responseOk("添加服务器成功");
    }
}
