package com.cloud.sms.docker.service.util;

import com.asura.framework.base.paging.SearchMap;
import com.cloud.configure.RedisKey;
import com.cloud.sms.docker.app.entity.DockerContainerEntity;
import com.cloud.sms.docker.common.response.PageResponse;
import com.cloud.sms.docker.service.entity.DockerCloudServiceEntity;
import com.cloud.sms.docker.service.entity.GetContainersEntity;
import com.cloud.sms.docker.service.service.DockerCloudServiceService;
import com.cloud.sms.docker.service.service.DockerServiceEntity;
import com.cloud.sms.docker.util.CheckUtil;
import com.cloud.sms.docker.util.RedisUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyun on 2017/10/6.
 */
public class ServiceInfoUtil {

    /**
     *
     * @param serviceName
     * @param serviceService
     * @param entId
     * @return
     */
    public static DockerServiceEntity getServiceInfo(String serviceName, DockerCloudServiceService serviceService, String entId){
        SearchMap searchMap = new SearchMap();
        searchMap.put("serviceName", serviceName);
        searchMap.put("entId", entId);
        List<DockerCloudServiceEntity> serviceEntities1 = serviceService.getListData(searchMap, "selectByAll");
        if (serviceEntities1.size() < 1){
            return new DockerServiceEntity();
        }
        DockerCloudServiceEntity serviceEntityDb = serviceEntities1.get(0);
        String clusterName = serviceEntityDb.getGroupsName()+serviceEntityDb.getEntId();
        RedisUtil redisUtil = new RedisUtil();
        String serviceData = redisUtil.get(RedisKey.serviceInfo.concat(clusterName).concat(serviceName));
        if (CheckUtil.checkString(serviceData)) {
            Gson gson = new Gson();
            DockerServiceEntity dockerServiceEntity = gson.fromJson(serviceData, DockerServiceEntity.class);
            String gsonData = serviceEntityDb.getGsonData();
            DockerServiceEntity dockerCloudServerEntity = gson.fromJson(gsonData, DockerServiceEntity.class);
            dockerServiceEntity.setAppDescription(dockerCloudServerEntity.getAppDescription());
            if (dockerServiceEntity.getLimitMemory() != null && !"无".equals(dockerServiceEntity.getLimitMemory())) {
                dockerServiceEntity.setLimitMemory((Long.valueOf(dockerServiceEntity.getLimitMemory()) / 1024 / 1024) + "");
            }
            if (null != dockerCloudServerEntity.getNetwork()) {
                dockerServiceEntity.setNetwork(dockerCloudServerEntity.getNetwork());
            }
            if (null != dockerCloudServerEntity.getScaleOn() && dockerCloudServerEntity.getScaleOn() > 0) {
                dockerServiceEntity.setScaleCpu(dockerCloudServerEntity.getScaleCpu());
                dockerServiceEntity.setScaleMin(dockerCloudServerEntity.getScaleMin());
                dockerServiceEntity.setScaleMax(dockerCloudServerEntity.getScaleMax());
                dockerServiceEntity.setScaleMem(dockerCloudServerEntity.getScaleMem());
                dockerServiceEntity.setScaleOn(dockerCloudServerEntity.getScaleOn());
            }
            if (null != dockerCloudServerEntity.getEnv()){
                dockerServiceEntity.setEnv(dockerCloudServerEntity.getEnv());
            }
            if (null != dockerCloudServerEntity.getPort()){
                dockerServiceEntity.setPort(dockerCloudServerEntity.getPort());
            }
            if (null != dockerCloudServerEntity.getMount()){
                dockerServiceEntity.setMount(dockerCloudServerEntity.getMount());
            }
            dockerServiceEntity.setServiceName(serviceName);
            dockerServiceEntity.setEntname(serviceEntityDb.getEntname());
            dockerServiceEntity.setEntId(serviceEntityDb.getEntId());
            dockerServiceEntity.setGroupsId(serviceEntityDb.getGroupsId());
            dockerServiceEntity.setGroupsName(serviceEntityDb.getGroupsName());
            System.out.println("dockerServiceEntity" + new Gson().toJson(dockerServiceEntity));
            return dockerServiceEntity;
        }
        return null;
    }

    /**
     *
     * @param containers
     * @param start
     * @param length
     * @return
     */
    public static GetContainersEntity getContainers(String containers, int start, int length){
        GetContainersEntity getContainersEntity= new GetContainersEntity();
        Gson gson = new Gson();
        Type containerType = new TypeToken<ArrayList<DockerContainerEntity>>() {
        }.getType();
        ArrayList<DockerContainerEntity>  containerSet = gson.fromJson(containers, containerType);
        int count = 1;
        ArrayList<DockerContainerEntity> dockerContainerEntities = new ArrayList<>();
        for (DockerContainerEntity entity : containerSet) {
            // 分页检查
            if (!PageResponse.checkPaging(start, length, count)) {
                count += 1;
                continue;
            }
            count += 1;
            dockerContainerEntities.add(entity);
        }
        getContainersEntity.setSize(containerSet.size());
        getContainersEntity.setDockerContainerEntity(dockerContainerEntities);
        return getContainersEntity;
    }
}
