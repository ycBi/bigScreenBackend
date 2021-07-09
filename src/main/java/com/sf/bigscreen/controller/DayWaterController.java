package com.sf.bigscreen.controller;

import com.sf.bigscreen.entity.Result;
import com.sf.bigscreen.service.IDayWaService;
import com.sf.bigscreen.service.IDayWeatherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: bi xuan
 * @Date: 2021/7/9 8:40
 * @Description:
 **/
@Api("关于水表信息的参数接口")
@RestController
@RequestMapping("/dayWa")
public class DayWaterController {
    @Autowired
    private IDayWaService dayWaterService;

    @GetMapping("/updateDayWa")
    public Result updateDayAmInfo(){
        Integer integer = dayWaterService.addOrUpdateDayWaAllInfoRangeTime();
        return Result.buildSuccessData(integer);
    }
}
