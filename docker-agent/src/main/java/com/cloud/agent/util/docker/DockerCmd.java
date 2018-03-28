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

    // 获取docker运行容器的数量Z
    public static String getRunningContainer = getConf("getRunningContainer", 1," docker ps |grep -v '(Paused)'|grep -v 'CONTAINER ID'  |wc -l");

    // 获取docker的镜像数量
    public static String getImagesNumber = getConf("getImagesNumber", 1," docker images -q |wc -l");

    // 获取docker停止的数量
    public static String getStopContainer = getConf("getStopContainer", 1,"docker ps -a |grep Exited |wc -l ");

    // 获取暂停的数量
    public static String getPauseContainer = getConf("getPauseContainer", 1,"docker ps |grep \"(Paused)\" |wc -l");

    // 获取docker硬盘使用信息
    public static String getServerDisk = getConf("getServerDisk", 1,"docker info |egrep  \"Data Space Total|Data Space Used\" |awk '{print $4$5}' |sed 's/GB/ 1024/g;s/TB/ 1048576/g;s/MB/ 1/g'|awk '{printf \"%-10d \", $1*$2}' | awk '{print $1,$2,$1/$2*100\"%\"}'");

    // 获取docker元数据使用信息
    public static String getServerMetadata = getConf("getServerMetadata", 1,"docker info |egrep  \"Metadata Space Total|Metadata Space Used\" |awk '{print $4$5}' |sed 's/GB/ 1024/g;s/TB/ 1048576/g;s/MB/ 1/g'|awk '{printf \"%-10d \", $1*$2}' | awk '{print $1,$2,$1/$2*100\"%\"}'");

    // 获取容器是否运行
    public static String getContainerRun = getConf("getContainerRun", 1,"docker inspect -f \"{{.State.Paused}} {{.State.ExitCode}}\" {0} |awk '$2 >0 {print \"Exited\"} $1==\"true\" {print \"Paused\"} $1 == \"false\" && $2 == 0 {print \"Running\"}'   ");

    // 获取子网掩码,kvm,docker
    public static String netmask = getConf("netmask", 1,"ip add |grep SERVER|awk -F\"/\" '{print $2}'|awk '{print $1}'");

    // 获取默认网关
    public static String gateway = getConf("gateway", 1,"ip route show |grep default|awk '{print $3}'|head -n 1");

    // 获取dns服务器
    public static String nameserver = getConf("nameserver", 1,"grep nameserver /etc/resolv.conf |grep -v '#'|awk '{print $2}'|paste -s|sed 's/\\t/,/g'");

    // 启动服务
    public static String startService = getConf("startService", 1," service docker start || systemctl start docker");

    // 停止服务
    public static String stopService = getConf("startService", 1," service docker stop || systemctl stop docker ");

    // 获取服务器运行状态
    public static String getServerStatus = getConf("getServerStatus", 1," service docker status 2>/dev/null|grep PID  -i|head -n 1 ");

    // 获取内存使用率
    public static String memoryUsedPercent = getConf("memoryUsedPercent", 1,"free -m|awk '$0 ~ /Mem/ {OFMT=\"%.2f\" ; print ($3/$2)*100}'");

    // 获取CPU使用率
    public static String cpuUsedPercent = getConf("cpuUsedPercent", 1,"sar 1 2 |tail -n1|awk '{print 100-$NF}'");

    // 获取服务器CPU数量
    public static String getCpuNumber = getConf("getCpuNumber", 1,"grep processor /proc/cpuinfo |wc -l ");

    // 获取docker镜像是否存在
    public static String imagesExist = getConf("imagesExist", 1,"docker images |awk '$3 == \"IMAGE_ID\" {print}'  ");

    // 获取服务器以及存在的镜像
    public static String getImages = getConf("getImages", 1,"docker images  |awk '$0 !~ /CREATED/ {print $2,$3,$4\"_\"$5\"_\"$6,$(NF-1)$NF}'");

    // 获取运行的容器
    public static String getGetRunningContainer = getConf("getGetRunningContainer", 1,"docker inspect -f \"{{.Id}} {{.Config.Image}} {{.Config.Hostname}} {{.State.Running}} {{.Created}}  {{.State.StartedAt}}\" `docker ps -q` |awk '{split($6,created,\".\") ;split($10,start,\".\");id=substr($1,1,12); print id,$2,$3,$4,$5\"_\"created[1],$9\"_\"start[1]}'");

    // 下载镜像
    public static String downloadImage = getConf("downloadImage", 1,"curl URL -o /dev/shm/{0}.tar.bz2 ;cd /dev/shm && " +
            "               tar xjvf {0}.tar.bz2 && " +
            "               docker load < {0}.tar && " +
            "               docker tag {0}  {2}:{3} && " +
            "               rm -fv {0}.tar && " +
            "               rm -fv {0}.tar.bz2 ");


    public static String getPassword = getConf("getPassword", 1,"echo -n `echo '{0}' | openssl passwd -1 -salt $(< /dev/urandom tr -dc '[:alnum:]' | head -c 32) -stdin `");


    // 启动容器，新生产一个
    public static String runContainer = getConf("runContainer", 1,"docker run {0} -d -t -i {1} 2>&1");

    // 修改docker容器的密码
    public static String changePassword = getConf("changePassword", 1,"docker exec -t {0} sed -i 's#root:.*#root:{1}:17094:0:99999:7:::#g' /etc/shadow 2>&1 && echo 0 || echo 1 ");

    // 配置容器的IP地址
    public static String configureIp = getConf("configureIp", 1, "/bin/pipework br{4} {0} {1}/{5}@{3}");

    // 配置容器的host信息
    public static String setHosts = getConf("setHosts", 1,"id=$(ls /var/lib/docker/devicemapper/mnt/ |grep {0}|grep -v init|sed 's/\\\\n//g'); " +
            "   docker exec {0} egrep \"^{1}.*|localhost\" /etc/hosts > /var/lib/docker/devicemapper/mnt/$id/rootfs/etc/hosts.new 2>&1;" +
            "   docker exec {0} cp /etc/hosts.new /etc/hosts 2>&1");

    public static String containerStart = getConf("containerStart", 1,"docker start {0};");
    public static String containerStop = getConf("containerStop", 1,"docker unpause {0}; docker stop {0};");

    public static String containerRemove = getConf("containerRemove", 1,"docker start {0}; docker inspect --format \"brctl delif br0 veth1pl{{.State.Pid}}\" {0} > /dev/shm/{0}; sh /dev/shm/{0} ; umount -fl /dev/shm/mount/{1}/ ; docker rm -f {0};sh /dev/shm/{0} ; ");
    public static String containerPause = getConf("containerPause", 1,"docker pause {0}");
    public static String containerReuse = getConf("containerReuse", 1,"docker unpause {0}");
    public static String containerRestart = getConf("containerRestart", 1,"docker restart {0}");
    public static String containerInfo = getConf("containerInfo", 1,"docker inspect {0}");
    public static String containerStatus = getConf("containerStatus", 1,"docker inspect -f \"{{.State.Paused}} {{.State.ExitCode}}\" {0} |awk '$2 >0 {print \"Exited\"} $1==\"true\" {print \"Paused\"} $1 == \"false\" && $2 == 0 {print \"Running\"}'   ");
    public static String getContainerMemoryUsed = getConf("getContainerMemoryUsed", 1,"cat /sys/fs/cgroup/memory/system.slice/docker-{0}*/memory.limit_in_bytes  /sys/fs/cgroup/memory/system.slice/docker-{0}*/memory.usage_in_bytes |paste -s |awk '{OFMT=\"%.2f\" ;print $1/1024/1024,$2/1024/1024,$2/$1*100}'|awk '$1>1024 {printf $1/1024\"GB\"} $1< 1024 {printf $1\"MB\"} {printf \" \"} $2>1024 {printf $2/1024\"GB\"} $2<1024 {printf $2\"MB\"} {printf \" \"$3}' ");
    public static String getContainerDiskUsed = getConf("getContainerDiskUsed", 1,"docker exec {0} df -h  |head -n 2 |tail -n 1 |awk '{print $2,$3,$5}'|sed 's/%//g'");

    public static String getValidContainer = getConf("getValidContainer", 1, "docker ps -aq");
    public static String getStartScripts = getConf("getStartScripts", 1, "ls -l /var/lib/docker/auto_start/ |awk '$0 ~ /rwx/ {print $NF}'");

    public static String getLogDirSize = getConf("getLogDirSize", 1, "docker inspect {0}|awk '$0 ~ \"/home/data/logs/\" {printf $2}'|sed 's/\",//g;s/\"//g'|awk '{print \"du -sh \"$1}'|sed 's/D//g'|bash |awk '{print $1}'");

    public static String removeInvalidStartScripts = getConf("removeInvalidStartScripts", 1, "cd /var/lib/docker/auto_start/; rm -fv {0}");

    // 启动停止掉的容器
    public static String getExitedContainer = getConf("getExitedContainer", 1, "docker ps -f \"Status=exited\" -q ");

    // 挂载docker容器到本地
    public static String mountContainer = getConf("mountContainer", 1, "echo 1 ; mkdir -pv /dev/shm/mount/{1} ; docker inspect -f \"{{.GraphDriver.Data.DeviceName}}\"  {0} |awk '{print \"mount /dev/mapper/\"$1\" /dev/shm/mount/{1}\"}'|bash;");

    // 获取是否恢复完成
    public static String readRecoverOk = "cat /dev/shm/{0}.ok; echo 1";

    // 恢复备份的数据到本地路径
    public static String recoverContainer = getConf("recoverContainer", 1, "( rsync -arz /home/data/backup/docker/{0}/home/ /dev/shm/mount/{1}/rootfs/home/ ; echo {2} > /dev/shm/{2}.ok ; sleep 1; docker stop {2} ) & sleep 3; rsync -azr /home/data/backup/docker/{0}/etc/profile /dev/shm/mount/{1}/rootfs/etc/ ; rsync -azr /home/data/backup/docker/{0}/etc/shadow /dev/shm/mount/{1}/rootfs/etc/;  mkdir /home/data/logs/{1}/logs/{1..5}; chmod 777 /home/data/logs/{1}/logs/*; ");
}
