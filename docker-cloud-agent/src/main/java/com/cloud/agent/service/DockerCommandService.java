package com.cloud.agent.service;

import com.cloud.agent.configure.RedisKey;
import com.cloud.agent.controller.RemoteApiController;
import com.cloud.agent.entity.*;
import com.cloud.agent.scheduler.DockerScheduler;
import com.cloud.agent.thread.BuildImageThread;
import com.cloud.agent.util.*;
import com.cloud.agent.util.docker.*;
import com.cloud.agent.util.k8s.K8sCmd;
import com.cloud.agent.util.k8s.K8sRcTemplate;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;

import static com.cloud.agent.util.RunCommandUtil.getSecurityCmd;
import static com.cloud.agent.util.RunCommandUtil.runScript;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author zhaozq
 * @version 1.0
 */

public class DockerCommandService {

    private static Logger logger = LoggerFactory.getLogger(RemoteApiController.class);


    /**
     * @param cmd
     * @param entity
     * @return
     */
    static String getContainerCmd(String cmd, DockerCommandEntity entity) {
        cmd = cmd.replace("{0}", getSecurityCmd(entity.getContainerId()));
        cmd = cmd.replace(",", " ");
        return cmd;
    }


    /**
     * docker stack管理
     *
     * @param commands
     * @param entity
     * @return
     */
    public static String runStackCommand(String commands, DockerStackEntity entity) {
        String command = "docker stack ";
        switch (commands) {
            case "deploy":
                String content = entity.getComposeFile();
                String file = "/dev/shm/deploy_".concat(entity.getName()).concat(".yaml");
                FileIoUtil.writeFile(file, content, false);
                command += "deploy --compose-file ".concat(file).concat(entity.getName());
                break;
            case "remove":
                command = command + " rm " + getSecurityCmd(entity.getName());
                break;
            default:
                break;
        }
        LogUtil.info("run command  " + commands + " " + command);
        String result = runScript(command);
        DockerScheduler.setServices();
        return result;
    }


