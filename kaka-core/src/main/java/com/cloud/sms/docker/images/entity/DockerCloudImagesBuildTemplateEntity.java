package com.cloud.sms.docker.images.entity;
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
 * @date 2017-10-18 17:27:39
 * @since 1.0
 */
public class DockerCloudImagesBuildTemplateEntity extends BaseEntity{
    /**
     * This field corresponds to the database column docker_cloud_images_build_template.template_id
     * Comment: 
     * @param templateId the value for docker_cloud_images_build_template.template_id
     */

    private java.lang.Integer templateId;


    /**
     * This field corresponds to the database column docker_cloud_images_build_template.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_images_build_template.create_time
     */

    private java.lang.String createTime;


    /**
     * This field corresponds to the database column docker_cloud_images_build_template.create_user
     * Comment: 创建人
     * @param createUser the value for docker_cloud_images_build_template.create_user
     */

    private java.lang.String createUser;


    /**
     * This field corresponds to the database column docker_cloud_images_build_template.description
     * Comment: 备注信息
     * @param description the value for docker_cloud_images_build_template.description
     */

    private java.lang.String description;


    /**
     * This field corresponds to the database column docker_cloud_images_build_template.property
     * Comment: 属性,分公开和私有2种,公开的别人也可以看到,私有的就自己可以看到
     * @param property the value for docker_cloud_images_build_template.property
     */

    private java.lang.String property;


    /**
     * This field corresponds to the database column docker_cloud_images_build_template.docker_file
     * Comment: docker编译文件
     * @param dockerFile the value for docker_cloud_images_build_template.docker_file
     */

    private java.lang.String dockerFile;


    /**
     * This field corresponds to the database column docker_cloud_images_build_template.image_name
     * Comment: 镜像类型
     * @param imageName the value for docker_cloud_images_build_template.image_name
     */

    private java.lang.String imageName;


    /**
     * This field corresponds to the database column docker_cloud_images_build_template.image_url
     * Comment: 镜像图标地址
     * @param imageUrl the value for docker_cloud_images_build_template.image_url
     */

    private java.lang.String imageUrl;


    /**
     * This field corresponds to the database column docker_cloud_images_build_template.last_modify_user
     * Comment: 最近修改人
     * @param lastModifyUser the value for docker_cloud_images_build_template.last_modify_user
     */

    private java.lang.String lastModifyUser;


    /**
     * This field corresponds to the database column docker_cloud_images_build_template.template_id
     * Comment: 
     * @param templateId the value for docker_cloud_images_build_template.template_id
     */
    public void setTemplateId(java.lang.Integer templateId){
       this.templateId = templateId;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_template.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_images_build_template.create_time
     */
    public void setCreateTime(java.lang.String createTime){
       this.createTime = createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_template.create_user
     * Comment: 创建人
     * @param createUser the value for docker_cloud_images_build_template.create_user
     */
    public void setCreateUser(java.lang.String createUser){
       this.createUser = createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_template.description
     * Comment: 备注信息
     * @param description the value for docker_cloud_images_build_template.description
     */
    public void setDescription(java.lang.String description){
       this.description = description;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_template.property
     * Comment: 属性,分公开和私有2种,公开的别人也可以看到,私有的就自己可以看到
     * @param property the value for docker_cloud_images_build_template.property
     */
    public void setProperty(java.lang.String property){
       this.property = property;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_template.docker_file
     * Comment: docker编译文件
     * @param dockerFile the value for docker_cloud_images_build_template.docker_file
     */
    public void setDockerFile(java.lang.String dockerFile){
       this.dockerFile = dockerFile;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_template.image_name
     * Comment: 镜像类型
     * @param imageName the value for docker_cloud_images_build_template.image_name
     */
    public void setImageName(java.lang.String imageName){
       this.imageName = imageName;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_template.image_url
     * Comment: 镜像图标地址
     * @param imageUrl the value for docker_cloud_images_build_template.image_url
     */
    public void setImageUrl(java.lang.String imageUrl){
       this.imageUrl = imageUrl;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_template.last_modify_user
     * Comment: 最近修改人
     * @param lastModifyUser the value for docker_cloud_images_build_template.last_modify_user
     */
    public void setLastModifyUser(java.lang.String lastModifyUser){
       this.lastModifyUser = lastModifyUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_template.template_id
     * Comment: 
     * @return the value of docker_cloud_images_build_template.template_id
     */
     public java.lang.Integer getTemplateId() {
        return templateId;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_template.create_time
     * Comment: 创建时间
     * @return the value of docker_cloud_images_build_template.create_time
     */
     public java.lang.String getCreateTime() {
        return createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_template.create_user
     * Comment: 创建人
     * @return the value of docker_cloud_images_build_template.create_user
     */
     public java.lang.String getCreateUser() {
        return createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_template.description
     * Comment: 备注信息
     * @return the value of docker_cloud_images_build_template.description
     */
     public java.lang.String getDescription() {
        return description;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_template.property
     * Comment: 属性,分公开和私有2种,公开的别人也可以看到,私有的就自己可以看到
     * @return the value of docker_cloud_images_build_template.property
     */
     public java.lang.String getProperty() {
        return property;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_template.docker_file
     * Comment: docker编译文件
     * @return the value of docker_cloud_images_build_template.docker_file
     */
     public java.lang.String getDockerFile() {
        return dockerFile;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_template.image_name
     * Comment: 镜像类型
     * @return the value of docker_cloud_images_build_template.image_name
     */
     public java.lang.String getImageName() {
        return imageName;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_template.image_url
     * Comment: 镜像图标地址
     * @return the value of docker_cloud_images_build_template.image_url
     */
     public java.lang.String getImageUrl() {
        return imageUrl;
    }

    /**
     * This field corresponds to the database column docker_cloud_images_build_template.last_modify_user
     * Comment: 最近修改人
     * @return the value of docker_cloud_images_build_template.last_modify_user
     */
     public java.lang.String getLastModifyUser() {
        return lastModifyUser;
    }
}
