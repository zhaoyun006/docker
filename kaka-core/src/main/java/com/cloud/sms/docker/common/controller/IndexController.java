package com.cloud.sms.docker.common.controller;

import com.asura.framework.base.paging.SearchMap;
import com.cloud.sms.docker.authority.entity.DockerCloudAuthorityUserEntity;
import com.cloud.sms.docker.authority.service.DockerCloudAuthorityUserService;
import com.cloud.sms.docker.base.constant.Const;
import com.cloud.sms.docker.ci.service.DockerCloudCiEnvService;
import com.cloud.sms.docker.ci.service.DockerCloudImagesBuildLogService;
import com.cloud.sms.docker.common.entity.LoginEntity;
import com.cloud.sms.docker.common.entity.UserAccount;
import com.cloud.sms.docker.common.response.ResponseVo;
import com.cloud.sms.docker.images.service.DockerCloudImagesBuildTemplateService;
import com.cloud.sms.docker.log.service.DockerCloudLogService;
import com.cloud.sms.docker.log.service.DockerCloudOperLogService;
import com.cloud.sms.docker.server.entity.DockerCloudServerEntity;
import com.cloud.sms.docker.server.service.DockerCloudServerService;
import com.cloud.sms.docker.service.controller.ServiceController;
import com.cloud.sms.docker.util.*;
import com.google.gson.Gson;
import com.novell.ldap.LDAPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @author zy 默认入口，登陆
 */
@Controller
public class IndexController {

    @Autowired
    private LdapAuthenticate ldapAuthenticate;

    @Autowired
    PermissionsCheck permissionsCheck;

    @Resource(name = "com.cloud.sms.docker.authority.service.DockerCloudAuthorityUserService")
    private DockerCloudAuthorityUserService userService;

    @Autowired
    private DockerCloudServerService serverService;


    @Autowired
    private ServiceController serviceController;

    @Autowired
    private DockerCloudOperLogService operLogService;

    @Autowired
    private DockerCloudLogService logService;

    @Autowired
    private DockerCloudCiEnvService ciEnvService;

    @Autowired
    private DockerCloudImagesBuildTemplateService templateService;

    @Autowired
    private DockerCloudImagesBuildLogService buildLogService;

    /*
     * 默认入口
     */
    @RequestMapping("")
    public String main() {
        return "forward:index.do";
    }

    /*
     * 默认入口
     */
    @RequestMapping("/index.do")
    public String index(ModelMap model, HttpSession session) {
        try {
            String username = permissionsCheck.getLoginUser(session);
            model.addAttribute("username", username);
        } catch (Exception e) {
            return "index/login";
        }
        return "index/index";
    }

    /**
     *
     * @param size
     * @return
     */
    String getLog(long size){
        String logSize = size+"";
        if (size > 10000){
            size = Long.valueOf(size / 10000);
            logSize = size + "万";
        }
        return logSize;
    }

    /*
    * 首页
   */
    @RequestMapping("/home")
    public String home(ModelMap modelMap) {
        List<DockerCloudServerEntity> serverServiceListData = serverService.getListData(new SearchMap(), "selectByAll");
        modelMap.addAttribute("hostNumber", serverServiceListData.size());
        modelMap.addAttribute("containerNumber", serviceController.getContainerTotle());
        SearchMap searchMap = new SearchMap();
        long size = operLogService.getListData(searchMap,"selectCounter").size();
        long containerLog = logService.getListData(searchMap,"selectCounter").size();
        long envSize = ciEnvService.getListData(searchMap,"selectCounter").size();
        long buildLog = buildLogService.getListData(searchMap, "selectCounter").size();
        modelMap.addAttribute("operationLog", getLog(size));
        modelMap.addAttribute("containerLog", getLog(containerLog));
        modelMap.addAttribute("envSize", envSize);
        modelMap.addAttribute("templateSize", templateService.getListData(searchMap, "selectCounter").size());
        modelMap.addAttribute("buildLog", getLog(buildLog));
        return "index/home";
    }

    /*
     * 登陆页面
     */
    @RequestMapping("/loginPage.do")
    public String loginPage() {
        return "index/login";
    }

    /**
     * @return
     */
    @RequestMapping("logout.do")
    public String logout(HttpSession session) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.del(session.getId());
        return "index/login";
    }

    /**
     * 获取当前登录用户信息
     *
     * @param session
     * @return
     */
    @RequestMapping("user.do")
    @ResponseBody
    public String user(HttpSession session) {
        System.out.println(session.getId());
        String result = (String) session.getAttribute(session.getId());
        if (CheckUtil.checkString(result)) {
            return new Gson().fromJson(result, LoginEntity.class).getUsername();
        }
        return result;
    }

    /*
     * 登陆验证数据
     */
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo login(@Valid UserAccount userAccount, BindingResult error, HttpServletRequest request) throws LDAPException {
        //验证错误
        RedisUtil redisUtil = new RedisUtil();
        //登录
        boolean loginFlag = ldapAuthenticate.connetLDAP(userAccount.getUserName(), userAccount.getPassword());
        String sessionId =request.getSession().getId();
        if (loginFlag) {
            LoginEntity loginEntity = new LoginEntity();
            loginEntity.setLoginIp(request.getRemoteAddr());
            loginEntity.setUsername(userAccount.getUserName());
            loginEntity.setLoginTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
            SearchMap searchMap = new SearchMap();
            searchMap.put("userName", userAccount.getUserName());
            List<DockerCloudAuthorityUserEntity> dockerCloudAuthorityUserEntities  = userService.getListData(searchMap, "selectByAll");
            if (null == dockerCloudAuthorityUserEntities || dockerCloudAuthorityUserEntities.size() < 1) {
                //创建用户
                DockerCloudAuthorityUserEntity userEntity = new DockerCloudAuthorityUserEntity();
                userEntity.setUserName(userAccount.getUserName());
                userEntity.setIsDel((int) Const.NOTDELETE);
                userEntity.setIsValid((int) Const.VALID);
                userService.save(userEntity);
            }else {
                DockerCloudAuthorityUserEntity entity = dockerCloudAuthorityUserEntities.get(0);
                if (!ValidUtil.isValid(entity.getIsValid(), entity.getIsDel())) {
                    return ResponseVo.responseError("用户已经被禁用,请联系管理员!");
                }
            }
            request.getSession().setAttribute(sessionId, new Gson().toJson(loginEntity));
            return ResponseVo.responseOk("OK");
        }
        return ResponseVo.responseError("用户名或者密码错误");
    }

    /*
     * 发送没有权限的页面
     */
    @RequestMapping("/noPermissions.do")
    @ResponseBody
    public String noPermissions() {
        return "no permissions";
    }

}
