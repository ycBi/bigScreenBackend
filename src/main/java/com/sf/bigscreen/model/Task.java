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

/**
 * @Author: bi xuan
 * @Date: 2021/8/4 16:47
 * @Description:
 **/
@Data
@TableName("task")
public class Task extends BaseEntity {

    @TableField("task_name")
    @ApiModelProperty("任务名称")
    private String taskName;

    @TableField("task_status_predict")
    @ApiModelProperty("任务预计状态")
    private String taskStatusPredict;

    @TableField("task_status_practical")
    @ApiModelProperty("任务实际完成状态")
    private String taskStatusPractical;

    @TableField("task_status_optimization")
    @ApiModelProperty("任务优化状态")
    private String taskStatusOptimization;
}
