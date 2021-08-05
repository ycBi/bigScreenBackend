package com.sf.bigscreen.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sf.bigscreen.entity.Result;
import com.sf.bigscreen.model.WaterType;
import com.sf.bigscreen.service.IWaterTypeService;
import com.sf.bigscreen.service.impl.WaterTypeService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: bi xuan
 * @Date: 2021/8/5 11:40
 * @Description:
 **/
@RestController
@RequestMapping("/waterType")
public class WaterTypeController {

    @Autowired
    private IWaterTypeService waterTypeService;

    @ApiModelProperty("从水平衡接口获取数据插入到大屏数据库")
    @GetMapping("/setWaterTypeInfo")
    public void setWaterTypeInfo(){
        waterTypeService.insertWaterTypeData();
    }

    @ApiModelProperty("获取水平衡接口")
    @GetMapping("/getWaterTypeInfo")
    public void getWaterTypeInfo(){
        LambdaQueryWrapper<WaterType> waterTypeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<WaterType> list = waterTypeService.list(waterTypeLambdaQueryWrapper);
        Result<Object> objectResult = new Result<>();
    }

}
