package com.cloud.sms.docker.container.entity;
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
 * @date 2017-09-23 19:50:41
 * @since 1.0
 */
public class DockerCloudContainerEntity extends BaseEntity{
    /**
     * This field corresponds to the database column docker_cloud_container.container_id
     * Comment: 
     * @param containerId the value for docker_cloud_container.container_id
     */

    private java.lang.Integer containerId;


    /**
     * This field corresponds to the database column docker_cloud_container.container_name
     * Comment: 
     * @param containerName the value for docker_cloud_container.container_name
     */

    private java.lang.String containerName;


    /**
     * This field corresponds to the database column docker_cloud_container.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_container.create_time
     */

    private java.lang.String createTime;


    /**
     * This field corresponds to the database column docker_cloud_container.create_user
     * Comment: 创建人
     * @param createUser the value for docker_cloud_container.create_user
     */

    private java.lang.String createUser;


    /**
     * This field corresponds to the database column docker_cloud_container.image
     * Comment: 
     * @param image the value for docker_cloud_container.image
     */

    private java.lang.String image;


    /**
     * This field corresponds to the database column docker_cloud_container.status
     * Comment: 运行状态
     * @param status the value for docker_cloud_container.status
     */

    private java.lang.Integer status;


    /**
     * This field corresponds to the database column docker_cloud_container.network
     * Comment: 
     * @param network the value for docker_cloud_container.network
     */

    private java.lang.String network;


    /**
     * This field corresponds to the database column docker_cloud_container.server_ip
     * Comment: 
     * @param serverIp the value for docker_cloud_container.server_ip
     */

    private java.lang.String serverIp;


    /**
     * This field corresponds to the database column docker_cloud_container.memory
     * Comment: 内存
     * @param memory the value for docker_cloud_container.memory
     */

    private java.lang.String memory;


    /**
     * This field corresponds to the database column docker_cloud_container.cpu
     * Comment: cpu使用
     * @param cpu the value for docker_cloud_container.cpu
     */

    private java.lang.String cpu;


    /**
     * This field corresponds to the database column docker_cloud_container.gson_data
     * Comment: 其他未知数据
     * @param gsonData the value for docker_cloud_container.gson_data
     */

    private java.lang.String gsonData;


    /**
     * This field corresponds to the database column docker_cloud_container.app_name
     * Comment: 应用栈名称,和应用栈关联使用
     * @param appName the value for docker_cloud_container.app_name
     */

    private java.lang.String appName;


    /**
     * This field corresponds to the database column docker_cloud_container.service_name
     * Comment: 服务名称
     * @param serviceName the value for docker_cloud_container.service_name
     */

    private java.lang.String serviceName;


    /**
     * This field corresponds to the database column docker_cloud_container.container_id
     * Comment: 
     * @param containerId the value for docker_cloud_container.container_id
     */
    public void setContainerId(java.lang.Integer containerId){
       this.containerId = containerId;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.container_name
     * Comment: 
     * @param containerName the value for docker_cloud_container.container_name
     */
    public void setContainerName(java.lang.String containerName){
       this.containerName = containerName;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_container.create_time
     */
    public void setCreateTime(java.lang.String createTime){
       this.createTime = createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.create_user
     * Comment: 创建人
     * @param createUser the value for docker_cloud_container.create_user
     */
    public void setCreateUser(java.lang.String createUser){
       this.createUser = createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.image
     * Comment: 
     * @param image the value for docker_cloud_container.image
     */
    public void setImage(java.lang.String image){
       this.image = image;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.status
     * Comment: 运行状态
     * @param status the value for docker_cloud_container.status
     */
    public void setStatus(java.lang.Integer status){
       this.status = status;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.network
     * Comment: 
     * @param network the value for docker_cloud_container.network
     */
    public void setNetwork(java.lang.String network){
       this.network = network;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.server_ip
     * Comment: 
     * @param serverIp the value for docker_cloud_container.server_ip
     */
    public void setServerIp(java.lang.String serverIp){
       this.serverIp = serverIp;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.memory
     * Comment: 内存
     * @param memory the value for docker_cloud_container.memory
     */
    public void setMemory(java.lang.String memory){
       this.memory = memory;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.cpu
     * Comment: cpu使用
     * @param cpu the value for docker_cloud_container.cpu
     */
    public void setCpu(java.lang.String cpu){
       this.cpu = cpu;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.gson_data
     * Comment: 其他未知数据
     * @param gsonData the value for docker_cloud_container.gson_data
     */
    public void setGsonData(java.lang.String gsonData){
       this.gsonData = gsonData;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.app_name
     * Comment: 应用栈名称,和应用栈关联使用
     * @param appName the value for docker_cloud_container.app_name
     */
    public void setAppName(java.lang.String appName){
       this.appName = appName;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.service_name
     * Comment: 服务名称
     * @param serviceName the value for docker_cloud_container.service_name
     */
    public void setServiceName(java.lang.String serviceName){
       this.serviceName = serviceName;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.container_id
     * Comment: 
     * @return the value of docker_cloud_container.container_id
     */
     public java.lang.Integer getContainerId() {
        return containerId;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.container_name
     * Comment: 
     * @return the value of docker_cloud_container.container_name
     */
     public java.lang.String getContainerName() {
        return containerName;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.create_time
     * Comment: 创建时间
     * @return the value of docker_cloud_container.create_time
     */
     public java.lang.String getCreateTime() {
        return createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.create_user
     * Comment: 创建人
     * @return the value of docker_cloud_container.create_user
     */
     public java.lang.String getCreateUser() {
        return createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.image
     * Comment: 
     * @return the value of docker_cloud_container.image
     */
     public java.lang.String getImage() {
        return image;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.status
     * Comment: 运行状态
     * @return the value of docker_cloud_container.status
     */
     public java.lang.Integer getStatus() {
        return status;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.network
     * Comment: 
     * @return the value of docker_cloud_container.network
     */
     public java.lang.String getNetwork() {
        return network;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.server_ip
     * Comment: 
     * @return the value of docker_cloud_container.server_ip
     */
     public java.lang.String getServerIp() {
        return serverIp;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.memory
     * Comment: 内存
     * @return the value of docker_cloud_container.memory
     */
     public java.lang.String getMemory() {
        return memory;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.cpu
     * Comment: cpu使用
     * @return the value of docker_cloud_container.cpu
     */
     public java.lang.String getCpu() {
        return cpu;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.gson_data
     * Comment: 其他未知数据
     * @return the value of docker_cloud_container.gson_data
     */
     public java.lang.String getGsonData() {
        return gsonData;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.app_name
     * Comment: 应用栈名称,和应用栈关联使用
     * @return the value of docker_cloud_container.app_name
     */
     public java.lang.String getAppName() {
        return appName;
    }

    /**
     * This field corresponds to the database column docker_cloud_container.service_name
     * Comment: 服务名称
     * @return the value of docker_cloud_container.service_name
     */
     public java.lang.String getServiceName() {
        return serviceName;
    }
}
