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
 * @Date: 2021/6/28 10:29
 * @Description: 对应前端的路由表
 **/
@Data
@TableName("router")
public class Router extends BaseEntity {

    @TableField("role")
    @ApiModelProperty("角色")
    private String role;

    @TableField("routes")
    @ApiModelProperty("路由列表")
    private String routes;
}
