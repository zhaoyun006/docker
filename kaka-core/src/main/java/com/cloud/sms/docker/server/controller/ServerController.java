package com.cloud.sms.docker.server.controller;

import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchMap;
import com.asura.framework.dao.mybatis.paginator.domain.PageBounds;
import com.cloud.configure.RedisKey;
import com.cloud.sms.docker.common.response.PageResponse;
import com.cloud.sms.docker.common.response.ResponseVo;
import com.cloud.sms.docker.server.entity.DockerCloudServerEntity;
import com.cloud.sms.docker.server.entity.ServerInfoEntity;
import com.cloud.sms.docker.server.service.DockerCloudGroupsService;
import com.cloud.sms.docker.server.service.DockerCloudServerService;
import com.cloud.sms.docker.util.CheckUtil;
import com.cloud.sms.docker.util.DateUtil;
import com.cloud.sms.docker.util.PermissionsCheck;
import com.cloud.sms.docker.util.RedisUtil;
import com.cloud.util.HttpUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by zhaoyun on 2017/9/26.
 */
@Controller
@RequestMapping("/server/")
public class ServerController {

    @Autowired
    private DockerCloudServerService serverService;

    @Autowired
    private PermissionsCheck permissionsCheck;

    @Autowired
    private DockerCloudGroupsService groupsService;


    /**
     *
     * @param modelMap
     * @param serverId
     * @return
     */
    @RequestMapping("add")
    public String add(ModelMap modelMap, int serverId){
        SearchMap searchMap = new SearchMap();
        modelMap.addAttribute("groups", groupsService.getListData(searchMap, "selectGroups"));
        modelMap.addAttribute("configs", new DockerCloudServerEntity());
        if (serverId > 0){
            DockerCloudServerEntity entity = serverService.findById(serverId, DockerCloudServerEntity.class);
            modelMap.addAttribute("configs", entity);
        }
        return "server/add";
    }

    /**
     *
     * @return
     */
    @RequestMapping("list")
    public String list(ModelMap modelMap, String groups){
        modelMap.addAttribute("groupsName", "");
        if (CheckUtil.checkString(groups)){
            modelMap.addAttribute("groupsName", groups);
        }
        modelMap.addAttribute("groups", groupsService.getListData(new SearchMap(), "selectGroups"));
        return "server/list";
    }

    /**
     *
     * @param entity
     * @param session
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResponseVo save(DockerCloudServerEntity entity, HttpSession session){
        String info = HttpUtil.sendGet("http://"+entity.getServerAddress()+":"+entity.getApiPort()+"/api/server/info");
        if (!CheckUtil.checkString(info)){
            return ResponseVo.responseError("添加服务器失败,请先启动agent后再添加服务器");
        }
        ServerInfoEntity serverInfoEntity = new Gson().fromJson(info,  ServerInfoEntity.class);
        String user = permissionsCheck.getLoginUser(session);
        entity.setLastModifyUser(user);
        entity.setLastModifyTime(DateUtil.getDate(DateUtil.TIME_FORMAT));
        if (entity.getServerId() != null && entity.getServerId() > 0){
            serverService.update(entity);
        }else {
            entity.setCpuNumber(serverInfoEntity.getCpu());
            entity.setMemSize(serverInfoEntity.getMemory());
            entity.setImages("0");
            entity.setInstance("0");
            serverService.save(entity);
        }
        return ResponseVo.responseOk("添加服务器成功");
    }

    /**
     * 服务器数据
     * @return
     */
    @RequestMapping(value = "data", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String data(int draw, int start, int length, HttpServletRequest request, String groups, String types) {
        PageBounds pageBounds = PageResponse.getPageBounds(length, start);
        SearchMap searchMap = new SearchMap();
        if (CheckUtil.checkString(types)){
            searchMap.put("type", types);
        }
        if (CheckUtil.checkString(groups)){
            searchMap.put("groups", groups);
        }
        String search = request.getParameter("search[value]");
        if (CheckUtil.checkString(search)){
            searchMap.put("key", search);
        }
        Gson gson = new Gson();
        RedisUtil redisUtil = new RedisUtil();
        Jedis jedis = redisUtil.getJedis();
        String app = RedisUtil.app.concat("_");
        String infoData;
        String master;
        String masterKey = app.concat(RedisKey.serverIsMaster);
        ArrayList<DockerCloudServerEntity> list = new ArrayList<>();
        DockerCloudServerEntity dockerCloudServerEntity ;
        PagingResult<DockerCloudServerEntity> result = serverService.findAll(searchMap, pageBounds, "selectByAll");
        for (DockerCloudServerEntity serverEntity:result.getRows()){
            infoData = jedis.get(app.concat(RedisKey.serverContainerInfo).concat(serverEntity.getServerAddress()));
            if (CheckUtil.checkString(infoData)){
                dockerCloudServerEntity = gson.fromJson(infoData, DockerCloudServerEntity.class);
            }else{
                dockerCloudServerEntity = new DockerCloudServerEntity();
                dockerCloudServerEntity.setImages("未知");
                dockerCloudServerEntity.setInstance("未知");
                dockerCloudServerEntity.setNoRuning("未知");
                dockerCloudServerEntity.setPaused("未知");
                dockerCloudServerEntity.setHostname("未知");
                dockerCloudServerEntity.setCpuNumber("未知");
                dockerCloudServerEntity.setMemSize("未知");
                dockerCloudServerEntity.setStatus("未知");
            }
            master = jedis.get(masterKey.concat(serverEntity.getServerAddress()));
            if (CheckUtil.checkString(master)){
                dockerCloudServerEntity.setIsMaster("<span class='text-info'>是</span>");
            }else {
                dockerCloudServerEntity.setIsMaster("<span class='text-danger'>否</span>");
            }
            dockerCloudServerEntity.setComm(serverEntity.getComm());
            dockerCloudServerEntity.setGroups(serverEntity.getGroups());
            dockerCloudServerEntity.setServerId(serverEntity.getServerId());
            dockerCloudServerEntity.setServerAddress(serverEntity.getServerAddress());
            dockerCloudServerEntity.setLastModifyTime(serverEntity.getLastModifyTime());
            dockerCloudServerEntity.setLastModifyUser(serverEntity.getLastModifyUser());
            dockerCloudServerEntity.setCluterTp(serverEntity.getCluterTp());
            list.add(dockerCloudServerEntity);
        }
        return PageResponse.getList(list, draw, (int)result.getTotal());
    }


}
