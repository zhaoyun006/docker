package com.cloud.agent.scheduler;

import com.cloud.agent.thread.MonitorThread;
import com.cloud.agent.util.CheckUtil;
import com.cloud.agent.util.LogUtil;
import com.cloud.agent.util.RunCommandUtil;
import com.cloud.agent.util.docker.DockerCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaoyun on 2017/10/1. 10:00
 * 监控docker容器信息
 */
@RestController
@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
public class MonitorScheduler {

    private static String dockerCmd;

    /**
     * 获取执行监控的脚本
     */
    static void setDockerV(){
        if (dockerCmd != null){return;}
        String cmd =  DockerCmd.getDockerStatsV;
        String result = RunCommandUtil.runScript(cmd);
        String v13 = "13";
        String v19 = "19";
        if (CheckUtil.checkString(result)){
            if (v19.equals(result.trim())){
                dockerCmd = DockerCmd.getDockerStats1126;
                return;
            }
            if (v13.equals(result.trim())){
                dockerCmd = DockerCmd.getDockerStats17ce;
                return;
            }
            LogUtil.info("获取到docker版本异常,请自定义脚本执行方式,输出格式为 容器ID CPU使用率 内存使用率 网络INPUT 网络OUTPUT 块操作写 块操作读");
        }
    }


    /**
     * 监控容器状态信息
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public static void monitorData() {
        setDockerV();
        if (dockerCmd == null){return;}
        MonitorThread monitorThread = new MonitorThread(dockerCmd);
        monitorThread.start();
    }
}
