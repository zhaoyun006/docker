package com.cloud.agent.controller;

import com.cloud.agent.configure.Configure;
import com.cloud.agent.entity.DockerCommandEntity;
import com.cloud.agent.entity.KvmCommandEntity;
import com.cloud.agent.service.KvmCommandService;
import com.cloud.agent.util.HttpClientIpAddress;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.cloud.agent.service.DockerCommandService.runCommand;

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
@RestController
public class RemoteApiController {

    /**
     * @param cmds
     *
     * @return
     */
    ArrayList getCmd(String cmds) {
        ArrayList<String> list = new ArrayList<>();
        String[] command = cmds.split(",");
        for (String cmd : command) {
            if (!list.contains(cmd)) {
                list.add(cmd);
            }
        }
        return list;
    }

    /**
     *
     * @param request
     * @return
     */
    boolean checkAllowIp(HttpServletRequest request){
        String ip = HttpClientIpAddress.getIpAddr(request);
        ArrayList list = new ArrayList();
        String allowIp = Configure.get("allowIp", "agent.conf");
        if (allowIp != null && allowIp.length()>5){
            String[] ips = allowIp.split(",");
            for (String ipaddr: ips){
                list.add(ipaddr);
            }
        }else{
            return true;
        }
        if (list.contains(ip)){
            return true;
        }
        return false;
    }

    /**
     * 执行命令接口
     *
     * @return
     */
    @RequestMapping(value = "/api/docker/commands", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String commands(DockerCommandEntity entity, HttpServletRequest request) {
        if (!checkAllowIp(request)){
            return "{}";
        }
        String server = request.getLocalAddr();
        entity.setServer(server);
        Gson gson = new Gson();
        Map map = new HashMap();
        if (entity.getCommands().contains(",")) {
            ArrayList<String> command = getCmd(entity.getCommands());
            for (String cmd : command) {
                try {
                    map.put(cmd, runCommand(cmd, entity));
                } catch (Exception e) {
                    e.printStackTrace();
                    map.put(cmd, "");
                }
            }
        } else {
            try {
                map.put(entity.getCommands(), runCommand(entity.getCommands(), entity));
            } catch (Exception e) {
                e.printStackTrace();
                map.put(entity.getCommands(), "");
            }
        }

        return gson.toJson(map);
    }

    /**
     * kvm执行命令接口
     *
     * @return
     */
    @RequestMapping(value = "/api/kvm/commands", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String kvmCommands(KvmCommandEntity entity, HttpServletRequest request) {
        if (!checkAllowIp(request)){
            return "{}";
        }
        String server = request.getLocalAddr();
        entity.setServer(server);
        Gson gson = new Gson();
        Map map = new HashMap();
        if (entity.getCommands().contains(",")) {
            ArrayList<String> command = getCmd(entity.getCommands());
            for (String cmd : command) {
                try {
                    map.put(cmd, KvmCommandService.runCommand(cmd, entity));
                } catch (Exception e) {
                    e.printStackTrace();
                    map.put(cmd, "");
                }
            }

        } else {
            try {
                map.put(entity.getCommands(), KvmCommandService.runCommand(entity.getCommands(), entity));
            } catch (Exception e) {
                e.printStackTrace();
                map.put(entity.getCommands(), "");
            }
        }
        return gson.toJson(map);
    }
}
