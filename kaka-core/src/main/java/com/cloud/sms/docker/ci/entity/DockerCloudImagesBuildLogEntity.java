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
 * @date 2017-10-18 09:52:19
 * @since 1.0
 */
public class DockerCloudImagesBuildLogEntity extends BaseEntity{


    /**
     * This field corresponds to the database column docker_cloud_images_build_log.build_tp
     * Comment:
     * @param buildTp the value for docker_cloud_images_build_log.build_tp
     */
    private String buildTp;

    public String getBuildTp() {
        return buildTp;
    }

    public void setBuildTp(String buildTp) {
        this.buildTp = buildTp;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_log.log_id
     * Comment: 
     * @param logId the value for docker_cloud_images_build_log.log_id
     */

    private java.lang.Integer logId;


    /**
     * This field corresponds to the database column docker_cloud_images_build_log.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_images_build_log.create_time
     */

    private java.lang.String createTime;


    /**
     * This field corresponds to the database column docker_cloud_images_build_log.create_user
     * Comment: 创建人
     * @param createUser the value for docker_cloud_images_build_log.create_user
     */

    private java.lang.String createUser;


    /**
     * This field corresponds to the database column docker_cloud_images_build_log.messages
     * Comment: 消息信息
     * @param messages the value for docker_cloud_images_build_log.messages
     */

    private java.lang.String messages;


    /**
     * This field corresponds to the database column docker_cloud_images_build_log.env_id
     * Comment: 参考环境信息
     * @param envId the value for docker_cloud_images_build_log.env_id
     */

    private java.lang.Integer envId;


    /**
     * This field corresponds to the database column docker_cloud_images_build_log.build_status
     * Comment: 编译状态
     * @param buildStatus the value for docker_cloud_images_build_log.build_status
     */

    private java.lang.String buildStatus;


    /**
     * This field corresponds to the database column docker_cloud_images_build_log.build_server_api
     * Comment: API调用信息
     * @param buildServerApi the value for docker_cloud_images_build_log.build_server_api
     */

    private java.lang.String buildServerApi;


    /**
     * This field corresponds to the database column docker_cloud_images_build_log.build_param
     * Comment: 编译参数
     * @param buildParam the value for docker_cloud_images_build_log.build_param
     */

    private java.lang.String buildParam;


    /**
     * This field corresponds to the database column docker_cloud_images_build_log.tag
     * Comment: 镜像tag
     * @param tag the value for docker_cloud_images_build_log.tag
     */

    private java.lang.String tag;


    /**
     * This field corresponds to the database column docker_cloud_images_build_log.log_id
     * Comment: 
     * @param logId the value for docker_cloud_images_build_log.log_id
     */
    public void setLogId(java.lang.Integer logId){
       this.logId = logId;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_log.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_images_build_log.create_time
     */
    public void setCreateTime(java.lang.String createTime){
       this.createTime = createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_log.create_user
     * Comment: 创建人
     * @param createUser the value for docker_cloud_images_build_log.create_user
     */
    public void setCreateUser(java.lang.String createUser){
       this.createUser = createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_log.messages
     * Comment: 消息信息
     * @param messages the value for docker_cloud_images_build_log.messages
     */
    public void setMessages(java.lang.String messages){
       this.messages = messages;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_log.env_id
     * Comment: 参考环境信息
     * @param envId the value for docker_cloud_images_build_log.env_id
     */
    public void setEnvId(java.lang.Integer envId){
       this.envId = envId;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_log.build_status
     * Comment: 编译状态
     * @param buildStatus the value for docker_cloud_images_build_log.build_status
     */
    public void setBuildStatus(java.lang.String buildStatus){
       this.buildStatus = buildStatus;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_log.build_server_api
     * Comment: API调用信息
     * @param buildServerApi the value for docker_cloud_images_build_log.build_server_api
     */
    public void setBuildServerApi(java.lang.String buildServerApi){
       this.buildServerApi = buildServerApi;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_log.build_param
     * Comment: 编译参数
     * @param buildParam the value for docker_cloud_images_build_log.build_param
     */
    public void setBuildParam(java.lang.String buildParam){
       this.buildParam = buildParam;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_log.tag
     * Comment: 镜像tag
     * @param tag the value for docker_cloud_images_build_log.tag
     */
    public void setTag(java.lang.String tag){
       this.tag = tag;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_log.log_id
     * Comment: 
     * @return the value of docker_cloud_images_build_log.log_id
     */
     public java.lang.Integer getLogId() {
        return logId;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_log.create_time
     * Comment: 创建时间
     * @return the value of docker_cloud_images_build_log.create_time
     */
     public java.lang.String getCreateTime() {
        return createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_log.create_user
     * Comment: 创建人
     * @return the value of docker_cloud_images_build_log.create_user
     */
     public java.lang.String getCreateUser() {
        return createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_log.messages
     * Comment: 消息信息
     * @return the value of docker_cloud_images_build_log.messages
     */
     public java.lang.String getMessages() {
        return messages;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_log.env_id
     * Comment: 参考环境信息
     * @return the value of docker_cloud_images_build_log.env_id
     */
     public java.lang.Integer getEnvId() {
        return envId;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_log.build_status
     * Comment: 编译状态
     * @return the value of docker_cloud_images_build_log.build_status
     */
     public java.lang.String getBuildStatus() {
        return buildStatus;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_log.build_server_api
     * Comment: API调用信息
     * @return the value of docker_cloud_images_build_log.build_server_api
     */
     public java.lang.String getBuildServerApi() {
        return buildServerApi;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_log.build_param
     * Comment: 编译参数
     * @return the value of docker_cloud_images_build_log.build_param
     */
     public java.lang.String getBuildParam() {
        return buildParam;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_log.tag
     * Comment: 镜像tag
     * @return the value of docker_cloud_images_build_log.tag
     */
     public java.lang.String getTag() {
        return tag;
    }
}
