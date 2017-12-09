package com.cloud.sms.docker.network.controller;

import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchMap;
import com.asura.framework.dao.mybatis.paginator.domain.PageBounds;
import com.cloud.configure.RedisKey;
import com.cloud.sms.docker.app.entity.DockerContainerEntity;
import com.cloud.sms.docker.common.response.PageResponse;
import com.cloud.sms.docker.common.response.ResponseVo;
import com.cloud.sms.docker.log.controller.LogController;
import com.cloud.sms.docker.network.entity.DockerCloudNetworkEntity;
import com.cloud.sms.docker.network.service.DockerCloudNetworkService;
import com.cloud.sms.docker.server.entity.DockerCloudGroupsEntity;
import com.cloud.sms.docker.server.entity.DockerCloudServerEntity;
import com.cloud.sms.docker.server.service.DockerCloudGroupsService;
import com.cloud.sms.docker.server.service.DockerCloudServerService;
import com.cloud.sms.docker.service.entity.GetContainersEntity;
import com.cloud.sms.docker.service.util.ServiceInfoUtil;
import com.cloud.sms.docker.util.CheckUtil;
import com.cloud.sms.docker.util.DateUtil;
import com.cloud.sms.docker.util.PermissionsCheck;
import com.cloud.sms.docker.util.RedisUtil;
import com.cloud.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyun on 2017/10/08 07:48.
 * 网络管理
 * @author zhaoyun
 */
@Controller
@RequestMapping("/network/")
public class NetworkController {

    @Autowired
    private DockerCloudNetworkService networkService;

    @Autowired
    private PermissionsCheck permissionsCheck;

    @Autowired
    private DockerCloudGroupsService groupsService;

    @Autowired
    private LogController logController;

    @Autowired
    private DockerCloudServerService serverService;

    /**
     *
     * @return
     */
    @RequestMapping("add")
    public String add(ModelMap modelMap){
        modelMap.addAttribute("groups", groupsService.getListData(new SearchMap<>(), "selectGroups"));
        return "/network/add";
    }


    /**
     * 删除子网
     * @param networkId
     * @param request
     * @return
     */
    @RequestMapping("delete/{networkId}")
    @ResponseBody
    public ResponseVo delete(@PathVariable int networkId, HttpServletRequest request){
        DockerCloudNetworkEntity  networkServiceById = networkService.findById(networkId, DockerCloudNetworkEntity.class);
        RedisUtil redisUtil = new RedisUtil();
        int containerSize = 10;
        String containers = redisUtil.get(RedisKey.networkContainers.concat(networkServiceById.getName()));
        if (CheckUtil.checkString(containers) && containers.length() > containerSize){
            return ResponseVo.responseError("网络 ".concat(networkServiceById.getName()).concat("  还有在运行的容器,不能删除,请将所有依赖容器删除后，删除网络 ".concat(networkServiceById.getName())));
        }
        String ok = "ok";
        ResponseVo responseVo = getMasterServer(networkServiceById.getGroupsId());
        if (!ok.equals(responseVo.getStatus())){
            return  responseVo;
        }
        DockerCloudServerEntity dockerCloudServerEntity = (DockerCloudServerEntity)responseVo.getData();
        String master = dockerCloudServerEntity.getServerAddress();
        String apiUrl = "http://".concat(master).concat(":").concat(dockerCloudServerEntity.getApiPort()).concat("/api/docker/network");
        String result = HttpUtil.sendPost(apiUrl.concat("?commands=removeNetwork"),  "param="+new Gson().toJson(networkServiceById));
        if (!CheckUtil.checkString(result)){
            return ResponseVo.responseError("调用Agent接口失败,请检查服务器agent是否正常运行" + apiUrl);
        }
        if (!result.contains(networkServiceById.getName())){
            return ResponseVo.responseError("返回失败" + result);
        }
        logController.saveOperLog(request, "删除网络" + new Gson().toJson(networkServiceById));
        networkService.delete(networkServiceById);
        return ResponseVo.responseError("删除 "+networkServiceById.getName()+" 网络完成");
    }


    /**
     *
     * @return
     */
    @RequestMapping("subnet")
    public String subnetwork(ModelMap modelMap){
        modelMap.addAttribute("groups", groupsService.getListData(new SearchMap<>(), "selectGroups"));
        return "/network/subnet";
    }

    /**
     * 网络详情
     * @return
     */
    @RequestMapping("detail/{name}")
    public String detail(@PathVariable String name, String networkIp, ModelMap modelMap, String clusterName){
        modelMap.addAttribute("name", name);
        modelMap.addAttribute("clusterName", clusterName);
        modelMap.addAttribute("networkIp", networkIp);
        return "/network/detail";
    }


    /**
     *
     * @param draw
     * @param start
     * @param length
     * @param name
     * @return
     */
    @RequestMapping(value = "detailData", produces = {"application/json"})
    @ResponseBody
    public String detailData(int draw, int start, int length, String name, String clusterName) {
        String containers;
        RedisUtil redisUtil = new RedisUtil();
        Jedis jedis = redisUtil.getJedis();

        containers = jedis.get(RedisUtil.app+"_"+RedisKey.networkContainers.concat(clusterName).concat(name));
        if (CheckUtil.checkString(containers)) {
            GetContainersEntity getContainersEntity  = ServiceInfoUtil.getContainers(containers, start, length);
            return PageResponse.getList(getContainersEntity.getDockerContainerEntity(), draw, getContainersEntity.getSize());
        }
        return PageResponse.getList(new ArrayList(), draw, 0);
    }

