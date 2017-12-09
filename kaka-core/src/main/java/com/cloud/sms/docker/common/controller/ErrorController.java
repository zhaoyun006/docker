/**
 * @FileName: ErrorController.java
 * @Package: com.cloud.sms.ares.commons.controller
 * @author liusq23
 * @created 8/24/2016 12:02 PM
 * <p/>
 *
 */
package com.cloud.sms.docker.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author liusq23
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("/404")
    public String _404() {
        return "error/404";
    }

    @RequestMapping("/400")
    public String _400() {
        return "error/400";
    }

    @RequestMapping("/500")
    public String _500(HttpServletRequest request, Model model) {
        return "error/500";
    }

    @RequestMapping("/503")
    public String _503() {
        return "error/503";
    }

    @RequestMapping("/403")
    public String _403(HttpServletRequest request, Model model) {
        return "error/403";
    }

}
