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
 * @date 2017-10-12 10:26:27
 * @since 1.0
 */
public class DockerCloudOperLogEntity extends BaseEntity{
    /**
     * This field corresponds to the database column docker_cloud_oper_log.log_id
     * Comment: 
     * @param logId the value for docker_cloud_oper_log.log_id
     */

    private java.lang.Integer logId;


    /**
     * This field corresponds to the database column docker_cloud_oper_log.time
     * Comment: 操作时间
     * @param time the value for docker_cloud_oper_log.time
     */

    private java.lang.String time;


    /**
     * This field corresponds to the database column docker_cloud_oper_log.user
     * Comment: 操作用户
     * @param user the value for docker_cloud_oper_log.user
     */

    private java.lang.String user;


    /**
     * This field corresponds to the database column docker_cloud_oper_log.messages
     * Comment: 操作信息
     * @param messages the value for docker_cloud_oper_log.messages
     */

    private java.lang.String messages;


    /**
     * This field corresponds to the database column docker_cloud_oper_log.ip
     * Comment: 操作IP地址
     * @param ip the value for docker_cloud_oper_log.ip
     */

    private java.lang.String ip;


    /**
     * This field corresponds to the database column docker_cloud_oper_log.log_id
     * Comment: 
     * @param logId the value for docker_cloud_oper_log.log_id
     */
    public void setLogId(java.lang.Integer logId){
       this.logId = logId;
    }

    /**
     * This field corresponds to the database column docker_cloud_oper_log.time
     * Comment: 操作时间
     * @param time the value for docker_cloud_oper_log.time
     */
    public void setTime(java.lang.String time){
       this.time = time;
    }

    /**
     * This field corresponds to the database column docker_cloud_oper_log.user
     * Comment: 操作用户
     * @param user the value for docker_cloud_oper_log.user
     */
    public void setUser(java.lang.String user){
       this.user = user;
    }

    /**
     * This field corresponds to the database column docker_cloud_oper_log.messages
     * Comment: 操作信息
     * @param messages the value for docker_cloud_oper_log.messages
     */
    public void setMessages(java.lang.String messages){
       this.messages = messages;
    }

    /**
     * This field corresponds to the database column docker_cloud_oper_log.ip
     * Comment: 操作IP地址
     * @param ip the value for docker_cloud_oper_log.ip
     */
    public void setIp(java.lang.String ip){
       this.ip = ip;
    }

    /**
     * This field corresponds to the database column docker_cloud_oper_log.log_id
     * Comment: 
     * @return the value of docker_cloud_oper_log.log_id
     */
     public java.lang.Integer getLogId() {
        return logId;
    }

    /**
     * This field corresponds to the database column docker_cloud_oper_log.time
     * Comment: 操作时间
     * @return the value of docker_cloud_oper_log.time
     */
     public java.lang.String getTime() {
        return time;
    }

    /**
     * This field corresponds to the database column docker_cloud_oper_log.user
     * Comment: 操作用户
     * @return the value of docker_cloud_oper_log.user
     */
     public java.lang.String getUser() {
        return user;
    }

    /**
     * This field corresponds to the database column docker_cloud_oper_log.messages
     * Comment: 操作信息
     * @return the value of docker_cloud_oper_log.messages
     */
     public java.lang.String getMessages() {
        return messages;
    }

    /**
     * This field corresponds to the database column docker_cloud_oper_log.ip
     * Comment: 操作IP地址
     * @return the value of docker_cloud_oper_log.ip
     */
     public java.lang.String getIp() {
        return ip;
    }
}
