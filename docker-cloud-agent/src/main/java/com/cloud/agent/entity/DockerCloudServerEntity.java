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
 * @date 2017-09-28 10:38:04
 * @since 1.0
 */
public class DockerCloudServerEntity {
    /**
     * This field corresponds to the database column docker_cloud_server.server_id
     * Comment: 
     * @param serverId the value for docker_cloud_server.server_id
     */

    private Integer serverId;


    /**
     * This field corresponds to the database column docker_cloud_server.server_address
     * Comment:
     * @param serverAddress the value for docker_cloud_server.server_address
     */

    private String serverAddress;


    /**
     * This field corresponds to the database column docker_cloud_server.groups
     * Comment:
     * @param groups the value for docker_cloud_server.groups
     */

    private String groups;


    /**
     * This field corresponds to the database column docker_cloud_server.comm
     * Comment:
     * @param comm the value for docker_cloud_server.comm
     */

    private String comm;


    /**
     * This field corresponds to the database column docker_cloud_server.cpu_number
     * Comment:
     * @param cpuNumber the value for docker_cloud_server.cpu_number
     */

    private String cpuNumber;


    /**
     * This field corresponds to the database column docker_cloud_server.mem_size
     * Comment:
     * @param memSize the value for docker_cloud_server.mem_size
     */

    private String memSize;


    /**
     * This field corresponds to the database column docker_cloud_server.instance
     * Comment:
     * @param instance the value for docker_cloud_server.instance
     */

    private String instance;


    /**
     * This field corresponds to the database column docker_cloud_server.status
     * Comment:
     * @param status the value for docker_cloud_server.status
     */

    private String status;


    /**
     * This field corresponds to the database column docker_cloud_server.no_runing
     * Comment:
     * @param noRuning the value for docker_cloud_server.no_runing
     */

    private String noRuning;


    /**
     * This field corresponds to the database column docker_cloud_server.images
     * Comment:
     * @param images the value for docker_cloud_server.images
     */

    private String images;


    /**
     * This field corresponds to the database column docker_cloud_server.paused
     * Comment:
     * @param paused the value for docker_cloud_server.paused
     */

    private String paused;


    /**
     * This field corresponds to the database column docker_cloud_server.docker_version
     * Comment:
     * @param dockerVersion the value for docker_cloud_server.docker_version
     */

    private String dockerVersion;


    /**
     * This field corresponds to the database column docker_cloud_server.mem_limit
     * Comment:
     * @param memLimit the value for docker_cloud_server.mem_limit
     */

    private String memLimit;


    /**
     * This field corresponds to the database column docker_cloud_server.can_install
     * Comment:
     * @param canInstall the value for docker_cloud_server.can_install
     */

    private String canInstall;


    /**
     * This field corresponds to the database column docker_cloud_server.kernel
     * Comment:
     * @param kernel the value for docker_cloud_server.kernel
     */

    private String kernel;


    /**
     * This field corresponds to the database column docker_cloud_server.hostname
     * Comment:
     * @param hostname the value for docker_cloud_server.hostname
     */

    private String hostname;


    /**
     * This field corresponds to the database column docker_cloud_server.cpu_shares
     * Comment:
     * @param cpuShares the value for docker_cloud_server.cpu_shares
     */

    private String cpuShares;


    /**
     * This field corresponds to the database column docker_cloud_server.no_running
     * Comment:
     * @param noRunning the value for docker_cloud_server.no_running
     */

    private String noRunning;


    /**
     * This field corresponds to the database column docker_cloud_server.last_modify_time
     * Comment: 最近修改时间
     * @param lastModifyTime the value for docker_cloud_server.last_modify_time
     */

    private String lastModifyTime;


    /**
     * This field corresponds to the database column docker_cloud_server.last_modify_user
     * Comment: 最近修改人
     * @param lastModifyUser the value for docker_cloud_server.last_modify_user
     */

    private String lastModifyUser;


    /**
     * This field corresponds to the database column docker_cloud_server.api_port
     * Comment: æŽ¥å£çš„å»¥
     * @param apiPort the value for docker_cloud_server.api_port
     */

