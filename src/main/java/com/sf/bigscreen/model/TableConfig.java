package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sf.bigscreen.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: bi xuan
 * @Date: 2021/7/6 15:11
 * @Description:对其他数据库配置的表
 **/
@Data
@TableName("table_config")
public class TableConfig extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableField("table_name")
    @ApiModelProperty("数据库的表的名称")
    private String tableName;

    @TableField("info_style")
    @ApiModelProperty("所属对象类型")
    private String infoStyle;

    @TableField("info_index")
    @ApiModelProperty("所属对象id")
    private String infoIndex;

    @TableField("info_name")
    @ApiModelProperty("所属对象名称")
    private String infoName;

    @TableField("cron")
    @ApiModelProperty("定时任务的时间")
    private String cron;

    @TableField("start_date")
    @ApiModelProperty("起始时间段")
    private Date startDate;

    @TableField("end_date")
    @ApiModelProperty("终止时间段")
    private Date endDate;

    @TableField("update_range")
    @ApiModelProperty("是否开启同步一段时间，1：开启，0：关闭，默认未只同步当天的")
    private Boolean  updateRange;
}
