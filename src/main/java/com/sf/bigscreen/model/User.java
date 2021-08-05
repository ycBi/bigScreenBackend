package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sf.bigscreen.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: bi xuan
 * @Date: 2021/6/21 15:42
 * @Description:
 **/
@Data
@TableName("user")
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableField(value = "username")
    @ApiModelProperty(value = "用户名")
    private String username;

    @TableField(value = "password")
    @ApiModelProperty(value = "密码")
    private String password;

    @TableField(value = "role")
    @ApiModelProperty(value = "角色权限")
    private String role;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

