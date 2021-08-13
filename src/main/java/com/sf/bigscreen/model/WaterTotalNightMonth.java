package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sf.bigscreen.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: bi xuan
 * @Date: 2021/8/4 17:07
 * @Description:
 **/
@Data
@TableName("water_total_night_month")
public class WaterTotalNightMonth extends BaseEntity {

    @TableField("year")
    @ApiModelProperty("年")
    private Integer year;

    @TableField("month")
    @ApiModelProperty("月")
    private Integer month;

    @TableField("day")
    @ApiModelProperty("日")
    private Integer day;

    @TableField("total")
    @ApiModelProperty("每日的夜间流量总量")
    private Double total;
}
