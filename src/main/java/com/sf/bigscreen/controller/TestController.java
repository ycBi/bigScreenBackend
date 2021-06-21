package com.sf.bigscreen.controller;

import com.sf.bigscreen.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: bi xuan
 * @Date: 2021/6/18 16:11
 * @Description:
 **/
@Api(tags = "基本信息接口")
@RestController
@RequestMapping("/baseInfo")
public class TestController {

    @ApiOperation("获取登录状态")
    @GetMapping("/getLogin")
    public Result getLoginStatus(){
        return Result.buildSuccessMessage("success");
    }
}
