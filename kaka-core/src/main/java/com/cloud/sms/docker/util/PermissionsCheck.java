package com.cloud.sms.docker.util;

import com.asura.framework.base.paging.SearchMap;
import com.google.gson.Gson;
import com.cloud.sms.docker.common.entity.LoginEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * @author zy
 * @Date 2016-06-26 检查用户权限工具
 */
@Component
public class PermissionsCheck {



    /**
     * @param session
     * @return
     */
    public String getRedisSession(HttpSession session) {
        String sessionId = session.getId();
        RedisUtil redisUtil = new RedisUtil();
        String result = redisUtil.get(sessionId);
        return result;
    }

    /**
     * @param session
     * @return 获取登陆的用户名
     */
    public String getLoginUser(HttpSession session) {
        String result = (String) session.getAttribute(session.getId());
        LoginEntity loginDao = new Gson().fromJson(result, LoginEntity.class);
        return loginDao.getUsername();
    }


    /**
     * 检查用户的权限
     *
     * @param username
     * @param url
     * @return
     */

    public boolean checkUserPermissions(String username, String url) {

        SearchMap map = new SearchMap();

        map.put("menuUrl", url);
        map.put("isActionIs", 1);

        return true;
    }

}
