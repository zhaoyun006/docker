package com.cloud.agent.util.docker;

import static com.cloud.agent.configure.Configure.getConf;

/**
 * Created by zhaoyun on 2017/10/8.
 * @author zhaoyun
 */
public class DockerNetworkCmd {

    public static String createNetwork = getConf("createNetwork", 1," docker network create --driver overlay --scope=swarm --subnet {0} --attachable {1} 2>&1");

    public static String removeNetwork = getConf("removeNetwork", 1," docker network remove {0} 2>&1");

    public static String getNetwork  = getConf("removeNetwork", 1,"   docker network ls |grep overlay |grep -v ingress|awk '{print $1,$2}' 2>&1");
}