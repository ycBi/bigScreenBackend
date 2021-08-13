package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sf.bigscreen.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import javafx.scene.chart.ValueAxis;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: bi xuan
 * @Date: 2021/8/4 17:09
 * @Description:
 **/
@Data
@TableName("water_total_year")
public class WaterTotalYear extends BaseEntity {

    @ApiModelProperty("年")
    @TableField("year")
    private Integer year;

    @ApiModelProperty("月")
    @TableField("month")
    private Integer month;

    @TableField("total")
    @ApiModelProperty("总量")
    private Double total;
}
