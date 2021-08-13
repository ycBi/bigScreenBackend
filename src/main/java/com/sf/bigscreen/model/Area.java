package com.sf.bigscreen.model;

/**
 * @Author: bi xuan
 * @Date: 2021/8/11 16:13
 * @Description:
 **/

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
 * @Date: 2021/8/7 9:21
 * @Description:
 **/
@Data
@TableName("area")
public class Area extends BaseEntity {

    @ApiModelProperty("区域id")
    @TableField("area_id")
    private Integer areaId;

    @ApiModelProperty("区域名称")
    @TableField("area_name")
    private String areaName;

    @ApiModelProperty("状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("总量")
    @TableField("total")
    private Double total;

    @ApiModelProperty("父节点，指向自己父节点的区域id")
    @TableField("parent")
    private Integer parent;
}

