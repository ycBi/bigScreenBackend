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
 * @Date: 2021/8/4 16:52
 * @Description:
 **/
@Data
@TableName("water_total")
public class WaterTotal extends BaseEntity {

    @TableField("total_mount")
    @ApiModelProperty("总用水量")
    private Double totalMount;

    @TableField("exception_mount")
    @ApiModelProperty("异常用水量")
    private Double exceptionMount;

    @TableField("date")
    @ApiModelProperty("日期")
    private String date;

    @TableField("date_type")
    @ApiModelProperty("日期类型：year、month、day、yesterday")
    private String dateType;
}
