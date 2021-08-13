package com.sf.bigscreen.controller;

import com.sf.bigscreen.entity.Result;
import com.sf.bigscreen.service.IAreaService;
import com.sf.bigscreen.service.IWaterTotalMonthService;
import com.sf.bigscreen.service.IWaterTotalNightMonthService;
import com.sf.bigscreen.service.IWaterTotalYearService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: bi xuan
 * @Date: 2021/8/11 16:53
 * @Description:
 **/
@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private IAreaService iAreaService;

    @Autowired
    private IWaterTotalYearService iWaterTotalYearService;

    @Autowired
    private IWaterTotalMonthService iWaterTotalMonthService;

    @Autowired
    private IWaterTotalNightMonthService iWaterTotalNightMonthService;

    @ApiModelProperty("同步区域信息")
    @GetMapping("/updateArea")
    public Result updateAreInfo(){
        iAreaService.setAreaData();
        return Result.buildSuccessMessage("同步成功");
    }

    @ApiModelProperty("同步两年用水趋势表")
    @GetMapping("/setAllAreaGross")
    public Result setAllAreaGrossPer(){
        iWaterTotalYearService.insertWaterYearInfo();
        return Result.buildSuccessMessage("同步成功");
    }

    @ApiModelProperty("同步两月用水趋势表")
    @GetMapping("/setAllAreaGrossPerMonth")
    public Result setAllAreaGrossPerMonth(){
        iWaterTotalMonthService.insertWaterMonthInfo();
        return Result.buildSuccessMessage("同步成功");
    }

    @ApiModelProperty("同步两月夜间用水趋势表")
    @GetMapping("/setAllAreaNightGross")
    public Result setAllAreaNightGross(){
        iWaterTotalNightMonthService.insertWaterNightMonthInfo();
        return Result.buildSuccessMessage("同步成功");
    }
}
