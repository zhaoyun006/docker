/**
 * @FileName: Application.java
 * @Package: com.cloud.kaka
 * @author wurt2
 * @created 2017/5/25 10:33
 * <p>
 * Copyright 2015
 */
package com.cloud.sms.docker;


import com.cloud.sms.docker.common.autoconfig.RedisSessionProperties;
import com.cloud.sms.docker.common.session.redis.CodisRedisSessionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * <p></p>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author wurt2
 * @since 1.0
 * @version 1.0
 */
@SpringBootApplication
@EnableAsync
@EnableCaching
public class Application {



    /**
     * 入口main方法
     *
     * @param args
     */
    public static void main(String[] args) {
        // 启动spring boot
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public EmbeddedServletContainerFactory embeddedServletContainerFactory(){
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.addContextCustomizers((e)->
        {
            CodisRedisSessionManager codisRedisSessionManager = new CodisRedisSessionManager();
            e.setManager(codisRedisSessionManager);
            e.setDistributable(true);
        });
        return factory;
    }

}
