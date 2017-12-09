package com.cloud.sms.docker.common.session;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author liusq23
 * @version 1.0
 * @since 1.0
 */
public class RedisSessionRequestValue extends ValveBase {
    public RedisSessionManager redisSessionManager;

    public RedisSessionRequestValue(RedisSessionManager redisSessionManager) {
        this.redisSessionManager = redisSessionManager;
    }

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException{

        try {
            getNext().invoke(request, response);
        }finally {
                redisSessionManager.afterRequest();
            }
    }
}
