package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import javax.jnlp.IntegrationService;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: bi xuan
 * @Date: 2021/8/4 16:03
 * @Description:
 **/
@Data
@TableName("area_night_min_flow_monitor")
public class AreaNightMinFlowMonitor implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("area_id")
    @ApiModelProperty("区域id")
    private Integer areaId;

    @TableField("area_name")
    @ApiModelProperty("区域名称")
    private String areaName;

    @TableField("date")
    @ApiModelProperty("日期")
    private Date date;

    @TableField("right_min_flow")
    @ApiModelProperty("夜间最小流量数值")
    private Double nightMinFlow;

}
