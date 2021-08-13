package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sf.bigscreen.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: bi xuan
 * @Date: 2021/8/4 16:20
 * @Description:
 **/
@Data
@TableName("leakage_rate_control")
public class LeakageRateControl extends BaseEntity {

    @TableField("water_balance_rate")
    @ApiModelProperty("用水平衡率")
    private String waterBalanceRate;

    @TableField("timely_processing_rate")
    @ApiModelProperty("处理及时率")
    private String timelyProcessingRate;

    @TableField("patrol_inspection_completion_rate")
    @ApiModelProperty("巡检完成率")
    private String patrolInspectionCompletionRate;

    @TableField("equipment_online_rate")
    @ApiModelProperty("设备在线率")
    private String equipmentOnlineRate;

    @TableField("acquisition_coverage_rate")
    @ApiModelProperty("采集覆盖率")
    private String acquisitionCoverageRate;

    @TableField("index_achievement_rate")
    @ApiModelProperty("指标达成率")
    private String indexAchievementRate;

}
