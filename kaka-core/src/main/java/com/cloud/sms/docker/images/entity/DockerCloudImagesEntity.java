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
 * @date 2017-10-13 14:16:48
 * @since 1.0
 */
public class DockerCloudImagesEntity extends BaseEntity{

    /**
     * This field corresponds to the database column docker_cloud_images.images_id
     * Comment: 
     * @param imagesId the value for docker_cloud_images.images_id
     */

    private java.lang.Integer imagesId;


    /**
     * This field corresponds to the database column docker_cloud_images.froms
     * Comment: 镜像来源
     * @param froms the value for docker_cloud_images.froms
     */

    private java.lang.String froms;


    /**
     * This field corresponds to the database column docker_cloud_images.name
     * Comment: 镜像名称
     * @param name the value for docker_cloud_images.name
     */

    private java.lang.String name;


    /**
     * This field corresponds to the database column docker_cloud_images.registry
     * Comment: 镜像仓库
     * @param registry the value for docker_cloud_images.registry
     */

    private java.lang.String registry;


    /**
     * This field corresponds to the database column docker_cloud_images.tag
     * Comment: 镜像标签
     * @param tag the value for docker_cloud_images.tag
     */

    private java.lang.String tag;


    /**
     * This field corresponds to the database column docker_cloud_images.size
     * Comment: 镜像大小
     * @param size the value for docker_cloud_images.size
     */

    private java.lang.String size;


    /**
     * This field corresponds to the database column docker_cloud_images.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_images.create_time
     */

    private java.lang.String createTime;


    /**
     * This field corresponds to the database column docker_cloud_images.create_user
     * Comment: 创建人
     * @param createUser the value for docker_cloud_images.create_user
     */

    private java.lang.String createUser;


    /**
     * This field corresponds to the database column docker_cloud_images.description
     * Comment: 备注信息
     * @param description the value for docker_cloud_images.description
     */

    private java.lang.String description;


    /**
     * This field corresponds to the database column docker_cloud_images.images_id
     * Comment: 
     * @param imagesId the value for docker_cloud_images.images_id
     */
    public void setImagesId(java.lang.Integer imagesId){
       this.imagesId = imagesId;
    }

    /**
     * This field corresponds to the database column docker_cloud_images.froms
     * Comment: 镜像来源
     * @param froms the value for docker_cloud_images.froms
     */
    public void setFroms(java.lang.String froms){
       this.froms = froms;
    }

    /**
     * This field corresponds to the database column docker_cloud_images.name
     * Comment: 镜像名称
     * @param name the value for docker_cloud_images.name
     */
    public void setName(java.lang.String name){
       this.name = name;
    }

    /**
     * This field corresponds to the database column docker_cloud_images.registry
     * Comment: 镜像仓库
     * @param registry the value for docker_cloud_images.registry
     */
    public void setRegistry(java.lang.String registry){
       this.registry = registry;
    }

    /**
     * This field corresponds to the database column docker_cloud_images.tag
     * Comment: 镜像标签
     * @param tag the value for docker_cloud_images.tag
     */
    public void setTag(java.lang.String tag){
       this.tag = tag;
    }

    /**
     * This field corresponds to the database column docker_cloud_images.size
     * Comment: 镜像大小
     * @param size the value for docker_cloud_images.size
     */
    public void setSize(java.lang.String size){
       this.size = size;
    }

    /**
     * This field corresponds to the database column docker_cloud_images.create_time
     * Comment: 创建时间
     * @param createTime the value for docker_cloud_images.create_time
     */
    public void setCreateTime(java.lang.String createTime){
       this.createTime = createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_images.create_user
     * Comment: 创建人
     * @param createUser the value for docker_cloud_images.create_user
     */
    public void setCreateUser(java.lang.String createUser){
       this.createUser = createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_images.description
     * Comment: 备注信息
     * @param description the value for docker_cloud_images.description
     */
    public void setDescription(java.lang.String description){
       this.description = description;
    }

    /**
     * This field corresponds to the database column docker_cloud_images.images_id
     * Comment: 
     * @return the value of docker_cloud_images.images_id
     */
     public java.lang.Integer getImagesId() {
        return imagesId;
    }

    /**
     * This field corresponds to the database column docker_cloud_images.froms
     * Comment: 镜像来源
     * @return the value of docker_cloud_images.froms
     */
     public java.lang.String getFroms() {
        return froms;
    }

    /**
     * This field corresponds to the database column docker_cloud_images.name
     * Comment: 镜像名称
     * @return the value of docker_cloud_images.name
     */
     public java.lang.String getName() {
        return name;
    }

    /**
     * This field corresponds to the database column docker_cloud_images.registry
     * Comment: 镜像仓库
     * @return the value of docker_cloud_images.registry
     */
     public java.lang.String getRegistry() {
        return registry;
    }

    /**
     * This field corresponds to the database column docker_cloud_images.tag
     * Comment: 镜像标签
     * @return the value of docker_cloud_images.tag
     */
     public java.lang.String getTag() {
        return tag;
    }

    /**
     * This field corresponds to the database column docker_cloud_images.size
     * Comment: 镜像大小
     * @return the value of docker_cloud_images.size
     */
     public java.lang.String getSize() {
        return size;
    }

    /**
     * This field corresponds to the database column docker_cloud_images.create_time
     * Comment: 创建时间
     * @return the value of docker_cloud_images.create_time
     */
     public java.lang.String getCreateTime() {
        return createTime;
    }

    /**
     * This field corresponds to the database column docker_cloud_images.create_user
     * Comment: 创建人
     * @return the value of docker_cloud_images.create_user
     */
     public java.lang.String getCreateUser() {
        return createUser;
    }

    /**
     * This field corresponds to the database column docker_cloud_images.description
     * Comment: 备注信息
     * @return the value of docker_cloud_images.description
     */
     public java.lang.String getDescription() {
        return description;
    }
}
