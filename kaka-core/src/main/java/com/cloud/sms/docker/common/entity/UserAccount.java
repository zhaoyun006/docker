/**
 * @FileName: User.java
 * @Package: com.cloud.sms.archetype.commons.context
 * @author sence
 * @created 4/13/2016 5:20 PM
 * <p/>
 *
 */
package com.cloud.sms.docker.common.entity;

import org.hibernate.validator.constraints.NotBlank;


/**
 * <p></p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author sence
 * @version 1.0
 * @since 1.0
 */
public class UserAccount {

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名称
     */
    @NotBlank(message = "{UserAccount.userName.null}")
    private String userName;
    /**
     * 密码
     */
    @NotBlank(message = "{UserAccount.password.null}")
    private String password;




    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
