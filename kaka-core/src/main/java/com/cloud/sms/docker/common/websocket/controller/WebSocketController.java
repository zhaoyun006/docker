package com.cloud.sms.docker.common.websocket.controller;

import com.cloud.configure.RedisKey;
import com.cloud.sms.docker.common.websocket.entity.OperQueueEntity;
import com.cloud.sms.docker.log.controller.LogController;
import com.cloud.sms.docker.log.entity.DockerCloudLogEntity;
import com.cloud.sms.docker.util.CheckUtil;
import com.cloud.sms.docker.util.DateUtil;
import com.cloud.sms.docker.util.PermissionsCheck;
import com.cloud.sms.docker.util.RedisUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;

/**
 * Created by zhaoyun on 2017/10/4.
 */
@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private LogController logController;

    private static Long sendTimer;

    private Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    /**
     * @param message
     * @return
     * @throws Exception
     */
    @MessageMapping("/send")
    @SendTo("/topic/send")
    public OperQueueEntity send(OperQueueEntity message) throws Exception {
        message.ping = message.getPing();
        return message;
    }

    /**
     * 每1秒检查一次redis，并推送消息
     *
     * @return
     * @throws Exception
     */
    @Scheduled(fixedRate = 2000)
    @SendTo("/topic/callback")
    public String callback() throws Exception {
        if (sendTimer == null) {
            sendTimer = System.currentTimeMillis() / 1000;
        }
        if (System.currentTimeMillis() / 1000 - sendTimer < 2) {
            System.out.println("小于2秒，退出i");
            return "callback";
        }
        RedisUtil redisUtil = new RedisUtil();
        long len = redisUtil.llen(RedisKey.operQueue);
        logger.info("len " + len);
        if (len < 1) {
            return "callback";
        }
        logger.info("queue len" + len);
        for (int i = 0; i < len; i++) {
            String data = redisUtil.rpop(RedisKey.operQueue);
            DockerCloudLogEntity logEntity = new DockerCloudLogEntity();
            logger.info(data);
            OperQueueEntity operQueueEntity = new Gson().fromJson(data, OperQueueEntity.class);
            logEntity.setMessages(operQueueEntity.getMessages());
            try {
                logEntity.setUserName(operQueueEntity.getUser());
            }catch (Exception e){
                logEntity.setUserName("system");
            }
            logEntity.setTime(operQueueEntity.getMessages());
            logController.save(logEntity, null);
            Thread.sleep(400);
            messagingTemplate.convertAndSend("/topic/callback", data);
        }
        return "callback";
    }
}
