/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.web.general;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 一般测试控制器
 *
 * @author 80338398
 * @date 2022/07/27
 */
@RestController
@RequestMapping("general")
@Slf4j
public class GeneralTestController {

    @PostMapping("")
    @NotControllerResponseAdvice
    public String test() {
        return "OK";
    }

    @PostMapping("vo")
    public ResultVo testVo() {
        return new ResultVo("OK");
    }
}
