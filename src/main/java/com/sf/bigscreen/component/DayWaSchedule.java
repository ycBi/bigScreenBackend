package com.sf.bigscreen.component;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sf.bigscreen.mapper.ITableConfigMapper;
import com.sf.bigscreen.model.TableConfig;
import com.sf.bigscreen.service.IDayWaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: bi xuan
 * @Date: 2021/7/9 15:01
 * @Description:定时任务，同步数据导dayWa表
 **/
@Slf4j
@Component
public class DayWaSchedule implements SchedulingConfigurer {

    @Resource
    private ITableConfigMapper iTableConfigMapper;

    @Autowired
    private IDayWaService dayWaService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(executeSchedule(),
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cron = iTableConfigMapper.selectOne(new LambdaQueryWrapper<TableConfig>().eq(TableConfig::getTableName,"daywa").eq(TableConfig::getInfoStyle,"All")).getCron();
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                });
    }

    private Runnable executeSchedule(){
        return new Runnable() {
            @Override
            public void run() {
//                Integer integer = dayWaService.addOrUpdateDayWaAllInfo();
//                log.info("执行水表数据整合任务"+new Date());
            }
        };
    }
}
