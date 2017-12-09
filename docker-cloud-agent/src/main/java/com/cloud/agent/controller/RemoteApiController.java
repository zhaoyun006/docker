package com.cloud.agent.controller;

import com.cloud.agent.configure.Configure;
import com.cloud.agent.entity.*;
import com.cloud.agent.util.HttpClientIpAddress;
import com.cloud.agent.util.RunCommandUtil;
import com.cloud.agent.util.docker.DockerApi;
import com.cloud.agent.util.docker.DockerCmd;
import com.cloud.agent.util.docker.DockerServiceCmd;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.cloud.agent.service.DockerCommandService.*;

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

    Logger logger = LoggerFactory.getLogger(RemoteApiController.class);

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
     *runImagesCmd
     * @return
     */
    @RequestMapping(value = "/api/images/commands", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String images(String commands, String param, HttpServletRequest request){
        if (!checkAllowIp(request)){
            return "{}";
        }
        Gson gson = new Gson();
        Map map = new HashMap(10);
        try {
            ImagesBuildInfoEntity entity = gson.fromJson(param, ImagesBuildInfoEntity.class);
            map.put(commands, runImagesCmd(commands, entity));
        } catch (Exception e) {
            logger.error("images faild ", e);
            map.put(commands, "");
        }
        return gson.toJson(map);
    }


    /**
     *
     * @return
     */
    @RequestMapping(value = "/api/server/info", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String info() {
        ServerInfoEntity serverInfoEntity = new ServerInfoEntity();
        int cpu =  Runtime.getRuntime().availableProcessors();
        serverInfoEntity.setCpu(cpu+"");
        serverInfoEntity.setMemory(RunCommandUtil.runScript("awk '$0 ~ /MemTotal/ {print $2/ 1024/1024\"GB\"}'  /proc/meminfo ").trim());
        return new Gson().toJson(serverInfoEntity);
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
     *
     * @param commands
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "/api/k8s", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String k8sCommands(String commands, HttpServletRequest request, String param){
        if (!checkAllowIp(request)){
            return "{}";
        }
        Gson gson = new Gson();
        Map map = new HashMap();
        try {
            DockerServiceEntity entity = gson.fromJson(param, DockerServiceEntity.class);
            map.put(commands, k8sRun(commands, entity));
        } catch (Exception e) {
            e.printStackTrace();
            map.put(commands, "");
        }

        return gson.toJson(map);
    }

    /**
     * 执行命令接口
     *
     * @return
     */
    @RequestMapping(value = "/api/docker/service", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String service(String commands , HttpServletRequest request, String param) {
        if (!checkAllowIp(request)){
            return "{}";
        }
        Gson gson = new Gson();
        Map map = new HashMap();
            try {
                DockerServiceEntity entity = gson.fromJson(param, DockerServiceEntity.class);
                map.put(commands, runServiceCommand(commands, entity));
            } catch (Exception e) {
                e.printStackTrace();
                map.put(commands, "");
            }

        return gson.toJson(map);
    }


    /**
     * 执行命令接口
     * @return
     */
    @RequestMapping(value = "/api/docker/network", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String network(String commands , HttpServletRequest request, String param) {
        if (!checkAllowIp(request)){
            return "{}";
        }
        Gson gson = new Gson();
        Map map = new HashMap();
        try {
            DockerNetworkEntity entity = gson.fromJson(param, DockerNetworkEntity.class);
            map.put(commands, runNetworkCommand(commands, entity));
        } catch (Exception e) {
            logger.error("network faild ", e);
            map.put(commands, "");
        }
        return gson.toJson(map);
    }

    /**
     *
     * stack管理接口
     * @return
     */
    @RequestMapping(value = "/api/docker/stack", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String stack(String commands , HttpServletRequest request, String param) {
        if (!checkAllowIp(request)){
            return "{}";
        }
        Gson gson = new Gson();
        Map map = new HashMap();
        try {
            DockerStackEntity entity = gson.fromJson(param, DockerStackEntity.class);
            map.put(commands, runStackCommand(commands, entity));
        } catch (Exception e) {
            e.printStackTrace();
            map.put(commands, "");
        }

        return gson.toJson(map);
    }

    /**
     * 删除容器
     * @return
     */
    @RequestMapping(value = "/containers/{id}/remove", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteContainer(@PathVariable String id, String user){
        logger.info("删除容器" + "/containers/".concat(id));
        String cmd = DockerCmd.containerRemove.replace("{0}", RunCommandUtil.getSecurityCmd(id.trim()));
        String result = RunCommandUtil.runScript(cmd);
        DockerApi.queue("删除容器"+id.substring(0,12), user, result);
        return result;
    }

    /**
     * 删除容器
     * @return
     */
    @RequestMapping("/containers/{id}/{action}")
    @ResponseBody
    public String startContainer(@PathVariable String id, @PathVariable String action, String user){
        String result = "";
        String start = "start";
        String stop = "stop";
        String info = "info";
        if (start.equals(action)){
            result = DockerApi.dockerPost("/containers/".concat(id).concat("/start"), "");
            DockerApi.queue("启动容器&nbsp;"+id.substring(0,12), user, result);
        }
        if (stop.equals(action)){
            result = DockerApi.dockerPost("/containers/".concat(id).concat("/stop"), "");
            DockerApi.queue("停止容器&nbsp;"+id.substring(0,12), user, result);
        }
        if (info.equals(action)){
            result  =  DockerApi.dockerInfo("containers/"+id+"/json");
        }
        return result;
    }


    /**
     * 删除服务,只能主节点删除
     * @return
     */
    @RequestMapping(value = "/services/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteServices(@PathVariable String id){
        logger.info("删除service ");
        String result = DockerApi.dockerDelete("/services/".concat(id), "");
        DockerApi.queue("删除服务"+id, "", result);
        return result;
    }


    /**
     *
     * @param id
     * @param number
     * @return
     */
    @RequestMapping(value = "/services/scaleService/{id}/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String scaleService(@PathVariable String id,  @PathVariable int number, String user){
        logger.info("扩容缩容操作 ");
        String scale = RunCommandUtil.getSecurityCmd(id) + "="+ number;
        String result =  RunCommandUtil.runScript(  DockerServiceCmd.scaleService.replace("{0}", scale));
        DockerApi.queue("扩缩容:"+id, user, result);
        return result;
    }
}
