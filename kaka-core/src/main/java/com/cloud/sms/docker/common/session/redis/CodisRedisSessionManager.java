package com.cloud.sms.docker.common.session.redis;

import com.cloud.sms.docker.common.autoconfig.RedisSessionProperties;
import com.cloud.sms.docker.common.session.RedisSessionClient;
import com.cloud.sms.docker.common.session.RedisSessionManager;
import com.cloud.sms.docker.util.CheckUtil;
import com.google.gson.Gson;
import io.netty.channel.epoll.Epoll;
import org.apache.catalina.LifecycleException;
import org.redisson.codec.SerializationCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

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
public class CodisRedisSessionManager extends RedisSessionManager{


    public static final int DEFAULT_DATABASE = 0;
    public static final String DEFAULT_PASSWORD = null;
    public static final int DEFAULT_TIMEOUT = 3000;
    public static final int DEFAULT_PING_TIMEOUT = 1000;
    public static final int DEFAULT_RETRY_ATTEMPTS = 20;
    public static final int DEFAULT_RETRY_INTERVAL = 1000;
    public static final String DEFAULT_ENDPOINT = "redis://localhost:6379";
    public static final int DEFAULT_CONN_POOL_SIZE = 100;

    protected int database = DEFAULT_DATABASE;
    protected String password = DEFAULT_PASSWORD;
    protected int timeout = DEFAULT_TIMEOUT;
    protected int pingTimeout = DEFAULT_PING_TIMEOUT;
    protected int retryAttempts = DEFAULT_RETRY_ATTEMPTS;
    protected int retryInterval = DEFAULT_RETRY_INTERVAL;
    private String endpoint = DEFAULT_ENDPOINT ;
    private int connectionPoolSize = DEFAULT_CONN_POOL_SIZE;

    @Autowired
    private RedisSessionProperties redisSessionProperties;

    public CodisRedisSessionManager(){
        if (DEFAULT_ENDPOINT != endpoint){
            return;
        }
        Resource resource ;
        Properties props ;
        resource = new ClassPathResource("/application.properties");
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);

            String endpoint1 = (String) props.get("cloud.tomcat.session.address");
            endpoint = endpoint1.trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws LifecycleException
     */
    @Override
    protected RedisSessionClient buildClient() throws ClassNotFoundException, InstantiationException, IllegalAccessException, LifecycleException {
        Config config = new Config();
        config.useSingleServer().setAddress(getEndpoint())
                .setDatabase(getDatabase())
                .setConnectionPoolSize(getConnectionPoolSize())
                .setTimeout(getTimeout())
                .setPingTimeout(getPingTimeout())
                .setPassword(getPassword())
                .setRetryAttempts(getRetryAttempts())
                .setRetryInterval(getRetryInterval());
        config.setCodec(new SerializationCodec(getContainerClassLoader()))
                .setUseLinuxNativeEpoll(isEpollSupported());
        return new RedissonSessionClient(config);
    }


    /**
     * Determine if native Epoll for netty is available
     * @return
     */
    protected boolean isEpollSupported() {
        final boolean available = Epoll.isAvailable();
        if (available) {
            log.info("Using native epoll");
        }
        return available;
    }

    @Override
    public ClassLoader getContainerClassLoader() {
        return getContext().getLoader().getClassLoader();
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getPingTimeout() {
        return pingTimeout;
    }

    public void setPingTimeout(int pingTimeout) {
        this.pingTimeout = pingTimeout;
    }

    public int getRetryAttempts() {
        return retryAttempts;
    }

    public void setRetryAttempts(int retryAttempts) {
        this.retryAttempts = retryAttempts;
    }

    public int getRetryInterval() {
        return retryInterval;
    }

    public void setRetryInterval(int retryInterval) {
        this.retryInterval = retryInterval;
    }

    public String getEndpoint() {

        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public int getConnectionPoolSize() {
        return connectionPoolSize;
    }

    public void setConnectionPoolSize(int connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
    }
}
