package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.omg.PortableInterceptor.ORBIdHelper;

/**
 * @Author: bi xuan
 * @Date: 2021/6/23 16:39
 * @Description: 年二氧化碳中和明细
 **/
@Data
@TableName("yearco2info")
public class YearCo2Info {

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

    @TableField("CO2Style")
    @ApiModelProperty("类型：out排放，in吸收")
    private String co2Style;

    @TableField("CO2StyleNum")
    @ApiModelProperty("编号")
    private String co2StyleNum;

    @TableField("CO2StyleName")
    @ApiModelProperty("名称")
    private String co2StyleName;

    @TableField("TheGross")
    @ApiModelProperty("总量")
    private Double theGross;

}
