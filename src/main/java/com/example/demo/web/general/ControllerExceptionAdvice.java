/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.web.general;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

/**
 * @author liangbo
 * @version V1.0
 * @Title: ControllerExceptionAdvice.java
 * @Package com.example.demo.general
 * @Description 统一异常处理
 * @date 2022 07-26 14:42.
 */
@RestControllerAdvice
@Slf4j
public class ControllerExceptionAdvice {
    @ExceptionHandler({BindException.class})
    public ResultVo<String> methodArgumentNotValidExceptionHandler(BindException be) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = be.getBindingResult().getAllErrors().get(0);
        return new ResultVo<>(ResultCode.VALIDATE_ERROR, objectError.getDefaultMessage());
    }

    @ExceptionHandler(APIException.class)
    public ResultVo<String> APIExceptionHandler(APIException e) {
         log.error(e.getMessage(), e);
        return new ResultVo<>(e.getCode(), e.getMsg(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    @ResponseBody
    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public ResultVo asyncRequestTimeoutHandler(AsyncRequestTimeoutException e) {
        log.error("异步请求超时");
        return new ResultVo<>(ResultCode.ASYNC_TIME_OUT);
    }
}
