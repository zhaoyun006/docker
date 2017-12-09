package com.cloud.sms.docker.server.entity;
import com.asura.framework.base.entity.BaseEntity;

import java.util.ArrayList;


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
 * @date 2017-11-08 17:22:02
 * @since 1.0
 */
public class DockerCloudEntEntity extends BaseEntity{
    private String release;
    private ArrayList network;
    private String defaultNetwork;
    private String loadblanceTp;

    public String getLoadblanceTp() {
        return loadblanceTp;
    }

    public void setLoadblanceTp(String loadblanceTp) {
        this.loadblanceTp = loadblanceTp;
    }

    public String getDefaultNetwork() {
        return defaultNetwork;
    }

    public void setDefaultNetwork(String defaultNetwork) {
        this.defaultNetwork = defaultNetwork;
    }

    public ArrayList getNetwork() {
        return network;
    }

    public void setNetwork(ArrayList network) {
        this.network = network;
    }



    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.ent_id
     * Comment: 
     * @param entId the value for docker_cloud_ent.ent_id
     */

    private java.lang.Integer entId;


    /**
     * This field corresponds to the database column docker_cloud_ent.create_user
     * Comment: 创建人
     * @param createUser the value for docker_cloud_ent.create_user
     */

    private java.lang.String createUser;


    /**
     * This field corresponds to the database column docker_cloud_ent.last_modify_user
     * Comment: 最近修改人
     * @param lastModifyUser the value for docker_cloud_ent.last_modify_user
     */

    private java.lang.String lastModifyUser;


    /**
     * This field corresponds to the database column docker_cloud_ent.last_modify_time
     * Comment: 最近修改时间
     * @param lastModifyTime the value for docker_cloud_ent.last_modify_time
     */

    private java.lang.String lastModifyTime;


    /**
     * This field corresponds to the database column docker_cloud_ent.description
     * Comment: 备注信息
     * @param description the value for docker_cloud_ent.description
     */

    private java.lang.String description;


    /**
     * This field corresponds to the database column docker_cloud_ent.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_ent.create_time
     */

    private java.lang.String createTime;


    /**
     * This field corresponds to the database column docker_cloud_ent.entname
     * Comment: 环境名称
     * @param entname the value for docker_cloud_ent.entname
     */

    private java.lang.String entname;


    /**
     * This field corresponds to the database column docker_cloud_ent.container_min
     * Comment: 最小初始化容器
     * @param containerMin the value for docker_cloud_ent.container_min
     */

    private java.lang.Integer containerMin;


    /**
     * This field corresponds to the database column docker_cloud_ent.container_max
     * Comment: 最大扩容容器量
     * @param containerMax the value for docker_cloud_ent.container_max
     */

    private java.lang.Integer containerMax;


    /**
     * This field corresponds to the database column docker_cloud_ent.cpu
     * Comment: 初始化cpu大小
     * @param cpu the value for docker_cloud_ent.cpu
     */

    private java.lang.Integer cpu;


    /**
     * This field corresponds to the database column docker_cloud_ent.memory
     * Comment: 初始化memory大小
     * @param memory the value for docker_cloud_ent.memory
     */

    private java.lang.Integer memory;


    /**
     * This field corresponds to the database column docker_cloud_ent.version
     * Comment: 占位
     * @param version the value for docker_cloud_ent.version
     */

    private java.lang.String version;


    /**
     * This field corresponds to the database column docker_cloud_ent.checked
     * Comment: 占位
     * @param checked the value for docker_cloud_ent.checked
     */

    private java.lang.String checked;


    /**
     * This field corresponds to the database column docker_cloud_ent.disabled
     * Comment: 占位
     * @param disabled the value for docker_cloud_ent.disabled
     */

    private java.lang.String disabled;


    /**
     * This field corresponds to the database column docker_cloud_ent.title
     * Comment: 占位
     * @param title the value for docker_cloud_ent.title
     */

    private java.lang.String title;


