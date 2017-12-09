package com.cloud.agent.thread;

import com.cloud.agent.configure.RedisKey;
import com.cloud.agent.entity.ImagesBuildInfoEntity;
import com.cloud.agent.service.CompileService;
import com.cloud.agent.util.*;
import com.cloud.agent.util.docker.DockerApi;
import com.google.gson.Gson;


import java.io.File;

import static com.cloud.agent.util.RunCommandUtil.getSecurityCmd;

/**
 * Created by zhaoyun on 2017/10/25.
 *
 * @author zhaoyun
 */
public class BuildImageThread extends Thread {

    private LogUtil logUtil;
    private ImagesBuildInfoEntity entity;
    private CompileService compileService;
    private String buildScript;
    private StringBuilder command;
    private String fileName;
    private String lock;
    private String buildLog;

    public BuildImageThread(LogUtil logUtil, ImagesBuildInfoEntity entity, CompileService compileService, String buildScript, StringBuilder command, String fileName, String buildLog) {
        this.logUtil = logUtil;
        this.entity = entity;
        this.compileService = compileService;
        this.buildScript = buildScript;
        this.command = command;
        this.fileName = fileName;
        this.lock = "/dev/shm/".concat(entity.getUser()) + entity.getEnvId() + ".lock";
        this.buildLog = buildLog;
        this.setName("BuildImageThread");
    }

    /**
     * @param entity
     * @return
     */
    static String getImageTag(ImagesBuildInfoEntity entity) {
        StringBuilder command = new StringBuilder();
        command.append(getSecurityCmd(entity.getRegistry()));
        command.append("/");
        command.append(getSecurityCmd(entity.getServiceName()));
        command.append(":");
        if (CheckUtil.checkString(entity.getVersion())) {
            command.append(getSecurityCmd(entity.getVersion()));
        } else {
            command.append("latest");
        }
        return command.toString();
    }

    /**
     * @param command
     * @param fileName
     * @param entity
     * @param lock
     * @param logFile
     * @return
     */
    static StringBuilder getBuildCmd(StringBuilder command, String fileName, ImagesBuildInfoEntity entity, String lock, String logFile) {
        FileIoUtil.writeFile(fileName, entity.getDockerFile(), false);
        command.append("cd /dev/shm ; touch ".concat(lock));
        command.append(";docker build --tag ");
        command.append(getImageTag(entity));
        command.append(" --file ".concat(fileName));
        command.append(" ");
        command.append(" . ");
        command.append(" &>> ".concat(logFile));

        return command;
    }

    void setQueue(){
        // 自动编译将编译结果写入到队列去
        if (compileService.isAutoCompile()){
            RedisUtil redisUtil = new RedisUtil();
            String log = FileIoUtil.readFile(logUtil.getFilePath());
            if (log.contains("Successfully tagged") && log.contains("Successfully built")) {
                entity.setBuildStatus("1");
            }else{
                entity.setBuildStatus("0");
            }
            entity.setLog(log);
            redisUtil.lpush(RedisKey.autoCompileCompleteQueue, new Gson().toJson(entity));
            // 完成后删除锁
            redisUtil.del(RedisKey.autoCompileLock.concat(entity.getServiceName()));
        }
    }


    void writeFaildInfo(){
        logUtil.compileLog("退出构建");
        logUtil.compileLog("结束本次构建");
        logUtil.comlileLogClose();
        File file = new File(lock);
        // 删除镜像编译的锁文件
        if (file.exists()){
            file.delete();
        }
        setQueue();
        super.interrupt();
    }

    @Override
    public void run() {
        String buildTp1 = "1";
        String svn = "svn";
        String git = "git";
        String buildTp2 = "2";
        String auto = "auto";
        String custom = "custom";
        logUtil.compileLog("构建服务器为:"+ HttpClientIpAddress.getHostname());
        logUtil.compileLog("开始执行构建程序");
        if (buildTp1.equals(entity.getBuildTp())) {
            if (CheckUtil.checkString(entity.getBuildScript())) {
                FileIoUtil.writeFile(buildScript, entity.getBuildScript(), false);
                FileIoUtil.setFileExec(buildScript);
            }

            if (git.equals(entity.getCodeTp())) {
                logUtil.compileLog("获取到代码获取方式为 git");
                logUtil.compileLog("获取到版本分支为" + entity.getGitBranch());
                compileService.gitCheckout();
            }
            logUtil.comlileLogClose();
            if (svn.equals(entity.getCodeTp())) {
                logUtil.compileLog("获取到代码获方式为 svn");
                logUtil.compileLog("获取到版本为" + entity.getSvnVersion());
                compileService.svnCheckout();
            }
            logUtil.compileLog("获取代码完成");
            if (compileService.isFaild()){
                writeFaildInfo();
                return;
            }
            if (custom.equals(entity.getDockerFileSource())) {
                String filePath = entity.getDockerFilePath();
                logUtil.compileLog("使用自定义Dockerfile");
                boolean dockerFileExists = new File(compileService.getFilePath().concat(filePath)).exists();
                if (!dockerFileExists){
                    compileService.setFaild(true);
                    logUtil.compileLog("自定义Dockerfile路径不存在,退出构建".concat(filePath));
                }else{
                    entity.setDockerFile(FileIoUtil.readFile(filePath));
                }
            }
            if (auto.equals(entity.getDockerFileSource())) {
                logUtil.compileLog("使用模板Dockerfile");
            }
            if (!CheckUtil.checkString(entity.getDockerFile())) {
                logUtil.compileLog("本次编译失败,没有有效的Dockerfile内容");
                compileService.setFaild(true);
            }
            if (compileService.isFaild()){
                writeFaildInfo();
                return;
            }
            logUtil.compileLog("\n".concat(entity.getDockerFile()));

            logUtil.compileLog("开始执行Dockerfile");
            logUtil.compileLog("执行结果:");
            logUtil.comlileLogClose();
            try {
                command = getBuildCmd(command, fileName, entity, lock, buildLog);
                compileService.buildImages(command.toString(), buildLog);
                if (compileService.isFaild()){
                    writeFaildInfo();
                    return;
                }
                compileService.push(getImageTag(entity));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (buildTp2.equals(entity.getBuildTp())) {
            logUtil.compileLog("通过Dockerfile构建镜像");
            logUtil.compileLog("获取到Dockerfile内容为");
            logUtil.compileLog(entity.getDockerFile());
            command = getBuildCmd(command, fileName, entity, lock, buildLog);
            compileService.buildImages(command.toString(), buildLog);
            compileService.push(getImageTag(entity));
        }
        setQueue();
        DockerApi.queue("编译镜像完成 "+entity.getServiceName(), entity.getUser(), "");
        this.interrupt();
    }
}
