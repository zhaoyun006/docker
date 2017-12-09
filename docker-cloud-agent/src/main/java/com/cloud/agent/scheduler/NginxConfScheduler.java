package com.cloud.agent.scheduler;

import com.cloud.agent.util.docker.MakeProxyUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import static com.cloud.agent.configure.Configure.getConf;

/**
 * Created by zhaoyun on 2017/11/17.
 * @author zhaoyun
 */
@RestController
@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
public class NginxConfScheduler {

    private boolean isNginxServer = false;

    private final String TRUE = "true";


    /**
     * 每10秒钟执行一次
     */
    @Scheduled(cron = "*/10 * * * * ?")
    void makeNginxConf() {
        if (isNginxServer) {
            MakeProxyUtil.setNginxConf();
        }
    }

    /**
     * 每59秒检查一次
     * 是否打开nginx配置
     */
    @Scheduled(cron = "*/59 * * * * ?")
    void setNginxServer() {
      String data = getConf("isNginxServer", 1, "false");
      if (TRUE.equals(data)){
          isNginxServer = true;
      }
    }
}