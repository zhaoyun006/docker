package com.cloud.sms.docker.service.entity;
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
 * @date 2017-11-07 21:33:20
 * @since 1.0
 */
public class DockerCloudServiceEntity extends BaseEntity{
    private String tableName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    /**
     * This field corresponds to the database column docker_cloud_service.service_id
     * Comment: 主键
     * @param serviceId the value for docker_cloud_service.service_id
     */

    private java.lang.Integer serviceId;


    /**
     * This field corresponds to the database column docker_cloud_service.service_name
     * Comment: 服务名称
     * @param serviceName the value for docker_cloud_service.service_name
     */

    private java.lang.String serviceName;


    /**
     * This field corresponds to the database column docker_cloud_service.app_name
     * Comment: 应用栈名称,和应用栈关联使用
     * @param appName the value for docker_cloud_service.app_name
     */

    private java.lang.String appName;


    /**
     * This field corresponds to the database column docker_cloud_service.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_service.create_time
     */

    private java.lang.String createTime;


    /**
     * This field corresponds to the database column docker_cloud_service.create_user
     * Comment: 创建人
     * @param createUser the value for docker_cloud_service.create_user
     */

    private java.lang.String createUser;


    /**
     * This field corresponds to the database column docker_cloud_service.memory
     * Comment: 内存
     * @param memory the value for docker_cloud_service.memory
     */

    private java.lang.String memory;


    /**
     * This field corresponds to the database column docker_cloud_service.cpu
     * Comment: cpu使用
     * @param cpu the value for docker_cloud_service.cpu
     */

    private java.lang.String cpu;


    /**
     * This field corresponds to the database column docker_cloud_service.privileged
     * Comment: 特权模式
     * @param privileged the value for docker_cloud_service.privileged
     */

    private java.lang.String privileged;


    /**
     * This field corresponds to the database column docker_cloud_service.cmd
     * Comment: 运行命令
     * @param cmd the value for docker_cloud_service.cmd
     */

    private java.lang.String cmd;


    /**
     * This field corresponds to the database column docker_cloud_service.image
     * Comment: 镜像
     * @param image the value for docker_cloud_service.image
     */

    private java.lang.String image;


    /**
     * This field corresponds to the database column docker_cloud_service.container_number
     * Comment: 容器数量
     * @param containerNumber the value for docker_cloud_service.container_number
     */

    private java.lang.Integer containerNumber;


    /**
     * This field corresponds to the database column docker_cloud_service.gson_data
     * Comment: 其他未知数据
     * @param gsonData the value for docker_cloud_service.gson_data
     */

    private java.lang.String gsonData;


    /**
     * This field corresponds to the database column docker_cloud_service.service_tp
     * Comment: 服务类型
     * @param serviceTp the value for docker_cloud_service.service_tp
     */

    private java.lang.Integer serviceTp;


    /**
     * This field corresponds to the database column docker_cloud_service.scale_on
     * Comment: 是否打开自动伸缩
     * @param scaleOn the value for docker_cloud_service.scale_on
     */

    private java.lang.Integer scaleOn;


    /**
     * This field corresponds to the database column docker_cloud_service.scale_min
     * Comment: 最小容器数
     * @param scaleMin the value for docker_cloud_service.scale_min
     */

    private java.lang.Integer scaleMin;


    /**
     * This field corresponds to the database column docker_cloud_service.scale_max
     * Comment: 最大容器数,自动扩容不会超过
     * @param scaleMax the value for docker_cloud_service.scale_max
     */

    private java.lang.Integer scaleMax;


    /**
     * This field corresponds to the database column docker_cloud_service.scale_mem
     * Comment: 多个容器平均内存大于这个数就开始扩容,内存百分比
     * @param scaleMem the value for docker_cloud_service.scale_mem
     */

    private java.lang.Integer scaleMem;


    /**
     * This field corresponds to the database column docker_cloud_service.scale_cpu
     * Comment: 多个容器平均CPU大于这个数就开始扩容,CPU百分比
     * @param scaleCpu the value for docker_cloud_service.scale_cpu
     */

    private java.lang.Integer scaleCpu;


    /**
     * This field corresponds to the database column docker_cloud_service.ent_id
     * Comment: 所属环境
     * @param entId the value for docker_cloud_service.ent_id
     */

    private java.lang.Integer entId;


    /**
     * This field corresponds to the database column docker_cloud_service.groups_id
     * Comment: 所属环境
     * @param groupsId the value for docker_cloud_service.groups_id
     */

