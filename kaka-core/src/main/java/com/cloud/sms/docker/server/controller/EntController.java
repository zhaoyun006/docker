package com.cloud.sms.docker.server.controller;

import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchMap;
import com.asura.framework.dao.mybatis.paginator.domain.PageBounds;
import com.cloud.sms.docker.common.response.PageResponse;
import com.cloud.sms.docker.common.response.ResponseVo;
import com.cloud.sms.docker.log.controller.LogController;
import com.cloud.sms.docker.network.controller.NetworkController;
import com.cloud.sms.docker.network.service.DockerCloudNetworkService;
import com.cloud.sms.docker.server.entity.DockerCloudEntEntity;
import com.cloud.sms.docker.server.entity.DockerCloudServerEntity;
import com.cloud.sms.docker.server.service.DockerCloudEntService;
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
import java.util.List;

/**
 * Created by zhaoyun on 2017/10/11.
 *
 * @author zhaoyun
 */
@Controller
@RequestMapping("/ent/")
public class EntController {

    @Autowired
    private PermissionsCheck permissionsCheck;

    @Autowired
    private DockerCloudEntService entService;

    @Autowired
    private DockerCloudServerService serverService;

    @Autowired
    private LogController logController;

    @Autowired
    private DockerCloudNetworkService networkService;

    /**
     * @param modelMap
     * @param entId
     * @return
     */
    @RequestMapping("add")
    public String add(ModelMap modelMap, int entId) {
        modelMap.addAttribute("configs", new DockerCloudEntEntity());
        if (entId > 0) {
            DockerCloudEntEntity entity = entService.findById(entId, DockerCloudEntEntity.class);
            modelMap.addAttribute("configs", entity);
        }
        SearchMap searchMap = new SearchMap();
        searchMap.put("entId", entId);
        modelMap.addAttribute("networks", networkService.getListData(searchMap, "selectEntNetwork"));
        return "/ent/add";
    }

    /**
     * @return
     */
    @RequestMapping("list")
    public String list() {
        return "/ent/list";
    }

    /**
     * @param entity
     * @param session
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResponseVo save(DockerCloudEntEntity entity, HttpServletRequest  request) {
        String user = permissionsCheck.getLoginUser(request.getSession());
        entity.setLastModifyUser(user);
        entity.setLastModifyTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
        if (entity.getEntId() != null && entity.getEntId() > 0) {
            entService.update(entity);
        } else {
            entity.setCreateUser(user);
            entity.setCreateTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
            entService.save(entity);
        }
        logController.saveOperLog(request, "保存环境信息"+ new Gson().toJson(entity));
        return ResponseVo.responseOk("添加环境成功");
    }

    /**
     * @param groupsName
     * @return
     */
    int getEntServerSize(String groupsName) {
        SearchMap searchMap = new SearchMap();
        searchMap.put("groupsName", groupsName);
        List<DockerCloudServerEntity> groups = serverService.getListData(searchMap, "selectByAll");
        return groups.size();
    }

    /**
     * 删除数据
     * @return
     */
    @RequestMapping(value = "delete/{entId}", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseVo delete(@PathVariable int entId, HttpServletRequest request) {
        DockerCloudEntEntity groupsEntity = entService.findById(entId, DockerCloudEntEntity.class);
        int size = getEntServerSize(groupsEntity.getEntname());
        if (size > 0) {
            return ResponseVo.responseError("该环境还有" + size + "个依赖没有删除!");
        }
        entService.delete(groupsEntity);
        logController.saveOperLog(request, "删除环境" + new Gson().toJson(groupsEntity));
        return ResponseVo.responseOk("ok");
    }


    /**
     * 注册服务器数据
     * @return
     */
    @RequestMapping(value = "data", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String data(int draw, int start, int length, HttpServletRequest request, String groups, String key, String ids) {

        PageBounds pageBounds = PageResponse.getPageBounds(length, start);
        SearchMap searchMap = new SearchMap();
        String search = request.getParameter("search[value]");
        if (CheckUtil.checkString(search)) {
            searchMap.put("key", search);
        }
        if (CheckUtil.checkString(key)) {
            searchMap.put("key", key);
        }
        if (CheckUtil.checkString(groups)) {
            searchMap.put("groups", groups);
        }
        if (CheckUtil.checkString(ids)) {
            searchMap.put("ids", ids.split(","));
        }
        PagingResult<DockerCloudEntEntity> result = entService.findAll(searchMap, pageBounds, "selectByAll");
        return PageResponse.getMap(result, draw);
    }
}
