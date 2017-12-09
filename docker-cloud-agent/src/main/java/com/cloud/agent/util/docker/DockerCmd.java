package com.cloud.agent.util.docker;
import static com.cloud.agent.configure.Configure.getConf;

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

public class DockerCmd {


    public static String getRunningContainer = getConf("getRunningContainer", 1," docker ps |grep -v '(Paused)'|grep -v 'CONTAINER ID'  |wc -l");


    public static String getImagesNumber = getConf("getImagesNumber", 1," docker images -q |wc -l");


    public static String getStopContainer = getConf("getStopContainer", 1,"docker ps -a |grep Exited |wc -l ");


    public static String getPauseContainer = getConf("getPauseContainer", 1,"docker ps |grep \"(Paused)\" |wc -l");


    public static String getServerDisk = getConf("getServerDisk", 1,"docker info |egrep  \"Data Space Total|Data Space Used\" |awk '{print $4$5}' |sed 's/GB/ 1024/g;s/TB/ 1048576/g;s/MB/ 1/g'|awk '{printf \"%-10d \", $1*$2}' | awk '{print $1,$2,$1/$2*100\"%\"}'");


    public static String getServerMetadata = getConf("getServerMetadata", 1,"docker info |egrep  \"Metadata Space Total|Metadata Space Used\" |awk '{print $4$5}' |sed 's/GB/ 1024/g;s/TB/ 1048576/g;s/MB/ 1/g'|awk '{printf \"%-10d \", $1*$2}' | awk '{print $1,$2,$1/$2*100\"%\"}'");


    public static String getContainerRun = getConf("getContainerRun", 1,"docker inspect -f \"{{.State.Paused}} {{.State.ExitCode}}\" {0} |awk '$2 >0 {print \"Exited\"} $1==\"true\" {print \"Paused\"} $1 == \"false\" && $2 == 0 {print \"Running\"}'   ");


    public static String startService = getConf("startService", 1," service docker start || systemctl start docker");


    public static String stopService = getConf("startService", 1," service docker stop || systemctl stop docker ");


    public static String getServerStatus = getConf("getServerStatus", 1," service docker status 2>/dev/null|grep PID  -i|head -n 1 ");


    public static String memoryUsedPercent = getConf("memoryUsedPercent", 1,"free -m|awk '$0 ~ /Mem/ {OFMT=\"%.2f\" ; print ($3/$2)*100}'");


    public static String cpuUsedPercent = getConf("cpuUsedPercent", 1,"sar 1 2 |tail -n1|awk '{print 100-$NF}'");


    public static String getCpuNumber = Runtime.getRuntime().availableProcessors()+"";


    public static String getImages = getConf("getImages", 1,"docker images  |awk '$0 !~ /CREATED/ {print $2,$3,$4\"_\"$5\"_\"$6,$(NF-1)$NF}'");
    public static String pullImages = getConf("pullImages", 1,"docker pull {0} 2>&1");
    public static String mergerImages = getConf("mergerImages", 1,"docker tag {1} {0} 2>&1; docker push {0} 2>&1");

    public static String getGetRunningContainer = getConf("getGetRunningContainer", 1,"docker inspect -f \"{{.Id}} {{.Config.Image}} {{.Config.Hostname}} {{.State.Running}} {{.Created}}  {{.State.StartedAt}}\" `docker ps -q` |awk '{split($6,created,\".\") ;split($10,start,\".\");id=substr($1,1,12); print id,$2,$3,$4,$5\"_\"created[1],$9\"_\"start[1]}'");


    public static String containerStart = getConf("containerStart", 1,"docker start {0};");
    public static String containerStop = getConf("containerStop", 1,"docker unpause {0}; docker stop {0};");

    public static String containerRemove= getConf("containerRemove", 1,"docker stop {0} ;docker unpause {0}; docker rm -f {0}");
    public static String containerPause = getConf("containerPause", 1,"docker pause {0}");
    public static String containerReuse = getConf("containerReuse", 1,"docker unpause {0}");
    public static String containerRestart = getConf("containerRestart", 1,"docker restart {0}");
    public static String containerInfo = getConf("containerInfo", 1,"docker inspect {0}");
    public static String containerStatus = getConf("containerStatus", 1,"docker inspect -f \"{{.State.Paused}} {{.State.ExitCode}}\" {0} |awk '$2 >0 {print \"Exited\"} $1==\"true\" {print \"Paused\"} $1 == \"false\" && $2 == 0 {print \"Running\"}'   ");


    public static String getLastDockerImages = getConf("getLastDockerImages", 1," docker images --format \"{{.Repository}}:{{.Tag}}\" |head -n 1");
    public static String getDockerStatsV = getConf("getDockerStatsV", 1," docker stats  --no-stream |grep -v CONTA |awk '{print NF}'|head -n 1");
    public static String getDockerStats1126 = getConf("getDockerStats", 1," docker stats  --no-stream  |awk '$0 !~/CONTAINER/{print $1,$2,$8,$9$10,$12$13,$14$15,$17$18}' ");
    public static String getDockerStats17ce = getConf("getDockerStats", 1," docker stats  --no-stream  |awk '$0 !~/CONTAINER/{print $1,$2,$6,$7,$9,$10,$12}'' ");

}