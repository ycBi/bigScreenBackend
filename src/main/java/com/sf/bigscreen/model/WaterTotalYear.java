package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class WaterTotalYear implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("date")
    @ApiModelProperty("日期")
    private Date date;

    @TableField("total")
    @ApiModelProperty("总量")
    private Double total;
}
