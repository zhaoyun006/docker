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
 * @date 2017-09-22 09:56:03
 * @since 1.0
 */
public class DockerCloudAuthorityUserEntity extends BaseEntity{
    /**
     * This field corresponds to the database column docker_cloud_authority_user.user_id
     * Comment: 用户id
     * @param userId the value for docker_cloud_authority_user.user_id
     */

    private java.lang.Integer userId;


    /**
     * This field corresponds to the database column docker_cloud_authority_user.user_name
     * Comment: 用户名称
     * @param userName the value for docker_cloud_authority_user.user_name
     */

    private java.lang.String userName;


    /**
     * This field corresponds to the database column docker_cloud_authority_user.user_pic
     * Comment: 用户头像
     * @param userPic the value for docker_cloud_authority_user.user_pic
     */

    private java.lang.String userPic;


    /**
     * This field corresponds to the database column docker_cloud_authority_user.third_id
     * Comment: 第三方id
     * @param thirdId the value for docker_cloud_authority_user.third_id
     */

    private java.lang.String thirdId;


    /**
     * This field corresponds to the database column docker_cloud_authority_user.third_true_name
     * Comment: 真实姓名
     * @param thirdTrueName the value for docker_cloud_authority_user.third_true_name
     */

    private java.lang.String thirdTrueName;


    /**
     * This field corresponds to the database column docker_cloud_authority_user.user_email
     * Comment: 用户邮箱
     * @param userEmail the value for docker_cloud_authority_user.user_email
     */

    private java.lang.String userEmail;


    /**
     * This field corresponds to the database column docker_cloud_authority_user.user_mobile
     * Comment: 用户电话
     * @param userMobile the value for docker_cloud_authority_user.user_mobile
     */

    private java.lang.String userMobile;


    /**
     * This field corresponds to the database column docker_cloud_authority_user.is_valid
     * Comment: 是否启用（0无效，1有效）
     * @param isValid the value for docker_cloud_authority_user.is_valid
     */

    private java.lang.Integer isValid;


    /**
     * This field corresponds to the database column docker_cloud_authority_user.is_del
     * Comment: 是否删除（0未删除，1删除）
     * @param isDel the value for docker_cloud_authority_user.is_del
     */

    private java.lang.Integer isDel;


    /**
     * This field corresponds to the database column docker_cloud_authority_user.user_id
     * Comment: 用户id
     * @param userId the value for docker_cloud_authority_user.user_id
     */
    public void setUserId(java.lang.Integer userId){
       this.userId = userId;
    }

    /**
     * This field corresponds to the database column docker_cloud_authority_user.user_name
     * Comment: 用户名称
     * @param userName the value for docker_cloud_authority_user.user_name
     */
    public void setUserName(java.lang.String userName){
       this.userName = userName;
    }

    /**
     * This field corresponds to the database column docker_cloud_authority_user.user_pic
     * Comment: 用户头像
     * @param userPic the value for docker_cloud_authority_user.user_pic
     */
    public void setUserPic(java.lang.String userPic){
       this.userPic = userPic;
    }

    /**
     * This field corresponds to the database column docker_cloud_authority_user.third_id
     * Comment: 第三方id
     * @param thirdId the value for docker_cloud_authority_user.third_id
     */
    public void setThirdId(java.lang.String thirdId){
       this.thirdId = thirdId;
    }

    /**
     * This field corresponds to the database column docker_cloud_authority_user.third_true_name
     * Comment: 真实姓名
     * @param thirdTrueName the value for docker_cloud_authority_user.third_true_name
     */
    public void setThirdTrueName(java.lang.String thirdTrueName){
       this.thirdTrueName = thirdTrueName;
    }

    /**
     * This field corresponds to the database column docker_cloud_authority_user.user_email
     * Comment: 用户邮箱
     * @param userEmail the value for docker_cloud_authority_user.user_email
     */
    public void setUserEmail(java.lang.String userEmail){
       this.userEmail = userEmail;
    }

    /**
     * This field corresponds to the database column docker_cloud_authority_user.user_mobile
     * Comment: 用户电话
     * @param userMobile the value for docker_cloud_authority_user.user_mobile
     */
    public void setUserMobile(java.lang.String userMobile){
       this.userMobile = userMobile;
    }

    /**
     * This field corresponds to the database column docker_cloud_authority_user.is_valid
     * Comment: 是否启用（0无效，1有效）
     * @param isValid the value for docker_cloud_authority_user.is_valid
     */
    public void setIsValid(java.lang.Integer isValid){
       this.isValid = isValid;
    }

    /**
     * This field corresponds to the database column docker_cloud_authority_user.is_del
     * Comment: 是否删除（0未删除，1删除）
     * @param isDel the value for docker_cloud_authority_user.is_del
     */
    public void setIsDel(java.lang.Integer isDel){
       this.isDel = isDel;
    }

    /**
     * This field corresponds to the database column docker_cloud_authority_user.user_id
     * Comment: 用户id
     * @return the value of docker_cloud_authority_user.user_id
     */
     public java.lang.Integer getUserId() {
        return userId;
    }

    /**
     * This field corresponds to the database column docker_cloud_authority_user.user_name
     * Comment: 用户名称
     * @return the value of docker_cloud_authority_user.user_name
     */
     public java.lang.String getUserName() {
        return userName;
    }

    /**
     * This field corresponds to the database column docker_cloud_authority_user.user_pic
     * Comment: 用户头像
     * @return the value of docker_cloud_authority_user.user_pic
     */
     public java.lang.String getUserPic() {
        return userPic;
    }

    /**
     * This field corresponds to the database column docker_cloud_authority_user.third_id
     * Comment: 第三方id
     * @return the value of docker_cloud_authority_user.third_id
     */
     public java.lang.String getThirdId() {
        return thirdId;
    }

    /**
     * This field corresponds to the database column docker_cloud_authority_user.third_true_name
     * Comment: 真实姓名
     * @return the value of docker_cloud_authority_user.third_true_name
     */
     public java.lang.String getThirdTrueName() {
        return thirdTrueName;
    }

    /**
     * This field corresponds to the database column docker_cloud_authority_user.user_email
     * Comment: 用户邮箱
     * @return the value of docker_cloud_authority_user.user_email
     */
     public java.lang.String getUserEmail() {
        return userEmail;
    }

    /**
     * This field corresponds to the database column docker_cloud_authority_user.user_mobile
     * Comment: 用户电话
     * @return the value of docker_cloud_authority_user.user_mobile
     */
     public java.lang.String getUserMobile() {
        return userMobile;
    }

    /**
     * This field corresponds to the database column docker_cloud_authority_user.is_valid
     * Comment: 是否启用（0无效，1有效）
     * @return the value of docker_cloud_authority_user.is_valid
     */
     public java.lang.Integer getIsValid() {
        return isValid;
    }

    /**
     * This field corresponds to the database column docker_cloud_authority_user.is_del
     * Comment: 是否删除（0未删除，1删除）
     * @return the value of docker_cloud_authority_user.is_del
     */
     public java.lang.Integer getIsDel() {
        return isDel;
    }
}
