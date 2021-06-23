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
 * @Date: 2021/6/23 16:19
 * @Description:小时用水情况
 **/
@Data
@TableName("hourwa")
public class HourWa {
    @TableId(type = IdType.AUTO)
    @TableField("ID")
    @ApiModelProperty("ID")
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
    @ApiModelProperty("日期")
    private Date theTime;

    @TableField("ZGross")
    @ApiModelProperty("总用量")
    private Double gross;

    @TableField("WorkGross")
    @ApiModelProperty("工作时段用量")
    private Double workGross;

    @TableField("UnWorkGross")
    @ApiModelProperty("非工作时段用量")
    private Double unWorkGross;

    @TableField("IsWorkDay")
    @ApiModelProperty("是否是工作日")
    private Boolean workDay;

}
