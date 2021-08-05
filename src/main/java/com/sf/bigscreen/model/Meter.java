package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: bi xuan
 * @Date: 2021/8/4 16:37
 * @Description:
 **/
@Data
@TableName("meter")
public class Meter implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("meter_name")
    @ApiModelProperty("电表")
    private String meterName;

    @TableField("cumulative_flow")
    @ApiModelProperty("累计流量")
    private Double cumulative_flow;

    @TableField("instantaneous_flow")
    @ApiModelProperty("瞬时流量")
    private Double instantaneous_flow;

    @TableField("pressure")
    @ApiModelProperty("压力")
    private Double pressure;

    @TableField("meter_address")
    @ApiModelProperty("电表通讯的485地址")
    private String meterAddress;

    @TableField("meter_number")
    @ApiModelProperty("电表的表号")
    private Integer meterNumber;

    @TableField("meter_caliber")
    @ApiModelProperty("电表的口径")
    private Integer meterCaliber;

    @TableField("meter_magnification")
    @ApiModelProperty("电表的倍率")
    private String meterMagnification;
}
