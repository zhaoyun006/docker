/**
 * @FileName: WebConfig.java
 * @Package: com.cloud.kaka.kaka.config
 * @author wurt2
 * @created 2017/5/17 11:08
 * <p>
 */
package com.cloud.sms.docker.common.config;

import com.cloud.sms.docker.common.interceptor.AccessLogInterceptor;
import com.cloud.sms.docker.common.interceptor.AllInterceptor;
import com.cloud.sms.docker.common.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



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
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public AccessLogInterceptor getAccessLogInterceptor() {
        return new AccessLogInterceptor();
    }

    @Bean
    public AllInterceptor getAllInterceptor() {
        return new AllInterceptor();
    }

    @Bean
    public TokenInterceptor getTokenInterceptor() {
        return new TokenInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAccessLogInterceptor()).excludePathPatterns("/error/**").addPathPatterns("/**");
        registry.addInterceptor(getAllInterceptor()).excludePathPatterns("/error/**").addPathPatterns("/**");
        registry.addInterceptor(getTokenInterceptor()).excludePathPatterns("/error/**").addPathPatterns("/**");
    }

}
