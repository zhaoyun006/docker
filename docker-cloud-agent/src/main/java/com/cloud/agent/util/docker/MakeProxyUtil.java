package com.cloud.agent.util.docker;

import com.cloud.agent.scheduler.DockerScheduler;
import com.cloud.agent.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

import static com.cloud.agent.configure.Configure.getConf;

/**
 * Created by zhaoyun on 2017/11/17.
 *
 * @author zhaoyun
 *         创建负载服务的脚本,默认只支持nginx服务的创建
 *         在nginx的服务器运行该代理，就可以根据配置信息更新nginx配置文件,只更新upstream
 *         nginx按 域名.upstream.conf
 *         例子如:
 *         www.gg.com.upstream.conf
 *         nginx 配置文件默认为 /usr/local/nginx/conf/vhosts
 *         可设置agent配置文件设置nginx配置文件路径 nginxConfigPath=/usr/local/nginx/conf/vhosts
 */
public class MakeProxyUtil {

    private static final Logger logger = LoggerFactory.getLogger(MakeProxyUtil.class);

    /**
     * 配置文件路径
     */
    private static final String nginxConfigPath = getConf("nginxConfigPath", 1, "/usr/local/nginx/conf/vhosts/");

    /**
     * 模板路径
     */
    private static final String nginxTemplatePath = getConf("nginxTemplatePath", 1, "/usr/local/nginx/conf/template/");

    /**
     * 服务重载命令
     */
    private static final String nginxReloadCmd = getConf("nginxReloadCmd", 1, "/usr/local/nginx/sbin/nginx -s reload");

    static final String nodeKey = "${node}";
    static final String domainEky = "${domain}";
    static final String enterKey = "\n";
    static final String tempPath = "/dev/shm/";
    static final int size = 2;

    /**
     * 默认模板文件
     * 可以在 /usr/local/nginx/conf/template/ 放置域名的模板文件,只有2个变量, 也可以自定义模板路径
     * ${node} 和 ${domain}
     * 替换按node的数量读取 包含${node} 的行, 循环复制
     */
    private static final String defaultTemplate = "upstream ${domain} {\n" +
            "   server ${node}  max_fails=8 fail_timeout=3s;\n" +
            "}\n" +
            "server {\n" +
            "          listen      80;\n" +
            "          access_log  logs/${domain}_access.log;\n" +
            "          error_log   logs/${domain}_error.log;\n" +
            "          server_name ${domain};\n" +
            "\n" +
            "          location / {\n" +
            "                proxy_pass http://${domain};\n" +
            "          }\n" +
            "}";


    /**
     *
     * @param domain
     * @return
     */
    static String getNginxTemplate(String domain){
        String templateFile = nginxTemplatePath.concat(domain);
        if (new File(templateFile).exists()){
            String conf = FileIoUtil.readFile(templateFile);
            LogUtil.info("获取到配置文件内容 " + domain +" "+ conf + " " + conf.split(enterKey).length);
            if (conf.contains(nodeKey) && conf.contains(domainEky) && conf.split(enterKey).length > size){
                return conf;
            }else{
                logger.error("模板文件错误");
            }
        }
        return defaultTemplate;
    }


    /**
     *
     */
    public static void setNginxConf() {
        Map<String, Object> serviceMap;
        Map<String, Object> dataMap;
        String[] template;
        Map labels;
        String domain;
        String upstream;
        HashSet<String> pushServers;
        String confData;
        String port;
        TreeSet<String> ts;
        String md5;
        String tempMd5;
        String file;
        String tempFile;
        DockerGetUtil dockerGetUtil = new DockerGetUtil();
        ArrayList<String>  services = DockerScheduler.getServices();
        if (null == services){
            return;
        }
        for (String service : services) {
            serviceMap = dockerGetUtil.services(service);
            dataMap = (Map<String, Object>) serviceMap.get("Spec");
            labels = (Map<String, String>) dataMap.get("Labels");
            if (null == labels) {
                continue;
            }
            domain = (String) labels.get("domain");
            ServiceInfoUtil.getPublishPort(serviceMap, ";");
            port = ServiceInfoUtil.getPort(serviceMap);
            if (CheckUtil.checkString(domain) && CheckUtil.checkString(port)) {
                file = domain.concat(".conf");
                upstream = nginxConfigPath.concat(file);
                confData = "";
                template = getNginxTemplate(domain).split("\n");
                for (int i = 0; i < template.length; i++) {
                    if (template[i].contains(nodeKey)) {
                        pushServers = DockerScheduler.getPushServer();
                        ts = new TreeSet<>(pushServers);
                        ts.comparator();
                        for (String server : ts) {
                            confData += template[i].replace(nodeKey, server + ':' + port).concat("\n");
                        }
                    } else {
                        confData += template[i].replace(domainEky, domain).concat("\n");
                    }
                }
                if (!new File(upstream).exists()){
                    FileIoUtil.writeFile(upstream, confData, false);
                    RunCommandUtil.runScript(nginxReloadCmd);
                }
                md5 = Md5Util.getMd5ByFile(new File(upstream));
                tempFile = tempPath.concat(file);
                FileIoUtil.writeFile(tempFile, confData, false);
                tempMd5 = Md5Util.getMd5ByFile(new File(tempFile));
                if (CheckUtil.checkString(md5) && CheckUtil.checkString(tempMd5)) {
                    if (!tempMd5.equals(md5) && confData.split(enterKey).length > size) {
                        FileIoUtil.writeFile(upstream, confData, false);
                        RunCommandUtil.runScript(nginxReloadCmd);
                    }
                }
            }
        }
    }
}
