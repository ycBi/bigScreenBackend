package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @Author: bi xuan
 * @Date: 2021/6/23 16:34
 * @Description:定额信息
 **/

@Data
@TableName("quotadata")
public class QuotaData {
    @TableId(type = IdType.AUTO)
    @TableField("ID")
    private Integer id;

    @TableField("InfoStyle")
    @ApiModelProperty("所属对象类型")
    private String infoStyle;

    @TableField("InfoIndex")
    @ApiModelProperty("所属对象id")
    private String infoIndex;

    @TableField("InfoName")
    @ApiModelProperty("所属对象名称")
    private String infoName;

    @TableField("TheYear")
    @ApiModelProperty("年份")
    private Integer theYear;

    @TableField("ZValue")
    @ApiModelProperty("总定额")
    private Double value;

    @TableField("UseValue")
    @ApiModelProperty("使用定额")
    private Double useValue;

    @TableField("Score")
    @ApiModelProperty("评分")
    private Double score;

}
