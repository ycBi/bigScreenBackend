package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

/**
 * @Author: bi xuan
 * @Date: 2021/6/23 15:08
 * @Description: 报警信息表
 **/
@Data
@TableName("alarminfo")
public class AlarmInfo {
    @TableId(type = IdType.AUTO)
    @TableField("ID")
    @ApiModelProperty(value = "ID")
    private Integer id;

    @TableField("Style")
    @ApiModelProperty(value = "类型")
    private String style;

    @TableField("TheLevel")
    @ApiModelProperty(value = "级别")
    private String theLevel;

    @TableField("Title")
    @ApiModelProperty(value="标题")
    private String title;

    @TableField("Con")
    @ApiModelProperty(value="详细内容")
    private String con;

    @TableField("HappenTime")
    @ApiModelProperty(value = "发生时间")
    private Date HappenTime;

    @TableField("InsertTime")
    @ApiModelProperty(value="插入时间")
    private Date insertTime;

    @TableField("OverTime")
    @ApiModelProperty(value="显示超时时间")
    private Date OverTime;
}

