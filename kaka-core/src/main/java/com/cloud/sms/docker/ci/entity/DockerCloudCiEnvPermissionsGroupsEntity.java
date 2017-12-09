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
 * @date 2017-10-23 15:07:37
 * @since 1.0
 */
public class DockerCloudCiEnvPermissionsGroupsEntity extends BaseEntity{
    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.groups_id
     * Comment: 
     * @param groupsId the value for docker_cloud_ci_env_permissions_groups.groups_id
     */

    private java.lang.Integer groupsId;


    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.groups_name
     * Comment: 组名称
     * @param groupsName the value for docker_cloud_ci_env_permissions_groups.groups_name
     */

    private java.lang.String groupsName;


    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.username
     * Comment: 组名称
     * @param username the value for docker_cloud_ci_env_permissions_groups.username
     */

    private java.lang.String username;


    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_ci_env_permissions_groups.create_time
     */

    private java.lang.String createTime;


    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.create_user
     * Comment: 创建用户
     * @param createUser the value for docker_cloud_ci_env_permissions_groups.create_user
     */

    private java.lang.String createUser;


    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.last_modify_user
     * Comment: 最近修改用户
     * @param lastModifyUser the value for docker_cloud_ci_env_permissions_groups.last_modify_user
     */

    private java.lang.String lastModifyUser;


    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.last_modify_time
     * Comment: 最近修改时间
     * @param lastModifyTime the value for docker_cloud_ci_env_permissions_groups.last_modify_time
     */

    private java.lang.String lastModifyTime;


    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.description
     * Comment: 描述信息
     * @param description the value for docker_cloud_ci_env_permissions_groups.description
     */

    private java.lang.String description;


    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.groups_id
     * Comment: 
     * @param groupsId the value for docker_cloud_ci_env_permissions_groups.groups_id
     */
    public void setGroupsId(java.lang.Integer groupsId){
       this.groupsId = groupsId;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.groups_name
     * Comment: 组名称
     * @param groupsName the value for docker_cloud_ci_env_permissions_groups.groups_name
     */
    public void setGroupsName(java.lang.String groupsName){
       this.groupsName = groupsName;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.username
     * Comment: 组名称
     * @param username the value for docker_cloud_ci_env_permissions_groups.username
     */
    public void setUsername(java.lang.String username){
       this.username = username;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_ci_env_permissions_groups.create_time
     */
    public void setCreateTime(java.lang.String createTime){
       this.createTime = createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.create_user
     * Comment: 创建用户
     * @param createUser the value for docker_cloud_ci_env_permissions_groups.create_user
     */
    public void setCreateUser(java.lang.String createUser){
       this.createUser = createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.last_modify_user
     * Comment: 最近修改用户
     * @param lastModifyUser the value for docker_cloud_ci_env_permissions_groups.last_modify_user
     */
    public void setLastModifyUser(java.lang.String lastModifyUser){
       this.lastModifyUser = lastModifyUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.last_modify_time
     * Comment: 最近修改时间
     * @param lastModifyTime the value for docker_cloud_ci_env_permissions_groups.last_modify_time
     */
    public void setLastModifyTime(java.lang.String lastModifyTime){
       this.lastModifyTime = lastModifyTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.description
     * Comment: 描述信息
     * @param description the value for docker_cloud_ci_env_permissions_groups.description
     */
    public void setDescription(java.lang.String description){
       this.description = description;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.groups_id
     * Comment: 
     * @return the value of docker_cloud_ci_env_permissions_groups.groups_id
     */
     public java.lang.Integer getGroupsId() {
        return groupsId;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.groups_name
     * Comment: 组名称
     * @return the value of docker_cloud_ci_env_permissions_groups.groups_name
     */
     public java.lang.String getGroupsName() {
        return groupsName;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.username
     * Comment: 组名称
     * @return the value of docker_cloud_ci_env_permissions_groups.username
     */
     public java.lang.String getUsername() {
        return username;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.create_time
     * Comment: 创建时间
     * @return the value of docker_cloud_ci_env_permissions_groups.create_time
     */
     public java.lang.String getCreateTime() {
        return createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.create_user
     * Comment: 创建用户
     * @return the value of docker_cloud_ci_env_permissions_groups.create_user
     */
     public java.lang.String getCreateUser() {
        return createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.last_modify_user
     * Comment: 最近修改用户
     * @return the value of docker_cloud_ci_env_permissions_groups.last_modify_user
     */
     public java.lang.String getLastModifyUser() {
        return lastModifyUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.last_modify_time
     * Comment: 最近修改时间
     * @return the value of docker_cloud_ci_env_permissions_groups.last_modify_time
     */
     public java.lang.String getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions_groups.description
     * Comment: 描述信息
     * @return the value of docker_cloud_ci_env_permissions_groups.description
     */
     public java.lang.String getDescription() {
        return description;
    }
}
