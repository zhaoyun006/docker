package com.cloud.sms.docker.common.session.redis;

import com.cloud.sms.docker.common.session.RedisSession;
import com.cloud.sms.docker.common.session.RedisSessionClient;
import org.apache.catalina.LifecycleException;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
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
public class RedissonSessionClient implements RedisSessionClient {
    private RedissonClient redisClient;

    /**
     * 创建 redisson client
     *
     * @param config
     */
    public RedissonSessionClient(Config config) throws LifecycleException {
        try {
            redisClient = Redisson.create(config);
        } catch (Exception e) {
            throw new LifecycleException(e);
        }
    }

    @Override
    public void save(String key, RedisSession session, int expires) {
        redisClient.getBucket(key).set(session, expires, TimeUnit.MINUTES);
    }

    @Override
    public RedisSession load(String key) {
        Object object = redisClient.getBucket(key).get();
        //判断是否
        if (object!=null && object.getClass().isAssignableFrom(RedisSession.class)) {
            return (RedisSession) object;
        }
        return null;
    }

    @Override
    public void delete(String key) {
        redisClient.getBucket(key).delete();
    }

    @Override
    public void expire(String key, long expirationTime, TimeUnit timeUnit) {
        redisClient.getBucket(key).expire(expirationTime, TimeUnit.MINUTES);
    }

    @Override
    public boolean exists(String key) {
        return redisClient.getBucket(key).isExists();
    }

    @Override
    public int getEncodedSize(Object obj) {
        try {
            return redisClient.getConfig().getCodec().getValueEncoder().encode(obj).capacity();
        }catch (IOException e){
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void shutdown() {
        redisClient.shutdown();
    }

}
