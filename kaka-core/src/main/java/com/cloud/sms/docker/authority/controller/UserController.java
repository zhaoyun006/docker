package com.cloud.sms.docker.authority.controller;

import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchMap;
import com.asura.framework.dao.mybatis.paginator.domain.PageBounds;
import com.cloud.sms.docker.authority.entity.DockerCloudAuthorityUserEntity;
import com.cloud.sms.docker.authority.service.DockerCloudAuthorityUserService;
import com.cloud.sms.docker.ci.entity.DockerCloudCiEnvPermissionsEntity;
import com.cloud.sms.docker.common.response.PageResponse;
import com.cloud.sms.docker.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhaoyun on 2017/10/23.
 * @author zhaoyun
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private DockerCloudAuthorityUserService userService;

    /**
     * 获取登录的用户
     * @return
     */
    @RequestMapping(value = "data", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String data(String key, String id) {
        SearchMap searchMap = new SearchMap();
        if (CheckUtil.checkString(key)){
            searchMap.put("key", key);
        }
        String defS = "0";
        if (CheckUtil.checkString(id) &&  !defS.equals(id)){
            String[] ids = id.split(",");
            searchMap.put("ids",ids);
        }
        List<DockerCloudAuthorityUserEntity> result = userService.getListData(searchMap,"selectLoginUser");
        return PageResponse.getList(result, 1,result.size());
    }

}
