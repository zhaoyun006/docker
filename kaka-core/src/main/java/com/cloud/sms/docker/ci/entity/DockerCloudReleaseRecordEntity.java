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
 * @date 2017-11-06 14:53:02
 * @since 1.0
 */
public class DockerCloudReleaseRecordEntity extends BaseEntity{
    private String entName;
    private String registry;

    public String getRegistry() {
        return registry;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    /**
     * This field corresponds to the database column docker_cloud_release_record.record_id
     * Comment: 
     * @param recordId the value for docker_cloud_release_record.record_id
     */

    private java.lang.Integer recordId;


    /**
     * This field corresponds to the database column docker_cloud_release_record.env_id
     * Comment: 项目服务名称
     * @param envId the value for docker_cloud_release_record.env_id
     */

    private java.lang.Integer envId;


    /**
     * This field corresponds to the database column docker_cloud_release_record.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_release_record.create_time
     */

    private java.lang.String createTime;


    /**
     * This field corresponds to the database column docker_cloud_release_record.create_user
     * Comment: 创建用户
     * @param createUser the value for docker_cloud_release_record.create_user
     */

    private java.lang.String createUser;


    /**
     * This field corresponds to the database column docker_cloud_release_record.current_version
     * Comment: 当前版本号
     * @param currentVersion the value for docker_cloud_release_record.current_version
     */

    private java.lang.String currentVersion;


    /**
     * This field corresponds to the database column docker_cloud_release_record.last_version
     * Comment: 发布前版本号
     * @param lastVersion the value for docker_cloud_release_record.last_version
     */

    private java.lang.String lastVersion;


    /**
     * This field corresponds to the database column docker_cloud_release_record.status
     * Comment: 更新状态
     * @param status the value for docker_cloud_release_record.status
     */

    private java.lang.String status;


    /**
     * This field corresponds to the database column docker_cloud_release_record.service_name
     * Comment: 
     * @param serviceName the value for docker_cloud_release_record.service_name
     */

    private java.lang.String serviceName;


    /**
     * This field corresponds to the database column docker_cloud_release_record.domain
     * Comment: 
     * @param domain the value for docker_cloud_release_record.domain
     */

    private java.lang.String domain;


    /**
     * This field corresponds to the database column docker_cloud_release_record.registry_address
     * Comment: 仓库地址
     * @param registryAddress the value for docker_cloud_release_record.registry_address
     */

    private java.lang.String registryAddress;


    /**
     * This field corresponds to the database column docker_cloud_release_record.ent_id
     * Comment: 环境信息ID
     * @param entId the value for docker_cloud_release_record.ent_id
     */

    private java.lang.Integer entId;


    /**
     * This field corresponds to the database column docker_cloud_release_record.record_id
     * Comment: 
     * @param recordId the value for docker_cloud_release_record.record_id
     */
    public void setRecordId(java.lang.Integer recordId){
       this.recordId = recordId;
    }

    /**
     * This field corresponds to the database column docker_cloud_release_record.env_id
     * Comment: 项目服务名称
     * @param envId the value for docker_cloud_release_record.env_id
     */
    public void setEnvId(java.lang.Integer envId){
       this.envId = envId;
    }

    /**
     * This field corresponds to the database column docker_cloud_release_record.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_release_record.create_time
     */
    public void setCreateTime(java.lang.String createTime){
       this.createTime = createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_release_record.create_user
     * Comment: 创建用户
     * @param createUser the value for docker_cloud_release_record.create_user
     */
    public void setCreateUser(java.lang.String createUser){
       this.createUser = createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_release_record.current_version
     * Comment: 当前版本号
     * @param currentVersion the value for docker_cloud_release_record.current_version
     */
    public void setCurrentVersion(java.lang.String currentVersion){
       this.currentVersion = currentVersion;
    }

    /**
     * This field corresponds to the database column docker_cloud_release_record.last_version
     * Comment: 发布前版本号
     * @param lastVersion the value for docker_cloud_release_record.last_version
     */
    public void setLastVersion(java.lang.String lastVersion){
       this.lastVersion = lastVersion;
    }

    /**
     * This field corresponds to the database column docker_cloud_release_record.status
     * Comment: 更新状态
     * @param status the value for docker_cloud_release_record.status
     */
    public void setStatus(java.lang.String status){
       this.status = status;
    }

    /**
     * This field corresponds to the database column docker_cloud_release_record.service_name
     * Comment: 
     * @param serviceName the value for docker_cloud_release_record.service_name
     */
    public void setServiceName(java.lang.String serviceName){
       this.serviceName = serviceName;
    }

    /**
     * This field corresponds to the database column docker_cloud_release_record.domain
     * Comment: 
     * @param domain the value for docker_cloud_release_record.domain
     */
    public void setDomain(java.lang.String domain){
       this.domain = domain;
    }

    /**
     * This field corresponds to the database column docker_cloud_release_record.registry_address
     * Comment: 仓库地址
     * @param registryAddress the value for docker_cloud_release_record.registry_address
     */
    public void setRegistryAddress(java.lang.String registryAddress){
       this.registryAddress = registryAddress;
    }

    /**
     * This field corresponds to the database column docker_cloud_release_record.ent_id
     * Comment: 环境信息ID
     * @param entId the value for docker_cloud_release_record.ent_id
     */
    public void setEntId(java.lang.Integer entId){
       this.entId = entId;
    }

    /**
     * This field corresponds to the database column docker_cloud_release_record.record_id
     * Comment: 
     * @return the value of docker_cloud_release_record.record_id
     */
     public java.lang.Integer getRecordId() {
        return recordId;
    }

    /**
     * This field corresponds to the database column docker_cloud_release_record.env_id
     * Comment: 项目服务名称
     * @return the value of docker_cloud_release_record.env_id
     */
     public java.lang.Integer getEnvId() {
        return envId;
    }

    /**
     * This field corresponds to the database column docker_cloud_release_record.create_time
     * Comment: 创建时间
     * @return the value of docker_cloud_release_record.create_time
     */
     public java.lang.String getCreateTime() {
        return createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_release_record.create_user
     * Comment: 创建用户
     * @return the value of docker_cloud_release_record.create_user
     */
     public java.lang.String getCreateUser() {
        return createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_release_record.current_version
     * Comment: 当前版本号
     * @return the value of docker_cloud_release_record.current_version
     */
     public java.lang.String getCurrentVersion() {
        return currentVersion;
    }

    /**
     * This field corresponds to the database column docker_cloud_release_record.last_version
     * Comment: 发布前版本号
     * @return the value of docker_cloud_release_record.last_version
     */
     public java.lang.String getLastVersion() {
        return lastVersion;
    }

    /**
     * This field corresponds to the database column docker_cloud_release_record.status
     * Comment: 更新状态
     * @return the value of docker_cloud_release_record.status
     */
     public java.lang.String getStatus() {
        return status;
    }

    /**
     * This field corresponds to the database column docker_cloud_release_record.service_name
     * Comment: 
     * @return the value of docker_cloud_release_record.service_name
     */
     public java.lang.String getServiceName() {
        return serviceName;
    }

    /**
     * This field corresponds to the database column docker_cloud_release_record.domain
     * Comment: 
     * @return the value of docker_cloud_release_record.domain
     */
     public java.lang.String getDomain() {
        return domain;
    }

    /**
     * This field corresponds to the database column docker_cloud_release_record.registry_address
     * Comment: 仓库地址
     * @return the value of docker_cloud_release_record.registry_address
     */
     public java.lang.String getRegistryAddress() {
        return registryAddress;
    }

    /**
     * This field corresponds to the database column docker_cloud_release_record.ent_id
     * Comment: 环境信息ID
     * @return the value of docker_cloud_release_record.ent_id
     */
     public java.lang.Integer getEntId() {
        return entId;
    }
}
