package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: bi xuan
 * @Date: 2021/6/23 16:30
 * @Description:其他信息
 **/
@Data
@TableName("ortherinfo")
public class OrtherInfo {

    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Integer id;

    @TableField("Num")
    @ApiModelProperty("信息编号")
    private String num;

    @TableField("Name")
    @ApiModelProperty("名称")
    private String name;

    @TableField("Value")
    @ApiModelProperty("值")
    private Double value;

    @TableField("Remark")
    @ApiModelProperty("备注")
    private String remark;

}