    private java.lang.Integer groupsId;


    /**
     * This field corresponds to the database column docker_cloud_service.groups_name
     * Comment: 组名称
     * @param groupsName the value for docker_cloud_service.groups_name
     */

    private java.lang.String groupsName;


    /**
     * This field corresponds to the database column docker_cloud_service.entname
     * Comment: 环境名称
     * @param entname the value for docker_cloud_service.entname
     */

    private java.lang.String entname;


    /**
     * This field corresponds to the database column docker_cloud_service.service_id
     * Comment: 主键
     * @param serviceId the value for docker_cloud_service.service_id
     */
    public void setServiceId(java.lang.Integer serviceId){
        this.serviceId = serviceId;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.service_name
     * Comment: 服务名称
     * @param serviceName the value for docker_cloud_service.service_name
     */
    public void setServiceName(java.lang.String serviceName){
        this.serviceName = serviceName;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.app_name
     * Comment: 应用栈名称,和应用栈关联使用
     * @param appName the value for docker_cloud_service.app_name
     */
    public void setAppName(java.lang.String appName){
        this.appName = appName;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_service.create_time
     */
    public void setCreateTime(java.lang.String createTime){
        this.createTime = createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.create_user
     * Comment: 创建人
     * @param createUser the value for docker_cloud_service.create_user
     */
    public void setCreateUser(java.lang.String createUser){
        this.createUser = createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.memory
     * Comment: 内存
     * @param memory the value for docker_cloud_service.memory
     */
    public void setMemory(java.lang.String memory){
        this.memory = memory;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.cpu
     * Comment: cpu使用
     * @param cpu the value for docker_cloud_service.cpu
     */
    public void setCpu(java.lang.String cpu){
        this.cpu = cpu;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.privileged
     * Comment: 特权模式
     * @param privileged the value for docker_cloud_service.privileged
     */
    public void setPrivileged(java.lang.String privileged){
        this.privileged = privileged;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.cmd
     * Comment: 运行命令
     * @param cmd the value for docker_cloud_service.cmd
     */
    public void setCmd(java.lang.String cmd){
        this.cmd = cmd;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.image
     * Comment: 镜像
     * @param image the value for docker_cloud_service.image
     */
    public void setImage(java.lang.String image){
        this.image = image;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.container_number
     * Comment: 容器数量
     * @param containerNumber the value for docker_cloud_service.container_number
     */
    public void setContainerNumber(java.lang.Integer containerNumber){
        this.containerNumber = containerNumber;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.gson_data
     * Comment: 其他未知数据
     * @param gsonData the value for docker_cloud_service.gson_data
     */
    public void setGsonData(java.lang.String gsonData){
        this.gsonData = gsonData;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.service_tp
     * Comment: 服务类型
     * @param serviceTp the value for docker_cloud_service.service_tp
     */
    public void setServiceTp(java.lang.Integer serviceTp){
        this.serviceTp = serviceTp;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.scale_on
     * Comment: 是否打开自动伸缩
     * @param scaleOn the value for docker_cloud_service.scale_on
     */
    public void setScaleOn(java.lang.Integer scaleOn){
        this.scaleOn = scaleOn;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.scale_min
     * Comment: 最小容器数
     * @param scaleMin the value for docker_cloud_service.scale_min
     */
    public void setScaleMin(java.lang.Integer scaleMin){
        this.scaleMin = scaleMin;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.scale_max
     * Comment: 最大容器数,自动扩容不会超过
     * @param scaleMax the value for docker_cloud_service.scale_max
     */
    public void setScaleMax(java.lang.Integer scaleMax){
        this.scaleMax = scaleMax;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.scale_mem
     * Comment: 多个容器平均内存大于这个数就开始扩容,内存百分比
     * @param scaleMem the value for docker_cloud_service.scale_mem
     */
    public void setScaleMem(java.lang.Integer scaleMem){
        this.scaleMem = scaleMem;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.scale_cpu
     * Comment: 多个容器平均CPU大于这个数就开始扩容,CPU百分比
     * @param scaleCpu the value for docker_cloud_service.scale_cpu
     */
    public void setScaleCpu(java.lang.Integer scaleCpu){
        this.scaleCpu = scaleCpu;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.ent_id
     * Comment: 所属环境
     * @param entId the value for docker_cloud_service.ent_id
     */
    public void setEntId(java.lang.Integer entId){
        this.entId = entId;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.groups_id
     * Comment: 所属环境
     * @param groupsId the value for docker_cloud_service.groups_id
     */
    public void setGroupsId(java.lang.Integer groupsId){
        this.groupsId = groupsId;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.groups_name
     * Comment: 组名称
     * @param groupsName the value for docker_cloud_service.groups_name
     */
    public void setGroupsName(java.lang.String groupsName){
        this.groupsName = groupsName;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.entname
     * Comment: 环境名称
     * @param entname the value for docker_cloud_service.entname
     */
    public void setEntname(java.lang.String entname){
        this.entname = entname;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.service_id
     * Comment: 主键
     * @return the value of docker_cloud_service.service_id
     */
    public java.lang.Integer getServiceId() {
        return serviceId;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.service_name
     * Comment: 服务名称
     * @return the value of docker_cloud_service.service_name
     */
    public java.lang.String getServiceName() {
        return serviceName;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.app_name
     * Comment: 应用栈名称,和应用栈关联使用
     * @return the value of docker_cloud_service.app_name
     */
    public java.lang.String getAppName() {
        return appName;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.create_time
     * Comment: 创建时间
     * @return the value of docker_cloud_service.create_time
     */
    public java.lang.String getCreateTime() {
        return createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.create_user
     * Comment: 创建人
     * @return the value of docker_cloud_service.create_user
     */
    public java.lang.String getCreateUser() {
        return createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.memory
     * Comment: 内存
     * @return the value of docker_cloud_service.memory
     */
    public java.lang.String getMemory() {
        return memory;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.cpu
     * Comment: cpu使用
     * @return the value of docker_cloud_service.cpu
     */
    public java.lang.String getCpu() {
        return cpu;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.privileged
     * Comment: 特权模式
     * @return the value of docker_cloud_service.privileged
     */
    public java.lang.String getPrivileged() {
        return privileged;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.cmd
     * Comment: 运行命令
     * @return the value of docker_cloud_service.cmd
     */
    public java.lang.String getCmd() {
        return cmd;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.image
     * Comment: 镜像
     * @return the value of docker_cloud_service.image
     */
    public java.lang.String getImage() {
        return image;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.container_number
     * Comment: 容器数量
     * @return the value of docker_cloud_service.container_number
     */
    public java.lang.Integer getContainerNumber() {
        return containerNumber;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.gson_data
     * Comment: 其他未知数据
     * @return the value of docker_cloud_service.gson_data
     */
    public java.lang.String getGsonData() {
        return gsonData;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.service_tp
     * Comment: 服务类型
     * @return the value of docker_cloud_service.service_tp
     */
    public java.lang.Integer getServiceTp() {
        return serviceTp;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.scale_on
     * Comment: 是否打开自动伸缩
     * @return the value of docker_cloud_service.scale_on
     */
    public java.lang.Integer getScaleOn() {
        return scaleOn;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.scale_min
     * Comment: 最小容器数
     * @return the value of docker_cloud_service.scale_min
     */
    public java.lang.Integer getScaleMin() {
        return scaleMin;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.scale_max
     * Comment: 最大容器数,自动扩容不会超过
     * @return the value of docker_cloud_service.scale_max
     */
    public java.lang.Integer getScaleMax() {
        return scaleMax;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.scale_mem
     * Comment: 多个容器平均内存大于这个数就开始扩容,内存百分比
     * @return the value of docker_cloud_service.scale_mem
     */
    public java.lang.Integer getScaleMem() {
        return scaleMem;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.scale_cpu
     * Comment: 多个容器平均CPU大于这个数就开始扩容,CPU百分比
     * @return the value of docker_cloud_service.scale_cpu
     */
    public java.lang.Integer getScaleCpu() {
        return scaleCpu;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.ent_id
     * Comment: 所属环境
     * @return the value of docker_cloud_service.ent_id
     */
    public java.lang.Integer getEntId() {
        return entId;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.groups_id
     * Comment: 所属环境
     * @return the value of docker_cloud_service.groups_id
     */
    public java.lang.Integer getGroupsId() {
        return groupsId;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.groups_name
     * Comment: 组名称
     * @return the value of docker_cloud_service.groups_name
     */
    public java.lang.String getGroupsName() {
        return groupsName;
    }

    /**
     * This field corresponds to the database column docker_cloud_service.entname
     * Comment: 环境名称
     * @return the value of docker_cloud_service.entname
     */
    public java.lang.String getEntname() {
        return entname;
    }
}
