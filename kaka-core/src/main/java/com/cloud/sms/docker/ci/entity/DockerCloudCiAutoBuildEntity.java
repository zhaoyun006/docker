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
 * @date 2017-10-26 10:24:46
 * @since 1.0
 */
public class DockerCloudCiAutoBuildEntity extends BaseEntity{
    /**
     * This field corresponds to the database column docker_cloud_ci_auto_build.build_id
     * Comment: 
     * @param buildId the value for docker_cloud_ci_auto_build.build_id
     */

    private java.lang.Integer buildId;


    /**
     * This field corresponds to the database column docker_cloud_ci_auto_build.env_id
     * Comment: 参考应用环境env_id
     * @param envId the value for docker_cloud_ci_auto_build.env_id
     */

    private java.lang.Integer envId;


    /**
     * This field corresponds to the database column docker_cloud_ci_auto_build.update_build
     * Comment: 代码更新后触发编译,0为不编译,1为代码更新后触发编译
     * @param updateBuild the value for docker_cloud_ci_auto_build.update_build
     */

    private java.lang.Integer updateBuild;


    /**
     * This field corresponds to the database column docker_cloud_ci_auto_build.build_time
     * Comment: 编译时间,按任务计划格式配置 * * * * * 分钟,小时,日期,月份,星期
     * @param buildTime the value for docker_cloud_ci_auto_build.build_time
     */

    private java.lang.String buildTime;


    /**
     * This field corresponds to the database column docker_cloud_ci_auto_build.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_ci_auto_build.create_time
     */

    private java.lang.String createTime;


    /**
     * This field corresponds to the database column docker_cloud_ci_auto_build.build_param
     * Comment: 编译参数
     * @param buildParam the value for docker_cloud_ci_auto_build.build_param
     */

    private java.lang.String buildParam;


    /**
     * This field corresponds to the database column docker_cloud_ci_auto_build.crete_user
     * Comment: 创建用户
     * @param creteUser the value for docker_cloud_ci_auto_build.crete_user
     */

    private java.lang.String creteUser;


    /**
     * This field corresponds to the database column docker_cloud_ci_auto_build.build_id
     * Comment: 
     * @param buildId the value for docker_cloud_ci_auto_build.build_id
     */
    public void setBuildId(java.lang.Integer buildId){
       this.buildId = buildId;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_auto_build.env_id
     * Comment: 参考应用环境env_id
     * @param envId the value for docker_cloud_ci_auto_build.env_id
     */
    public void setEnvId(java.lang.Integer envId){
       this.envId = envId;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_auto_build.update_build
     * Comment: 代码更新后触发编译,0为不编译,1为代码更新后触发编译
     * @param updateBuild the value for docker_cloud_ci_auto_build.update_build
     */
    public void setUpdateBuild(java.lang.Integer updateBuild){
       this.updateBuild = updateBuild;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_auto_build.build_time
     * Comment: 编译时间,按任务计划格式配置 * * * * * 分钟,小时,日期,月份,星期
     * @param buildTime the value for docker_cloud_ci_auto_build.build_time
     */
    public void setBuildTime(java.lang.String buildTime){
       this.buildTime = buildTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_auto_build.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_ci_auto_build.create_time
     */
    public void setCreateTime(java.lang.String createTime){
       this.createTime = createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_auto_build.build_param
     * Comment: 编译参数
     * @param buildParam the value for docker_cloud_ci_auto_build.build_param
     */
    public void setBuildParam(java.lang.String buildParam){
       this.buildParam = buildParam;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_auto_build.crete_user
     * Comment: 创建用户
     * @param creteUser the value for docker_cloud_ci_auto_build.crete_user
     */
    public void setCreteUser(java.lang.String creteUser){
       this.creteUser = creteUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_auto_build.build_id
     * Comment: 
     * @return the value of docker_cloud_ci_auto_build.build_id
     */
     public java.lang.Integer getBuildId() {
        return buildId;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_auto_build.env_id
     * Comment: 参考应用环境env_id
     * @return the value of docker_cloud_ci_auto_build.env_id
     */
     public java.lang.Integer getEnvId() {
        return envId;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_auto_build.update_build
     * Comment: 代码更新后触发编译,0为不编译,1为代码更新后触发编译
     * @return the value of docker_cloud_ci_auto_build.update_build
     */
     public java.lang.Integer getUpdateBuild() {
        return updateBuild;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_auto_build.build_time
     * Comment: 编译时间,按任务计划格式配置 * * * * * 分钟,小时,日期,月份,星期
     * @return the value of docker_cloud_ci_auto_build.build_time
     */
     public java.lang.String getBuildTime() {
        return buildTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_auto_build.create_time
     * Comment: 创建时间
     * @return the value of docker_cloud_ci_auto_build.create_time
     */
     public java.lang.String getCreateTime() {
        return createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_auto_build.build_param
     * Comment: 编译参数
     * @return the value of docker_cloud_ci_auto_build.build_param
     */
     public java.lang.String getBuildParam() {
        return buildParam;
    }

    /**
     * This field corresponds to the database column docker_cloud_ci_auto_build.crete_user
     * Comment: 创建用户
     * @return the value of docker_cloud_ci_auto_build.crete_user
     */
     public java.lang.String getCreteUser() {
        return creteUser;
    }
}
