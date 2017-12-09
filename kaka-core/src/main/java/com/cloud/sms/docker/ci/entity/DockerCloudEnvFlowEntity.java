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
 * @date 2017-11-03 14:32:15
 * @since 1.0
 */
public class DockerCloudEnvFlowEntity extends BaseEntity{
    private String serviceName;
    private String domain;
    private int flow;
    public String getServiceName() {
        return serviceName;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * This field corresponds to the database column docker_cloud_env_flow.flow_id
     * Comment: 
     * @param flowId the value for docker_cloud_env_flow.flow_id
     */

    private java.lang.Integer flowId;


    /**
     * This field corresponds to the database column docker_cloud_env_flow.env_id
     * Comment: 参考环境信息
     * @param envId the value for docker_cloud_env_flow.env_id
     */

    private java.lang.Integer envId;


    /**
     * This field corresponds to the database column docker_cloud_env_flow.release_order
     * Comment: 环境发布顺序,第一个先发布,发布完第一个才可以发布第二个
     * @param releaseOrder the value for docker_cloud_env_flow.release_order
     */

    private java.lang.String releaseOrder;


    /**
     * This field corresponds to the database column docker_cloud_env_flow.create_user
     * Comment: 创建人
     * @param createUser the value for docker_cloud_env_flow.create_user
     */

    private java.lang.String createUser;


    /**
     * This field corresponds to the database column docker_cloud_env_flow.last_modify_user
     * Comment: 最近修改人
     * @param lastModifyUser the value for docker_cloud_env_flow.last_modify_user
     */

    private java.lang.String lastModifyUser;


    /**
     * This field corresponds to the database column docker_cloud_env_flow.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_env_flow.create_time
     */

    private java.lang.String createTime;


    /**
     * This field corresponds to the database column docker_cloud_env_flow.last_modify_time
     * Comment: 最近修改时间
     * @param lastModifyTime the value for docker_cloud_env_flow.last_modify_time
     */

    private java.lang.String lastModifyTime;


    /**
     * This field corresponds to the database column docker_cloud_env_flow.description
     * Comment: 备注信息
     * @param description the value for docker_cloud_env_flow.description
     */

    private java.lang.String description;


    /**
     * This field corresponds to the database column docker_cloud_env_flow.flow_id
     * Comment: 
     * @param flowId the value for docker_cloud_env_flow.flow_id
     */
    public void setFlowId(java.lang.Integer flowId){
       this.flowId = flowId;
    }

    /**
     * This field corresponds to the database column docker_cloud_env_flow.env_id
     * Comment: 参考环境信息
     * @param envId the value for docker_cloud_env_flow.env_id
     */
    public void setEnvId(java.lang.Integer envId){
       this.envId = envId;
    }

    /**
     * This field corresponds to the database column docker_cloud_env_flow.release_order
     * Comment: 环境发布顺序,第一个先发布,发布完第一个才可以发布第二个
     * @param releaseOrder the value for docker_cloud_env_flow.release_order
     */
    public void setReleaseOrder(java.lang.String releaseOrder){
       this.releaseOrder = releaseOrder;
    }

    /**
     * This field corresponds to the database column docker_cloud_env_flow.create_user
     * Comment: 创建人
     * @param createUser the value for docker_cloud_env_flow.create_user
     */
    public void setCreateUser(java.lang.String createUser){
       this.createUser = createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_env_flow.last_modify_user
     * Comment: 最近修改人
     * @param lastModifyUser the value for docker_cloud_env_flow.last_modify_user
     */
    public void setLastModifyUser(java.lang.String lastModifyUser){
       this.lastModifyUser = lastModifyUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_env_flow.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_env_flow.create_time
     */
    public void setCreateTime(java.lang.String createTime){
       this.createTime = createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_env_flow.last_modify_time
     * Comment: 最近修改时间
     * @param lastModifyTime the value for docker_cloud_env_flow.last_modify_time
     */
    public void setLastModifyTime(java.lang.String lastModifyTime){
       this.lastModifyTime = lastModifyTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_env_flow.description
     * Comment: 备注信息
     * @param description the value for docker_cloud_env_flow.description
     */
    public void setDescription(java.lang.String description){
       this.description = description;
    }

    /**
     * This field corresponds to the database column docker_cloud_env_flow.flow_id
     * Comment: 
     * @return the value of docker_cloud_env_flow.flow_id
     */
     public java.lang.Integer getFlowId() {
        return flowId;
    }

    /**
     * This field corresponds to the database column docker_cloud_env_flow.env_id
     * Comment: 参考环境信息
     * @return the value of docker_cloud_env_flow.env_id
     */
     public java.lang.Integer getEnvId() {
        return envId;
    }

    /**
     * This field corresponds to the database column docker_cloud_env_flow.release_order
     * Comment: 环境发布顺序,第一个先发布,发布完第一个才可以发布第二个
     * @return the value of docker_cloud_env_flow.release_order
     */
     public java.lang.String getReleaseOrder() {
        return releaseOrder;
    }

    /**
     * This field corresponds to the database column docker_cloud_env_flow.create_user
     * Comment: 创建人
     * @return the value of docker_cloud_env_flow.create_user
     */
     public java.lang.String getCreateUser() {
        return createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_env_flow.last_modify_user
     * Comment: 最近修改人
     * @return the value of docker_cloud_env_flow.last_modify_user
     */
     public java.lang.String getLastModifyUser() {
        return lastModifyUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_env_flow.create_time
     * Comment: 创建时间
     * @return the value of docker_cloud_env_flow.create_time
     */
     public java.lang.String getCreateTime() {
        return createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_env_flow.last_modify_time
     * Comment: 最近修改时间
     * @return the value of docker_cloud_env_flow.last_modify_time
     */
     public java.lang.String getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_env_flow.description
     * Comment: 备注信息
     * @return the value of docker_cloud_env_flow.description
     */
     public java.lang.String getDescription() {
        return description;
    }
}
