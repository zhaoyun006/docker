package com.cloud.agent.thread;

import com.cloud.agent.util.LogUtil;
import com.cloud.agent.util.RunCommandUtil;

import java.util.List;

/**
 * <p></p>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 * 脚本使用线程方式调用，设置超时时间关闭线程，防止把程序拖挂
 * @author zhaozq
 * @version 1.0
 * @since 1.0
 */

public class RunCmdThread extends Thread{

    private String cmd;
    private List<String> result;

    public RunCmdThread(String cmd, List<String> result) {
        this.cmd = cmd;
        this.result = result;
        super.setName("RunCmdThread");
    }

    @Override
    public void run(){
        String cmdResult = RunCommandUtil.runScript(cmd, 1);
        if (cmdResult != null && cmdResult.length()>0){
            LogUtil.info("运行" + cmd + " " + cmdResult);
            result.add(cmdResult);
            return;
        }else{
            LogUtil.info("运行" + cmd + " ");
            result.add("0");
        }
    }
}
