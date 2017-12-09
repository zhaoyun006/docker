/**
 * @FileName: DataSourceConfig.java
 * @Package: com.cloud.docker.common.config
 * @author wurt2
 * @created 2017/5/25 11:01
 * <p>
 * Copyright 2015
 */
package com.cloud.sms.docker.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.asura.framework.dao.mybatis.base.MybatisDaoContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

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
@PropertySource("jdbc.properties")
public class DataSourceConfig implements TransactionManagementConfigurer {


    @Autowired
    @Qualifier("master.datasource")
    private DataSource masterDataSource;

    @Autowired
    @Qualifier("slave.datasource")
    private DataSource slaveDataSource;

    @Autowired
    @Qualifier("docker.sqlSessionFactory.M")
    private SqlSessionFactory masterSqlSessionFactory;

    @Autowired
    @Qualifier("docker.sqlSessionFactory.S")
    private SqlSessionFactory slaveSqlSessionFactory;

    @Autowired
    @Qualifier("docker.sqlSessionTemplate.M")
    private SqlSessionTemplate writeSqlSessionTemplate;

    @Autowired
    @Qualifier("docker.sqlSessionTemplate.S")
    private SqlSessionTemplate readSqlSessionTemplate;


    @Bean(name = "master.datasource")
    @Primary
    @ConfigurationProperties(prefix = "datasource.master")
    public DataSource primaryDataSource() {
        DruidDataSource druidDataSource = (DruidDataSource) DataSourceBuilder.create().type(DruidDataSource.class).build();
        return druidDataSource;
    }

    @Bean(name = "slave.datasource")
    @ConfigurationProperties(prefix = "datasource.slave")
    public DataSource secondaryDataSource() {
        DruidDataSource druidDataSource = (DruidDataSource) DataSourceBuilder.create().type(DruidDataSource.class).build();
        return druidDataSource;
    }

    @Bean(name = "docker.sqlSessionFactory.M")
    public SqlSessionFactory masterSqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(masterDataSource);

        return getSqlSessionFactory(bean);
    }

    /**
     *
     * @return
     */
    @Bean(name = "docker.sqlSessionFactory.S")
    public SqlSessionFactory slaveSqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(slaveDataSource);

        return getSqlSessionFactory(bean);
    }

    /**
     *
     * @param bean
     * @return
     */
    private SqlSessionFactory getSqlSessionFactory(SqlSessionFactoryBean bean) {
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath:com/cloud/sms/docker/**/dao/map/*.xml"));
            bean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean(name = "docker.sqlSessionTemplate.M")
    public SqlSessionTemplate masterSqlSessionTemplate() {
        return new SqlSessionTemplate(masterSqlSessionFactory);
    }

    @Bean(name = "docker.sqlSessionTemplate.S")
    public SqlSessionTemplate slaveSqlSessionTemplate() {
        return new SqlSessionTemplate(slaveSqlSessionFactory);
    }


    @Bean(name = "docker.MybatisDaoContext")
    public MybatisDaoContext createMybatisDaoContext() {
        MybatisDaoContext mybatisDaoContext = new MybatisDaoContext();
        mybatisDaoContext.setReadSqlSessionTemplate(readSqlSessionTemplate);
        mybatisDaoContext.setWriteSqlSessionTemplate(writeSqlSessionTemplate);
        return mybatisDaoContext;
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate DynamicSqlSessionTemplate() {
        return new SqlSessionTemplate(masterSqlSessionFactory);
    }
    
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource);
    }
}
