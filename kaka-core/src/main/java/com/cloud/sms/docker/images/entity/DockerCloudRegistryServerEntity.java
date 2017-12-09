package com.cloud.sms.docker.images.entity;
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
 * @date 2017-10-10 16:37:39
 * @since 1.0
 */
public class DockerCloudRegistryServerEntity extends BaseEntity{

    private String username;
    private String password;
    private String prefix;
    private int groupsId;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getGroupsId() {
        return groupsId;
    }

    public void setGroupsId(int groupsId) {
        this.groupsId = groupsId;
    }

    /**
     * This field corresponds to the database column docker_cloud_registry_server.server_id
     * Comment: 
     * @param serverId the value for docker_cloud_registry_server.server_id
     */

    private java.lang.Integer serverId;


    /**
     * This field corresponds to the database column docker_cloud_registry_server.server_address
     * Comment: 
     * @param serverAddress the value for docker_cloud_registry_server.server_address
     */

    private java.lang.String serverAddress;


    /**
     * This field corresponds to the database column docker_cloud_registry_server.server_domain
     * Comment: 
     * @param serverDomain the value for docker_cloud_registry_server.server_domain
     */

    private java.lang.String serverDomain;


    /**
     * This field corresponds to the database column docker_cloud_registry_server.last_modify_time
     * Comment: 最近修改时间
     * @param lastModifyTime the value for docker_cloud_registry_server.last_modify_time
     */

    private java.lang.String lastModifyTime;


    /**
     * This field corresponds to the database column docker_cloud_registry_server.last_modify_user
     * Comment: 最近修改用户
     * @param lastModifyUser the value for docker_cloud_registry_server.last_modify_user
     */

    private java.lang.String lastModifyUser;


    /**
     * This field corresponds to the database column docker_cloud_registry_server.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_registry_server.create_time
     */

    private java.lang.String createTime;


    /**
     * This field corresponds to the database column docker_cloud_registry_server.create_user
     * Comment: 创建用户
     * @param createUser the value for docker_cloud_registry_server.create_user
     */

    private java.lang.String createUser;


    /**
     * This field corresponds to the database column docker_cloud_registry_server.groups
     * Comment: 组名称,不同的服务属于不同的组
     * @param groups the value for docker_cloud_registry_server.groups
     */

    private java.lang.String groups;


    /**
     * This field corresponds to the database column docker_cloud_registry_server.images_number
     * Comment: 镜像数量
     * @param imagesNumber the value for docker_cloud_registry_server.images_number
     */

    private java.lang.Integer imagesNumber;


    /**
     * This field corresponds to the database column docker_cloud_registry_server.description
     * Comment: 描述信息
     * @param description the value for docker_cloud_registry_server.description
     */

    private java.lang.String description;


    /**
     * This field corresponds to the database column docker_cloud_registry_server.server_id
     * Comment: 
     * @param serverId the value for docker_cloud_registry_server.server_id
     */
    public void setServerId(java.lang.Integer serverId){
       this.serverId = serverId;
    }

    /**
     * This field corresponds to the database column docker_cloud_registry_server.server_address
     * Comment: 
     * @param serverAddress the value for docker_cloud_registry_server.server_address
     */
    public void setServerAddress(java.lang.String serverAddress){
       this.serverAddress = serverAddress;
    }

    /**
     * This field corresponds to the database column docker_cloud_registry_server.server_domain
     * Comment: 
     * @param serverDomain the value for docker_cloud_registry_server.server_domain
     */
    public void setServerDomain(java.lang.String serverDomain){
       this.serverDomain = serverDomain;
    }

    /**
     * This field corresponds to the database column docker_cloud_registry_server.last_modify_time
     * Comment: 最近修改时间
     * @param lastModifyTime the value for docker_cloud_registry_server.last_modify_time
     */
    public void setLastModifyTime(java.lang.String lastModifyTime){
       this.lastModifyTime = lastModifyTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_registry_server.last_modify_user
     * Comment: 最近修改用户
     * @param lastModifyUser the value for docker_cloud_registry_server.last_modify_user
     */
    public void setLastModifyUser(java.lang.String lastModifyUser){
       this.lastModifyUser = lastModifyUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_registry_server.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_registry_server.create_time
     */
    public void setCreateTime(java.lang.String createTime){
       this.createTime = createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_registry_server.create_user
     * Comment: 创建用户
     * @param createUser the value for docker_cloud_registry_server.create_user
     */
    public void setCreateUser(java.lang.String createUser){
       this.createUser = createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_registry_server.groups
     * Comment: 组名称,不同的服务属于不同的组
     * @param groups the value for docker_cloud_registry_server.groups
     */
    public void setGroups(java.lang.String groups){
       this.groups = groups;
    }

    /**
     * This field corresponds to the database column docker_cloud_registry_server.images_number
     * Comment: 镜像数量
     * @param imagesNumber the value for docker_cloud_registry_server.images_number
     */
    public void setImagesNumber(java.lang.Integer imagesNumber){
       this.imagesNumber = imagesNumber;
    }

    /**
     * This field corresponds to the database column docker_cloud_registry_server.description
     * Comment: 描述信息
     * @param description the value for docker_cloud_registry_server.description
     */
    public void setDescription(java.lang.String description){
       this.description = description;
    }

    /**
     * This field corresponds to the database column docker_cloud_registry_server.server_id
     * Comment: 
     * @return the value of docker_cloud_registry_server.server_id
     */
     public java.lang.Integer getServerId() {
        return serverId;
    }

    /**
     * This field corresponds to the database column docker_cloud_registry_server.server_address
     * Comment: 
     * @return the value of docker_cloud_registry_server.server_address
     */
     public java.lang.String getServerAddress() {
        return serverAddress;
    }

    /**
     * This field corresponds to the database column docker_cloud_registry_server.server_domain
     * Comment: 
     * @return the value of docker_cloud_registry_server.server_domain
     */
     public java.lang.String getServerDomain() {
        return serverDomain;
    }

    /**
     * This field corresponds to the database column docker_cloud_registry_server.last_modify_time
     * Comment: 最近修改时间
     * @return the value of docker_cloud_registry_server.last_modify_time
     */
     public java.lang.String getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_registry_server.last_modify_user
     * Comment: 最近修改用户
     * @return the value of docker_cloud_registry_server.last_modify_user
     */
     public java.lang.String getLastModifyUser() {
        return lastModifyUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_registry_server.create_time
     * Comment: 创建时间
     * @return the value of docker_cloud_registry_server.create_time
     */
     public java.lang.String getCreateTime() {
        return createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_registry_server.create_user
     * Comment: 创建用户
     * @return the value of docker_cloud_registry_server.create_user
     */
     public java.lang.String getCreateUser() {
        return createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_registry_server.groups
     * Comment: 组名称,不同的服务属于不同的组
     * @return the value of docker_cloud_registry_server.groups
     */
     public java.lang.String getGroups() {
        return groups;
    }

    /**
     * This field corresponds to the database column docker_cloud_registry_server.images_number
     * Comment: 镜像数量
     * @return the value of docker_cloud_registry_server.images_number
     */
     public java.lang.Integer getImagesNumber() {
        return imagesNumber;
    }

    /**
     * This field corresponds to the database column docker_cloud_registry_server.description
     * Comment: 描述信息
     * @return the value of docker_cloud_registry_server.description
     */
     public java.lang.String getDescription() {
        return description;
    }
}
