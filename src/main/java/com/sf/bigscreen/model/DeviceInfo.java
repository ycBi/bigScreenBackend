package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: bi xuan
 * @Date: 2021/6/23 15:47
 * @Description: 设备信息表
 **/
@Data
@TableName("deviceinfo")
public class DeviceInfo {

    @TableId(type = IdType.AUTO)
    @TableField("ID")
    @ApiModelProperty("id")
    private Integer id;

    @TableField("InfoStyle")
    @ApiModelProperty("所属对象类型")
    private String infoStyle;

    @TableField("InfoIndex")
    @ApiModelProperty("所属对象ID")
    private String infoIndex;

    @TableField("InfoName")
    @ApiModelProperty("所属对象名称")
    private String infoName;

    @TableField("deviceID")
    @ApiModelProperty("设备ID")
    private String deviceID;

    @TableField("deviceStyle")
    @ApiModelProperty("设备类型")
    private String deviceStyle;

    @TableField("MarkerStyle")
    @ApiModelProperty("设备标记类型（对应地图标记）")
    private String markerStyle;

    @TableField("deviceNum")
    @ApiModelProperty("设备编号")
    private String deviceNum;

    @TableField("deviceName")
    @ApiModelProperty("设备名称")
    private String deviceName;

    @TableField("coordinate")
    @ApiModelProperty("坐标（对应地图标记）")
    private String coordinate;

    @TableField("deviceOnline")
    @ApiModelProperty("设备在线状态")
    private Integer deviceOnline;

    @TableField("deviceState")
    @ApiModelProperty("设备运行状态")
    private Integer deviceState;

    @TableField("remark")
    @ApiModelProperty("备注")
    private String remark;

    @TableField("LastTime")
    @ApiModelProperty("最后更新时间")
    private Date lastTime;

}
