package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sf.bigscreen.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: bi xuan
 * @Date: 2021/8/4 15:57
 * @Description:
 **/
@Data
@TableName("alarm")
public class Alarm extends BaseEntity {

    @TableField("alarm_name")
    @ApiModelProperty("报警名称")
    private String alarmName;

    @TableField("alarm_date")
    @ApiModelProperty("报警日期")
    private Date alarmDate;

    @TableField("alarm_info")
    @ApiModelProperty("报警信息")
    private String alarmInfo;
}