    private Integer apiPort;


    /**
     * This field corresponds to the database column docker_cloud_server.gson_data
     * Comment: 其他未知数据
     * @param gsonData the value for docker_cloud_server.gson_data
     */

    private String gsonData;


    /**
     * This field corresponds to the database column docker_cloud_server.cluter_tp
     * Comment: 集群类型
     * @param cluterTp the value for docker_cloud_server.cluter_tp
     */

    private String cluterTp;


    /**
     * This field corresponds to the database column docker_cloud_server.server_id
     * Comment:
     * @param serverId the value for docker_cloud_server.server_id
     */
    public void setServerId(Integer serverId){
       this.serverId = serverId;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.server_address
     * Comment:
     * @param serverAddress the value for docker_cloud_server.server_address
     */
    public void setServerAddress(String serverAddress){
       this.serverAddress = serverAddress;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.groups
     * Comment:
     * @param groups the value for docker_cloud_server.groups
     */
    public void setGroups(String groups){
       this.groups = groups;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.comm
     * Comment:
     * @param comm the value for docker_cloud_server.comm
     */
    public void setComm(String comm){
       this.comm = comm;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.cpu_number
     * Comment:
     * @param cpuNumber the value for docker_cloud_server.cpu_number
     */
    public void setCpuNumber(String cpuNumber){
       this.cpuNumber = cpuNumber;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.mem_size
     * Comment:
     * @param memSize the value for docker_cloud_server.mem_size
     */
    public void setMemSize(String memSize){
       this.memSize = memSize;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.instance
     * Comment:
     * @param instance the value for docker_cloud_server.instance
     */
    public void setInstance(String instance){
       this.instance = instance;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.status
     * Comment:
     * @param status the value for docker_cloud_server.status
     */
    public void setStatus(String status){
       this.status = status;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.no_runing
     * Comment:
     * @param noRuning the value for docker_cloud_server.no_runing
     */
    public void setNoRuning(String noRuning){
       this.noRuning = noRuning;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.images
     * Comment:
     * @param images the value for docker_cloud_server.images
     */
    public void setImages(String images){
       this.images = images;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.paused
     * Comment:
     * @param paused the value for docker_cloud_server.paused
     */
    public void setPaused(String paused){
       this.paused = paused;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.docker_version
     * Comment:
     * @param dockerVersion the value for docker_cloud_server.docker_version
     */
    public void setDockerVersion(String dockerVersion){
       this.dockerVersion = dockerVersion;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.mem_limit
     * Comment:
     * @param memLimit the value for docker_cloud_server.mem_limit
     */
    public void setMemLimit(String memLimit){
       this.memLimit = memLimit;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.can_install
     * Comment:
     * @param canInstall the value for docker_cloud_server.can_install
     */
    public void setCanInstall(String canInstall){
       this.canInstall = canInstall;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.kernel
     * Comment:
     * @param kernel the value for docker_cloud_server.kernel
     */
    public void setKernel(String kernel){
       this.kernel = kernel;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.hostname
     * Comment:
     * @param hostname the value for docker_cloud_server.hostname
     */
    public void setHostname(String hostname){
       this.hostname = hostname;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.cpu_shares
     * Comment:
     * @param cpuShares the value for docker_cloud_server.cpu_shares
     */
    public void setCpuShares(String cpuShares){
       this.cpuShares = cpuShares;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.no_running
     * Comment:
     * @param noRunning the value for docker_cloud_server.no_running
     */
    public void setNoRunning(String noRunning){
       this.noRunning = noRunning;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.last_modify_time
     * Comment: 最近修改时间
     * @param lastModifyTime the value for docker_cloud_server.last_modify_time
     */
    public void setLastModifyTime(String lastModifyTime){
       this.lastModifyTime = lastModifyTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.last_modify_user
     * Comment: 最近修改人
     * @param lastModifyUser the value for docker_cloud_server.last_modify_user
     */
    public void setLastModifyUser(String lastModifyUser){
       this.lastModifyUser = lastModifyUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.api_port
     * Comment: æŽ¥å£çš„å»¥
     * @param apiPort the value for docker_cloud_server.api_port
     */
    public void setApiPort(Integer apiPort){
       this.apiPort = apiPort;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.gson_data
     * Comment: 其他未知数据
     * @param gsonData the value for docker_cloud_server.gson_data
     */
    public void setGsonData(String gsonData){
       this.gsonData = gsonData;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.cluter_tp
     * Comment: 集群类型
     * @param cluterTp the value for docker_cloud_server.cluter_tp
     */
    public void setCluterTp(String cluterTp){
       this.cluterTp = cluterTp;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.server_id
     * Comment:
     * @return the value of docker_cloud_server.server_id
     */
     public Integer getServerId() {
        return serverId;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.server_address
     * Comment:
     * @return the value of docker_cloud_server.server_address
     */
     public String getServerAddress() {
        return serverAddress;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.groups
     * Comment:
     * @return the value of docker_cloud_server.groups
     */
     public String getGroups() {
        return groups;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.comm
     * Comment:
     * @return the value of docker_cloud_server.comm
     */
     public String getComm() {
        return comm;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.cpu_number
     * Comment:
     * @return the value of docker_cloud_server.cpu_number
     */
     public String getCpuNumber() {
        return cpuNumber;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.mem_size
     * Comment:
     * @return the value of docker_cloud_server.mem_size
     */
     public String getMemSize() {
        return memSize;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.instance
     * Comment:
     * @return the value of docker_cloud_server.instance
     */
     public String getInstance() {
        return instance;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.status
     * Comment:
     * @return the value of docker_cloud_server.status
     */
     public String getStatus() {
        return status;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.no_runing
     * Comment:
     * @return the value of docker_cloud_server.no_runing
     */
     public String getNoRuning() {
        return noRuning;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.images
     * Comment:
     * @return the value of docker_cloud_server.images
     */
     public String getImages() {
        return images;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.paused
     * Comment:
     * @return the value of docker_cloud_server.paused
     */
     public String getPaused() {
        return paused;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.docker_version
     * Comment:
     * @return the value of docker_cloud_server.docker_version
     */
     public String getDockerVersion() {
        return dockerVersion;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.mem_limit
     * Comment:
     * @return the value of docker_cloud_server.mem_limit
     */
     public String getMemLimit() {
        return memLimit;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.can_install
     * Comment:
     * @return the value of docker_cloud_server.can_install
     */
     public String getCanInstall() {
        return canInstall;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.kernel
     * Comment:
     * @return the value of docker_cloud_server.kernel
     */
     public String getKernel() {
        return kernel;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.hostname
     * Comment:
     * @return the value of docker_cloud_server.hostname
     */
     public String getHostname() {
        return hostname;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.cpu_shares
     * Comment:
     * @return the value of docker_cloud_server.cpu_shares
     */
     public String getCpuShares() {
        return cpuShares;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.no_running
     * Comment:
     * @return the value of docker_cloud_server.no_running
     */
     public String getNoRunning() {
        return noRunning;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.last_modify_time
     * Comment: 最近修改时间
     * @return the value of docker_cloud_server.last_modify_time
     */
     public String getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.last_modify_user
     * Comment: 最近修改人
     * @return the value of docker_cloud_server.last_modify_user
     */
     public String getLastModifyUser() {
        return lastModifyUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.api_port
     * Comment: æŽ¥å£çš„å»¥
     * @return the value of docker_cloud_server.api_port
     */
     public Integer getApiPort() {
        return apiPort;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.gson_data
     * Comment: 其他未知数据
     * @return the value of docker_cloud_server.gson_data
     */
     public String getGsonData() {
        return gsonData;
    }

    /**
     * This field corresponds to the database column docker_cloud_server.cluter_tp
     * Comment: 集群类型
     * @return the value of docker_cloud_server.cluter_tp
     */
     public String getCluterTp() {
        return cluterTp;
    }
}
