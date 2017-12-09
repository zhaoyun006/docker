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
 * @date 2017-09-28 10:38:04
 * @since 1.0
 */
public class DockerCloudServerEntity extends BaseEntity{
    private String serviceName;
    private int groupsId;
    private String entId;
    private String isMaster;

    public String getIsMaster() {
        return isMaster;
    }

    public void setIsMaster(String isMaster) {
        this.isMaster = isMaster;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public int getGroupsId() {
        return groupsId;
    }

    public void setGroupsId(int groupsId) {
        this.groupsId = groupsId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.server_id
     * Comment: 
     * @param serverId the value for docker_cloud_server.server_id
     */

    private java.lang.Integer serverId;


    /**
     * This field corresponds to the database column docker_cloud_server.server_address
     * Comment: 
     * @param serverAddress the value for docker_cloud_server.server_address
     */

    private java.lang.String serverAddress;


    /**
     * This field corresponds to the database column docker_cloud_server.groups
     * Comment: 
     * @param groups the value for docker_cloud_server.groups
     */

    private java.lang.String groups;


    /**
     * This field corresponds to the database column docker_cloud_server.comm
     * Comment: 
     * @param comm the value for docker_cloud_server.comm
     */

    private java.lang.String comm;


    /**
     * This field corresponds to the database column docker_cloud_server.cpu_number
     * Comment: 
     * @param cpuNumber the value for docker_cloud_server.cpu_number
     */

    private java.lang.String cpuNumber;


    /**
     * This field corresponds to the database column docker_cloud_server.mem_size
     * Comment: 
     * @param memSize the value for docker_cloud_server.mem_size
     */

    private java.lang.String memSize;


    /**
     * This field corresponds to the database column docker_cloud_server.instance
     * Comment: 
     * @param instance the value for docker_cloud_server.instance
     */

    private java.lang.String instance;


    /**
     * This field corresponds to the database column docker_cloud_server.status
     * Comment: 
     * @param status the value for docker_cloud_server.status
     */

    private java.lang.String status;


    /**
     * This field corresponds to the database column docker_cloud_server.no_runing
     * Comment: 
     * @param noRuning the value for docker_cloud_server.no_runing
     */

    private java.lang.String noRuning;


    /**
     * This field corresponds to the database column docker_cloud_server.images
     * Comment: 
     * @param images the value for docker_cloud_server.images
     */

    private java.lang.String images;


    /**
     * This field corresponds to the database column docker_cloud_server.paused
     * Comment: 
     * @param paused the value for docker_cloud_server.paused
     */

    private java.lang.String paused;


    /**
     * This field corresponds to the database column docker_cloud_server.docker_version
     * Comment: 
     * @param dockerVersion the value for docker_cloud_server.docker_version
     */

    private java.lang.String dockerVersion;


    /**
     * This field corresponds to the database column docker_cloud_server.mem_limit
     * Comment: 
     * @param memLimit the value for docker_cloud_server.mem_limit
     */

    private java.lang.String memLimit;


    /**
     * This field corresponds to the database column docker_cloud_server.can_install
     * Comment: 
     * @param canInstall the value for docker_cloud_server.can_install
     */

    private java.lang.String canInstall;


    /**
     * This field corresponds to the database column docker_cloud_server.kernel
     * Comment: 
     * @param kernel the value for docker_cloud_server.kernel
     */

    private java.lang.String kernel;


    /**
     * This field corresponds to the database column docker_cloud_server.hostname
     * Comment: 
     * @param hostname the value for docker_cloud_server.hostname
     */

    private java.lang.String hostname;


    /**
     * This field corresponds to the database column docker_cloud_server.cpu_shares
     * Comment: 
     * @param cpuShares the value for docker_cloud_server.cpu_shares
     */

    private java.lang.String cpuShares;


    /**
     * This field corresponds to the database column docker_cloud_server.no_running
     * Comment: 
     * @param noRunning the value for docker_cloud_server.no_running
     */

    private java.lang.String noRunning;


    /**
     * This field corresponds to the database column docker_cloud_server.last_modify_time
     * Comment: 最近修改时间
     * @param lastModifyTime the value for docker_cloud_server.last_modify_time
     */

    private java.lang.String lastModifyTime;


    /**
     * This field corresponds to the database column docker_cloud_server.last_modify_user
     * Comment: 最近修改人
     * @param lastModifyUser the value for docker_cloud_server.last_modify_user
     */

    private java.lang.String lastModifyUser;


    /**
     * This field corresponds to the database column docker_cloud_server.api_port
     * Comment: æŽ¥å£çš„å»¥
     * @param apiPort the value for docker_cloud_server.api_port
     */

    private String apiPort;


    /**
     * This field corresponds to the database column docker_cloud_server.gson_data
     * Comment: 其他未知数据
     * @param gsonData the value for docker_cloud_server.gson_data
     */

    private java.lang.String gsonData;


    /**
     * This field corresponds to the database column docker_cloud_server.cluter_tp
     * Comment: 集群类型
     * @param cluterTp the value for docker_cloud_server.cluter_tp
     */

    private java.lang.String cluterTp;


    /**
     * This field corresponds to the database column docker_cloud_server.server_id
     * Comment: 
     * @param serverId the value for docker_cloud_server.server_id
     */
    public void setServerId(java.lang.Integer serverId){
       this.serverId = serverId;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.server_address
     * Comment: 
     * @param serverAddress the value for docker_cloud_server.server_address
     */
    public void setServerAddress(java.lang.String serverAddress){
       this.serverAddress = serverAddress;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.groups
     * Comment: 
     * @param groups the value for docker_cloud_server.groups
     */
    public void setGroups(java.lang.String groups){
       this.groups = groups;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.comm
     * Comment: 
     * @param comm the value for docker_cloud_server.comm
     */
    public void setComm(java.lang.String comm){
       this.comm = comm;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.cpu_number
     * Comment: 
     * @param cpuNumber the value for docker_cloud_server.cpu_number
     */
    public void setCpuNumber(java.lang.String cpuNumber){
       this.cpuNumber = cpuNumber;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.mem_size
     * Comment: 
     * @param memSize the value for docker_cloud_server.mem_size
     */
    public void setMemSize(java.lang.String memSize){
       this.memSize = memSize;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.instance
     * Comment: 
     * @param instance the value for docker_cloud_server.instance
     */
    public void setInstance(java.lang.String instance){
       this.instance = instance;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.status
     * Comment: 
     * @param status the value for docker_cloud_server.status
     */
    public void setStatus(java.lang.String status){
       this.status = status;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.no_runing
     * Comment: 
     * @param noRuning the value for docker_cloud_server.no_runing
     */
    public void setNoRuning(java.lang.String noRuning){
       this.noRuning = noRuning;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.images
     * Comment: 
     * @param images the value for docker_cloud_server.images
     */
    public void setImages(java.lang.String images){
       this.images = images;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.paused
     * Comment: 
     * @param paused the value for docker_cloud_server.paused
     */
    public void setPaused(java.lang.String paused){
       this.paused = paused;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.docker_version
     * Comment: 
     * @param dockerVersion the value for docker_cloud_server.docker_version
     */
    public void setDockerVersion(java.lang.String dockerVersion){
       this.dockerVersion = dockerVersion;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.mem_limit
     * Comment: 
     * @param memLimit the value for docker_cloud_server.mem_limit
     */
    public void setMemLimit(java.lang.String memLimit){
       this.memLimit = memLimit;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.can_install
     * Comment: 
     * @param canInstall the value for docker_cloud_server.can_install
     */
    public void setCanInstall(java.lang.String canInstall){
       this.canInstall = canInstall;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.kernel
     * Comment: 
     * @param kernel the value for docker_cloud_server.kernel
     */
    public void setKernel(java.lang.String kernel){
       this.kernel = kernel;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.hostname
     * Comment: 
     * @param hostname the value for docker_cloud_server.hostname
     */
    public void setHostname(java.lang.String hostname){
       this.hostname = hostname;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.cpu_shares
     * Comment: 
     * @param cpuShares the value for docker_cloud_server.cpu_shares
     */
    public void setCpuShares(java.lang.String cpuShares){
       this.cpuShares = cpuShares;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.no_running
     * Comment: 
     * @param noRunning the value for docker_cloud_server.no_running
     */
    public void setNoRunning(java.lang.String noRunning){
       this.noRunning = noRunning;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.last_modify_time
     * Comment: 最近修改时间
     * @param lastModifyTime the value for docker_cloud_server.last_modify_time
     */
    public void setLastModifyTime(java.lang.String lastModifyTime){
       this.lastModifyTime = lastModifyTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.last_modify_user
     * Comment: 最近修改人
     * @param lastModifyUser the value for docker_cloud_server.last_modify_user
     */
    public void setLastModifyUser(java.lang.String lastModifyUser){
       this.lastModifyUser = lastModifyUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.api_port
     * Comment: æŽ¥å£çš„å»¥
     * @param apiPort the value for docker_cloud_server.api_port
     */
    public void setApiPort(String apiPort){
       this.apiPort = apiPort;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.gson_data
     * Comment: 其他未知数据
     * @param gsonData the value for docker_cloud_server.gson_data
     */
    public void setGsonData(java.lang.String gsonData){
       this.gsonData = gsonData;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.cluter_tp
     * Comment: 集群类型
     * @param cluterTp the value for docker_cloud_server.cluter_tp
     */
    public void setCluterTp(java.lang.String cluterTp){
       this.cluterTp = cluterTp;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.server_id
     * Comment: 
     * @return the value of docker_cloud_server.server_id
     */
     public java.lang.Integer getServerId() {
        return serverId;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.server_address
     * Comment: 
     * @return the value of docker_cloud_server.server_address
     */
     public java.lang.String getServerAddress() {
        return serverAddress;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.groups
     * Comment: 
     * @return the value of docker_cloud_server.groups
     */
     public java.lang.String getGroups() {
        return groups;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.comm
     * Comment: 
     * @return the value of docker_cloud_server.comm
     */
     public java.lang.String getComm() {
        return comm;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.cpu_number
     * Comment: 
     * @return the value of docker_cloud_server.cpu_number
     */
     public java.lang.String getCpuNumber() {
        return cpuNumber;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.mem_size
     * Comment: 
     * @return the value of docker_cloud_server.mem_size
     */
     public java.lang.String getMemSize() {
        return memSize;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.instance
     * Comment: 
     * @return the value of docker_cloud_server.instance
     */
     public java.lang.String getInstance() {
        return instance;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.status
     * Comment: 
     * @return the value of docker_cloud_server.status
     */
     public java.lang.String getStatus() {
        return status;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.no_runing
     * Comment: 
     * @return the value of docker_cloud_server.no_runing
     */
     public java.lang.String getNoRuning() {
        return noRuning;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.images
     * Comment: 
     * @return the value of docker_cloud_server.images
     */
     public java.lang.String getImages() {
        return images;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.paused
     * Comment: 
     * @return the value of docker_cloud_server.paused
     */
     public java.lang.String getPaused() {
        return paused;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.docker_version
     * Comment: 
     * @return the value of docker_cloud_server.docker_version
     */
     public java.lang.String getDockerVersion() {
        return dockerVersion;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.mem_limit
     * Comment: 
     * @return the value of docker_cloud_server.mem_limit
     */
     public java.lang.String getMemLimit() {
        return memLimit;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.can_install
     * Comment: 
     * @return the value of docker_cloud_server.can_install
     */
     public java.lang.String getCanInstall() {
        return canInstall;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.kernel
     * Comment: 
     * @return the value of docker_cloud_server.kernel
     */
     public java.lang.String getKernel() {
        return kernel;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.hostname
     * Comment: 
     * @return the value of docker_cloud_server.hostname
     */
     public java.lang.String getHostname() {
        return hostname;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.cpu_shares
     * Comment: 
     * @return the value of docker_cloud_server.cpu_shares
     */
     public java.lang.String getCpuShares() {
        return cpuShares;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.no_running
     * Comment: 
     * @return the value of docker_cloud_server.no_running
     */
     public java.lang.String getNoRunning() {
        return noRunning;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.last_modify_time
     * Comment: 最近修改时间
     * @return the value of docker_cloud_server.last_modify_time
     */
     public java.lang.String getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.last_modify_user
     * Comment: 最近修改人
     * @return the value of docker_cloud_server.last_modify_user
     */
     public java.lang.String getLastModifyUser() {
        return lastModifyUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.api_port
     * Comment: æŽ¥å£çš„å»¥
     * @return the value of docker_cloud_server.api_port
     */
     public String getApiPort() {
        return apiPort;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.gson_data
     * Comment: 其他未知数据
     * @return the value of docker_cloud_server.gson_data
     */
     public java.lang.String getGsonData() {
        return gsonData;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.cluter_tp
     * Comment: 集群类型
     * @return the value of docker_cloud_server.cluter_tp
     */
     public java.lang.String getCluterTp() {
        return cluterTp;
    }
}
