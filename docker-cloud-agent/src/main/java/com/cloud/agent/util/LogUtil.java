package com.cloud.agent.util;

import com.cloud.agent.configure.Configure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.FileWriter;

/**
 * Created by zhaoyun on 2017/10/9.
 */
public class LogUtil {

    private static Logger logger = LoggerFactory.getLogger(LogUtil.class);
    private static boolean DEBUG = true;

    private String filePath;
    private FileWriter logFile;
    private String data;
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     *
     */
    @Scheduled(cron = "0/59 * * * * ?")
    void setDEBUG() {
        String trues ="true";
        String agentConf = "agent.conf";
        String debug = "debug";
        if (trues.equals(Configure.get(agentConf,debug).trim())) {
            DEBUG = true;
        }else{
            DEBUG = true;
        }
    }

    /**
     * 打印日志
     * @param messages
     */
    public static void info(String messages) {
        if (null == messages) {
            return;
        }
        if (DEBUG) {
            logger.info(messages);
        }
    }

    /**
     *
     * @param messages
     */
    public void compileLog(String messages){
        if (null == logFile) {
            try {
                logFile = new java.io.FileWriter(filePath, false);
            } catch (Exception e) {
            }
        }
        data = DateUtil.getDate(DateUtil.TIME_FORMAT).concat(" ").concat(user).concat(" ").concat(messages).concat("\n");
        try {
            logFile.write(data);
        } catch (Exception e) {
            try {
                logFile = new java.io.FileWriter(filePath, true);
                logFile.write(data);
            }catch (Exception e1){
                logger.error("写入日志失败", e1);
            }
        }
    }

    /**
     *
     */
    public void comlileLogClose(){
        if (null != logFile){
            try {
                logFile.close();
            }catch (Exception e){
            }
        }
    }
}
