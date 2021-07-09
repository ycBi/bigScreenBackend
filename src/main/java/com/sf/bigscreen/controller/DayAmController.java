package com.sf.bigscreen.controller;

import com.sf.bigscreen.entity.Result;
import com.sf.bigscreen.service.IDayAmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty("同步一段时间的全校电费数据")
    @GetMapping("/updateDayAmAll")
    public Result updateDayAmAllInfo(){
        Integer integer = dayAmService.addOrUpdateDayAmAllInfoRangeTime();
        return Result.buildSuccessData(integer);
    }

    @ApiModelProperty("同步一段时间的区域电费数据")
    @GetMapping("/updateDayAmArea")
    public Result updateDayAmAreaInfo(){
        Integer integer = dayAmService.addOrUpdateDayAmAreaInfoRangeTime();
        return Result.buildSuccessData(integer);
    }

    @ApiModelProperty("同步一段时间的建筑电费数据")
    @GetMapping("/updateDayAmArc")
    public Result updateDayAmArcInfo(){
        Integer integer = dayAmService.addOrUpdateDayAmArcInfoRangeTime();
        return Result.buildSuccessData(integer);
    }

    @ApiModelProperty("同步一段时间的部门电费数据")
    @GetMapping("/updateDayAmPart")
    public Result updateDayAmPartInfo(){
        Integer integer = dayAmService.addOrUpdateDayAmDepartmentInfoRangeTime();
        return Result.buildSuccessData(integer);
    }

    @ApiModelProperty("同步一段时间的单只电表电费数据")
    @GetMapping("/updateDayAmMeter")
    public Result updateDayAmMeterInfo(){
        Integer integer = dayAmService.addOrUpdateDayAmInfoRangeTime();
        return Result.buildSuccessData(integer);
    }
}
