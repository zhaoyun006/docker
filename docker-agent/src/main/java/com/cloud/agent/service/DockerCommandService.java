package com.cloud.agent.service;

import com.cloud.agent.configure.Configure;
import com.cloud.agent.controller.RemoteApiController;
import com.cloud.agent.entity.DockerCommandEntity;
import com.cloud.agent.util.Base64Util;
import com.cloud.agent.util.FileIoUtil;
import com.cloud.agent.util.docker.DockerCmd;
import com.cloud.agent.util.docker.PipeworkScripts;
import com.cloud.agent.util.vlan.CreateVlan;
import org.apache.log4j.Logger;

import java.io.File;

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

public class DockerCommandService {

    private static Logger logger = Logger.getLogger(RemoteApiController.class);


    /**
     *
     * @param cmd
     * @param entity
     * @return
     */
    static String getContainerCmd(String cmd, DockerCommandEntity entity){
        cmd = cmd.replace("{0}", getSecurityCmd(entity.getContainerId()));
        cmd = cmd.replace(","," ");
        return cmd;
    }

    /**
     * 配置网络地址
     * @param entity
     * @return
     */
    static String configureIp(DockerCommandEntity entity){
        PipeworkScripts.writeScripts();
        if (entity.getVlan() == null){
            entity.setVlan("0");
        }else if (!entity.getVlan().equals("0")){
            CreateVlan.createVlan(entity.getIpAddress(), entity.getVlan());
        }
        String containerId = getSecurityCmd(entity.getContainerId());
        String command = DockerCmd.configureIp.replace("{0}", containerId);
        command = command.replace("{1}", getSecurityCmd(entity.getIpAddress()));
        command = command.replace("{2}", getSecurityCmd(entity.getNetmask()));
        command = command.replace("{3}", getSecurityCmd(entity.getGateway()));
        command = command.replace("{4}", getSecurityCmd(entity.getVlan()));
        command = command.replace("{5}", getSecurityCmd(entity.getNetmask()));
        return command;
    }