    /**
     * 镜像构建方法
     * 将代码下载,在固定路径
     * 使用脚本打包,脚本不考虑安全问题,或者只能由运维修改配置脚本
     * 研发可配置dockerfile
     * 脚本将编译或者代码文件复制到可以添加到docker镜像的路径
     * 执行docker镜像制作命令
     * 完成镜像后提交到仓库（仓库-(分测试，和生产仓库)
     * 每次镜像打包，修改tag标签，标签可以按 www-20171020-0001 进行版本定义, 服务名-时间-编译次数
     * 测试完成后，触发将镜像提交到生产仓库并分发到不同机器
     * 完成后进行生产容器更新
     *
     * @param commands
     * @param entity
     * @return
     */
    public static String runImagesCmd(String commands, ImagesBuildInfoEntity entity) {
        // 替换掉 ..
        entity.setServiceName(entity.getServiceName().replace("..", "").replace("\\.\\.", ""));
        StringBuilder command = new StringBuilder();
        String fileName = "/dev/shm/" + entity.getUser() + entity.getEnvId();
        String logFile = "/tmp/.".concat(entity.getUser()) + entity.getEnvId();
        String buildLog = logFile.concat(".build");
        String lock = "/dev/shm/".concat(entity.getUser()) + entity.getEnvId() + ".lock";

        String buildScript = fileName.concat(".build");
        CompileService compileService = new CompileService();
        LogUtil logUtil = new LogUtil();
        logUtil.setFilePath(logFile);
        logUtil.setUser(entity.getUser());
        compileService.setLogUtil(logUtil);
        compileService.setImagesBuildInfoEntity(entity);
        switch (commands) {
            case "build":
                BuildImageThread buildImageThread = new BuildImageThread(logUtil, entity, compileService, buildScript, command, fileName, buildLog);
                buildImageThread.setDaemon(true);
                buildImageThread.start();
                return "0";
            case "log":
                return FileIoUtil.readFile(logFile);
            case "pullImage":
                command.append(DockerCmd.pullImages.replace("{0}", getSecurityCmd(entity.getRegistry())));
                break;
            case "merger":
                DockerGetUtil dockerGetUtil = new DockerGetUtil();
                Map map = dockerGetUtil.images(entity.getImagesId());
                String imagesId = map.get("Id").toString().split("sha256:")[1].substring(0, 12);
                command.append(DockerCmd.mergerImages.replace("{0}", getSecurityCmd(entity.getRegistry())).replace("{1}", imagesId));
                break;
            case "check":
                File file = new File(lock);
                if (file.exists()) {
                    return "exists";
                } else {
                    return "ok";
                }
            default:
                break;
        }
        RedisUtil redisUtil = new RedisUtil();
        LogUtil.info("run command  " + commands + " " + command.toString());
        String result = runScript(command.toString());
        if (commands.equals("pullImage")) {
            DockerCloudImagePullLogEntity cloudImagePullLogEntity = new DockerCloudImagePullLogEntity();
            cloudImagePullLogEntity.setPullUser(entity.getUser());
            cloudImagePullLogEntity.setImageName(entity.getRegistry());
            cloudImagePullLogEntity.setPullTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
            cloudImagePullLogEntity.setPullResult(result);
            cloudImagePullLogEntity.setEnvId(entity.getEnvId());
            cloudImagePullLogEntity.setServiceName(entity.getServiceName());
            cloudImagePullLogEntity.setServerAddress(DockerScheduler.ip);
            cloudImagePullLogEntity.setEntId(Integer.valueOf(entity.getEntId()));
            redisUtil.lpush(RedisKey.imagePullQueue, new Gson().toJson(cloudImagePullLogEntity));
        }
        if (commands.equals("merger")) {
            DockerCloudImageMergerRecordEntity mergerRecordEntity = new DockerCloudImageMergerRecordEntity();
            mergerRecordEntity.setEntId(Integer.valueOf(entity.getEntId()));
            mergerRecordEntity.setCurrentVersion(entity.getVersion());
            mergerRecordEntity.setCreateUser(entity.getUser());
            mergerRecordEntity.setServiceName(entity.getServiceName());
            mergerRecordEntity.setResult(result);
            mergerRecordEntity.setCreateTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
            redisUtil.lpush(RedisKey.imageMergerQueue, new Gson().toJson(mergerRecordEntity));
        }
        return result;
    }

    /**
     * @param commands
     * @param entity
     * @return
     */
    public static String runNetworkCommand(String commands, DockerNetworkEntity entity) {
        String command = "";
        String createNetwork = "createNetwork";
        switch (commands) {
            case "createNetwork":
                command = DockerNetworkCmd.createNetwork.replace("{0}", getSecurityCmd(entity.getNetworkIp())).replace("{1}", getSecurityCmd(entity.getName()));
                break;
            case "removeNetwork":
                command = DockerNetworkCmd.removeNetwork.replace("{0}", getSecurityCmd(entity.getName()));
                break;
            default:
                break;
        }
        String result = runScript(command);
        if (commands.equals(createNetwork)) {
            DockerGetUtil getUtil = new DockerGetUtil();
            Map<String, Object> map = getUtil.networks(entity.getName());
            String driver = map.get("Driver").toString();
            logger.info("获取到网络信息" + new Gson().toJson(map));
            logger.info("获取到驱动信息" + driver);
            if (map.size() > 1 && CheckUtil.checkString(driver) && driver.length() > 1) {
                System.out.println("Driver " + driver);
                setUpdateQueue(commands, "createNetwork", entity.getName(), map.get("Name").toString(), entity.getUser(), "创建网络");
                return result;
            } else {
                logger.error("创建网络失败");
                runNetworkCommand("removeNetwork", entity);
                return "创建网络失败,请检查地址段是否正常!!!";
            }
        }
        setUpdateQueue(commands, "removeNetwork", entity.getName(), result, entity.getUser(), "删除网络");
        // 网络变动时及时更新数据
        DockerScheduler.setNetworks();
        return result;
    }

