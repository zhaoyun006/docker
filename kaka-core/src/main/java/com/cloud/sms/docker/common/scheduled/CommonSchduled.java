package com.cloud.sms.docker.common.scheduled;

import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchMap;
import com.cloud.configure.RedisKey;
import com.cloud.sms.docker.ci.entity.*;
import com.cloud.sms.docker.ci.service.DockerCloudCiAutoBuildService;
import com.cloud.sms.docker.ci.service.DockerCloudCiEnvService;
import com.cloud.sms.docker.ci.service.DockerCloudImagePullLogService;
import com.cloud.sms.docker.ci.service.DockerCloudImagesBuildLogService;
import com.cloud.sms.docker.common.response.PageResponse;
import com.cloud.sms.docker.images.entity.DockerCloudImageMergerRecordEntity;
import com.cloud.sms.docker.images.service.DockerCloudImageMergerRecordService;
import com.cloud.sms.docker.server.entity.DockerCloudServerEntity;
import com.cloud.sms.docker.server.service.DockerCloudServerService;
import com.cloud.sms.docker.util.CheckUtil;
import com.cloud.sms.docker.util.DateUtil;
import com.cloud.sms.docker.util.RedisUtil;
import com.cloud.util.HttpUtil;
import com.google.gson.Gson;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyun on 2017/10/18.
 *
 * @author zhaoyun
 */
@RestController
@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
public class CommonSchduled {


    @Autowired
    private DockerCloudServerService serverService;

    @Autowired
    private DockerCloudCiEnvService envService;

    @Autowired
    private DockerCloudImagesBuildLogService buildLogService;

    @Autowired
    private DockerCloudCiAutoBuildService autoBuildService;

    @Autowired
    private DockerCloudImagePullLogService pullLogService;

    @Autowired
    private DockerCloudImageMergerRecordService mergerRecordService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private Gson gson;

    /**
     * 设置
     * 1分钟一次
     */
    @Scheduled(fixedRate = 60000)
    void setAutoCompile() {
        ArrayList data = new ArrayList();
        ImagesBuildInfoEntity img;
        List<DockerCloudCiAutoBuildEntity> list = autoBuildService.getListData(new SearchMap(), "selectBuildParam");
        for (DockerCloudCiAutoBuildEntity entity : list) {
            img = gson.fromJson(entity.getBuildParam(), ImagesBuildInfoEntity.class);
            data.add(img);
        }
        redisUtil.set(RedisKey.autoCompile, gson.toJson(data));
    }

    /**
     * 获取镜像合并的信息
     * 11秒1次
     */
    @Scheduled(fixedRate = 11000)
    void getMergerImageQeueu() {
        Jedis jedis = redisUtil.getJedis();
        String app = RedisUtil.app + "_";
        String queueKey = app + RedisKey.imageMergerQueue;
        long len = jedis.llen(queueKey);
        if (len > 0) {
            Gson gson = new Gson();
            String data;
            DockerCloudImageMergerRecordEntity entity;
            for (int i = 0; i < len; i++) {
                data = jedis.lpop(queueKey);
                if (CheckUtil.checkString(data)) {
                    entity = gson.fromJson(data, DockerCloudImageMergerRecordEntity.class);
                    mergerRecordService.save(entity);
                }
            }
        }
        jedis.close();
    }

    /**
     * 获取镜像拉取信息写入到数据里面
     * 10秒1次
     */
    @Scheduled(fixedRate = 10000)
    void getPullImageQeueu() {
        Jedis jedis = redisUtil.getJedis();
        String app = RedisUtil.app + "_";
        String queueKey = app + RedisKey.imagePullQueue;
        long len = jedis.llen(queueKey);
        if (len > 0) {
            Gson gson = new Gson();
            String data;
            DockerCloudImagePullLogEntity entity;
            for (int i = 0; i < len; i++) {
                data = jedis.lpop(queueKey);
                if (CheckUtil.checkString(data)) {
                    entity = gson.fromJson(data, DockerCloudImagePullLogEntity.class);
                    pullLogService.save(entity);
                }
            }
        }
        jedis.close();
    }

    /**
     * 设置每个IP对应的集群名称
     * 20秒1次
     */
    @Scheduled(fixedRate = 20000)
    void setServerCluster() {
        Jedis jedis = redisUtil.getJedis();
        String app = RedisUtil.app + "_";
        PagingResult<DockerCloudServerEntity> result = serverService.findAll(new SearchMap(), PageResponse.getPageBounds(50000, 1), "selectIpGroup");
        for (DockerCloudServerEntity entity : result.getRows()) {
            try {
                jedis.set(app.concat(RedisKey.serverClueterName).concat(entity.getServerAddress()), entity.getGroups() + entity.getEntId());
                jedis.set(app.concat(RedisKey.serverClusterTp).concat(entity.getServerAddress()), entity.getCluterTp());
            } catch (Exception e) {
                jedis.close();
                jedis = redisUtil.getJedis();
            }
        }
        jedis.close();
    }

