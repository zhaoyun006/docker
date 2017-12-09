package com.cloud.agent.util;

import com.cloud.agent.configure.Configure;
import com.cloud.agent.thread.RunCmdThread;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
 * @since 1.0
 */

public class RunCommandUtil {

    private static Logger logger = Logger.getLogger(RunCommandUtil.class);

    public static String runScript(String command, int tid){
        String result = "";
        String line = "";
        try {
            List<String> cmds = new ArrayList<String>();
            cmds.add("sh");
            cmds.add("-c");
            cmds.add(command);
            ProcessBuilder pb =new ProcessBuilder(cmds);
            Process process = pb.start();
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                if (Configure.get("debug","agent.conf").equals("true")) {
                    logger.info(line);
                }
                result += line +"\n";
            }
            is.close();
            isr.close();
            br.close();
            return result;
        }catch (Exception e){
            logger.error("脚本执行出错", e);
        }
        return result;
    }

    /**
     * 最多15秒等待
     * @param command
     * @return
     */
    public static String runScript(String command){
        List<String> list = new ArrayList();
        RunCmdThread thread  = new RunCmdThread(command, list);
        thread.start();
        long start = System.currentTimeMillis() /1000;
        while (1 == 1){
            if (list.size() > 0){
                break;
            }
            if (System.currentTimeMillis()/1000 - start > 35) {
                logger.error("线程超时:" + command);
                if (thread.isAlive()){
                    try {
                        thread.interrupt();
                    }catch (Exception e){
                    }
                }
                list.add("time out " );
                break;
            }
            try {
                Thread.sleep(50);
            }catch (Exception e){
            }
        }
        if (list.size()>0) {
            return list.get(0);
        }else{
            return "";
        }
    }

    /**
     *
     * @param string
     * @return
     */
    public static  String getSecurityCmd(String string){
        string = string.replaceAll("&", "");
        string = string.replaceAll("`", "");
        string = string.replaceAll(";", "");
        string = string.replaceAll(">", "");
        string = string.replaceAll("<", "");
        string = string.replaceAll(" ", "");
        string = string.replace("\n", "");
        return string;
    }
}
