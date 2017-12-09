package com.cloud.configure;

/**
 * Created by zhaoyun on 2017/9/29.
 */
public class RedisKey {

    public static String serviceContainers = "service_containers_";

    /**
     *
     */
    public static String cachePushServer = "push_server";

    /**
     * 获取service组合容器的key
     */
    public static String serviceSetLock = "service_containers_lock";

    /**
     * 操作服务队列,每次操作记录到队列，由页面读取
     */
    public static String operQueue = "oper_queue";

    /**
     * 存储容器的监控数据 _containerId
     */
    public static String containerStats = "container_stats_";

    /**
     * // 存储容器的监控数据 _containerId
     */
    public static String serviceStats = "service_stats_";

    /**
     * 存储service信息 _service
     */
    public static String serviceInfo = "service_info_";

    /**
     * 存储容器信息 _容器ID
     */
    public static String containerInfo = "container_info_";

    /**
     * 存储每个网络里面的容器
     */
    public static String networkContainers = "network_containers_";

    /**
     * 存储每台服务器的集群名称 _ip 地址 , 字符串
     */

    public static String serverClueterName = "server_cluster_name_";

    /**
     * 存储master服务器 _集群名称 list []
     */

    public static String masterServersList = "master_server_list_";

    /**
     * 存储服务器容器信息 _ip
     */
    public static String serverContainerInfo = "server_container_info_";

    /**
     *  获取每个机器的镜像信息
     */
    public static String serverImages = "server_images_";

    /**
     * 存储30分钟缓存，过期后更新状态任务计划不再执行
     */
    public static String imagesBuildStatus = "images_build_status";

    /**
     * 设置每个网络的网络ID号, 30秒一次
     * _网络名字
     */
    public static String networkInfo = "network_info_";

    /**
     * 获取自动编译的列表
     * 一个list,里面存储编译的数据
     */
    public static String autoCompile = "auto_compile_data";

    /**
     * 编译完成后写入到队列,服务端做更新处理
     * redis队列
     */
    public static String autoCompileCompleteQueue = "auto_compile_complete_queue";

    /**
     * 镜像下载队列
     */
    public static String imagePullQueue = "image_pull_queue";

    /**
     * 镜像合并队列
     */
    public static String imageMergerQueue = "image_merger_queue";

    /**
     * 设置服务器类型
     * _swarm
     * _kubernetes
     * _host
     */
    public static String serverClusterTp = "server_cluster_type_";

    /**
     * 设置服务器是否是master, _ip地址
     */
    public static String serverIsMaster = "server_is_master_";
}
