package com.cloud.sms.docker.images.controller;

import com.asura.framework.base.paging.SearchMap;
import com.cloud.configure.RedisKey;
import com.cloud.sms.docker.ci.entity.DockerCloudCiEnvEntity;
import com.cloud.sms.docker.common.response.PageResponse;
import com.cloud.sms.docker.common.response.ResponseVo;
import com.cloud.sms.docker.images.entity.DockerCloudImagesEntity;
import com.cloud.sms.docker.images.entity.DockerCloudRegistryServerEntity;
import com.cloud.sms.docker.images.entity.ImagesEnttiy;
import com.cloud.sms.docker.images.service.DockerCloudImagesService;
import com.cloud.sms.docker.images.service.DockerCloudRegistryServerService;
import com.cloud.sms.docker.images.util.GetImagesUtil;
import com.cloud.sms.docker.log.controller.LogController;
import com.cloud.sms.docker.util.CheckUtil;
import com.cloud.sms.docker.util.DateUtil;
import com.cloud.sms.docker.util.PermissionsCheck;
import com.cloud.sms.docker.util.RedisUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyun on 2017/10/10.
 * @author zhaoyun
 */
@Controller
@RequestMapping("/images/")
public class ImagesController {

    @Autowired
    private DockerCloudRegistryServerService registryServerService;

    @Autowired
    private DockerCloudImagesService imagesService;

    @Autowired
    private PermissionsCheck permissionsCheck;

    @Autowired
    private LogController logController;

    /**
     * 镜像列表
     * @return
     */
    @RequestMapping("list")
    public String list(){
        return "/images/list";
    }

    /**
     * 镜像添加
     * @return
     */
    @RequestMapping("add")
    public String add(int imagesId, ModelMap modelMap){
        modelMap.addAttribute("configs", new DockerCloudImagesEntity());
        if(imagesId > 0){
            modelMap.addAttribute("configs", imagesService.findById(imagesId, DockerCloudImagesEntity.class));
        }
        return "/images/add";
    }

    /**
     * 镜像保存
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResponseVo save(DockerCloudImagesEntity entity, HttpServletRequest request){
        String user = permissionsCheck.getLoginUser(request.getSession());
        if (entity.getImagesId() != null && entity.getImagesId() > 0){
            imagesService.update(entity);
        }else {
            entity.setCreateTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
            entity.setCreateUser(user);
            imagesService.save(entity);
        }
        logController.saveOperLog(request, "添加镜像" + new Gson().toJson(entity));
        return ResponseVo.responseOk("添加镜像成功");
    }



    /**
     * 镜像数据
     * @return
     */
    @RequestMapping(value = "data", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String data(int draw, int start, int length, HttpServletRequest request, String groups) {
        ArrayList list = new ArrayList();
        ArrayList<String> tempList;
        Map<String, ArrayList> data;
        ImagesEnttiy imagesEnttiy;
        int size=0;
        String serverImages;
        Gson gson = new Gson();
        RedisUtil redisUtil = new RedisUtil();
        Type containerType;
        List<DockerCloudRegistryServerEntity> result = registryServerService.getListData(new SearchMap(), "selectByAll");
        for (DockerCloudRegistryServerEntity entity:result){
            data = GetImagesUtil.getImages(entity.getServerDomain());
            for(Map.Entry<String, ArrayList> map: data.entrySet()){
                tempList = map.getValue();
                for (String image:tempList){
                    imagesEnttiy = new ImagesEnttiy();
                    imagesEnttiy.setName(image);
                    imagesEnttiy.setRegistry(entity.getServerDomain());
                    imagesEnttiy.setTag("未知");
                    imagesEnttiy.setCreateTime("未知");
                    imagesEnttiy.setSize("未知");
                    imagesEnttiy.setFrom("仓库");
                    list.add(imagesEnttiy);
                    size += 1;
                }
            }
            serverImages= redisUtil.get(RedisKey.serverImages.concat(entity.getGroups()));
            if (CheckUtil.checkString(serverImages)){
                containerType = new TypeToken<ArrayList<ImagesEnttiy>>() {
                }.getType();
                ArrayList<ImagesEnttiy>  containerSet = gson.fromJson(serverImages, containerType);
                list.addAll(containerSet);
            }
        }
        return PageResponse.getList(list,draw, size);
    }

}
