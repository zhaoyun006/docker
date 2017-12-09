/**
 * @FileName: LdapConfg.java
 * @Package: com.cloud.kaka.common.config
 * @author wurt2
 * @created 2017/5/25 10:38
 * <p>
 *
 */
package com.cloud.sms.docker.common.config;

import com.cloud.sms.docker.common.entity.LdapEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

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
@Configuration
@PropertySource("system.properties")
public class LdapBootConfig {

    @Value("${ldap.server}")
    private String ldapServer;

    @Value("${ldap.port}")
    private int ldapPort;

    @Value("${ldap.searchBase}")
    private String ldapSearchBase;

    @Value("${ldap.password}")
    private String ldapPassword;

    @Value("${ldap.prefix}")
    private String ldapPrefix;

    @Value("${ldap.username}")
    private String ldapUsername;

    @Bean(name = "ldapConfig")
    public LdapEntity getLdapConfig() {
        LdapEntity entity = new LdapEntity();
        entity.setLdapSearchBase(ldapSearchBase);
        entity.setLdapServer(ldapServer);
        entity.setLdapPort(ldapPort);
        entity.setLdapPassword(ldapPassword);
        entity.setLdapPrefix(ldapPrefix);
        entity.setLdapUsername(ldapUsername);
        return entity;
    }
}
