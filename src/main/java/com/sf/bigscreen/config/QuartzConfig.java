package com.sf.bigscreen.config;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: bi xuan
 * @Date: 2021/7/6 9:02
 * @Description:
 **/
@Configuration
public class QuartzConfig {

//    @Value("${asphale.schedule.time}")
//    private int cronTime;
//
//    @Bean
//    public JobDetail teatQuartzDetail(){
//        return JobBuilder.newJob(JobSchedule.class).withIdentity("jobQuartz").storeDurably().build();
//    }
//    @Bean
//    public Trigger testQuartzTrigger(){
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(cronTime)  //设置时间周期单位秒
//                .repeatForever();
//        return TriggerBuilder.newTrigger().forJob(teatQuartzDetail())
//                .withIdentity("testQuartz")
//                .withSchedule(scheduleBuilder)
//                .build();
//    }

}

