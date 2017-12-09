/**
 * @FileName: ExceptionHandlerController.java
 * @Package: com.cloud.sms.archetype.commons.controller
 * @author sence
 * @created 4/14/2016 10:28 AM
 * <p/>
 *
 */
package com.cloud.sms.docker.common.controller;

import com.cloud.sms.docker.common.response.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
@ControllerAdvice
public class ExceptionHandlerController {

    public static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);


    /**
     * 产生异常
     *
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ResponseVo> handlerException(Exception exception) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error("error:", exception);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(ResponseVo.responseError("系统错误:"+exception.getMessage()),headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