    /**
     * 创建pod
     *
     * @param entity
     * @param commands
     * @return
     */
    public static String k8sRun(String commands, DockerServiceEntity entity) {
        String result = "";
        String yaml;
        String command;
        String r;
        String name = getSecurityCmd(entity.getName());
        String tempfile = "/dev/shm/" + getSecurityCmd(entity.getName());
        String app = entity.getDomain().replace(".", "-").replace("_", "-");
        if (!CheckUtil.checkString(entity.getReplicas())) {
            entity.setReplicas("1");
        }
        switch (commands) {
            case "getRcYaml":
                return K8sOperationService.getRcYaml(name, entity, app);

            case "getServicePort":
                command = K8sCmd.kubectlGetService.replace("{0}", name);
                return  runScript(command);

            case "getServiceYaml":
                return K8sOperationService.getServiceYaml(name, entity, app);

            case "createRc":
                if (!CheckUtil.checkString(entity.getImage())) {
                    entity = getImages(entity);
                }
                command = K8sCmd.kubectlCreate.replace("{0}", tempfile);
                yaml = K8sOperationService.getRcYaml(name, entity, app);
                if (CheckUtil.checkString(entity.getLoadblanceTp())) {
                    yaml += "---\n";
                    yaml += K8sOperationService.getServiceYaml(name, entity, app);
                }
                FileIoUtil.writeFile(tempfile, yaml, false);
                result = runScript(command, 1);
                r = "replicationcontroller \"" + name + "-rc\" created";
                if (result.contains(r)) {
                    return "ok";
                }
                break;
            case "updateRc":
                command = K8sCmd.kubectlUpdate.replace("{0}", name);
                command = command.replace("{1}", entity.getImage());
                result = runScript(command, 1);
                break;
            case "deleteRc":
                command = K8sCmd.kubectlDeleteRc.replace("{0}", name);
                result = runScript(command, 1);
                break;
            case "rollback":
                break;
            case "createService":
                command = K8sCmd.kubectlCreate.replace("{0}", tempfile);
                yaml = K8sOperationService.getServiceYaml(name, entity, app);
                FileIoUtil.writeFile(tempfile, yaml, false);
                result = runScript(command, 1);
                r = "service \"" + entity.getName() + "-rc\" created";
                if (result.contains(r)) {
                    return "ok";
                }
                break;
            case "deleteService":
                command = K8sCmd.kubectlDeleteService.replace("{0}", name);
                result = runScript(command, 1);
                break;
            default:
                break;
        }

        return result;
    }

    /**
     * 设置默认镜像
     *
     * @param entity
     * @return
     */
    static DockerServiceEntity getImages(DockerServiceEntity entity) {
        String images = RunCommandUtil.runScript(DockerCmd.getLastDockerImages, 1);
        if (CheckUtil.checkString(images)) {
            if (images.contains("none")) {
                entity.setImage(images.split(":")[0]);
            } else {
                entity.setImage(images);
            }
        } else {
            entity.setImage("alpine");
        }
        if (!CheckUtil.checkString(entity.getNetwork())) {
            entity.setNetwork("default_0");
        }
        return entity;
    }

