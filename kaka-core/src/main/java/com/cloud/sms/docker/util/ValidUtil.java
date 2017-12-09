/**
 * @FileName: ValidUtil.java
 * @Package: com.cloud.kaka.push.util
 * @author liusq23
 * @created 7/20/2016 9:58 AM
 * <p/>
 *
 */
package com.cloud.sms.docker.util;

import com.cloud.sms.docker.base.constant.Const;

/**
 * <p></p>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author liusq23
 * @since 1.0
 * @version 1.0
 */
public class ValidUtil {

    /**
     *
     * 判断是否合法的数据
     *
     * 这里使用Integer.valueOf()
     * 防止isValid，isDelete为null出现的基本int类型和null比对出错
     *
     * @param isValid
     * @param isDelete
     * @return
     */
    public static boolean isValid(Integer isValid,Integer isDelete){
        //已经删除
        if(Integer.valueOf(Const.DELETE).equals(isDelete)){
            return false;
        }
        //如果是非法
        if(Integer.valueOf(Const.INVALID).equals(isValid)){
            return false;
        }
        return true;
    }
}
