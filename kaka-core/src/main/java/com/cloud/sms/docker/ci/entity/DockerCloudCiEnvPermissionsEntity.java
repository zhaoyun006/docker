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
 * @date 2017-10-23 14:10:34
 * @since 1.0
 */
public class DockerCloudCiEnvPermissionsEntity extends BaseEntity{
    private String serviceName;
    private String groupsName;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupsName() {
        return groupsName;
    }

    public void setGroupsName(String groupsName) {
        this.groupsName = groupsName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getpStartT() {
        return pStartT;
    }

    public void setpStartT(String pStartT) {
        this.pStartT = pStartT;
    }

    public String getpEndT() {
        return pEndT;
    }

    public void setpEndT(String pEndT) {
        this.pEndT = pEndT;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.permissions_id
     * Comment: 
     * @param permissionsId the value for docker_cloud_ci_env_permissions.permissions_id
     */

    private java.lang.Integer permissionsId;


    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.env_id
     * Comment: 环境名称ID
     * @param envId the value for docker_cloud_ci_env_permissions.env_id
     */

    private java.lang.Integer envId;


    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.username
     * Comment: 用户名
     * @param username the value for docker_cloud_ci_env_permissions.username
     */

    private java.lang.String username;


    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.group_id
     * Comment: 参考组ID
     * @param groupId the value for docker_cloud_ci_env_permissions.group_id
     */

    private java.lang.Integer groupId;


    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_ci_env_permissions.create_time
     */

    private java.lang.String createTime;


    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.create_user
     * Comment: 创建用户
     * @param createUser the value for docker_cloud_ci_env_permissions.create_user
     */

    private java.lang.String createUser;


    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.p_start_t
     * Comment: 权限开始时间
     * @param pStartT the value for docker_cloud_ci_env_permissions.p_start_t
     */

    private java.lang.String pStartT;


    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.p_end_t
     * Comment: 权限结束时间
     * @param pEndT the value for docker_cloud_ci_env_permissions.p_end_t
     */

    private java.lang.String pEndT;


    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.permissions_id
     * Comment: 
     * @param permissionsId the value for docker_cloud_ci_env_permissions.permissions_id
     */
    public void setPermissionsId(java.lang.Integer permissionsId){
       this.permissionsId = permissionsId;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.env_id
     * Comment: 环境名称ID
     * @param envId the value for docker_cloud_ci_env_permissions.env_id
     */
    public void setEnvId(java.lang.Integer envId){
       this.envId = envId;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.username
     * Comment: 用户名
     * @param username the value for docker_cloud_ci_env_permissions.username
     */
    public void setUsername(java.lang.String username){
       this.username = username;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.group_id
     * Comment: 参考组ID
     * @param groupId the value for docker_cloud_ci_env_permissions.group_id
     */
    public void setGroupId(java.lang.Integer groupId){
       this.groupId = groupId;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_ci_env_permissions.create_time
     */
    public void setCreateTime(java.lang.String createTime){
       this.createTime = createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.create_user
     * Comment: 创建用户
     * @param createUser the value for docker_cloud_ci_env_permissions.create_user
     */
    public void setCreateUser(java.lang.String createUser){
       this.createUser = createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.p_start_t
     * Comment: 权限开始时间
     * @param pStartT the value for docker_cloud_ci_env_permissions.p_start_t
     */
    public void setPStartT(java.lang.String pStartT){
       this.pStartT = pStartT;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.p_end_t
     * Comment: 权限结束时间
     * @param pEndT the value for docker_cloud_ci_env_permissions.p_end_t
     */
    public void setPEndT(java.lang.String pEndT){
       this.pEndT = pEndT;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.permissions_id
     * Comment: 
     * @return the value of docker_cloud_ci_env_permissions.permissions_id
     */
     public java.lang.Integer getPermissionsId() {
        return permissionsId;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.env_id
     * Comment: 环境名称ID
     * @return the value of docker_cloud_ci_env_permissions.env_id
     */
     public java.lang.Integer getEnvId() {
        return envId;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.username
     * Comment: 用户名
     * @return the value of docker_cloud_ci_env_permissions.username
     */
     public java.lang.String getUsername() {
        return username;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.group_id
     * Comment: 参考组ID
     * @return the value of docker_cloud_ci_env_permissions.group_id
     */
     public java.lang.Integer getGroupId() {
        return groupId;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.create_time
     * Comment: 创建时间
     * @return the value of docker_cloud_ci_env_permissions.create_time
     */
     public java.lang.String getCreateTime() {
        return createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.create_user
     * Comment: 创建用户
     * @return the value of docker_cloud_ci_env_permissions.create_user
     */
     public java.lang.String getCreateUser() {
        return createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.p_start_t
     * Comment: 权限开始时间
     * @return the value of docker_cloud_ci_env_permissions.p_start_t
     */
     public java.lang.String getPStartT() {
        return pStartT;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env_permissions.p_end_t
     * Comment: 权限结束时间
     * @return the value of docker_cloud_ci_env_permissions.p_end_t
     */
     public java.lang.String getPEndT() {
        return pEndT;
    }
}