    /**
     * @param commands
     * @param entity
     * @return
     */
    public static String runServiceCommand(String commands, DockerServiceEntity entity) {
        String command = "";
        String args = "";
        switch (commands) {
            case "removeService":
                command = DockerServiceCmd.removeService.replace("{0}", getSecurityCmd(entity.getName()));
                break;
            case "createService":
                // 服务名称
                args += " --name ".concat(getSecurityCmd(entity.getName()));
                // 副本数量
                args += " --replicas ".concat(Integer.valueOf(entity.getReplicas()) + "");
                // cpu限制
                if (CheckUtil.checkString(entity.getLimitCpu())) {
                    args += "   --limit-cpu ".concat(getSecurityCmd(entity.getLimitCpu()));
                }
                // 内存限制 字节
                if (CheckUtil.checkString(entity.getLimitMemory())) {
                    args += " --limit-memory  ".concat(getSecurityCmd(entity.getLimitMemory()));
                }
                // 环境变量 --env
                if (CheckUtil.checkString(entity.getEnv())) {
                    args += entity.getEnv();
                }

                // 挂载路径  --mount type=bind,source=/tmp/,destination=/tmp/
                if (CheckUtil.checkString(entity.getMount())) {
                    args += getSecurityCmd(entity.getMount());
                }
                // 端口映射 -p 3000:3000 -p 2000:2000
                if (CheckUtil.checkString(entity.getPort())) {
                    args += getSecurityCmd(entity.getPort());
                }
                // 最大限制扩容数 labels
                // "max-cpu": "40",
                // "max-mem": "40",
                // "max-replicas": "5"
                if (CheckUtil.checkString(entity.getScaleMax())) {
                    args += "  --label max-replicas=".concat(Integer.valueOf(entity.getScaleMax().trim()) + "");
                }
                // 最小保留数
                if (CheckUtil.checkString(entity.getScaleMin())) {
                    args += "  --label min-replicas=".concat(Integer.valueOf(entity.getScaleMin().trim()) + "");
                }
                // 内存大于百分比
                if (CheckUtil.checkString(entity.getScaleMem())) {
                    args += "  --label max-mem=".concat(Integer.valueOf(entity.getScaleMem().trim()) + "");
                }
                // cpu大于百分比
                if (CheckUtil.checkString(entity.getScaleCpu())) {
                    args += "  --label max-cpu=".concat(Integer.valueOf(entity.getScaleCpu().trim()) + "");
                }
                // 添加域名信息
                if (CheckUtil.checkString(entity.getDomain())) {
                    args += " --label domain=".concat(getSecurityCmd(entity.getDomain()));
                }
                args += " --detach=true ";
                if (!CheckUtil.checkString(entity.getImage())) {
                    DockerGetUtil dockerGetUtil = new DockerGetUtil();
                    Map map = dockerGetUtil.networks("default_0");
                    if (null == map) {
                        DockerNetworkEntity networkEntity = new DockerNetworkEntity();
                        networkEntity.setName("default_0");
                        networkEntity.setUser("system");
                        networkEntity.setNetworkIp("172.16.255.0/24");
                        runNetworkCommand("createNetwork", networkEntity);
                    }
                    entity = getImages(entity);
                }
                // 网络
                if (CheckUtil.checkString(entity.getNetwork())) {
                    args += " --network ".concat(getSecurityCmd(entity.getNetwork()));
                }

                command = DockerServiceCmd.createService.replace("{0}", args + " " + getSecurityCmd(entity.getImage()));
                if (entity.getImage().contains("alpine")) {
                    command = command + " sleep 1000000000000000000";
                }
                break;

            case "updateService":
                // cpu限制
                if (CheckUtil.checkString(entity.getLimitCpu())) {
                    args += "   --limit-cpu ".concat(getSecurityCmd(entity.getLimitCpu()));
                }
                // 内存限制 字节
                if (CheckUtil.checkString(entity.getLimitMemory())) {
                    args += " --limit-memory  ".concat(getSecurityCmd(entity.getLimitMemory()));
                }
                // 环境变量 --env
                if (CheckUtil.checkString(entity.getEnv())) {
                    args += getSecurityCmd(entity.getEnv().replace("--env", " --env-add "));
                }
                // 网络
                if (CheckUtil.checkString(entity.getNetwork())) {
                    args += " --network-add ".concat(getSecurityCmd(entity.getNetwork()));
                }
                // 挂载路径  --mount type=bind,source=/tmp/,destination=/tmp/
                if (CheckUtil.checkString(entity.getMount())) {
                    args += getSecurityCmd(entity.getMount().replace("--mount", " --mount-add "));
                }
                // 端口映射 -p 3000:3000 -p 2000:2000
                if (CheckUtil.checkString(entity.getPort())) {
                    args += getSecurityCmd(entity.getPort().replace("--publish", " --publish-add "));
                }
                if (CheckUtil.checkString(entity.getScaleMax())) {
                    args += " --label-rm max-replicas  --label-add max-replicas=".concat(Integer.valueOf(entity.getScaleMax().trim()) + "");
                }
                // 最小保留数
                if (CheckUtil.checkString(entity.getScaleMin())) {
                    args += " --label-rm min-replicas --label-add min-replicas=".concat(Integer.valueOf(entity.getScaleMin().trim()) + "");
                }
                // 内存大于百分比
                if (CheckUtil.checkString(entity.getScaleMem())) {
                    args += " --label-rm max-mem  --label-add max-mem=".concat(Integer.valueOf(entity.getScaleMem().trim()) + "");
                }
                // cpu大于百分比
                if (CheckUtil.checkString(entity.getScaleCpu())) {
                    args += " --label-rm max-cpu --label-add max-cpu=".concat(Integer.valueOf(entity.getScaleCpu().trim()) + "");
                }

                // 并行更新
                if (CheckUtil.checkString(entity.getUpdateParallelism())) {
                    args += " --update-parallelism " + getSecurityCmd(entity.getUpdateParallelism());
                }
                if (null != entity.getUpdateFailureAction()) {
                    args += " --update-failure-action  ".concat(getSecurityCmd(entity.getUpdateFailureAction()));
                }

                logger.info("update service " + args);
                // 删除老的挂载数据
                try {
                    DockerServiceEntity serviceEntity = ServiceInfoUtil.getServiceInfo(entity.getName(), null);
                    args = getRmArgs(serviceEntity.getMount(), args, " --mount-rm ");
                    args = getRmArgs(serviceEntity.getPort(), args, " --publish-rm ");
                    args = getRmArgs(serviceEntity.getPort(), args, " --env-rm ");
                    args = getRmArgs(serviceEntity.getNetwork(), args, " --network-rm ");
                    if (CheckUtil.checkString(serviceEntity.getDomain())) {
                        args += " --label-rm domain ";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


                // 并行更新
                if (CheckUtil.checkString(entity.getUpdateParallelism())) {
                    args += " --rollback-parallelism " + getSecurityCmd(entity.getUpdateParallelism());
                }
                if (null != entity.getUpdateFailureAction()) {
                    args += " --rollback-failure-action  ".concat(getSecurityCmd(entity.getUpdateFailureAction()));
                }
                logger.info("update service " + args);
                if (CheckUtil.checkString(entity.getUpdateDelay())) {
                    args += " --rollback-delay " + getSecurityCmd(entity.getUpdateDelay());
                }
                if (CheckUtil.checkString(entity.getUpdateDelay())) {
                    args += " --update-delay " + getSecurityCmd(entity.getUpdateDelay());
                }
                args += " --detach=true ";
                if (CheckUtil.checkString(entity.getImage())) {
                    args += " --image " + getSecurityCmd(entity.getImage());
                }
                // 域名
                if (CheckUtil.checkString(entity.getDomain())) {
                    args += " --label-add domain=".concat(getSecurityCmd(entity.getDomain()));
                }

                logger.info("update service " + args);
                command = DockerServiceCmd.updateService.replace("{0}", args + " " + entity.getName());
                logger.info("update service " + command);
                break;
            case "rollbackService":
                args += " --detach=true ";
                command = DockerServiceCmd.rollbackService.replace("{0}", args + " " + entity.getName());
                break;
            default:
                break;
        }
        LogUtil.info("run command  " + commands + " " + command);
        String result = runScript(command);
        setUpdateQueue(commands, "updateService", entity.getName(), result, entity.getUser(), "更新服务");
        if ("createService".equals(commands)) {
            DockerGetUtil dockerGetUtil = new DockerGetUtil();
            Map map = dockerGetUtil.services(entity.getName());
            if (null != map) {
                setUpdateQueue(commands, "createService", entity.getName(), entity.getName(), entity.getUser(), "创建服务");
            } else {
                setUpdateQueue(commands, "createService", entity.getName(), entity.getName(), result, "创建服务");
            }
        }
        setUpdateQueue(commands, "removeService", entity.getName(), result, entity.getUser(), "删除服务");
        setUpdateQueue(commands, "rollbackService", entity.getName(), result, entity.getUser(), "回滚服务");
        DockerScheduler.setServices();
        return result;
    }

    /**
     * 运行脚本
     *
     * @return
     */
    public static String runCommand(String commands, DockerCommandEntity entity) {
        String command = "";
        switch (commands) {
            case "getRunningContainer":
                command = DockerCmd.getRunningContainer;
                break;
            case "getImagesNumber":
                command = DockerCmd.getImagesNumber;
                break;
            case "getPauseContainer":
                command = DockerCmd.getPauseContainer;
                break;
            case "getStopContainer":
                command = DockerCmd.getStopContainer;
                break;
            case "memoryUsedPercent":
                command = DockerCmd.memoryUsedPercent;
                break;
            case "cpuUsedPercent":
                command = DockerCmd.cpuUsedPercent;
                break;
            case "getContainerRun":
                command = DockerCmd.getContainerRun.replace("{0}", getSecurityCmd(entity.getContainerId()).replace(",", " "));
                break;
            case "getServerDisk":
                command = DockerCmd.getServerDisk;
                break;
            case "getServerMetadata":
                command = DockerCmd.getServerMetadata;
                break;
            case "getServerStatus":
                command = DockerCmd.getServerStatus;
                break;
            case "startService":
                command = DockerCmd.startService;
                break;
            case "stopService":
                command = DockerCmd.stopService;
                break;

            case "getCpuNumber":
                command = DockerCmd.getCpuNumber;
                break;

            case "getImages":
                command = DockerCmd.getImages;
                break;

            case "getGetRunningContainer":
                command = DockerCmd.getGetRunningContainer;
                break;

            case "containerStart":
                String containerIds = entity.getContainerId().replaceAll("  ", "");
                String[] cids = containerIds.split(",");
                for (int i = 0; i < cids.length; i++) {
                    command += DockerCmd.containerStart.replace("{0}", cids[i]);
                }
                break;
            case "containerStop":
                command = getContainerCmd(DockerCmd.containerStop, entity);
                break;
            case "containerRestart":
                command = getContainerCmd(DockerCmd.containerRestart, entity);
                break;
            case "containerPause":
                command = getContainerCmd(DockerCmd.containerPause, entity);
                break;
            case "containerInfo":
                command = getContainerCmd(DockerCmd.containerInfo, entity);
                break;
            case "containerReuse":
                command = getContainerCmd(DockerCmd.containerReuse, entity);
                break;
            case "containerStatus":
                command = getContainerCmd(DockerCmd.containerStatus, entity);
                break;
            default:
                break;

        }

        LogUtil.info("run command  " + commands + " " + command);
        return runScript(command);
    }

    /**
     * @param data
     * @param args
     * @param key
     * @return
     */
    static String getRmArgs(String data, String args, String key) {
        if (null == data) {
            return args;
        }
        String[] tempDatas = data.split(",");
        String[] temp;
        for (String tdata : tempDatas) {
            temp = tdata.split(":");
            if (temp.length > 1) {
                args += key.concat(temp[1]);
            } else {
                args += key.concat(temp[0]);
            }
        }
        return args;
    }

    /**
     * @param commands
     * @param name
     * @param result
     * @param user
     * @param info
     */
    static void setUpdateQueue(String commands, String command, String name, String result, String user, String info) {
        if (commands.equals(command)) {
            if (name.contains(result.trim())) {
                DockerApi.queue(info + "成功<br>" + name, user, result);
            } else {
                DockerApi.queue(info + "失败<br>" + name + "<br><font color='red'>" + result + "</font>", user, result);
            }
        }
    }
}
