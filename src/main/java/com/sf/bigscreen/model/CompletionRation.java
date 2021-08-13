package com.sf.bigscreen.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sf.bigscreen.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: bi xuan
 * @Date: 2021/8/4 16:09
 * @Description:
 **/
@Data
@TableName("completion_ration")
public class CompletionRation extends BaseEntity {

    @TableField("schedule_name")
    @ApiModelProperty("任务名称")
    private String scheduleName;

    @TableField("proportion")
    @ApiModelProperty("任务完成比率")
    private String proportion;
}
