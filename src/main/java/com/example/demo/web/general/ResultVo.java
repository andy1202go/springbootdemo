/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.web.general;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liangbo
 * @version V1.0
 * @Title: ResultVo.java
 * @Package com.example.demo.general
 * @Description 结果封装类
 * @date 2022 07-26 14:36.
 */
@Data
public class ResultVo<T> implements Serializable {
    private static final long serialVersionUID = -8456345788490416716L;
    // 状态码
    private int code;

    // 状态信息
    private String msg;

    // 返回对象
    private T data;

    private Page page;

    // 手动设置返回vo
    public ResultVo(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 默认返回成功状态码，数据对象
    public ResultVo(T data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.data = data;
    }

    // 返回指定状态码，数据对象
    public ResultVo(StatusCode statusCode, T data) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = data;
    }

    // 只返回状态码
    public ResultVo(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = null;
    }


    public ResultVo(T data, Page page) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.data = data;
        this.page = page;
    }

    public ResultVo(T data, int currentPage, int totalRecord, int pageSize) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.data = data;
        this.page = new Page(currentPage, totalRecord, pageSize);
    }

    public static ResultVo<String> success() {
        return new ResultVo("SUCCESS");
    }
}
