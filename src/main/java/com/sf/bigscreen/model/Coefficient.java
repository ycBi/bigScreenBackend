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
 * @Date: 2021/6/23 15:19
 * @Description: 计算系数表
 **/
@Data
@TableName("coefficient")
public class Coefficient {
    @TableId(type = IdType.AUTO)
    @TableField("ID")
    @ApiModelProperty(value = "id")
    private Integer id;

    @TableField("EnNum")
    @ApiModelProperty(value = "能源编号")
    private String enNum;

    @TableField("EnName")
    @ApiModelProperty(value = "能源名称")
    private String EnName;

    @TableField("ZBXSUnit")
    @ApiModelProperty("折标系数大内")
    private String zbxsUnit;

    @TableField("ZBXS")
    @ApiModelProperty("折标系数")
    private Double zbxs;

    @TableField("CPXSUnit")
    @ApiModelProperty("碳排系统单位")
    private String cpxsUnit;

    @TableField("CPXS")
    @ApiModelProperty("碳排放系数")
    private Double cpxs;

    @TableField("CO2XSUnit")
    @ApiModelProperty("二氧化碳排放系数单位")
    private String co2xsUnit;

    @TableField("CO2XS")
    @ApiModelProperty("二氧化碳排放系数")
    private Double co2xs;

    @TableField("EditTime")
    @ApiModelProperty("编辑时间")
    private Date editTime;

    @TableField("Basis")
    @ApiModelProperty("依据来源")
    private String basis;
}
