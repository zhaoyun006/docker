package com.cloud.sms.docker.common.autoconfig;

import com.cloud.sms.docker.common.session.RedisSessionManager;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>
 * spring tomcat session auto config
 * </p>
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
@ComponentScan
@Configuration
@EnableConfigurationProperties(RedisSessionProperties.class)
@AutoConfigureBefore(EmbeddedServletContainerAutoConfiguration.class)
@ConditionalOnClass({TomcatEmbeddedServletContainerFactory.class,RedissonClient.class})
@ConditionalOnMissingBean(RedisSessionManager.class)
public class SpringTomcatSessionAutoConfiguration {


}
