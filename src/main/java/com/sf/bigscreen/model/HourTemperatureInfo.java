package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: bi xuan
 * @Date: 2021/6/23 16:14
 * @Description:温湿度信息
 **/
@Data
@TableName("hourtemperatureinfo")
public class HourTemperatureInfo {
    @TableId(type = IdType.AUTO)
    @TableField("ID")
    private Integer id;

    @TableField("InfoStyle")
    @ApiModelProperty("所属对象类型")
    private String infoStyle;

    @TableField("InfoIndex")
    @ApiModelProperty("所属对象id")
    private String infoIndex;

    @TableField("InfoName")
    @ApiModelProperty("所属对象名称")
    private String infoName;

    @TableField("TheTime")
    @ApiModelProperty("时间")
    private Date theTime;

    @TableField("temperature")
    @ApiModelProperty("温度")
    private Double temperature;

    @TableField("humidity")
    @ApiModelProperty("湿度")
    private Double humidity;
}
