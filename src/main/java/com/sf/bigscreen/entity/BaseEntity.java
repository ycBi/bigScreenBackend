package com.sf.bigscreen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import java.io.Serializable;
import java.util.Date;

/**
 * @Author liman
 **/
@Data
public class BaseEntity<T extends Model<?>> extends Model<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    protected Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("gmt_create")
    protected Date gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("gmt_modify")
    protected Date gmtModify;
}