    /**
     * 运行脚本
     * @return
     */
   public static String runCommand(String commands, DockerCommandEntity entity){
        String command = "";
        switch (commands)
        {
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
                command = DockerCmd.getContainerRun.replace("{0}",   getSecurityCmd(entity.getContainerId()).replace(","," "));
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
            case "gateway":
                command = DockerCmd.gateway;
                break;
            case "netmask":
                command = DockerCmd.netmask.replace("SERVER", getSecurityCmd(entity.getServer()));
                break;
            case "getCpuNumber":
                command = DockerCmd.getCpuNumber;
                break;
            case "nameserver":
                command = DockerCmd.nameserver;
                break;
            case "getImages":
                command = DockerCmd.getImages;
                break;
            case "getLogDirSize":
                command = getContainerCmd(DockerCmd.getLogDirSize, entity);
                command = command.replace("D", ",");
                break;
            case "getGetRunningContainer":
                command = DockerCmd.getGetRunningContainer;
                break;
            case "imagesExist":
                command = DockerCmd.imagesExist.replace("IMAGE_ID", getSecurityCmd(entity.getImagesId()));
                break;
            case "downloadImage":
                command = DockerCmd.downloadImage.replace("URL", entity.getUrl());
                command = command.replace("{0}", getSecurityCmd(entity.getImagesId()));
                command = command.replace("{2}", System.currentTimeMillis()/1000+"");
                command = command.replace("{3}", getSecurityCmd(entity.getTag()));
                break;
            case "runContainer":
                int memory = Integer.valueOf(getSecurityCmd(entity.getMemory()));
                if (memory > 5000 && memory < 7000){
                    memory = memory - 2000;
                }
                if (memory > 7000){
                    memory = memory - 4000;
                }
                if (memory > 12000){
                    memory = memory - 4000;
                }
                if (memory > 10000 ){
                    memory = memory - 2000;
                }

                String args = " --privileged --memory="+(memory)+"m ";
                args += "  --cpuset-cpus="+getSecurityCmd(entity.getCpu());
                args += " -h="+getSecurityCmd(entity.getHostname());
                args += " --name="+getSecurityCmd(entity.getHostname());
                for (String dns:getSecurityCmd(entity.getNameserver()).split(",")) {
                    args += " --dns="+dns;
                }
                String logDir = "/home/data/logs/{0}/logs/".replace("{0}", entity.getIpAddress());
                args += " -v {0}:{1}".replace("{0}", logDir).replace("{1}", "/home/data/logs/");
                args += " --net=none ";
                if (null != entity.getNofile()){
                    args += " --ulimit nofile="+entity.getNofile();
                }else{
                    args += " --ulimit nofile=4096:10000";
                }

                if (null != entity.getNoproc()){
                    args += " --ulimit nproc="+ entity.getNoproc();
                }else {
                    args += " --ulimit nproc=2048:4096 ";
                }

                if (null != entity.getPidLimit()){
                    args += " --pids-limit " + Integer.valueOf(entity.getPidLimit());
                }else{
                    args += " --pids-limit 10000 " ;
                }

                args += " --add-host="+entity.getHostname()+":"+entity.getIpAddress();
                command = DockerCmd.runContainer.replace("{0}", args);
                command = command.replace("{1}", getSecurityCmd(entity.getImagesId()));
                // 创建程序日志目录
                String logCmd = "mkdir {0} -p; chmod 777 {0} -R;";
                logCmd = logCmd.replace("{0}", logDir);
                command = logCmd.concat(command);
                break;
            case "configureIp":
                command = configureIp(entity);
                break;
            case "changePassword":
                command = DockerCmd.changePassword.replace("{0}", getSecurityCmd(entity.getContainerId()));
                command = command.replace("{1}", getSecurityCmd(entity.getPassword()));
                break;
            case "getPassword":
                command = DockerCmd.getPassword.replace("{0}", getSecurityCmd(entity.getPassword()));
                break;
            case "containerStart":
                String containerIds = entity.getContainerId().replaceAll("  ","");
                String[] cids = containerIds.split(",");
                String startCid = "";
                String startPath = "/var/lib/docker/auto_start/";
                for (int i=0;i<cids.length;i++) {
                    startCid = startPath + getSecurityCmd(cids[i]);
                    File file = new File(startCid);
                    if (file.exists()){
                        command += "sh "+ startCid +";";
                    }else {
                        command += DockerCmd.containerStart.replace("{0}", cids[i]);
                    }
                }
                break;
            case "getContainerMemoryUsed":
                command = DockerCmd.getContainerMemoryUsed.replace("{0}", getSecurityCmd(entity.getContainerId()));
                break;
            case "getContainerDiskUsed":
                command = DockerCmd.getContainerDiskUsed.replace("{0}", getSecurityCmd(entity.getContainerId()));
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
            case  "containerRemove":
                command = getContainerCmd(DockerCmd.containerRemove, entity);
                command = command.replace("{1}", getSecurityCmd(entity.getIpAddress()));
                command += getContainerCmd(DockerCmd.removeInvalidStartScripts, entity);
                break;
            case "recoverContainer":
                command = getContainerCmd(DockerCmd.mountContainer, entity);
                command = command.replace("{1}", getSecurityCmd(entity.getIpAddress()));
                command += DockerCmd.recoverContainer.replace("{1}", getSecurityCmd(entity.getIpAddress())).replace("{0}",getSecurityCmd(entity.getRepository()));
                command = command.replace("{2}", getSecurityCmd(entity.getContainerId()));
                break;

            case "readRecoverOk":
                command = getContainerCmd(DockerCmd.readRecoverOk, entity);
                break;

            case "makeRecoverScript":
                String data = Base64Util.decode(entity.getParam());
                String file = "/dev/shm/mount/"+getSecurityCmd(entity.getIpAddress())+"/rootfs/tmp/recover.sh";
                data += "\nrm -fv /tmp/recover.sh ";
                FileIoUtil.writeFile(file, data, false);
                FileIoUtil.setFileExec(file);
                break;

            case "autoStart":
                String containerId = getSecurityCmd(entity.getContainerId());
                String dir = "/var/lib/docker/auto_start";
                FileIoUtil.makeDir(dir);
                command = "umount `awk '$0 ~ /mnt\\/{0}/ {print $2}' /proc/mounts` 2>/dev/null;";
                command += DockerCmd.containerStart.replace("{0}", containerId);
                command += configureIp(entity);
                FileIoUtil.writeFile(dir+"/"+containerId,command ,true);
                command = "chmod a+x "+dir+"/"+containerId+";echo 1;";
                command += DockerCmd.setHosts.replace("{0}", containerId).replace("{1}", entity.getIpAddress());
                break;
            default:
                    break;
        }

       // debug是否打开
       if (Configure.get("debug","agent.conf").equals("true")) {
           logger.info("run command  " + commands + " " + command);
       }
        return runScript(command);
    }
}
