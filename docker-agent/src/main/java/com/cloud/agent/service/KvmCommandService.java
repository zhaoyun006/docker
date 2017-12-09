package com.cloud.agent.service;

import com.cloud.agent.configure.Configure;
import com.cloud.agent.entity.KvmCommandEntity;
import com.cloud.agent.util.kvm.KvmCmd;
import org.apache.log4j.Logger;

import static com.cloud.agent.util.RunCommandUtil.getSecurityCmd;
import static com.cloud.agent.util.RunCommandUtil.runScript;

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
 */

public class KvmCommandService {

    private static Logger logger = Logger.getLogger(KvmCommandService.class);

    /**
     * @param cmd
     *
     * @return
     */
    static String replaceVm(String cmd, KvmCommandEntity entity) {
        cmd = cmd.replace("{0}", getSecurityCmd(entity.getVmName()));
        return cmd;
    }

    /**
     * 运行脚本
     *
     * @return
     */
    public static String runCommand(String commands, KvmCommandEntity entity) {
        String command = "";
        switch (commands) {
            case "getRunningVm":
                command = KvmCmd.getRunningVm;
                break;
            case "cpuUsed":
                command = KvmCmd.cpuUsed;
                break;
            case "memoryUsed":
                command = KvmCmd.memoryUsed;
                break;
            case "diskUsed":
                command = KvmCmd.diskUsed;
                break;
            case "shutdownVm":
                command = replaceVm(KvmCmd.shutdownVm, entity);
                break;
            case "removeVm":
                command = replaceVm(KvmCmd.removeVm, entity);
                break;
            case "startVm":
                command = replaceVm(KvmCmd.startVm, entity);
                break;
            case "supendVm":
                command = replaceVm(KvmCmd.supendVm, entity);
                break;
            case "resumeVm":
                command = replaceVm(KvmCmd.resumeVm, entity);
                break;
            case "setCpu":
                command = replaceVm(KvmCmd.setCpu, entity);
                command = command.replace("{1}", getSecurityCmd(entity.getCpu()));
                break;
            case "setMemory":
                command = replaceVm(KvmCmd.setMemory, entity);
                command = command.replace("{1}", getSecurityCmd(entity.getMemory()));
                break;
            case "memoryUsedPercent":
                command = KvmCmd.memoryUsedPercent;
                break;
            case "cpuUsedPercent":
                command = KvmCmd.cpuUsedPercent;
                break;
            case "imagesExist":
                command = KvmCmd.imagesExist.replace("{0}", entity.getApiUrl());
                String[] url = entity.getApiUrl().split("/");
                String template = url[url.length-1];
                command = command.replace("{1}", getSecurityCmd(template));
                break;
            case "downloadImage":
                String[] url1 = entity.getApiUrl().split("/");
                String template1 = url1[url1.length-1];
                command = KvmCmd.downloadImage.replace("{0}", entity.getApiUrl());
                command = command.replace("{1}", getSecurityCmd(template1));
                break;
            case "getPassword":
                command = KvmCmd.getPassword.replace("{0}", getSecurityCmd(entity.getPassword()));
                break;
        }
        // debug是否打开
        if (Configure.get("debug", "agent.conf").equals("true")) {
            logger.info("run command  " + commands + " " + command);
        }
        return runScript(command);
    }
}
