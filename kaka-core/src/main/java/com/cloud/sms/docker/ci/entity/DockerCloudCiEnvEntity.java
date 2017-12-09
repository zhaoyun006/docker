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
 * @date 2017-10-23 09:47:09
 * @since 1.0
 */
public class DockerCloudCiEnvEntity extends BaseEntity{
    private String serverAddress;
    private String serverDomain;
    private String tag;
    private int containerMaxNumber;
    private String buildTp;
    private String groupsIds;
    private String checked;
    private String defaultNetwork;
    private String loadblanceTp;
    private String containerInfo;
    private String clusterTp;
    private String port;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getClusterTp() {
        return clusterTp;
    }

    public void setClusterTp(String clusterTp) {
        this.clusterTp = clusterTp;
    }

    public String getContainerInfo() {
        return containerInfo;
    }

    public void setContainerInfo(String containerInfo) {
        this.containerInfo = containerInfo;
    }

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

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getGroupsIds() {
        return groupsIds;
    }

    public void setGroupsIds(String groupsIds) {
        this.groupsIds = groupsIds;
    }

    public String getBuildTp() {
        return buildTp;
    }

    public void setBuildTp(String buildTp) {
        this.buildTp = buildTp;
    }

    public int getContainerMaxNumber() {
        return containerMaxNumber;
    }

