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
 * @Date: 2021/6/23 15:31
 * @Description: 日用电情况
 **/
@Data
@TableName("dayam")
public class DayAm {
    @TableId(type = IdType.AUTO)
    @TableField("ID")
    @ApiModelProperty("id")
    private Integer id;

    @TableField("InfoStyle")
    @ApiModelProperty("所属对象类型")
    private String infoStyle;

    @TableField("InfoIndex")
    @ApiModelProperty("所属对象ID")
    private String infoIndex;

    @TableField("InfoName")
    @ApiModelProperty("所属对象名称")
    private String infoName;

    @TableField("TheDay")
    @ApiModelProperty("日期")
    private Date theDay;

    @TableField("ZGross")
    @ApiModelProperty("总用量")
    private Double zGross;

    @TableField("WorkGross")
    @ApiModelProperty("工作时间段用量")
    private Double workGross;

    @TableField("UnWorkGross")
    @ApiModelProperty("非工作时间段用量")
    private Double unWorkGross;

    @TableField("isWorkDay")
    @ApiModelProperty("是否为工作日")
    private Boolean workDay;
}
