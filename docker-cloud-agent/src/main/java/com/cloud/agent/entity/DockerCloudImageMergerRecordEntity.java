package com.cloud.agent.entity;


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
 * @date 2017-12-01 10:47:31
 * @since 1.0
 */
public class DockerCloudImageMergerRecordEntity {
    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.merger_id
     * Comment: 
     * @param mergerId the value for docker_cloud_image_merger_record.merger_id
     */

    private Integer mergerId;


    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.ent_id
     * Comment: 项目服务名称
     * @param entId the value for docker_cloud_image_merger_record.ent_id
     */

    private Integer entId;


    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_image_merger_record.create_time
     */

    private String createTime;


    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.create_user
     * Comment: 创建用户
     * @param createUser the value for docker_cloud_image_merger_record.create_user
     */

    private String createUser;


    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.current_version
     * Comment: 当前版本号
     * @param currentVersion the value for docker_cloud_image_merger_record.current_version
     */

    private String currentVersion;


    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.status
     * Comment: 更新状态
     * @param status the value for docker_cloud_image_merger_record.status
     */

    private String status;


    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.service_name
     * Comment:
     * @param serviceName the value for docker_cloud_image_merger_record.service_name
     */

    private String serviceName;


    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.domain
     * Comment:
     * @param domain the value for docker_cloud_image_merger_record.domain
     */

    private String domain;


    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.registry_address
     * Comment: 仓库地址
     * @param registryAddress the value for docker_cloud_image_merger_record.registry_address
     */

    private String registryAddress;


    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.ent_name
     * Comment: 环境信息
     * @param entName the value for docker_cloud_image_merger_record.ent_name
     */

    private String entName;


    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.registry
     * Comment: 镜像地址
     * @param registry the value for docker_cloud_image_merger_record.registry
     */

    private String registry;


    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.result
     * Comment: 提交信息
     * @param result the value for docker_cloud_image_merger_record.result
     */

    private String result;


    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.merger_id
     * Comment:
     * @param mergerId the value for docker_cloud_image_merger_record.merger_id
     */
    public void setMergerId(Integer mergerId){
       this.mergerId = mergerId;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.ent_id
     * Comment: 项目服务名称
     * @param entId the value for docker_cloud_image_merger_record.ent_id
     */
    public void setEntId(Integer entId){
       this.entId = entId;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_image_merger_record.create_time
     */
    public void setCreateTime(String createTime){
       this.createTime = createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.create_user
     * Comment: 创建用户
     * @param createUser the value for docker_cloud_image_merger_record.create_user
     */
    public void setCreateUser(String createUser){
       this.createUser = createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.current_version
     * Comment: 当前版本号
     * @param currentVersion the value for docker_cloud_image_merger_record.current_version
     */
    public void setCurrentVersion(String currentVersion){
       this.currentVersion = currentVersion;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.status
     * Comment: 更新状态
     * @param status the value for docker_cloud_image_merger_record.status
     */
    public void setStatus(String status){
       this.status = status;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.service_name
     * Comment:
     * @param serviceName the value for docker_cloud_image_merger_record.service_name
     */
    public void setServiceName(String serviceName){
       this.serviceName = serviceName;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.domain
     * Comment:
     * @param domain the value for docker_cloud_image_merger_record.domain
     */
    public void setDomain(String domain){
       this.domain = domain;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.registry_address
     * Comment: 仓库地址
     * @param registryAddress the value for docker_cloud_image_merger_record.registry_address
     */
    public void setRegistryAddress(String registryAddress){
       this.registryAddress = registryAddress;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.ent_name
     * Comment: 环境信息
     * @param entName the value for docker_cloud_image_merger_record.ent_name
     */
    public void setEntName(String entName){
       this.entName = entName;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.registry
     * Comment: 镜像地址
     * @param registry the value for docker_cloud_image_merger_record.registry
     */
    public void setRegistry(String registry){
       this.registry = registry;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.result
     * Comment: 提交信息
     * @param result the value for docker_cloud_image_merger_record.result
     */
    public void setResult(String result){
       this.result = result;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.merger_id
     * Comment:
     * @return the value of docker_cloud_image_merger_record.merger_id
     */
     public Integer getMergerId() {
        return mergerId;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.ent_id
     * Comment: 项目服务名称
     * @return the value of docker_cloud_image_merger_record.ent_id
     */
     public Integer getEntId() {
        return entId;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.create_time
     * Comment: 创建时间
     * @return the value of docker_cloud_image_merger_record.create_time
     */
     public String getCreateTime() {
        return createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.create_user
     * Comment: 创建用户
     * @return the value of docker_cloud_image_merger_record.create_user
     */
     public String getCreateUser() {
        return createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.current_version
     * Comment: 当前版本号
     * @return the value of docker_cloud_image_merger_record.current_version
     */
     public String getCurrentVersion() {
        return currentVersion;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.status
     * Comment: 更新状态
     * @return the value of docker_cloud_image_merger_record.status
     */
     public String getStatus() {
        return status;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.service_name
     * Comment:
     * @return the value of docker_cloud_image_merger_record.service_name
     */
     public String getServiceName() {
        return serviceName;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.domain
     * Comment:
     * @return the value of docker_cloud_image_merger_record.domain
     */
     public String getDomain() {
        return domain;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.registry_address
     * Comment: 仓库地址
     * @return the value of docker_cloud_image_merger_record.registry_address
     */
     public String getRegistryAddress() {
        return registryAddress;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.ent_name
     * Comment: 环境信息
     * @return the value of docker_cloud_image_merger_record.ent_name
     */
     public String getEntName() {
        return entName;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.registry
     * Comment: 镜像地址
     * @return the value of docker_cloud_image_merger_record.registry
     */
     public String getRegistry() {
        return registry;
    }

    /**
     * This field corresponds to the database column docker_cloud_image_merger_record.result
     * Comment: 提交信息
     * @return the value of docker_cloud_image_merger_record.result
     */
     public String getResult() {
        return result;
    }
}
