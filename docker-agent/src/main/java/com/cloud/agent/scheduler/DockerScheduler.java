package com.cloud.agent.scheduler;

import com.cloud.agent.configure.Configure;
import com.cloud.agent.util.RunCommandUtil;
import com.cloud.agent.util.docker.DockerCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.cloud.agent.util.RunCommandUtil.getSecurityCmd;

/**
 * <p></p>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author zhaozq
 * @version 1.0
 * @since 1.0
 */
@RestController
@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
public class DockerScheduler {

    private Logger logger = LoggerFactory.getLogger(DockerScheduler.class);

    String  runCmd(String cmd){
        String result = RunCommandUtil.runScript(cmd);
        if (Configure.get("debug","agent.conf").equals("true")) {
            logger.info("run scheduler command  " + cmd);
        }
        return result;
    }


    /**
     * 启动关掉的容器
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void startExitedContainer(){
        logger.info("启动关掉的容器检查");
        String  cmd = DockerCmd.getExitedContainer;
        String result = runCmd(cmd);
        String startPath = "/var/lib/docker/auto_start/";
        if ( result != null && result.length() > 11){
            for (String containerId: result.split("\n")){
                String startCid = startPath + getSecurityCmd(containerId);
                File file = new File(startCid);
                if (file.exists()) {
                    cmd = "sh " + startCid + ";";
                    runCmd(cmd);
                    cmd = "docker exec -ti "+containerId+" sh  /tmp/recover.sh ;echo 1";
                    logger.info("执行恢复脚本 " + cmd);
                    runCmd(cmd);
                    logger.info("启动docker " + startCid);
                }else{
                 //   cmd = DockerCmd.containerRemove.replace("{0}", containerId);
                  //  runCmd(cmd);
                    logger.info("删除无效docker " + startCid);
                }
            }
        }
    }

//    /**
//     * 删除无效的启动脚本
//     */
//    @Scheduled(cron = "0 */59 * * * ?")
//    public void removeInvalidStartScripts(){
//        String  cmd = DockerCmd.getValidContainer;
//        String result = runCmd(cmd);
//        List list = new ArrayList();
//        for (String containerId : result.split("\n")){
//            list.add(containerId);
//        }
//        if (list == null){
//            return;
//        }
//        cmd = DockerCmd.getStartScripts;
//        result = runCmd(cmd);
//        for (String containerId : result.split("\n")) {
//            if(!list.contains(containerId)){
//                //cmd = DockerCmd.removeInvalidStartScripts.replace("{0}", containerId);
//               // runCmd(cmd);
//            }
//        }
//    }
}
