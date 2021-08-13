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
 * @Date: 2021/8/4 17:06
 * @Description:
 **/
@Data
@TableName("water_total_month")
public class WaterTotalMonth extends BaseEntity {

    @ApiModelProperty(value = "年")
    @TableField("year")
    private Integer year;

    @ApiModelProperty(value = "月")
    @TableField("month")
    private Integer month;

    @ApiModelProperty(value = "日")
    @TableField("day")
    private Integer day;

    @TableField("total")
    @ApiModelProperty("每日总量")
    private Double total;
}
