package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: bi xuan
 * @Date: 2021/6/23 16:48
 * @Description: 年能耗详情
 **/
@Data
@TableName("yearinfoen")
public class YearInfoEn {
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

    @TableField("TheYear")
    @ApiModelProperty("年份")
    private Integer theYear;

    @TableField("EnGross")
    @ApiModelProperty("总能耗")
    private Double enGross;

    @TableField("AmGross")
    @ApiModelProperty("总电耗")
    private Double amGross;

    @TableField("WaGross")
    @ApiModelProperty("总水耗")
    private Double waGross;

    @TableField("GasGross")
    @ApiModelProperty("总气耗")
    private Double gasGross;

    @TableField("CO2OutGross")
    @ApiModelProperty("二氧化碳排放量")
    private Double co2OutGross;

    @TableField("CO2InGross")
    @ApiModelProperty("二氧化碳吸收量")
    private Double co2InGross;

    @TableField("COutGross")
    @ApiModelProperty("碳排量")
    private Double outGross;

}
