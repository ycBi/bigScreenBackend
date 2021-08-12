package com.sf.bigscreen.controller;

import com.sf.bigscreen.service.IAreaService;
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

    @ApiModelProperty("同步区域信息")
    @GetMapping("/updateArea")
    public void updateAreInfo(){
        iAreaService.setAreaData();
    }

}
