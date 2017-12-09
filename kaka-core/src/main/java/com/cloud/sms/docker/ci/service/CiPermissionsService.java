package com.cloud.sms.docker.ci.service;

import com.asura.framework.base.paging.SearchMap;
import com.cloud.sms.docker.app.entity.DockerContainerEntity;
import com.cloud.sms.docker.authority.entity.DockerCloudAuthorityUserEntity;
import com.cloud.sms.docker.authority.service.DockerCloudAuthorityUserService;
import com.cloud.sms.docker.ci.entity.DockerCloudCiEnvEntity;
import com.cloud.sms.docker.ci.entity.DockerCloudCiEnvPermissionsEntity;
import com.cloud.sms.docker.ci.entity.DockerCloudCiEnvPermissionsGroupsEntity;
import com.cloud.sms.docker.util.CheckUtil;
import com.cloud.sms.docker.util.PermissionsCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyun on 2017/10/25.
 * @author zhaoyun
 */
@Controller
@Service
public class CiPermissionsService {

    @Autowired
    private DockerCloudCiEnvPermissionsGroupsService permissionsGroupsService;

    @Autowired
    private DockerCloudCiEnvPermissionsService permissionsService;

    @Autowired
    private PermissionsCheck permissionsCheck;

    @Autowired
    private DockerCloudCiEnvService envService;

    @Autowired
    private DockerCloudAuthorityUserService authorityUserService;

    /**
     *
     * @param username
     * @param userId
     * @return
     */
    boolean getUserPermissions(String username, String userId){
        String[] users = username.split(",");
        for (int i=0;i<users.length;i++){
            if (users[i].equals(userId)){
                return true;
            }
        }
        return false;
    }

    /**
     * 获取用户ID
     * @param user
     */
    public  String getUserId(String user){
        SearchMap searchMap = new SearchMap();
        searchMap.put("userName", user);
        List<DockerCloudAuthorityUserEntity> authorityUserEntities =  authorityUserService.getListData(searchMap, "selectByAll");
        DockerCloudAuthorityUserEntity dockerCloudAuthorityUserEntity = authorityUserEntities.get(0);
        return dockerCloudAuthorityUserEntity.getUserId()+"";
    }

    /**
     * 获取用户有权限的组
     */
    ArrayList getUserGroups(String userId){
        ArrayList arrayList = new ArrayList();
        SearchMap searchMap = new SearchMap();
        searchMap.put("username", userId);
        List<DockerCloudCiEnvPermissionsGroupsEntity> groups = permissionsGroupsService.getListData(searchMap, "selectUserGroups");
        for (DockerCloudCiEnvPermissionsGroupsEntity envPermissionsGroupsEntity:groups){
            arrayList.add(envPermissionsGroupsEntity.getGroupsId());
        }
        return arrayList;
    }

    /**
     * 获取用户有权限的环境信息
     * @param user
     * @return
     */
    public ArrayList getUeerEnv(String user){
        String userId = getUserId(user);
        ArrayList groupsId = getUserGroups(userId);
        SearchMap searchMap = new SearchMap();
        searchMap.put("userId", userId);
        searchMap.put("groupsId", groupsId);
        searchMap.put("createUser", user);
        ArrayList arrayList = new ArrayList();
        List<DockerCloudCiEnvPermissionsEntity> envs = permissionsService.getListData(searchMap, "selectEnvId");
        for (DockerCloudCiEnvPermissionsEntity envEntity:envs){
            arrayList.add(envEntity.getEnvId());
        }
        return arrayList;
    }

    /**
     * 环境权限检查
     * @param envId
     * @param session
     * @return
     */
    public boolean checkPermissions(int envId, HttpSession session){
        String user = permissionsCheck.getLoginUser(session);
        String isVerify = "1";
        DockerCloudCiEnvEntity dockerCloudCiEnvEntity =  envService.findById(envId, DockerCloudCiEnvEntity.class);
        if (!CheckUtil.checkString(dockerCloudCiEnvEntity.getApprove()) || !isVerify.equals(dockerCloudCiEnvEntity.getApprove())){
            return false;
        }
        if (user.equals(dockerCloudCiEnvEntity.getCreateUser())){
            return true;
        }

        SearchMap searchMap = new SearchMap();
        searchMap.put("envId", envId);
        List<DockerCloudCiEnvPermissionsEntity> data = permissionsService.getListData(searchMap, "selectByAll");
        if (data.size() < 1){
            return false;
        }

        String userId = getUserId(user);
        // 检查用户权限
        DockerCloudCiEnvPermissionsEntity envPermissionsEntity = data.get(0);
        if (getUserPermissions(envPermissionsEntity.getUsername(), user)){
            return true;
        }

        // 检查组权限
        DockerCloudCiEnvPermissionsGroupsEntity dockerCloudCiEnvPermissionsGroupsEntity;
        String[] groups = envPermissionsEntity.getGroupsName().split(",");
        for (int i=0;i<groups.length;i++){
            dockerCloudCiEnvPermissionsGroupsEntity =  permissionsGroupsService.findById(Integer.valueOf(groups[i]), DockerCloudCiEnvPermissionsGroupsEntity.class);
            if (getUserPermissions(dockerCloudCiEnvPermissionsGroupsEntity.getUsername(), userId)){
                return true;
            }
        }
        return false;
    }
}