    /**
     * This field corresponds to the database column docker_cloud_ent.ent_id
     * Comment: 
     * @param entId the value for docker_cloud_ent.ent_id
     */
    public void setEntId(java.lang.Integer entId){
       this.entId = entId;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.create_user
     * Comment: 创建人
     * @param createUser the value for docker_cloud_ent.create_user
     */
    public void setCreateUser(java.lang.String createUser){
       this.createUser = createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.last_modify_user
     * Comment: 最近修改人
     * @param lastModifyUser the value for docker_cloud_ent.last_modify_user
     */
    public void setLastModifyUser(java.lang.String lastModifyUser){
       this.lastModifyUser = lastModifyUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.last_modify_time
     * Comment: 最近修改时间
     * @param lastModifyTime the value for docker_cloud_ent.last_modify_time
     */
    public void setLastModifyTime(java.lang.String lastModifyTime){
       this.lastModifyTime = lastModifyTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.description
     * Comment: 备注信息
     * @param description the value for docker_cloud_ent.description
     */
    public void setDescription(java.lang.String description){
       this.description = description;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_ent.create_time
     */
    public void setCreateTime(java.lang.String createTime){
       this.createTime = createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.entname
     * Comment: 环境名称
     * @param entname the value for docker_cloud_ent.entname
     */
    public void setEntname(java.lang.String entname){
       this.entname = entname;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.container_min
     * Comment: 最小初始化容器
     * @param containerMin the value for docker_cloud_ent.container_min
     */
    public void setContainerMin(java.lang.Integer containerMin){
       this.containerMin = containerMin;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.container_max
     * Comment: 最大扩容容器量
     * @param containerMax the value for docker_cloud_ent.container_max
     */
    public void setContainerMax(java.lang.Integer containerMax){
       this.containerMax = containerMax;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.cpu
     * Comment: 初始化cpu大小
     * @param cpu the value for docker_cloud_ent.cpu
     */
    public void setCpu(java.lang.Integer cpu){
       this.cpu = cpu;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.memory
     * Comment: 初始化memory大小
     * @param memory the value for docker_cloud_ent.memory
     */
    public void setMemory(java.lang.Integer memory){
       this.memory = memory;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.version
     * Comment: 占位
     * @param version the value for docker_cloud_ent.version
     */
    public void setVersion(java.lang.String version){
       this.version = version;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.checked
     * Comment: 占位
     * @param checked the value for docker_cloud_ent.checked
     */
    public void setChecked(java.lang.String checked){
       this.checked = checked;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.disabled
     * Comment: 占位
     * @param disabled the value for docker_cloud_ent.disabled
     */
    public void setDisabled(java.lang.String disabled){
       this.disabled = disabled;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.title
     * Comment: 占位
     * @param title the value for docker_cloud_ent.title
     */
    public void setTitle(java.lang.String title){
       this.title = title;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.ent_id
     * Comment: 
     * @return the value of docker_cloud_ent.ent_id
     */
     public java.lang.Integer getEntId() {
        return entId;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.create_user
     * Comment: 创建人
     * @return the value of docker_cloud_ent.create_user
     */
     public java.lang.String getCreateUser() {
        return createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.last_modify_user
     * Comment: 最近修改人
     * @return the value of docker_cloud_ent.last_modify_user
     */
     public java.lang.String getLastModifyUser() {
        return lastModifyUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.last_modify_time
     * Comment: 最近修改时间
     * @return the value of docker_cloud_ent.last_modify_time
     */
     public java.lang.String getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.description
     * Comment: 备注信息
     * @return the value of docker_cloud_ent.description
     */
     public java.lang.String getDescription() {
        return description;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.create_time
     * Comment: 创建时间
     * @return the value of docker_cloud_ent.create_time
     */
     public java.lang.String getCreateTime() {
        return createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.entname
     * Comment: 环境名称
     * @return the value of docker_cloud_ent.entname
     */
     public java.lang.String getEntname() {
        return entname;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.container_min
     * Comment: 最小初始化容器
     * @return the value of docker_cloud_ent.container_min
     */
     public java.lang.Integer getContainerMin() {
        return containerMin;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.container_max
     * Comment: 最大扩容容器量
     * @return the value of docker_cloud_ent.container_max
     */
     public java.lang.Integer getContainerMax() {
        return containerMax;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.cpu
     * Comment: 初始化cpu大小
     * @return the value of docker_cloud_ent.cpu
     */
     public java.lang.Integer getCpu() {
        return cpu;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.memory
     * Comment: 初始化memory大小
     * @return the value of docker_cloud_ent.memory
     */
     public java.lang.Integer getMemory() {
        return memory;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.version
     * Comment: 占位
     * @return the value of docker_cloud_ent.version
     */
     public java.lang.String getVersion() {
        return version;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.checked
     * Comment: 占位
     * @return the value of docker_cloud_ent.checked
     */
     public java.lang.String getChecked() {
        return checked;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.disabled
     * Comment: 占位
     * @return the value of docker_cloud_ent.disabled
     */
     public java.lang.String getDisabled() {
        return disabled;
    }

    /**
     * This field corresponds to the database column docker_cloud_ent.title
     * Comment: 占位
     * @return the value of docker_cloud_ent.title
     */
     public java.lang.String getTitle() {
        return title;
    }
}
