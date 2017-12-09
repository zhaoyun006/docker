package com.cloud.agent.entity;


/**
 * <p></p>
 * <p/>
 * <PRE>
 * <BR>
 * <BR>-----------------------------------------------
 * <BR>
 * </PRE>
 *
 * @author zhaozq14
 * @version 1.0
 * @date 2017-11-23 16:00:05
 * @since 1.0
 */
public class DockerCloudImagePullLogEntity {
    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_id
     * Comment: 
     * @param pullId the value for docker_cloud_image_pull_log.pull_id
     */

    private Integer pullId;


    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.image_name
     * Comment: 镜像名称
     * @param imageName the value for docker_cloud_image_pull_log.image_name
     */

    private String imageName;


    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.server_address
     * Comment: 服务器地址
     * @param serverAddress the value for docker_cloud_image_pull_log.server_address
     */

    private String serverAddress;


    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_time
     * Comment: 下载时间
     * @param pullTime the value for docker_cloud_image_pull_log.pull_time
     */

    private String pullTime;


    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_result
     * Comment: 下载结果
     * @param pullResult the value for docker_cloud_image_pull_log.pull_result
     */

    private String pullResult;


    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_user
     * Comment:
     * @param pullUser the value for docker_cloud_image_pull_log.pull_user
     */

    private String pullUser;


    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.env_id
     * Comment: 环境ID
     * @param envId the value for docker_cloud_image_pull_log.env_id
     */

    private Integer envId;


    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.service_name
     * Comment: 服务名称
     * @param serviceName the value for docker_cloud_image_pull_log.service_name
     */

    private String serviceName;


    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.ent_id
     * Comment: 环境ID
     * @param entId the value for docker_cloud_image_pull_log.ent_id
     */

    private Integer entId;


    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.ent_name
     * Comment: 环境名称
     * @param entName the value for docker_cloud_image_pull_log.ent_name
     */

    private Integer entName;


    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_id
     * Comment:
     * @param pullId the value for docker_cloud_image_pull_log.pull_id
     */
    public void setPullId(Integer pullId){
       this.pullId = pullId;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.image_name
     * Comment: 镜像名称
     * @param imageName the value for docker_cloud_image_pull_log.image_name
     */
    public void setImageName(String imageName){
       this.imageName = imageName;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.server_address
     * Comment: 服务器地址
     * @param serverAddress the value for docker_cloud_image_pull_log.server_address
     */
    public void setServerAddress(String serverAddress){
       this.serverAddress = serverAddress;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_time
     * Comment: 下载时间
     * @param pullTime the value for docker_cloud_image_pull_log.pull_time
     */
    public void setPullTime(String pullTime){
       this.pullTime = pullTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_result
     * Comment: 下载结果
     * @param pullResult the value for docker_cloud_image_pull_log.pull_result
     */
    public void setPullResult(String pullResult){
       this.pullResult = pullResult;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_user
     * Comment:
     * @param pullUser the value for docker_cloud_image_pull_log.pull_user
     */
    public void setPullUser(String pullUser){
       this.pullUser = pullUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.env_id
     * Comment: 环境ID
     * @param envId the value for docker_cloud_image_pull_log.env_id
     */
    public void setEnvId(Integer envId){
       this.envId = envId;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.service_name
     * Comment: 服务名称
     * @param serviceName the value for docker_cloud_image_pull_log.service_name
     */
    public void setServiceName(String serviceName){
       this.serviceName = serviceName;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.ent_id
     * Comment: 环境ID
     * @param entId the value for docker_cloud_image_pull_log.ent_id
     */
    public void setEntId(Integer entId){
       this.entId = entId;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.ent_name
     * Comment: 环境名称
     * @param entName the value for docker_cloud_image_pull_log.ent_name
     */
    public void setEntName(Integer entName){
       this.entName = entName;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_id
     * Comment:
     * @return the value of docker_cloud_image_pull_log.pull_id
     */
     public Integer getPullId() {
        return pullId;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.image_name
     * Comment: 镜像名称
     * @return the value of docker_cloud_image_pull_log.image_name
     */
     public String getImageName() {
        return imageName;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.server_address
     * Comment: 服务器地址
     * @return the value of docker_cloud_image_pull_log.server_address
     */
     public String getServerAddress() {
        return serverAddress;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_time
     * Comment: 下载时间
     * @return the value of docker_cloud_image_pull_log.pull_time
     */
     public String getPullTime() {
        return pullTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_result
     * Comment: 下载结果
     * @return the value of docker_cloud_image_pull_log.pull_result
     */
     public String getPullResult() {
        return pullResult;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_user
     * Comment:
     * @return the value of docker_cloud_image_pull_log.pull_user
     */
     public String getPullUser() {
        return pullUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.env_id
     * Comment: 环境ID
     * @return the value of docker_cloud_image_pull_log.env_id
     */
     public Integer getEnvId() {
        return envId;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.service_name
     * Comment: 服务名称
     * @return the value of docker_cloud_image_pull_log.service_name
     */
     public String getServiceName() {
        return serviceName;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.ent_id
     * Comment: 环境ID
     * @return the value of docker_cloud_image_pull_log.ent_id
     */
     public Integer getEntId() {
        return entId;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.ent_name
     * Comment: 环境名称
     * @return the value of docker_cloud_image_pull_log.ent_name
     */
     public Integer getEntName() {
        return entName;
    }
}
