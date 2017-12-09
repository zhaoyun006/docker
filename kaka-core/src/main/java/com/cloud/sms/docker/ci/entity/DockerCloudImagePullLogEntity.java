package com.cloud.sms.docker.ci.entity;
import com.asura.framework.base.entity.BaseEntity;


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
public class DockerCloudImagePullLogEntity extends BaseEntity{
    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_id
     * Comment: 
     * @param pullId the value for docker_cloud_image_pull_log.pull_id
     */

    private java.lang.Integer pullId;


    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.image_name
     * Comment: 镜像名称
     * @param imageName the value for docker_cloud_image_pull_log.image_name
     */

    private java.lang.String imageName;


    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.server_address
     * Comment: 服务器地址
     * @param serverAddress the value for docker_cloud_image_pull_log.server_address
     */

    private java.lang.String serverAddress;


    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_time
     * Comment: 下载时间
     * @param pullTime the value for docker_cloud_image_pull_log.pull_time
     */

    private java.lang.String pullTime;


    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_result
     * Comment: 下载结果
     * @param pullResult the value for docker_cloud_image_pull_log.pull_result
     */

    private java.lang.String pullResult;


    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_user
     * Comment: 
     * @param pullUser the value for docker_cloud_image_pull_log.pull_user
     */

    private java.lang.String pullUser;


    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.env_id
     * Comment: 环境ID
     * @param envId the value for docker_cloud_image_pull_log.env_id
     */

    private java.lang.Integer envId;


    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.service_name
     * Comment: 服务名称
     * @param serviceName the value for docker_cloud_image_pull_log.service_name
     */

    private java.lang.String serviceName;


    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.ent_id
     * Comment: 环境ID
     * @param entId the value for docker_cloud_image_pull_log.ent_id
     */

    private java.lang.Integer entId;


    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.ent_name
     * Comment: 环境名称
     * @param entName the value for docker_cloud_image_pull_log.ent_name
     */

    private String entName;


    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_id
     * Comment: 
     * @param pullId the value for docker_cloud_image_pull_log.pull_id
     */
    public void setPullId(java.lang.Integer pullId){
       this.pullId = pullId;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.image_name
     * Comment: 镜像名称
     * @param imageName the value for docker_cloud_image_pull_log.image_name
     */
    public void setImageName(java.lang.String imageName){
       this.imageName = imageName;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.server_address
     * Comment: 服务器地址
     * @param serverAddress the value for docker_cloud_image_pull_log.server_address
     */
    public void setServerAddress(java.lang.String serverAddress){
       this.serverAddress = serverAddress;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_time
     * Comment: 下载时间
     * @param pullTime the value for docker_cloud_image_pull_log.pull_time
     */
    public void setPullTime(java.lang.String pullTime){
       this.pullTime = pullTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_result
     * Comment: 下载结果
     * @param pullResult the value for docker_cloud_image_pull_log.pull_result
     */
    public void setPullResult(java.lang.String pullResult){
       this.pullResult = pullResult;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_user
     * Comment: 
     * @param pullUser the value for docker_cloud_image_pull_log.pull_user
     */
    public void setPullUser(java.lang.String pullUser){
       this.pullUser = pullUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.env_id
     * Comment: 环境ID
     * @param envId the value for docker_cloud_image_pull_log.env_id
     */
    public void setEnvId(java.lang.Integer envId){
       this.envId = envId;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.service_name
     * Comment: 服务名称
     * @param serviceName the value for docker_cloud_image_pull_log.service_name
     */
    public void setServiceName(java.lang.String serviceName){
       this.serviceName = serviceName;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.ent_id
     * Comment: 环境ID
     * @param entId the value for docker_cloud_image_pull_log.ent_id
     */
    public void setEntId(java.lang.Integer entId){
       this.entId = entId;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.ent_name
     * Comment: 环境名称
     * @param entName the value for docker_cloud_image_pull_log.ent_name
     */
    public void setEntName(String entName){
       this.entName = entName;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_id
     * Comment: 
     * @return the value of docker_cloud_image_pull_log.pull_id
     */
     public java.lang.Integer getPullId() {
        return pullId;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.image_name
     * Comment: 镜像名称
     * @return the value of docker_cloud_image_pull_log.image_name
     */
     public java.lang.String getImageName() {
        return imageName;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.server_address
     * Comment: 服务器地址
     * @return the value of docker_cloud_image_pull_log.server_address
     */
     public java.lang.String getServerAddress() {
        return serverAddress;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_time
     * Comment: 下载时间
     * @return the value of docker_cloud_image_pull_log.pull_time
     */
     public java.lang.String getPullTime() {
        return pullTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_result
     * Comment: 下载结果
     * @return the value of docker_cloud_image_pull_log.pull_result
     */
     public java.lang.String getPullResult() {
        return pullResult;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.pull_user
     * Comment: 
     * @return the value of docker_cloud_image_pull_log.pull_user
     */
     public java.lang.String getPullUser() {
        return pullUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.env_id
     * Comment: 环境ID
     * @return the value of docker_cloud_image_pull_log.env_id
     */
     public java.lang.Integer getEnvId() {
        return envId;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.service_name
     * Comment: 服务名称
     * @return the value of docker_cloud_image_pull_log.service_name
     */
     public java.lang.String getServiceName() {
        return serviceName;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.ent_id
     * Comment: 环境ID
     * @return the value of docker_cloud_image_pull_log.ent_id
     */
     public java.lang.Integer getEntId() {
        return entId;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_pull_log.ent_name
     * Comment: 环境名称
     * @return the value of docker_cloud_image_pull_log.ent_name
     */
     public String getEntName() {
        return entName;
    }
}
