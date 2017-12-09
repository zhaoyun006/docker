package com.cloud.sms.docker.network.entity;
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
 * @date 2017-10-19 08:49:19
 * @since 1.0
 */
public class DockerCloudNetworkEntity extends BaseEntity{
    private String groupsName;
    private String entId;

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getGroupsName() {
        return groupsName;
    }

    public void setGroupsName(String groupsName) {
        this.groupsName = groupsName;
    }

    /**
     * This field corresponds to the database column docker_cloud_network.network_id
     * Comment: 
     * @param networkId the value for docker_cloud_network.network_id
     */

    private java.lang.Integer networkId;


    /**
     * This field corresponds to the database column docker_cloud_network.name
     * Comment: 子网名称
     * @param name the value for docker_cloud_network.name
     */

    private java.lang.String name;


    /**
     * This field corresponds to the database column docker_cloud_network.subnet_id
     * Comment: 网络ID
     * @param subnetId the value for docker_cloud_network.subnet_id
     */

    private java.lang.String subnetId;


    /**
     * This field corresponds to the database column docker_cloud_network.network_ip
     * Comment: 网络IP地址,子网掩码
     * @param networkIp the value for docker_cloud_network.network_ip
     */

    private java.lang.String networkIp;


    /**
     * This field corresponds to the database column docker_cloud_network.container_Number
     * Comment: 容器数量
     * @param containerNumber the value for docker_cloud_network.container_Number
     */

    private java.lang.Integer containerNumber;


    /**
     * This field corresponds to the database column docker_cloud_network.create_user
     * Comment: 创建人
     * @param createUser the value for docker_cloud_network.create_user
     */

    private java.lang.String createUser;


    /**
     * This field corresponds to the database column docker_cloud_network.last_modify_user
     * Comment: 最近修改人
     * @param lastModifyUser the value for docker_cloud_network.last_modify_user
     */

    private java.lang.String lastModifyUser;


    /**
     * This field corresponds to the database column docker_cloud_network.last_modify_time
     * Comment: 最近修改时间
     * @param lastModifyTime the value for docker_cloud_network.last_modify_time
     */

    private java.lang.String lastModifyTime;


    /**
     * This field corresponds to the database column docker_cloud_network.description
     * Comment: 备注信息
     * @param description the value for docker_cloud_network.description
     */

    private java.lang.String description;


    /**
     * This field corresponds to the database column docker_cloud_network.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_network.create_time
     */

    private java.lang.String createTime;


    /**
     * This field corresponds to the database column docker_cloud_network.groups_id
     * Comment: 参考主机组ID
     * @param groupsId the value for docker_cloud_network.groups_id
     */

    private java.lang.Integer groupsId;


    /**
     * This field corresponds to the database column docker_cloud_network.network_id
     * Comment: 
     * @param networkId the value for docker_cloud_network.network_id
     */
    public void setNetworkId(java.lang.Integer networkId){
       this.networkId = networkId;
    }

    /**
     * This field corresponds to the database column docker_cloud_network.name
     * Comment: 子网名称
     * @param name the value for docker_cloud_network.name
     */
    public void setName(java.lang.String name){
       this.name = name;
    }

    /**
     * This field corresponds to the database column docker_cloud_network.subnet_id
     * Comment: 网络ID
     * @param subnetId the value for docker_cloud_network.subnet_id
     */
    public void setSubnetId(java.lang.String subnetId){
       this.subnetId = subnetId;
    }

    /**
     * This field corresponds to the database column docker_cloud_network.network_ip
     * Comment: 网络IP地址,子网掩码
     * @param networkIp the value for docker_cloud_network.network_ip
     */
    public void setNetworkIp(java.lang.String networkIp){
       this.networkIp = networkIp;
    }

    /**
     * This field corresponds to the database column docker_cloud_network.container_Number
     * Comment: 容器数量
     * @param containerNumber the value for docker_cloud_network.container_Number
     */
    public void setContainerNumber(java.lang.Integer containerNumber){
       this.containerNumber = containerNumber;
    }

    /**
     * This field corresponds to the database column docker_cloud_network.create_user
     * Comment: 创建人
     * @param createUser the value for docker_cloud_network.create_user
     */
    public void setCreateUser(java.lang.String createUser){
       this.createUser = createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_network.last_modify_user
     * Comment: 最近修改人
     * @param lastModifyUser the value for docker_cloud_network.last_modify_user
     */
    public void setLastModifyUser(java.lang.String lastModifyUser){
       this.lastModifyUser = lastModifyUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_network.last_modify_time
     * Comment: 最近修改时间
     * @param lastModifyTime the value for docker_cloud_network.last_modify_time
     */
    public void setLastModifyTime(java.lang.String lastModifyTime){
       this.lastModifyTime = lastModifyTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_network.description
     * Comment: 备注信息
     * @param description the value for docker_cloud_network.description
     */
    public void setDescription(java.lang.String description){
       this.description = description;
    }

    /**
     * This field corresponds to the database column docker_cloud_network.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_network.create_time
     */
    public void setCreateTime(java.lang.String createTime){
       this.createTime = createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_network.groups_id
     * Comment: 参考主机组ID
     * @param groupsId the value for docker_cloud_network.groups_id
     */
    public void setGroupsId(java.lang.Integer groupsId){
       this.groupsId = groupsId;
    }

    /**
     * This field corresponds to the database column docker_cloud_network.network_id
     * Comment: 
     * @return the value of docker_cloud_network.network_id
     */
     public java.lang.Integer getNetworkId() {
        return networkId;
    }

    /**
     * This field corresponds to the database column docker_cloud_network.name
     * Comment: 子网名称
     * @return the value of docker_cloud_network.name
     */
     public java.lang.String getName() {
        return name;
    }

    /**
     * This field corresponds to the database column docker_cloud_network.subnet_id
     * Comment: 网络ID
     * @return the value of docker_cloud_network.subnet_id
     */
     public java.lang.String getSubnetId() {
        return subnetId;
    }

    /**
     * This field corresponds to the database column docker_cloud_network.network_ip
     * Comment: 网络IP地址,子网掩码
     * @return the value of docker_cloud_network.network_ip
     */
     public java.lang.String getNetworkIp() {
        return networkIp;
    }

    /**
     * This field corresponds to the database column docker_cloud_network.container_Number
     * Comment: 容器数量
     * @return the value of docker_cloud_network.container_Number
     */
     public java.lang.Integer getContainerNumber() {
        return containerNumber;
    }

    /**
     * This field corresponds to the database column docker_cloud_network.create_user
     * Comment: 创建人
     * @return the value of docker_cloud_network.create_user
     */
     public java.lang.String getCreateUser() {
        return createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_network.last_modify_user
     * Comment: 最近修改人
     * @return the value of docker_cloud_network.last_modify_user
     */
     public java.lang.String getLastModifyUser() {
        return lastModifyUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_network.last_modify_time
     * Comment: 最近修改时间
     * @return the value of docker_cloud_network.last_modify_time
     */
     public java.lang.String getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_network.description
     * Comment: 备注信息
     * @return the value of docker_cloud_network.description
     */
     public java.lang.String getDescription() {
        return description;
    }

    /**
     * This field corresponds to the database column docker_cloud_network.create_time
     * Comment: 创建时间
     * @return the value of docker_cloud_network.create_time
     */
     public java.lang.String getCreateTime() {
        return createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_network.groups_id
     * Comment: 参考主机组ID
     * @return the value of docker_cloud_network.groups_id
     */
     public java.lang.Integer getGroupsId() {
        return groupsId;
    }
}
