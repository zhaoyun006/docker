/**
 * @FileName: DynamicRoutingContextHolder.java
 * @Package: com.cloud.sms.docker.quartz.util
 * @author lig134
 * @created 2017/6/26 下午5:46
 * <p>
 *
 */
package com.cloud.sms.docker.util;

import org.springframework.util.Assert;

/**
 * <p></p>
 *
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author lig134
 * @since 1.0
 * @version 1.0
 */
public class DynamicRoutingContextHolder {
    private static final ThreadLocal<String> contextHolder =
            new ThreadLocal<>();

    public static void setRouteStrategy(String key) {
        Assert.notNull(key, "route key cannot be null");
        contextHolder.set(key);
    }

    public static String getRouteStrategy() {
        return  contextHolder.get();
    }

    public static void clearRouteStrategy() {
        contextHolder.remove();
    }
}