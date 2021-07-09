package com.sf.bigscreen.component;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sf.bigscreen.mapper.IDayAmMapper;
import com.sf.bigscreen.mapper.ITableConfigMapper;
import com.sf.bigscreen.model.DayAm;
import com.sf.bigscreen.model.TableConfig;
import com.sf.bigscreen.service.IDayAmService;
import com.sf.bigscreen.utils.GetEnergyPlatformTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import sun.util.locale.provider.LocaleServiceProviderPool;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: bi xuan
 * @Date: 2021/7/6 14:42
 * @Description:定时任务，填入dayam的信息
 **/
@Slf4j
@Component
public class DayAmSchedule implements SchedulingConfigurer {

    @Resource
    private ITableConfigMapper iTableConfigMapper;

    @Autowired
    private IDayAmService iDayAmService;
    




    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(executeSchedule(),
                triggerContext -> {
            //2.1 从数据库获取执行周期
            String cron = iTableConfigMapper.selectOne(new LambdaQueryWrapper<TableConfig>().eq(TableConfig::getTableName,"dayam").eq(TableConfig::getInfoStyle,"All")).getCron();;
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
//                Integer integer = iDayAmService.addOrUpdateDayAmAllInfo();
//                log.info("执行电表数据整合任务"+new Date());
            }
        };
    }
//    @Override
//    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
//
//    }
//    private  String timeInterval = "0 0/1 * * * ?";
//
//    // 凌晨1点钟整理任务
//    @Scheduled(cron = timeInterval)
//    /**
//     * 整理DayAm的数据(将前一天的数据整理到可视化大屏的dayam数据库中)
//     */
//    public void arrangeDayAm() {
//        DateTime yesterday = DateUtil.yesterday();
//    }


}
