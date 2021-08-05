package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.lettuce.core.output.DoubleOutput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: bi xuan
 * @Date: 2021/8/4 17:10
 * @Description:
 **/
@Data
@TableName("water_type")
public class WaterType implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("water_use_properties")
    @ApiModelProperty("日期")
    private String waterUseProperties;

    @TableField("total")
    @ApiModelProperty("用水总量")
    private Double total;
}
