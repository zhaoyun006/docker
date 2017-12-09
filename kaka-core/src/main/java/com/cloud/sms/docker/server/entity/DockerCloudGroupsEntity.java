package com.cloud.sms.docker.server.entity;
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
 * @date 2017-10-11 11:25:10
 * @since 1.0
 */
public class DockerCloudGroupsEntity extends BaseEntity{
    private String services;
    private String servers;
    private String containers;
    private String release;
    private String entId;

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public String getContainers() {
        return containers;
    }

    public void setContainers(String containers) {
        this.containers = containers;
    }

    public String getGroupsName() {
        return groupsName;
    }

    /**
     * This field corresponds to the database column docker_cloud_groups.groups_id
     * Comment: 
     * @param groupsId the value for docker_cloud_groups.groups_id
     */

    private java.lang.Integer groupsId;


    /**
     * This field corresponds to the database column docker_cloud_groups.groups_name
     * Comment: 组名称
     * @param groupsName the value for docker_cloud_groups.groups_name
     */

    private java.lang.String groupsName;


    /**
     * This field corresponds to the database column docker_cloud_groups.create_user
     * Comment: 创建人
     * @param createUser the value for docker_cloud_groups.create_user
     */

    private java.lang.String createUser;


    /**
     * This field corresponds to the database column docker_cloud_groups.last_modify_user
     * Comment: 最近修改人
     * @param lastModifyUser the value for docker_cloud_groups.last_modify_user
     */

    private java.lang.String lastModifyUser;


    /**
     * This field corresponds to the database column docker_cloud_groups.last_modify_time
     * Comment: 最近修改时间
     * @param lastModifyTime the value for docker_cloud_groups.last_modify_time
     */

    private java.lang.String lastModifyTime;


    /**
     * This field corresponds to the database column docker_cloud_groups.description
     * Comment: 备注信息
     * @param description the value for docker_cloud_groups.description
     */

    private java.lang.String description;


    /**
     * This field corresponds to the database column docker_cloud_groups.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_groups.create_time
     */

    private java.lang.String createTime;


    /**
     * This field corresponds to the database column docker_cloud_groups.entname
     * Comment: 环境名称
     * @param entname the value for docker_cloud_groups.entname
     */

    private java.lang.String entname;


    /**
     * This field corresponds to the database column docker_cloud_groups.groups_id
     * Comment: 
     * @param groupsId the value for docker_cloud_groups.groups_id
     */
    public void setGroupsId(java.lang.Integer groupsId){
       this.groupsId = groupsId;
    }

    /**
     * This field corresponds to the database column docker_cloud_groups.groups_name
     * Comment: 组名称
     * @param groupsName the value for docker_cloud_groups.groups_name
     */
    public void setGroupsName(java.lang.String groupsName){
       this.groupsName = groupsName;
    }

    /**
     * This field corresponds to the database column docker_cloud_groups.create_user
     * Comment: 创建人
     * @param createUser the value for docker_cloud_groups.create_user
     */
    public void setCreateUser(java.lang.String createUser){
       this.createUser = createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_groups.last_modify_user
     * Comment: 最近修改人
     * @param lastModifyUser the value for docker_cloud_groups.last_modify_user
     */
    public void setLastModifyUser(java.lang.String lastModifyUser){
       this.lastModifyUser = lastModifyUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_groups.last_modify_time
     * Comment: 最近修改时间
     * @param lastModifyTime the value for docker_cloud_groups.last_modify_time
     */
    public void setLastModifyTime(java.lang.String lastModifyTime){
       this.lastModifyTime = lastModifyTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_groups.description
     * Comment: 备注信息
     * @param description the value for docker_cloud_groups.description
     */
    public void setDescription(java.lang.String description){
       this.description = description;
    }

    /**
     * This field corresponds to the database column docker_cloud_groups.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_groups.create_time
     */
    public void setCreateTime(java.lang.String createTime){
       this.createTime = createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_groups.entname
     * Comment: 环境名称
     * @param entname the value for docker_cloud_groups.entname
     */
    public void setEntname(java.lang.String entname){
       this.entname = entname;
    }

    /**
     * This field corresponds to the database column docker_cloud_groups.groups_id
     * Comment: 
     * @return the value of docker_cloud_groups.groups_id
     */
     public java.lang.Integer getGroupsId() {
        return groupsId;
    }



    /**
     * This field corresponds to the database column docker_cloud_groups.create_user
     * Comment: 创建人
     * @return the value of docker_cloud_groups.create_user
     */
     public java.lang.String getCreateUser() {
        return createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_groups.last_modify_user
     * Comment: 最近修改人
     * @return the value of docker_cloud_groups.last_modify_user
     */
     public java.lang.String getLastModifyUser() {
        return lastModifyUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_groups.last_modify_time
     * Comment: 最近修改时间
     * @return the value of docker_cloud_groups.last_modify_time
     */
     public java.lang.String getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_groups.description
     * Comment: 备注信息
     * @return the value of docker_cloud_groups.description
     */
     public java.lang.String getDescription() {
        return description;
    }

    /**
     * This field corresponds to the database column docker_cloud_groups.create_time
     * Comment: 创建时间
     * @return the value of docker_cloud_groups.create_time
     */
     public java.lang.String getCreateTime() {
        return createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_groups.entname
     * Comment: 环境名称
     * @return the value of docker_cloud_groups.entname
     */
     public java.lang.String getEntname() {
        return entname;
    }
}
