package com.cloud.sms.docker.images.controller;

import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchMap;
import com.asura.framework.dao.mybatis.paginator.domain.PageBounds;
import com.cloud.sms.docker.common.response.PageResponse;
import com.cloud.sms.docker.common.response.ResponseVo;
import com.cloud.sms.docker.images.entity.DockerCloudImagesBuildTemplateEntity;
import com.cloud.sms.docker.images.entity.DockerCloudImagesEntity;
import com.cloud.sms.docker.images.entity.DockerCloudRegistryServerEntity;
import com.cloud.sms.docker.images.service.DockerCloudImagesBuildTemplateService;
import com.cloud.sms.docker.log.controller.LogController;
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

/**
 * Created by zhaoyun on 2017/10/18.
 * @author zhaoyun
 */
@Controller
@RequestMapping("/images/templates/")
public class ImagesTemplateController {

    @Autowired
    private DockerCloudImagesBuildTemplateService templateService;

    @Autowired
    private PermissionsCheck permissionsCheck;

    @Autowired
    private LogController logController;

    /**
     * 镜像模板列表
     * @return
     */
    @RequestMapping("list")
    public String list(){
        return "/images/templates/list";
    }


    /**
     * 镜像模板添加
     * @param templateId
     * @param modelMap
     * @return
     */
    @RequestMapping("add")
    public String add(int templateId, ModelMap modelMap){
        if(templateId > 0){
            modelMap.addAttribute("configs", templateService.findById(templateId, DockerCloudImagesBuildTemplateEntity.class));
        }else{
            modelMap.addAttribute("configs", new DockerCloudImagesBuildTemplateEntity());
        }
        return "/images/templates/add";
    }

    /**
     * 镜像保存
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResponseVo save(DockerCloudImagesBuildTemplateEntity entity, HttpServletRequest request){
        String user = permissionsCheck.getLoginUser(request.getSession());
        if (null != entity.getTemplateId() && entity.getTemplateId() > 0){
            DockerCloudImagesBuildTemplateEntity templateEntity = templateService.findById(entity.getTemplateId(), DockerCloudImagesBuildTemplateEntity.class);
            if (!user.equals(templateEntity.getCreateUser())){
                return ResponseVo.responseError("不能修改别人的模板");
            }
            templateService.update(entity);
        }else {
            entity.setCreateTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
            entity.setCreateUser(user);
            templateService.save(entity);
        }
        logController.saveOperLog(request, "添加镜像模板" + new Gson().toJson(entity));
        return ResponseVo.responseOk("添加镜像模板成功");
    }


    /**
     *
     * @param draw
     * @param start
     * @param length
     * @param request
     * @param groups
     * @param isTmplate
     * @return
     */
    @RequestMapping(value = "data", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String data(int draw, int start, int length, HttpServletRequest request, String groups, String isTmplate) {
        String user = permissionsCheck.getLoginUser(request.getSession());
        SearchMap searchMap = new SearchMap();
        searchMap.put("user", user);
        PageBounds pageBounds = PageResponse.getPageBounds(length, start);
        String sqlId = "selectByAll";
        if (CheckUtil.checkString(isTmplate)){
            sqlId = "selectTemplate";
        }
        PagingResult<DockerCloudImagesBuildTemplateEntity> result = templateService.findAll(searchMap, pageBounds, sqlId);
        return PageResponse.getMap(result, draw);
    }


    /**
     *
     * @return
     */
    @RequestMapping("delete/{templateId}")
    @ResponseBody
    public ResponseVo delete(@PathVariable int templateId, HttpServletRequest request){
        DockerCloudImagesBuildTemplateEntity templateEntity = templateService.findById(templateId, DockerCloudImagesBuildTemplateEntity.class);
        String user = permissionsCheck.getLoginUser(request.getSession());
        if (!user.equals(templateEntity.getCreateUser())){
            return ResponseVo.responseError("不能删除其他人的镜像");
        }
        templateService.delete(templateEntity);
        logController.saveOperLog(request, "删除仓库配置" + new Gson().toJson(templateEntity));
        return ResponseVo.responseOk("ok");
    }

}
