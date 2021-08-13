package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sf.bigscreen.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: bi xuan
 * @Date: 2021/8/4 16:28
 * @Description:
 **/
@Data
@TableName("leakage")
public class Leakage extends BaseEntity {

    @TableField("year")
    @ApiModelProperty("年份")
    private Integer year;

    @TableField("month")
    @ApiModelProperty("月份")
    private Integer month;

    @TableField("practical_leakage")
    @ApiModelProperty("实际漏损率")
    private Double practicalLeakage;

    @TableField("target_leakage")
    @ApiModelProperty("目标漏损率")
    private Double targetLeakage;
}

