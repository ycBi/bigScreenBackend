package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import sun.dc.pr.PRError;

/**
 * @Author: bi xuan
 * @Date: 2021/6/23 16:20
 * @Description:基本信息表
 **/
@Data
@TableName("information")
public class Information {

    @TableId(type = IdType.AUTO)
    @TableField("ID")
    private Integer id;

    @TableField("TheYear")
    @ApiModelProperty("年份")
    private Integer theYear;

    @TableField("InfoStyle")
    @ApiModelProperty("所属对象类型")
    private String infoStyle;

    @TableField("InfoIndex")
    @ApiModelProperty("所属对象iD")
    private String infoIndex;

    @TableField("InfoName")
    @ApiModelProperty("所属对象名称")
    private String infoName;

    @TableField("AreaZD")
    @ApiModelProperty("占地面积")
    private Double areaZD;

    @TableField("AreaJZ")
    @ApiModelProperty("建筑面积")
    private Double areaJZ;

    @TableField("AreaLH")
    @ApiModelProperty("绿化面积")
    private Double areaLH;

    @TableField("AreaKT")
    @ApiModelProperty("空调面积")
    private Double areaKT;

    @TableField("OutputValue")
    @ApiModelProperty("年产值")
    private Double outputValue;

    @TableField("TPersons")
    @ApiModelProperty("员工总数")
    private Integer persons;

    @TableField("TCar")
    @ApiModelProperty("车辆总数")
    private Integer car;

    @TableField("Ambalance")
    @ApiModelProperty("电平衡")
    private Double amBalance;

    @TableField("Wabalance")
    @ApiModelProperty("水平衡")
    private Double waBalance;
}