    /**
     *
     * @param draw
     * @param start
     * @param length
     * @param request
     * @return
     */
    @RequestMapping(value = "listData", produces = {"application/json"})
    @ResponseBody
    public String list(int draw, int start, int length, HttpServletRequest request, int groupsId) {
        PageBounds pageBounds = PageResponse.getPageBounds(length, start);
        SearchMap searchMap = new SearchMap();
        if (groupsId > 0){
            searchMap.put("groupsId", groupsId);
        }
       PagingResult<DockerCloudNetworkEntity> result =  networkService.findAll(searchMap, pageBounds, "selectByAll");
        ArrayList list = new ArrayList();
        RedisUtil redisUtil = new RedisUtil();
        String containers;
        Gson gson = new Gson();
        ArrayList<DockerContainerEntity> containerSet;
        Jedis jedis = redisUtil.getJedis();
        DockerContainerEntity containerEntity ;
        Type containerType = new TypeToken<ArrayList<DockerContainerEntity>>() {
        }.getType();
        String app = RedisUtil.app + "_";
        for (DockerCloudNetworkEntity entity:result.getRows()){
            containers = jedis.get(app + RedisKey.networkContainers.concat(entity.getGroupsName()).concat(entity.getEntId()).concat(entity.getName()));
            if (CheckUtil.checkString(containers) && containers.length() > 10){
                containerSet = gson.fromJson(containers, containerType);
                entity.setContainerNumber(containerSet.size());
                containerEntity = containerSet.get(0);
                entity.setSubnetId(containerEntity.getNetworkId());
            }else{
                entity.setSubnetId(jedis.get(app.concat(RedisKey.networkInfo).concat(entity.getName())));
                entity.setContainerNumber(0);
            }
            list.add(entity);
        }
       return PageResponse.getList(list, draw, (int)result.getTotal());
    }

    /**
     *
     * @param groupsId
     * @return
     */
    ResponseVo getMasterServer(int groupsId){
        DockerCloudGroupsEntity dockerCloudGroupsEntity = groupsService.findById(groupsId, DockerCloudGroupsEntity.class);
        RedisUtil redisUtil = new RedisUtil();
        String master = redisUtil.get(RedisKey.masterServersList.concat(dockerCloudGroupsEntity.getGroupsName()).concat(dockerCloudGroupsEntity.getEntId()));
        if (!CheckUtil.checkString(master)){
            return ResponseVo.responseError("无法获取到master节点,请确认master服务器agent正常");
        }
        SearchMap searchMap = new SearchMap();
        searchMap.put("serverAddress", master.trim());
        List<DockerCloudServerEntity> servers = serverService.getListData(searchMap, "selectByAll");
        if (servers.size() < 1){
            return ResponseVo.responseError("主机配置中没有找到master服务器的配置,master服务器为 "+ master);
        }
        DockerCloudServerEntity dockerCloudServerEntity = servers.get(0);
        return ResponseVo.responseOk(dockerCloudServerEntity);
    }

    /**
     *
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResponseVo save(DockerCloudNetworkEntity entity, HttpServletRequest  request, String create){
        ResponseVo responseVo = getMasterServer(entity.getGroupsId());
        if (!responseVo.getStatus().equals("ok")){
            return responseVo;
        }
        Gson gson = new Gson();
        DockerCloudServerEntity dockerCloudServerEntity = (DockerCloudServerEntity)responseVo.getData();
        String master = dockerCloudServerEntity.getServerAddress();
        String apiUrl = "http://".concat(master).concat(":").concat(dockerCloudServerEntity.getApiPort()).concat("/api/docker/network");
        String result = HttpUtil.sendPost(apiUrl.concat("?commands=createNetwork"),  "param="+gson.toJson(entity));
        if (!CheckUtil.checkString(result)){
            String msg = "调用Agent接口失败,请检查服务器agent是否正常运行" + apiUrl;
            logController.saveOperLog(request, msg);
            return ResponseVo.responseError(msg);
        }
        Map<String, String> map = gson.fromJson(result, HashMap.class);
        result = map.get("createNetwork");
        int subLength = 25;
        if (result.trim().length() != subLength){
            String msg = "创建网络失败 "+ result;
            logController.saveOperLog(request, msg);
            return ResponseVo.responseError(msg);
        }
        if (CheckUtil.checkString(create)){
            return ResponseVo.responseOk("ok");
        }
        String user = permissionsCheck.getLoginUser(request.getSession());
        entity.setLastModifyUser(user);
        String time = DateUtil.getDate(DateUtil.TIME_FORMAT);
        entity.setLastModifyUser(time);
        entity.setSubnetId("未知");
        if (entity.getNetworkId() != null && entity.getNetworkId() > 0){
            networkService.update(entity);
        }else {
            entity.setContainerNumber(0);
            entity.setCreateTime(time);
            entity.setCreateUser(user);
            networkService.save(entity);
        }
        return ResponseVo.responseOk("创建网络"+entity.getName()+ " 成功");
    }
}
