package com.cloud.sms.docker.log.entity;
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
 * @date 2017-09-23 18:21:17
 * @since 1.0
 */
public class DockerCloudLogEntity extends BaseEntity{
    /**
     * This field corresponds to the database column docker_cloud_log.log_id
     * Comment: 主键
     * @param logId the value for docker_cloud_log.log_id
     */

    private java.lang.Integer logId;


    /**
     * This field corresponds to the database column docker_cloud_log.time
     * Comment: 时间
     * @param time the value for docker_cloud_log.time
     */

    private java.lang.String time;


    /**
     * This field corresponds to the database column docker_cloud_log.messages
     * Comment: 日志信息
     * @param messages the value for docker_cloud_log.messages
     */

    private java.lang.String messages;


    /**
     * This field corresponds to the database column docker_cloud_log.user_name
     * Comment: 操作人
     * @param userName the value for docker_cloud_log.user_name
     */

    private java.lang.String userName;


    /**
     * This field corresponds to the database column docker_cloud_log.app_name
     * Comment: 应用栈名称
     * @param appName the value for docker_cloud_log.app_name
     */

    private java.lang.String appName;


    /**
     * This field corresponds to the database column docker_cloud_log.container_name
     * Comment: 容器名称
     * @param containerName the value for docker_cloud_log.container_name
     */

    private java.lang.String containerName;


    /**
     * This field corresponds to the database column docker_cloud_log.service_name
     * Comment: 服务名称
     * @param serviceName the value for docker_cloud_log.service_name
     */

    private java.lang.String serviceName;


    /**
     * This field corresponds to the database column docker_cloud_log.network_name
     * Comment: 网络名称
     * @param networkName the value for docker_cloud_log.network_name
     */

    private java.lang.String networkName;


    /**
     * This field corresponds to the database column docker_cloud_log.log_id
     * Comment: 主键
     * @param logId the value for docker_cloud_log.log_id
     */
    public void setLogId(java.lang.Integer logId){
       this.logId = logId;
    }

    /**
     * This field corresponds to the database column docker_cloud_log.time
     * Comment: 时间
     * @param time the value for docker_cloud_log.time
     */
    public void setTime(java.lang.String time){
       this.time = time;
    }

    /**
     * This field corresponds to the database column docker_cloud_log.messages
     * Comment: 日志信息
     * @param messages the value for docker_cloud_log.messages
     */
    public void setMessages(java.lang.String messages){
       this.messages = messages;
    }

    /**
     * This field corresponds to the database column docker_cloud_log.user_name
     * Comment: 操作人
     * @param userName the value for docker_cloud_log.user_name
     */
    public void setUserName(java.lang.String userName){
       this.userName = userName;
    }

    /**
     * This field corresponds to the database column docker_cloud_log.app_name
     * Comment: 应用栈名称
     * @param appName the value for docker_cloud_log.app_name
     */
    public void setAppName(java.lang.String appName){
       this.appName = appName;
    }

    /**
     * This field corresponds to the database column docker_cloud_log.container_name
     * Comment: 容器名称
     * @param containerName the value for docker_cloud_log.container_name
     */
    public void setContainerName(java.lang.String containerName){
       this.containerName = containerName;
    }

    /**
     * This field corresponds to the database column docker_cloud_log.service_name
     * Comment: 服务名称
     * @param serviceName the value for docker_cloud_log.service_name
     */
    public void setServiceName(java.lang.String serviceName){
       this.serviceName = serviceName;
    }

    /**
     * This field corresponds to the database column docker_cloud_log.network_name
     * Comment: 网络名称
     * @param networkName the value for docker_cloud_log.network_name
     */
    public void setNetworkName(java.lang.String networkName){
       this.networkName = networkName;
    }

    /**
     * This field corresponds to the database column docker_cloud_log.log_id
     * Comment: 主键
     * @return the value of docker_cloud_log.log_id
     */
     public java.lang.Integer getLogId() {
        return logId;
    }

    /**
     * This field corresponds to the database column docker_cloud_log.time
     * Comment: 时间
     * @return the value of docker_cloud_log.time
     */
     public java.lang.String getTime() {
        return time;
    }

    /**
     * This field corresponds to the database column docker_cloud_log.messages
     * Comment: 日志信息
     * @return the value of docker_cloud_log.messages
     */
     public java.lang.String getMessages() {
        return messages;
    }

    /**
     * This field corresponds to the database column docker_cloud_log.user_name
     * Comment: 操作人
     * @return the value of docker_cloud_log.user_name
     */
     public java.lang.String getUserName() {
        return userName;
    }

    /**
     * This field corresponds to the database column docker_cloud_log.app_name
     * Comment: 应用栈名称
     * @return the value of docker_cloud_log.app_name
     */
     public java.lang.String getAppName() {
        return appName;
    }

    /**
     * This field corresponds to the database column docker_cloud_log.container_name
     * Comment: 容器名称
     * @return the value of docker_cloud_log.container_name
     */
     public java.lang.String getContainerName() {
        return containerName;
    }

    /**
     * This field corresponds to the database column docker_cloud_log.service_name
     * Comment: 服务名称
     * @return the value of docker_cloud_log.service_name
     */
     public java.lang.String getServiceName() {
        return serviceName;
    }

    /**
     * This field corresponds to the database column docker_cloud_log.network_name
     * Comment: 网络名称
     * @return the value of docker_cloud_log.network_name
     */
     public java.lang.String getNetworkName() {
        return networkName;
    }
}
