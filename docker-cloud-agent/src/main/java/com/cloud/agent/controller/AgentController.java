package com.cloud.agent.controller;


import com.cloud.agent.configure.Configure;
import com.cloud.agent.util.FileIoUtil;
import com.cloud.agent.util.HttpUtil;
import com.cloud.agent.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;

/**
 * <p></p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author zhaozq14
 * @version 1.0
 * @Date 2016-08-13 06:31:00
 */
@RestController
public class AgentController {

    private final Logger logger = LoggerFactory.getLogger(AgentController.class);



    /**
     * 更新jar包，通过到指定的服务器下载新jar包更新
     */
    @RequestMapping("update")
    @ResponseBody
    public String update(String username,String password) throws  Exception{
        Runtime runtime = Runtime.getRuntime();
        // 获取用户名,密码
        String configUser = Configure.get("username", "agent.conf");
        String configPassword = Configure.get("password", "agent.conf");

        String noUserMsg = "用户名或密码错误,更新程序退出";
        if(configPassword.length()>1 && configUser.length()>1){
            if(username==null||password==null){
                logger.info(noUserMsg);
                return noUserMsg;
            }
            if(!username.equals(configUser)&&password.equals(configPassword)){
                logger.info(noUserMsg);
                return noUserMsg;
            }
        }

        String url = Configure.get("updateUrl", "agent.conf");
        if(url.length()< 3){
            logger.error("下载配置没有,updateUrl=http://xx.xx/file/");
            return "";
        }

        String path = Configure.getJarPath();

        // 临时文件定义
        String tempFile = "/tmp/agent.jar";
        String tempMd5 = "/tmp/agent.md5";

        logger.info("下载更新包开始...."+url);
        HttpUtil.downloadNet(url,tempFile);
        logger.info("下载更新包接收....");
        File f = new File(tempFile);


        if(f.exists()){

            // 获取文件的md5
            String md5 = Md5Util.getMd5ByFile(f);
            logger.info("更新包的文件md5值为:"+md5);

            // 获取提供更新包下载的MD5
            HttpUtil.downloadNet(url+".md5",tempMd5);
            ArrayList<String> fmd5 = FileIoUtil.readTxtFile(tempMd5);
            String rmd5 = "";

            String md5Msg = "没有获取到更新包的MD5值,更新程序结束";
            if(fmd5.size()>0){
                String[] rmd5List = fmd5.get(0).split(" ");
                rmd5 = rmd5List[0].trim();
                logger.info("提供的更新包md5值为:"+rmd5);
            }else {
                logger.info(md5Msg);
                return md5Msg;
            }

            // 对比已经下载的更新包的MD5值和远程文件提供的md5
            String exitMsg = "更新包提供的MD5和实际下载的MD5值不一致,更新退出";
            if(rmd5.equals(md5)){
                logger.info("更新包的md5和提供的md5一致");
            }else{
                logger.info(exitMsg);
                return exitMsg;
            }

            FileIoUtil.copyFile(tempFile,path);
            logger.info("更新文件完成");
            // 删除文件
            f.delete();
        }else{
            logger.error("下载文件失败");
        }

        String pid = System.getProperty("PID");
        logger.info("执行杀死进程"+pid);
        runtime.exec("kill -9 "+ pid);
        logger.info("执行杀死进程完成");
        return "ok";

    }
}