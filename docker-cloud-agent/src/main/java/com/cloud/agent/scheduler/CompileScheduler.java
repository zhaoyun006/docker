package com.cloud.agent.scheduler;

import com.cloud.agent.configure.RedisKey;
import com.cloud.agent.entity.ImagesBuildInfoEntity;
import com.cloud.agent.service.CompileService;
import com.cloud.agent.thread.BuildImageThread;
import com.cloud.agent.util.CheckUtil;
import com.cloud.agent.util.HttpClientIpAddress;
import com.cloud.agent.util.LogUtil;
import com.cloud.agent.util.RedisUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyun on 2017/10/26.
 *
 * @author zhaoyun
 */
@RestController
@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
public class CompileScheduler {

    private final String SVN = "svn";
    private final String GIT = "git";

    private Logger logger = LoggerFactory.getLogger(CompileScheduler.class);

    @Scheduled(cron = "0/50 * * * * ?")
    void autoCompile() {
        RedisUtil redisUtil = new RedisUtil();
        String data = redisUtil.get(RedisKey.autoCompile);
        if (CheckUtil.checkString(data)) {
            String lock;
            String lockKey;
            String codeTp;
            String logFile;
            String filePath;
            LogUtil logUtil;
            String fileName;
            String buildLog;
            String buildScript;
            String  newCommitId;
            String oldCommitId;
            Type type = new TypeToken<ArrayList<ImagesBuildInfoEntity>>() {
            }.getType();
            List<ImagesBuildInfoEntity> list = new Gson().fromJson(data, type);
            CompileService compileService = new CompileService();
            for (ImagesBuildInfoEntity imagesBuildInfoEntity : list) {
                lockKey = RedisKey.autoCompileLock.concat(imagesBuildInfoEntity.getServiceName());
                lock = redisUtil.get(lockKey);
                if (CheckUtil.checkString(lock)) {
                    continue;
                }
                fileName = "/dev/shm/" + imagesBuildInfoEntity.getUser() + imagesBuildInfoEntity.getEnvId();
                logFile = "/tmp/.".concat(imagesBuildInfoEntity.getUser()) + imagesBuildInfoEntity.getEnvId();
                buildLog = logFile.concat(".build");
                buildScript = fileName.concat(".build");
                logUtil = new LogUtil();
                logUtil.setFilePath(logFile);
                logUtil.setUser(imagesBuildInfoEntity.getUser());
                compileService.setLogUtil(logUtil);
                compileService.setImagesBuildInfoEntity(imagesBuildInfoEntity);
                redisUtil.setex(lockKey, 600, "1");
                codeTp = imagesBuildInfoEntity.getCodeTp();
                logger.info("获取到代码类型".concat(codeTp));
                if (GIT.equals(codeTp)) {
                    filePath = compileService.getFilePath().concat(".local");
                    File dir = new File(filePath);
                    logger.info("获取到代码路径".concat(filePath));
                    if (new File(filePath).exists()) {
                        compileService.updateGitCode(compileService.getCp(), dir);
                        boolean diff = compileService.diffRepository(compileService.getFilePath());
                        if (!diff) {
                            compileService.deleteGitCode(compileService.getFilePath());
                        }
                        oldCommitId = compileService.getCommitId(new File(compileService.getFilePath()));
                        newCommitId = compileService.getCommitId(new File(filePath));
                        if (oldCommitId.equals(newCommitId )){
                            redisUtil.del(lockKey);
                            return;
                        }
                        compileService.setAutoCompile(true);
                        imagesBuildInfoEntity.setVersion(compileService.makeTag(imagesBuildInfoEntity));
                        logUtil.compileLog("自动编译更新版本为:  ".concat(imagesBuildInfoEntity.getVersion()));
                        logUtil.compileLog("获取到老的版本信息为 ".concat(compileService.getRepository(compileService.getFilePath())));
                        logUtil.compileLog("获取到最新信息为 ".concat(compileService.getRepository(filePath)));
                        logUtil.compileLog("自动更新代码开始");
                        BuildImageThread buildImageThread = new BuildImageThread(logUtil, imagesBuildInfoEntity, compileService, buildScript, new StringBuilder(), "/dev/shm/" + imagesBuildInfoEntity.getUser() + imagesBuildInfoEntity.getEnvId(), buildLog);
                        buildImageThread.setDaemon(true);
                        buildImageThread.start();
                    } else {
                        logUtil.compileLog("初始化代码路径".concat(imagesBuildInfoEntity.getGitAddress()));
                        // 初始化
                        compileService.initGitCode(compileService.getCp(), filePath, imagesBuildInfoEntity.getGitAddress(), dir);
                    }
                    logUtil.comlileLogClose();
                }
                if (SVN.equals(codeTp)) {

                }

            }
        }
    }
}
