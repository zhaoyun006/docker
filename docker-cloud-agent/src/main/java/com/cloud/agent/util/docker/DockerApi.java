package com.cloud.agent.util.docker;

import com.cloud.agent.configure.Configure;
import com.cloud.agent.configure.RedisKey;
import com.cloud.agent.scheduler.DockerScheduler;
import com.cloud.agent.util.CheckUtil;
import com.cloud.agent.util.DateUtil;
import com.cloud.agent.util.HttpUtil;
import com.cloud.agent.util.RedisUtil;
import com.cloud.agent.util.entity.OperQueueEntity;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by zhaoyun on 2017/9/25.
 */
public class DockerApi {

    private static  Logger logger = LoggerFactory.getLogger(DockerApi.class);
    public static String port;

    static void setPort(){
        if (port == null) {
          String ports =   Configure.get("dockerApiPort", "agent.conf");
          if (CheckUtil.checkString(port)){
              port = ports;
          }else{
              port = "2375";
          }
        }
    }

    /**
     * 获取docker提供的api数据
     * @param key
     * @return
     */
    public static String dockerInfo(String key){
        setPort();
        try {
            String result = HttpUtil.sendGet("http://127.0.0.1:"+port+"/" + key);
            return result;
        }catch (Exception e){
            return null;
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public static String dockerDelete(String key, String data){
        try {
            String result =  HttpUtil.httpPostJson("http://127.0.0.1:"+port+"/"+key, data, "DELETE");
            return result;
        }catch (Exception e){
            return null;
        }
    }


    /**
     *
     * @param key
     * @return
     */
    public static String dockerPost(String key, String data){
        try {
            String result =  HttpUtil.sendPost("http://127.0.0.1:"+port+"/"+key, data);
            return result;
        }catch (Exception e){
            return null;
        }
    }

    /**
     *
     * @param messages
     */
    public static void queue(String messages, String user, String result){
        OperQueueEntity queueEntity = new OperQueueEntity();
        queueEntity.setDate(DateUtil.getDate(DateUtil.TIME_FORMAT));
        queueEntity.setHost(DockerScheduler.ip);
        queueEntity.setMessages(messages.replace("\n","<br>")+"<br><br>");
        queueEntity.setUser(user);
        queueEntity.setResult(result);
        RedisUtil redisUtil = new RedisUtil();
        String data = new Gson().toJson(queueEntity);
        logger.info("写入队列:" + data);
        redisUtil.lpush(RedisKey.operQueue, data);
    }

}
