package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sf.bigscreen.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.swing.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: bi xuan
 * @Date: 2021/8/4 16:12
 * @Description:
 **/
@Data
@TableName("leakage_distribution")
public class LeakageDistribution extends BaseEntity {

    @TableField("area_id")
    @ApiModelProperty("区域id")
    private Integer areaId;

    @TableField("area_name")
    @ApiModelProperty("区域名称")
    private String areaName;

    @TableField("date")
    @ApiModelProperty("日期")
    private Date date;

    @TableField("shenlou")
    @ApiModelProperty("渗漏")
    private Double shenlou;

    @TableField("baoguan")
    @ApiModelProperty("爆管")
    private Double baoguan;

    @TableField("loushi")
    @ApiModelProperty("漏失")
    private Double loushi;

    @TableField("bujifei")
    @ApiModelProperty("不计费")
    private Double bujifei;
}
