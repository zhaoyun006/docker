package com.cloud.agent.util.docker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

/**
 * Created by zhaoyun on 2017/9/29.
 */
public class HostNameUtil {

    private static Logger logger = LoggerFactory.getLogger(HostNameUtil.class);

    public static String getHostName(String string){
        try {
            InetAddress addr = InetAddress.getLocalHost();
            String ip = addr.getHostAddress().toString(); //获取本机ip
            String hostName = addr.getHostName().toString(); //获取本机计算机名称
            String action1 = "ip";
            String action2 = "hostname";
            if (action1.equals(string)){
                return ip;
            }
            if (action2.equals(string)){
                return hostName;
            }
            return "";
        }catch (Exception e){
            logger.error("获取主机名和IP地址失败，请绑定IP地址和主机名");
            return "";
        }
    }
}
