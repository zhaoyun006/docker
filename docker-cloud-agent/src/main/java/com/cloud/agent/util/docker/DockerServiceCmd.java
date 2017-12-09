package com.cloud.agent.util.docker;

import static com.cloud.agent.configure.Configure.getConf;

/**
 * Created by zhaoyun on 2017/9/27.
 * @author zhaoyun
 */
public class DockerServiceCmd {

    /**
     * // 创建服务
     */
    public static String createService = getConf("createService", 1,"docker service create {0} 2>&1");

    /**
     * // 删除服务
     */
    public static String removeService = getConf("removeService", 1,"docker service rm {0} 2>&1");

    /**
     *   // 服务更新
     */
    public static String updateService = getConf("updateService", 1,"docker service update {0} 2>&1");

    /**
     * // 服务回滚
     */
    public static String rollbackService = getConf("rollbackService", 1,"docker service rollback {0} 2>&1");

    /**
     *  // 扩容缩容服务
     */
    public static String scaleService = getConf("scaleService", 1,"docker service scale  {0} 2>&1");

    /**
     * // 获取服务信息docker service inspect
     */
    public static String inspectService = getConf("inspectService", 1,"docker service inspect  {0} 2>&1");

}