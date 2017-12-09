package com.cloud.agent.util;

import com.cloud.agent.configure.Configure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;


/**
 * @author zy
 * @Date 2016-06-21 redis工具
 */
@ComponentScan
public class RedisUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtil.class);
    public static String app;
    private static String redisPassword;
    private static String url ;
    private static int port;

    public RedisUtil(){
        if (url != null){
            return;
        }
        url = Configure.get("redis.server", "agent.conf");
        app = Configure.get("redis.app", "agent.conf");
        redisPassword = Configure.get("redis.password", "agent.conf");
        try {
            port = Integer.valueOf(Configure.get("redis.port", "agent.conf"));
        }catch (Exception e){
            LOGGER.info("使用默认redis端口 6379");
            port = 6379;
        }
        LOGGER.info("获取到redis 服务器 ".concat(url));
        LOGGER.info("获取到redis 密码".concat(redisPassword));
        LOGGER.info("获取到redis app ".concat(app));

    }


    /**
     * 获取单个链接
     * @return
     */
    public Jedis getJedis(){
        LOGGER.info("获取链接...");
        Jedis jedis = new Jedis(url, port);
        if (CheckUtil.checkString(redisPassword)){
            jedis.auth(redisPassword);
        }
        return jedis;
    }


    /**
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        Jedis jedis = getJedis();
        String r;
        try {
            LOGGER.info("set "+app + "_" + key + " "+ value);
            r = jedis.set(app + "_" + key, value);
            jedis.close();
        } catch (Exception e) {
            LOGGER.error("Redis SET 错误", e);
            r = "";
        }
        return r;
    }

    /**
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = getJedis();
        String r;
        LOGGER.info(url +" " + port);
        try {
            LOGGER.info("get "+ app + "_" + key);
            r = jedis.get(app + "_" + key);
            jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
            r = "";
            jedis.close();
        }
        return r;
    }

    /**
     * @param key
     * @return
     */
    public Long del(String key) {
        Jedis jedis = getJedis();
        long r;
        try {
            LOGGER.info("del "+ app + "_" + key);
            r = jedis.del(app + "_" + key);
            jedis.close();
        } catch (Exception e) {
            r = 0L;
            jedis.close();
        }
        return r;
    }

    /**
     * 先进先出
     * @param key
     * @return
     */
    public String  rpop(String key) {
        Jedis jedis = getJedis();
        String r;
        try {
            LOGGER.info("rpop "+ app + "_" + key);
            r = jedis.rpop(app + "_" + key);
            jedis.close();
        } catch (Exception e) {
            r = "";
            jedis.close();
        }
        return r;
    }

    /**
     * 获取队列长度
     * @param key
     * @return
     */
    public Long llen(String key){
        Jedis jedis = getJedis();
        Long r;
        try {
            LOGGER.info("rpop "+ app + "_" + key);
            r = jedis.llen(app + "_" + key);
            jedis.close();
        } catch (Exception e) {
            r = 0L;
            jedis.close();
        }
        return r;
    }

    /**
     * @param key
     * @return
     */
    public long lpush(String key, String value) {
        Jedis jedis = getJedis();
        long r;
        try {
            LOGGER.info("lpush "+ app + "_" + key + " value: "+ value);
            r = jedis.lpush(app + "_" + key, value);
            jedis.close();
        } catch (Exception e) {
            r = 0L;
            jedis.close();
        }
        return r;
    }

    /**
     * @param key
     * @param value
     * @return
     */
    public String setex(String key,int timeOut, String value) {
        Jedis jedis = getJedis();
        String r;
        try {
            LOGGER.info("setex "+app + "_" + key, value);
            r = jedis.setex(app + "_" + key,timeOut, value);
            jedis.close();
        } catch (Exception e) {
            r = "";
            jedis.close();
        }
        return r;
    }

  }
