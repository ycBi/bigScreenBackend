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
 * @Date: 2021/8/4 16:32
 * @Description:
 **/
@Data
@TableName("machine")
public class Machine extends BaseEntity {

    @TableField("machine_name")
    @ApiModelProperty("设备名称")
    private String machineName;

    @TableField("machine_number_predict")
    @ApiModelProperty("设备计划数量")
    private Integer machineNumberPredict;

    @TableField("machine_number_practical")
    @ApiModelProperty("设备完成的实际数量")
    private Integer machineNumberPractical;

    @TableField("machine_number_optimization")
    @ApiModelProperty("设备的调优优化数量")
    private Integer machineNumberOptimization;
}
