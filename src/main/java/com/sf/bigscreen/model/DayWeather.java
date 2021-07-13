package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("dayweather")
public class DayWeather {

    @TableId(type = IdType.AUTO)
    @TableField("ID")
    @ApiModelProperty("ID")
    private Integer id;

    @TableField("TheDay")
    @ApiModelProperty("日期")
    private Date theDay;

    @TableField("icon")
    @ApiModelProperty("天气图标")
    private String icon;

    @TableField("temperature")
    @ApiModelProperty("温度")
    private String temperature;

    @TableField("humidity")
    @ApiModelProperty("湿度")
    private String humidity;

    @TableField("text")
    @ApiModelProperty("天气状况文字描述")
    private String text;

}
