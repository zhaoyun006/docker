/**
 * @FileName: VeloctiyConfig.java
 * @Package: com.cloud.sms.boot.demo.config
 * @author wurt2
 * @created 2017/5/17 16:06
 * <p>
 */
package com.cloud.sms.docker.common.config;

import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author wurt2
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class ViewResolverConfig implements ErrorViewResolver {

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        switch (status) {
            case NOT_FOUND:
                return new ModelAndView("error/404");
            case BAD_REQUEST:
                return new ModelAndView("error/400");
            case FORBIDDEN:
                return new ModelAndView("error/403");
            case INTERNAL_SERVER_ERROR:
                return new ModelAndView("error/500");
            case SERVICE_UNAVAILABLE:
                return new ModelAndView("error/503");
            default:
                return new ModelAndView("error/error");
        }
    }
}
