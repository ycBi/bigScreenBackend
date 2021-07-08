package com.sf.bigscreen.controller;

import com.sf.bigscreen.entity.Result;
import com.sf.bigscreen.service.IDayAmService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: bi xuan
 * @Date: 2021/7/8 16:55
 * @Description:
 **/
@Api("关于电表信息的参数接口")
@RestController
@RequestMapping("/dayAm")
public class DayAmController {
    @Autowired
    private IDayAmService dayAmService;

    @GetMapping("/updateDayAm")
    public Result updateDayAmInfo(){
        Integer integer = dayAmService.addOrUpdateDayAmAllInfoRangeTime();
        return Result.buildSuccessData(integer);
    }
}