    public void setContainerMaxNumber(int containerMaxNumber) {
        this.containerMaxNumber = containerMaxNumber;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getServerDomain() {
        return serverDomain;
    }

    public void setServerDomain(String serverDomain) {
        this.serverDomain = serverDomain;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.env_id
     * Comment:
     * @param envId the value for docker_cloud_ci_env.env_id
     */

    private java.lang.Integer envId;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.create_user
     * Comment: 创建人
     * @param createUser the value for docker_cloud_ci_env.create_user
     */

    private java.lang.String createUser;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.last_modify_user
     * Comment: 最近修改人
     * @param lastModifyUser the value for docker_cloud_ci_env.last_modify_user
     */

    private java.lang.String lastModifyUser;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_ci_env.create_time
     */

    private java.lang.String createTime;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.last_modify_time
     * Comment: 最近修改时间
     * @param lastModifyTime the value for docker_cloud_ci_env.last_modify_time
     */

    private java.lang.String lastModifyTime;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.description
     * Comment: 备注信息
     * @param description the value for docker_cloud_ci_env.description
     */

    private java.lang.String description;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.service_name
     * Comment: 服务名称和服务管理的名称对应
     * @param serviceName the value for docker_cloud_ci_env.service_name
     */

    private java.lang.String serviceName;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.entname
     * Comment: 环境名称
     * @param entname the value for docker_cloud_ci_env.entname
     */

    private java.lang.String entname;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.dept
     * Comment: 部门名称
     * @param dept the value for docker_cloud_ci_env.dept
     */

    private java.lang.String dept;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.domain
     * Comment: 域名
     * @param domain the value for docker_cloud_ci_env.domain
     */

    private java.lang.String domain;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.build_script
     * Comment: 编译脚本
     * @param buildScript the value for docker_cloud_ci_env.build_script
     */

    private java.lang.String buildScript;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.item_tp
     * Comment: 项目类型
     * @param itemTp the value for docker_cloud_ci_env.item_tp
     */

    private java.lang.String itemTp;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.container_number
     * Comment: 容器数量
     * @param containerNumber the value for docker_cloud_ci_env.container_number
     */

    private java.lang.Integer containerNumber;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.health_script
     * Comment: 监控检查脚本
     * @param healthScript the value for docker_cloud_ci_env.health_script
     */

    private java.lang.String healthScript;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.gson_data
     * Comment: 其他未知信息
     * @param gsonData the value for docker_cloud_ci_env.gson_data
     */

    private java.lang.String gsonData;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.groups_id
     * Comment: 参考主机组
     * @param groupsId the value for docker_cloud_ci_env.groups_id
     */

    private java.lang.Integer groupsId;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.groups_name
     * Comment: 组名称
     * @param groupsName the value for docker_cloud_ci_env.groups_name
     */

    private java.lang.String groupsName;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.auto_compile
     * Comment: 是否自动编译
     * @param autoCompile the value for docker_cloud_ci_env.auto_compile
     */

    private java.lang.String autoCompile;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.auto_compile_tp
     * Comment: 自动编译类型
     * @param autoCompileTp the value for docker_cloud_ci_env.auto_compile_tp
     */

    private java.lang.String autoCompileTp;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.auto_compile_time
     * Comment: 自动编译时间
     * @param autoCompileTime the value for docker_cloud_ci_env.auto_compile_time
     */

    private java.lang.String autoCompileTime;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.auto_compile_status
     * Comment: 编译状态
     * @param autoCompileStatus the value for docker_cloud_ci_env.auto_compile_status
     */

    private java.lang.String autoCompileStatus;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.code_path
     * Comment: 代码路径
     * @param codePath the value for docker_cloud_ci_env.code_path
     */

    private java.lang.String codePath;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.code_branch
     * Comment: 代码分支
     * @param codeBranch the value for docker_cloud_ci_env.code_branch
     */

    private java.lang.String codeBranch;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.images
     * Comment: 镜像仓库
     * @param images the value for docker_cloud_ci_env.images
     */

    private java.lang.String images;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.images_tp
     * Comment: 镜像来源,手动上传,脚本编译,公共镜像
     * @param imagesTp the value for docker_cloud_ci_env.images_tp
     */

    private java.lang.String imagesTp;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.build_status
     * Comment:
     * @param buildStatus the value for docker_cloud_ci_env.build_status
     */

    private java.lang.String buildStatus;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.cpu
     * Comment: cpu资源限制
     * @param cpu the value for docker_cloud_ci_env.cpu
     */

    private java.lang.Integer cpu;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.memory
     * Comment: 内存资源限制,单位MB
     * @param memory the value for docker_cloud_ci_env.memory
     */

    private java.lang.Integer memory;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.approve
     * Comment: 审批状态
     * @param approve the value for docker_cloud_ci_env.approve
     */

    private java.lang.String approve;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.approve_time
     * Comment: 审批时间
     * @param approveTime the value for docker_cloud_ci_env.approve_time
     */

    private java.lang.String approveTime;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.approve_user
     * Comment: 审批用户
     * @param approveUser the value for docker_cloud_ci_env.approve_user
     */

    private java.lang.String approveUser;


    /**
     * This field corresponds to the database column docker_cloud_ci_env.env_id
     * Comment:
     * @param envId the value for docker_cloud_ci_env.env_id
     */
    public void setEnvId(java.lang.Integer envId){
        this.envId = envId;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.create_user
     * Comment: 创建人
     * @param createUser the value for docker_cloud_ci_env.create_user
     */
    public void setCreateUser(java.lang.String createUser){
        this.createUser = createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.last_modify_user
     * Comment: 最近修改人
     * @param lastModifyUser the value for docker_cloud_ci_env.last_modify_user
     */
    public void setLastModifyUser(java.lang.String lastModifyUser){
        this.lastModifyUser = lastModifyUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_ci_env.create_time
     */
    public void setCreateTime(java.lang.String createTime){
        this.createTime = createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.last_modify_time
     * Comment: 最近修改时间
     * @param lastModifyTime the value for docker_cloud_ci_env.last_modify_time
     */
    public void setLastModifyTime(java.lang.String lastModifyTime){
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.description
     * Comment: 备注信息
     * @param description the value for docker_cloud_ci_env.description
     */
    public void setDescription(java.lang.String description){
        this.description = description;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.service_name
     * Comment: 服务名称和服务管理的名称对应
     * @param serviceName the value for docker_cloud_ci_env.service_name
     */
    public void setServiceName(java.lang.String serviceName){
        this.serviceName = serviceName;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.entname
     * Comment: 环境名称
     * @param entname the value for docker_cloud_ci_env.entname
     */
    public void setEntname(java.lang.String entname){
        this.entname = entname;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.dept
     * Comment: 部门名称
     * @param dept the value for docker_cloud_ci_env.dept
     */
    public void setDept(java.lang.String dept){
        this.dept = dept;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.domain
     * Comment: 域名
     * @param domain the value for docker_cloud_ci_env.domain
     */
    public void setDomain(java.lang.String domain){
        this.domain = domain;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.build_script
     * Comment: 编译脚本
     * @param buildScript the value for docker_cloud_ci_env.build_script
     */
    public void setBuildScript(java.lang.String buildScript){
        this.buildScript = buildScript;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.item_tp
     * Comment: 项目类型
     * @param itemTp the value for docker_cloud_ci_env.item_tp
     */
    public void setItemTp(java.lang.String itemTp){
        this.itemTp = itemTp;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.container_number
     * Comment: 容器数量
     * @param containerNumber the value for docker_cloud_ci_env.container_number
     */
    public void setContainerNumber(java.lang.Integer containerNumber){
        this.containerNumber = containerNumber;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.health_script
     * Comment: 监控检查脚本
     * @param healthScript the value for docker_cloud_ci_env.health_script
     */
    public void setHealthScript(java.lang.String healthScript){
        this.healthScript = healthScript;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.gson_data
     * Comment: 其他未知信息
     * @param gsonData the value for docker_cloud_ci_env.gson_data
     */
    public void setGsonData(java.lang.String gsonData){
        this.gsonData = gsonData;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.groups_id
     * Comment: 参考主机组
     * @param groupsId the value for docker_cloud_ci_env.groups_id
     */
    public void setGroupsId(java.lang.Integer groupsId){
        this.groupsId = groupsId;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.groups_name
     * Comment: 组名称
     * @param groupsName the value for docker_cloud_ci_env.groups_name
     */
    public void setGroupsName(java.lang.String groupsName){
        this.groupsName = groupsName;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.auto_compile
     * Comment: 是否自动编译
     * @param autoCompile the value for docker_cloud_ci_env.auto_compile
     */
    public void setAutoCompile(java.lang.String autoCompile){
        this.autoCompile = autoCompile;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.auto_compile_tp
     * Comment: 自动编译类型
     * @param autoCompileTp the value for docker_cloud_ci_env.auto_compile_tp
     */
    public void setAutoCompileTp(java.lang.String autoCompileTp){
        this.autoCompileTp = autoCompileTp;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.auto_compile_time
     * Comment: 自动编译时间
     * @param autoCompileTime the value for docker_cloud_ci_env.auto_compile_time
     */
    public void setAutoCompileTime(java.lang.String autoCompileTime){
        this.autoCompileTime = autoCompileTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.auto_compile_status
     * Comment: 编译状态
     * @param autoCompileStatus the value for docker_cloud_ci_env.auto_compile_status
     */
    public void setAutoCompileStatus(java.lang.String autoCompileStatus){
        this.autoCompileStatus = autoCompileStatus;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.code_path
     * Comment: 代码路径
     * @param codePath the value for docker_cloud_ci_env.code_path
     */
    public void setCodePath(java.lang.String codePath){
        this.codePath = codePath;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.code_branch
     * Comment: 代码分支
     * @param codeBranch the value for docker_cloud_ci_env.code_branch
     */
    public void setCodeBranch(java.lang.String codeBranch){
        this.codeBranch = codeBranch;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.images
     * Comment: 镜像仓库
     * @param images the value for docker_cloud_ci_env.images
     */
    public void setImages(java.lang.String images){
        this.images = images;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.images_tp
     * Comment: 镜像来源,手动上传,脚本编译,公共镜像
     * @param imagesTp the value for docker_cloud_ci_env.images_tp
     */
    public void setImagesTp(java.lang.String imagesTp){
        this.imagesTp = imagesTp;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.build_status
     * Comment:
     * @param buildStatus the value for docker_cloud_ci_env.build_status
     */
    public void setBuildStatus(java.lang.String buildStatus){
        this.buildStatus = buildStatus;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.cpu
     * Comment: cpu资源限制
     * @param cpu the value for docker_cloud_ci_env.cpu
     */
    public void setCpu(java.lang.Integer cpu){
        this.cpu = cpu;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.memory
     * Comment: 内存资源限制,单位MB
     * @param memory the value for docker_cloud_ci_env.memory
     */
    public void setMemory(java.lang.Integer memory){
        this.memory = memory;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.approve
     * Comment: 审批状态
     * @param approve the value for docker_cloud_ci_env.approve
     */
    public void setApprove(java.lang.String approve){
        this.approve = approve;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.approve_time
     * Comment: 审批时间
     * @param approveTime the value for docker_cloud_ci_env.approve_time
     */
    public void setApproveTime(java.lang.String approveTime){
        this.approveTime = approveTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.approve_user
     * Comment: 审批用户
     * @param approveUser the value for docker_cloud_ci_env.approve_user
     */
    public void setApproveUser(java.lang.String approveUser){
        this.approveUser = approveUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.env_id
     * Comment:
     * @return the value of docker_cloud_ci_env.env_id
     */
    public java.lang.Integer getEnvId() {
        return envId;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.create_user
     * Comment: 创建人
     * @return the value of docker_cloud_ci_env.create_user
     */
    public java.lang.String getCreateUser() {
        return createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.last_modify_user
     * Comment: 最近修改人
     * @return the value of docker_cloud_ci_env.last_modify_user
     */
    public java.lang.String getLastModifyUser() {
        return lastModifyUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.create_time
     * Comment: 创建时间
     * @return the value of docker_cloud_ci_env.create_time
     */
    public java.lang.String getCreateTime() {
        return createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.last_modify_time
     * Comment: 最近修改时间
     * @return the value of docker_cloud_ci_env.last_modify_time
     */
    public java.lang.String getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.description
     * Comment: 备注信息
     * @return the value of docker_cloud_ci_env.description
     */
    public java.lang.String getDescription() {
        return description;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.service_name
     * Comment: 服务名称和服务管理的名称对应
     * @return the value of docker_cloud_ci_env.service_name
     */
    public java.lang.String getServiceName() {
        return serviceName;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.entname
     * Comment: 环境名称
     * @return the value of docker_cloud_ci_env.entname
     */
    public java.lang.String getEntname() {
        return entname;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.dept
     * Comment: 部门名称
     * @return the value of docker_cloud_ci_env.dept
     */
    public java.lang.String getDept() {
        return dept;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.domain
     * Comment: 域名
     * @return the value of docker_cloud_ci_env.domain
     */
    public java.lang.String getDomain() {
        return domain;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.build_script
     * Comment: 编译脚本
     * @return the value of docker_cloud_ci_env.build_script
     */
    public java.lang.String getBuildScript() {
        return buildScript;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.item_tp
     * Comment: 项目类型
     * @return the value of docker_cloud_ci_env.item_tp
     */
    public java.lang.String getItemTp() {
        return itemTp;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.container_number
     * Comment: 容器数量
     * @return the value of docker_cloud_ci_env.container_number
     */
    public java.lang.Integer getContainerNumber() {
        return containerNumber;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.health_script
     * Comment: 监控检查脚本
     * @return the value of docker_cloud_ci_env.health_script
     */
    public java.lang.String getHealthScript() {
        return healthScript;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.gson_data
     * Comment: 其他未知信息
     * @return the value of docker_cloud_ci_env.gson_data
     */
    public java.lang.String getGsonData() {
        return gsonData;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.groups_id
     * Comment: 参考主机组
     * @return the value of docker_cloud_ci_env.groups_id
     */
    public java.lang.Integer getGroupsId() {
        return groupsId;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.groups_name
     * Comment: 组名称
     * @return the value of docker_cloud_ci_env.groups_name
     */
    public java.lang.String getGroupsName() {
        return groupsName;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.auto_compile
     * Comment: 是否自动编译
     * @return the value of docker_cloud_ci_env.auto_compile
     */
    public java.lang.String getAutoCompile() {
        return autoCompile;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.auto_compile_tp
     * Comment: 自动编译类型
     * @return the value of docker_cloud_ci_env.auto_compile_tp
     */
    public java.lang.String getAutoCompileTp() {
        return autoCompileTp;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.auto_compile_time
     * Comment: 自动编译时间
     * @return the value of docker_cloud_ci_env.auto_compile_time
     */
    public java.lang.String getAutoCompileTime() {
        return autoCompileTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.auto_compile_status
     * Comment: 编译状态
     * @return the value of docker_cloud_ci_env.auto_compile_status
     */
    public java.lang.String getAutoCompileStatus() {
        return autoCompileStatus;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.code_path
     * Comment: 代码路径
     * @return the value of docker_cloud_ci_env.code_path
     */
    public java.lang.String getCodePath() {
        return codePath;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.code_branch
     * Comment: 代码分支
     * @return the value of docker_cloud_ci_env.code_branch
     */
    public java.lang.String getCodeBranch() {
        return codeBranch;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.images
     * Comment: 镜像仓库
     * @return the value of docker_cloud_ci_env.images
     */
    public java.lang.String getImages() {
        return images;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.images_tp
     * Comment: 镜像来源,手动上传,脚本编译,公共镜像
     * @return the value of docker_cloud_ci_env.images_tp
     */
    public java.lang.String getImagesTp() {
        return imagesTp;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.build_status
     * Comment:
     * @return the value of docker_cloud_ci_env.build_status
     */
    public java.lang.String getBuildStatus() {
        return buildStatus;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.cpu
     * Comment: cpu资源限制
     * @return the value of docker_cloud_ci_env.cpu
     */
    public java.lang.Integer getCpu() {
        return cpu;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.memory
     * Comment: 内存资源限制,单位MB
     * @return the value of docker_cloud_ci_env.memory
     */
    public java.lang.Integer getMemory() {
        return memory;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.approve
     * Comment: 审批状态
     * @return the value of docker_cloud_ci_env.approve
     */
    public java.lang.String getApprove() {
        return approve;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.approve_time
     * Comment: 审批时间
     * @return the value of docker_cloud_ci_env.approve_time
     */
    public java.lang.String getApproveTime() {
        return approveTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_env.approve_user
     * Comment: 审批用户
     * @return the value of docker_cloud_ci_env.approve_user
     */
    public java.lang.String getApproveUser() {
        return approveUser;
    }
}