    /**
     * 通过redis队列设置通过自动更新的构建信息
     */
    @Scheduled(fixedRate = 20000)
    void setAutoCompileStatus() {
        long size = redisUtil.llen(RedisKey.autoCompileCompleteQueue);
        if (size < 1) {
            return;
        }
        String data;
        ImagesBuildInfoEntity imagesBuildInfoEntity;
        DockerCloudImagesBuildLogEntity logEntity;
        DockerCloudCiAutoBuildEntity dockerCloudCiAutoBuildEntity;
        for (int i = 0; i < size; i++) {
            data = redisUtil.rpop(RedisKey.autoCompileCompleteQueue);
            if (CheckUtil.checkString(data)) {
                imagesBuildInfoEntity = new Gson().fromJson(data, ImagesBuildInfoEntity.class);
                if (null != imagesBuildInfoEntity) {
                    logEntity = new DockerCloudImagesBuildLogEntity();
                    logEntity.setTag(imagesBuildInfoEntity.getVersion());
                    logEntity.setBuildParam(data);
                    logEntity.setEnvId(imagesBuildInfoEntity.getEnvId());
                    logEntity.setCreateUser(imagesBuildInfoEntity.getUser());
                    logEntity.setBuildStatus(imagesBuildInfoEntity.getBuildStatus());
                    logEntity.setMessages(imagesBuildInfoEntity.getLog());
                    logEntity.setBuildTp("自动编译");
                    logEntity.setCreateTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
                    buildLogService.save(logEntity);
                    dockerCloudCiAutoBuildEntity = new DockerCloudCiAutoBuildEntity();
                    dockerCloudCiAutoBuildEntity.setEnvId(imagesBuildInfoEntity.getEnvId());
                    dockerCloudCiAutoBuildEntity.setBuildParam(data);
                    dockerCloudCiAutoBuildEntity.setUpdateBuild(1);
                    dockerCloudCiAutoBuildEntity.setCreateTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
                    dockerCloudCiAutoBuildEntity.setCreteUser(imagesBuildInfoEntity.getUser());
                    autoBuildService.update(dockerCloudCiAutoBuildEntity);
                    setAutoCompile();
                }
            }
        }
    }


    /**
     * 后台更新编译状态
     * 9秒1次
     */
    @Scheduled(fixedRate = 9000)
    void setImagesBuildStatus() {
        // 如果redis没有在更新的状态就不再执行任务计划
        RedisUtil redisUtil = new RedisUtil();
        String status = redisUtil.get(RedisKey.imagesBuildStatus);
        if (!CheckUtil.checkString(status)) {
            return;
        }
        String log;
        String checkApi;
        String logApi;
        String buildStatus = "1";
        SearchMap searchMap = new SearchMap();
        DockerCloudImagesBuildLogEntity buildLogEntity;
        PagingResult<DockerCloudCiEnvEntity> result = envService.findAll(searchMap, PageResponse.getPageBounds(2000, 1), "selectBuildStart");
        if (1 > result.getTotal()) {
            return;
        }
        String param;
        String checkResult;
        for (DockerCloudCiEnvEntity envEntity : result.getRows()) {
            if (buildStatus.equals(envEntity.getBuildStatus())) {
                searchMap.put("envId", envEntity.getEnvId());
                searchMap.put("createUser", envEntity.getCreateUser());
                buildLogEntity = buildLogService.getBuildLog(searchMap, "selectServerApi");
                logApi = buildLogEntity.getBuildServerApi().replace("build", "log");
                checkApi = buildLogEntity.getBuildServerApi().replace("build", "check");
                param = "param=".concat(buildLogEntity.getBuildParam());
                // 编译完成后更新日志和更新编译状态
                checkResult = HttpUtil.sendPost(checkApi, param);
                log = HttpUtil.sendPost(logApi, param);
                Map<String, String> map = gson.fromJson(log, HashMap.class);
                buildLogEntity.setMessages(map.get("log"));
                if (checkResult.contains("ok") && log.contains("结束本次构建")) {
                    if (log.contains("Successfully tagged") && log.contains("Successfully built")) {
                        envEntity.setBuildStatus("3");
                        buildLogEntity.setBuildStatus("1");
                    } else {
                        envEntity.setBuildStatus("2");
                        buildLogEntity.setBuildStatus("0");
                    }
                    envService.update(envEntity);
                    buildLogService.update(buildLogEntity);
                } else {
                    buildLogService.update(buildLogEntity);
                }
            }
        }
    }
}
