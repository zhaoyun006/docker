package com.cloud.sms.docker.common.interceptor;

import com.cloud.sms.docker.util.CheckUtil;
import com.cloud.sms.docker.util.RedisUtil;
import com.google.gson.Gson;
import com.cloud.sms.docker.common.entity.LoginEntity;
import com.cloud.sms.docker.util.PermissionsCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.channels.Channel;
import java.util.Map;

/**
 * @author zy
 * @Date 2016-06-21 登陆拦截器
 */
@PropertySource("system.properties")
public class AllInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllInterceptor.class);

    @Autowired
    private PermissionsCheck permissionsCheck;

    private static String urls = "/login.do,/index.do,/loginPage.do,/user.do";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI().toString();
        String[] urlSplit = urls.split(",");
        for (int i = 0; i < urlSplit.length; i++) {
            String matchString = urlSplit[i];
            if (url.matches(".*" + matchString + ".*")) {
                return super.preHandle(request, response, handler);
            }
        }
        if (url.contains("/login.do")) {
            return true;
        }

        // 从redis获取session数据
        String sessionId = request.getSession().getId();
        try {

            if (url.contains("/login.do")) {
                return true;
            }
            String value = (String) request.getSession().getAttribute(sessionId);
//            RedisUtil redisUtil = new RedisUtil();
//            String value = redisUtil.get(sessionId);
            // 如果没有登录session，则重新登录
            if (value == null){
//            if (value == null) {
                response.sendRedirect(request.getContextPath() + "/loginPage.do");
                LOGGER.info("登录失败");
                return false;
            }
            LoginEntity loginDao = new Gson().fromJson(value, LoginEntity.class);

            // 检查权限
            if (!permissionsCheck.checkUserPermissions(loginDao.getUsername(), url)) {
                request.getRequestDispatcher("/error/403").forward(request, response);
                return false;
            }

            if (CheckUtil.checkString(loginDao.getUsername())) {
                return super.preHandle(request, response, handler);

            }
        } catch (Exception e) {
            LOGGER.error("find user failure : {}", e);
            response.sendRedirect(request.getContextPath() + "/loginPage.do");
            return false;
        }
        return super.preHandle(request, response, handler);
    }

}
