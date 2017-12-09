package com.cloud.sms.docker.authority.entity;
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
 * @date 2017-09-22 09:53:16
 * @since 1.0
 */
public class DockerCloudAppEntity extends BaseEntity{
    /**
     * This field corresponds to the database column docker_cloud_app.app_id
     * Comment: 主键
     * @param appId the value for docker_cloud_app.app_id
     */

    private java.lang.Integer appId;


    /**
     * This field corresponds to the database column docker_cloud_app.app_name
     * Comment: 应用栈名称
     * @param appName the value for docker_cloud_app.app_name
     */

    private java.lang.String appName;


    /**
     * This field corresponds to the database column docker_cloud_app.app_description
     * Comment: 应用栈描述
     * @param appDescription the value for docker_cloud_app.app_description
     */

    private java.lang.String appDescription;


    /**
     * This field corresponds to the database column docker_cloud_app.compose
     * Comment: 编排脚本
     * @param compose the value for docker_cloud_app.compose
     */

    private java.lang.String compose;


    /**
     * This field corresponds to the database column docker_cloud_app.local_path
     * Comment: 本地文件路径
     * @param localPath the value for docker_cloud_app.local_path
     */

    private java.lang.String localPath;


    /**
     * This field corresponds to the database column docker_cloud_app.upload_file
     * Comment: 上传文件
     * @param uploadFile the value for docker_cloud_app.upload_file
     */

    private java.lang.String uploadFile;


    /**
     * This field corresponds to the database column docker_cloud_app.create_user
     * Comment: 创建用户
     * @param createUser the value for docker_cloud_app.create_user
     */

    private java.lang.String createUser;


    /**
     * This field corresponds to the database column docker_cloud_app.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_app.create_time
     */

    private java.lang.String createTime;


    /**
     * This field corresponds to the database column docker_cloud_app.is_del
     * Comment: 是否删除
     * @param isDel the value for docker_cloud_app.is_del
     */

    private java.lang.Integer isDel;


    /**
     * This field corresponds to the database column docker_cloud_app.app_id
     * Comment: 主键
     * @param appId the value for docker_cloud_app.app_id
     */
    public void setAppId(java.lang.Integer appId){
       this.appId = appId;
    }

    /**
     * This field corresponds to the database column docker_cloud_app.app_name
     * Comment: 应用栈名称
     * @param appName the value for docker_cloud_app.app_name
     */
    public void setAppName(java.lang.String appName){
       this.appName = appName;
    }

    /**
     * This field corresponds to the database column docker_cloud_app.app_description
     * Comment: 应用栈描述
     * @param appDescription the value for docker_cloud_app.app_description
     */
    public void setAppDescription(java.lang.String appDescription){
       this.appDescription = appDescription;
    }

    /**
     * This field corresponds to the database column docker_cloud_app.compose
     * Comment: 编排脚本
     * @param compose the value for docker_cloud_app.compose
     */
    public void setCompose(java.lang.String compose){
       this.compose = compose;
    }

    /**
     * This field corresponds to the database column docker_cloud_app.local_path
     * Comment: 本地文件路径
     * @param localPath the value for docker_cloud_app.local_path
     */
    public void setLocalPath(java.lang.String localPath){
       this.localPath = localPath;
    }

    /**
     * This field corresponds to the database column docker_cloud_app.upload_file
     * Comment: 上传文件
     * @param uploadFile the value for docker_cloud_app.upload_file
     */
    public void setUploadFile(java.lang.String uploadFile){
       this.uploadFile = uploadFile;
    }

    /**
     * This field corresponds to the database column docker_cloud_app.create_user
     * Comment: 创建用户
     * @param createUser the value for docker_cloud_app.create_user
     */
    public void setCreateUser(java.lang.String createUser){
       this.createUser = createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_app.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_app.create_time
     */
    public void setCreateTime(java.lang.String createTime){
       this.createTime = createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_app.is_del
     * Comment: 是否删除
     * @param isDel the value for docker_cloud_app.is_del
     */
    public void setIsDel(java.lang.Integer isDel){
       this.isDel = isDel;
    }

    /**
     * This field corresponds to the database column docker_cloud_app.app_id
     * Comment: 主键
     * @return the value of docker_cloud_app.app_id
     */
     public java.lang.Integer getAppId() {
        return appId;
    }

    /**
     * This field corresponds to the database column docker_cloud_app.app_name
     * Comment: 应用栈名称
     * @return the value of docker_cloud_app.app_name
     */
     public java.lang.String getAppName() {
        return appName;
    }

    /**
     * This field corresponds to the database column docker_cloud_app.app_description
     * Comment: 应用栈描述
     * @return the value of docker_cloud_app.app_description
     */
     public java.lang.String getAppDescription() {
        return appDescription;
    }

    /**
     * This field corresponds to the database column docker_cloud_app.compose
     * Comment: 编排脚本
     * @return the value of docker_cloud_app.compose
     */
     public java.lang.String getCompose() {
        return compose;
    }

    /**
     * This field corresponds to the database column docker_cloud_app.local_path
     * Comment: 本地文件路径
     * @return the value of docker_cloud_app.local_path
     */
     public java.lang.String getLocalPath() {
        return localPath;
    }

    /**
     * This field corresponds to the database column docker_cloud_app.upload_file
     * Comment: 上传文件
     * @return the value of docker_cloud_app.upload_file
     */
     public java.lang.String getUploadFile() {
        return uploadFile;
    }

    /**
     * This field corresponds to the database column docker_cloud_app.create_user
     * Comment: 创建用户
     * @return the value of docker_cloud_app.create_user
     */
     public java.lang.String getCreateUser() {
        return createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_app.create_time
     * Comment: 创建时间
     * @return the value of docker_cloud_app.create_time
     */
     public java.lang.String getCreateTime() {
        return createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_app.is_del
     * Comment: 是否删除
     * @return the value of docker_cloud_app.is_del
     */
     public java.lang.Integer getIsDel() {
        return isDel;
    }
}
