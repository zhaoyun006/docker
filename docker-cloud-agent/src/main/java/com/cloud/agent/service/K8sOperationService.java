package com.cloud.agent.service;

import com.cloud.agent.entity.DockerServiceEntity;
import com.cloud.agent.util.CheckUtil;
import com.cloud.agent.util.k8s.K8sRcTemplate;

/**
 * Created by zhaoyun on 2017/12/8.
 */
public class K8sOperationService {

    private static final String NGINX = "nginx";
    /**
     *
     * @param name
     * @param entity
     * @param app
     * @return
     */
    public static String getServiceYaml(String name, DockerServiceEntity entity, String app){
        String yaml = K8sRcTemplate.service;
        yaml = yaml.replace("${app}", app);
        yaml = yaml.replace("${name}", name);
        yaml = yaml.replace("${port}", entity.getPort());
        if (CheckUtil.checkString(entity.getPublish())){
            yaml = yaml.replace("${type}" ,"type: NodePort");
        }
        if (CheckUtil.checkString(entity.getLoadblanceTp())){
            if (NGINX.equals(entity.getLoadblanceTp())) {
                yaml = yaml.replace("${type}", "type: NodePort");
            }
        }
        yaml = yaml.replace("${type}","");
        yaml = yaml.replace("${publish}",entity.getPort());
        return yaml;
    }

    /**
     *
     * @param name
     * @param entity
     * @param app
     * @return
     */
    public static String getRcYaml(String name, DockerServiceEntity entity, String app) {
        String yaml = K8sRcTemplate.rc;
        yaml = yaml.replace("${name}", name);
        yaml = yaml.replace("${domain}", entity.getDomain());
        yaml = yaml.replace("${app}", app);
        if (CheckUtil.checkString(entity.getScaleMax())) {
            yaml = yaml.replace("${max-scale}", entity.getScaleMax());
        } else {
            yaml = yaml.replace("${max-scale}", entity.getReplicas());
        }
        if (CheckUtil.checkString(entity.getScaleMin())) {
            yaml = yaml.replace("${min-scale}", entity.getScaleMin());
        } else {
            yaml = yaml.replace("${min-scale}", entity.getReplicas());
        }
        yaml = yaml.replace("${replicas}", entity.getReplicas());
        yaml = yaml.replace("${image}", entity.getImage());
        yaml = yaml.replace("${cpu}", entity.getLimitCpu());
        yaml = yaml.replace("${memory}", (Long.valueOf(entity.getLimitMemory()) / 1024 / 1024) + "Mi");
        if (CheckUtil.checkString(entity.getPort())) {
            yaml = yaml.replace("${port}", entity.getPort());
        }

        if (CheckUtil.checkString(entity.getEnv())) {
            String env = "env:\n";
            String[] envs;
            String[] envData = entity.getEnv().split("--env");
            for (int i = 0; i < envData.length; i++) {
                envs = envData[i].split("=");
                if (envs.length > 1) {
                    env += K8sRcTemplate.env.replace("${name}", envs[0]).replace("${value}", envs[1]);
                }
            }
            yaml = yaml.replace("${env}", env);
        }
        if (yaml.contains("${env}")) {
            yaml = yaml.replace("${env}", "");
        }
        return yaml;
    }
}
