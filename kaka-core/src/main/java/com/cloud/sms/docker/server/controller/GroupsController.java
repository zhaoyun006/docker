package com.cloud.sms.docker.server.controller;

import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchMap;
import com.asura.framework.dao.mybatis.paginator.domain.PageBounds;
import com.cloud.sms.docker.common.entity.ParamEntity;
import com.cloud.sms.docker.common.response.PageResponse;
import com.cloud.sms.docker.common.response.ResponseVo;
import com.cloud.sms.docker.log.controller.LogController;
import com.cloud.sms.docker.server.entity.DockerCloudEntEntity;
import com.cloud.sms.docker.server.entity.DockerCloudGroupsEntity;
import com.cloud.sms.docker.server.entity.DockerCloudServerEntity;
import com.cloud.sms.docker.server.service.DockerCloudEntService;
import com.cloud.sms.docker.server.service.DockerCloudGroupsService;
import com.cloud.sms.docker.server.service.DockerCloudServerService;
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
 * Created by zhaoyun on 2017/10/11.
 * @author zhaoyun
 */
@Controller
@RequestMapping("/groups/")
@SuppressWarnings("unused")
public class GroupsController {

    @Autowired
    private PermissionsCheck permissionsCheck;
    
    @Autowired
    private DockerCloudGroupsService groupsService;

    @Autowired
    private DockerCloudServerService serverService;

    @Autowired
    private LogController logController;

    @Autowired
    private DockerCloudEntService entService;
    
    /**
     *
     * @param modelMap
     * @param groupsId
     * @return
     */
    @RequestMapping("add")
    public String add(ModelMap modelMap, int groupsId){
        modelMap.addAttribute("entnames", entService.getListData(new SearchMap(), "selectEnvGroups"));
        modelMap.addAttribute("configs", new DockerCloudGroupsEntity());
        if (groupsId > 0){
            DockerCloudGroupsEntity entity = groupsService.findById(groupsId, DockerCloudGroupsEntity.class);
            modelMap.addAttribute("configs", entity);
        }
        return "/groups/add";
    }

    /**
     *
     * @return
     */
    @RequestMapping("list")
    public String list(){
        return "/groups/list";
    }

    /**
     *
     * @param entity
     * @param session
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResponseVo save(DockerCloudGroupsEntity entity, HttpSession session){
        String user = permissionsCheck.getLoginUser(session);
        entity.setLastModifyUser(user);
        entity.setLastModifyTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
        if (entity.getGroupsId() != null && entity.getGroupsId() > 0){
            groupsService.update(entity);
        }else {
            entity.setCreateUser(user);
            entity.setCreateTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
            groupsService.save(entity);
        }
        return ResponseVo.responseOk("添加组成功");
    }

    /**
     *
     * @param groupsName
     * @return
     */
    int getGroupsServerSize(String groupsName){
        SearchMap searchMap = new SearchMap();
        searchMap.put("groups", groupsName);
        List<DockerCloudServerEntity> groups = serverService.getListData(searchMap, "selectByAll");
        return groups.size();
    }

    /**
     *
     * @param groupsId
     * @return
     */
    String getGroupsServiceSize(int groupsId){
        SearchMap searchMap = new SearchMap();
        searchMap.put("groupsId", groupsId);
        List<DockerCloudServerEntity> groups = serverService.getListData(searchMap, "selectServiceNumber");
        return groups.size()+"";
    }

    /**
     * 删除数据
     * @return
     */
    @RequestMapping(value = "delete/{groupsId}", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseVo delete(@PathVariable int groupsId, HttpServletRequest request){
        DockerCloudGroupsEntity groupsEntity = groupsService.findById(groupsId, DockerCloudGroupsEntity.class);
        int size = getGroupsServerSize(groupsEntity.getGroupsName());
        if (size > 0 ){
            return ResponseVo.responseError("该组还有"+ size + "个主机没有删除!");
        }

        groupsService.delete(groupsEntity);
        logController.saveOperLog(request, "删除主机组" + new Gson().toJson(groupsEntity));
        return ResponseVo.responseOk("ok");
    }

    /**
     *  默认配置每个环境选择一个组
     * @return
     */
    @RequestMapping(value = "selectEnvGroups", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String selectEnvGroups(){
        List<DockerCloudGroupsEntity> result = groupsService.getListData(new SearchMap(), "selectEnvGroups");
        return PageResponse.getList(result,1, result.size());
    }


    /**
     * 注册服务器数据
     * @return
     */
    @RequestMapping(value = "data", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String data(ParamEntity paramEntity, HttpServletRequest request, String groups, String key, String ids, String entId) {

        PageBounds pageBounds = PageResponse.getPageBounds(paramEntity.getLength(), paramEntity.getStart());
        SearchMap searchMap = new SearchMap();
        String search = request.getParameter("search[value]");
        if (CheckUtil.checkString(search)){
            searchMap.put("key", search);
        }
        if (CheckUtil.checkString(key)){
            searchMap.put("key", key);
        }
        if (CheckUtil.checkString(groups)){
            searchMap.put("groups", groups);
        }
        if (CheckUtil.checkString(ids)){
            searchMap.put("ids", ids.split(","));
        }
        if (CheckUtil.checkString(entId)){
            searchMap.put("entId", entId);
        }
        System.out.println(new Gson().toJson(searchMap));
        ArrayList list = new ArrayList();
        PagingResult<DockerCloudGroupsEntity> result = groupsService.findAll(searchMap, pageBounds, "selectByAll");
        for (DockerCloudGroupsEntity entity:result.getRows()){
            entity.setContainers("0");
            entity.setServers(getGroupsServerSize(entity.getGroupsName())+"");
            entity.setServices(getGroupsServiceSize(entity.getGroupsId()));
            list.add(entity);
        }
        return PageResponse.getList(list, paramEntity.getDraw(), result.getTotal());
    }
}
