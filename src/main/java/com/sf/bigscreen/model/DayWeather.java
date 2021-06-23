package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import sun.dc.pr.PRError;

import java.util.Date;

/**
 * @Author: bi xuan
 * @Date: 2021/6/23 15:43
 * @Description:天气信息表
 **/
@Data
public class DayWeather {

    @TableId(type = IdType.AUTO)
    @TableField("ID")
    @ApiModelProperty("ID")
    private Integer id;

    @TableField("TheDay")
    @ApiModelProperty("日期")
    private Date theDay;

    @TableField("weatherNum")
    @ApiModelProperty("天气编码")
    private String weatherNum;

    @TableField("weatherString")
    @ApiModelProperty("备注")
    private String weatherString;

}
