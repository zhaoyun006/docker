package com.cloud.sms.docker.common.session;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhaoyun on 2017/11/21.
 */
public interface RedisSessionClient {
    /**
     * Save the session to the given key.
     *
     * @param key
     * @param session
     */
    void save(String key, RedisSession session,int expires) ;

    /**
     * Load the session defined by the given key.
     *
     * @param key
     * @return the loaded {@link RedisSession} or <code>null</code> if no such key exists
     */
    RedisSession load(String key);

    /**
     * Delete the session defined by the given key.
     *
     * @param key
     */
    void delete(String key);

    /**
     * Update the expiration time for the session defined by the given key.
     *
     * @param key
     * @param expirationTime
     * @param timeUnit
     */
    void expire(String key, long expirationTime, TimeUnit timeUnit);

    /**
     * Check if a session with the given key exists in redis.
     *
     * @param key
     * @return
     */
    boolean exists(String key);

    /**
     * Get the encoded size of the given object
     *
     * @param obj
     * @return
     */
    int getEncodedSize(Object obj);

    /**
     * Perform any tasks necessary when shutting down
     */
    void shutdown();
}
