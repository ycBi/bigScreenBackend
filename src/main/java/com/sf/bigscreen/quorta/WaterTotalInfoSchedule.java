package com.sf.bigscreen.quorta;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sf.bigscreen.mapper.WaterTotalYearMapper;
import com.sf.bigscreen.model.WaterTotalMonth;
import com.sf.bigscreen.model.WaterTotalNightMonth;
import com.sf.bigscreen.model.WaterTotalYear;
import com.sf.bigscreen.service.IWaterTotalMonthService;
import com.sf.bigscreen.service.IWaterTotalNightMonthService;
import com.sf.bigscreen.service.IWaterTotalYearService;
import com.sf.bigscreen.service.impl.WaterTotalYearService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: bi xuan
 * @Date: 2021/8/13 15:45
 * @Description: 用于同步相关的用水量的信息
 **/
@Slf4j
@Component
public class WaterTotalInfoSchedule {

    @Value("${third.url}")
    private String baseThirdUrl;

    @Resource
    private WaterTotalYearMapper waterTotalYearMapper;

    @Autowired
    private IWaterTotalYearService iWaterTotalYearService;

    @Autowired
    private IWaterTotalMonthService iWaterTotalMonthService;

    @Autowired
    private IWaterTotalNightMonthService iWaterTotalNightMonthService;

    /**
     * 每23点钟执行一次同步任务
     */
//    @Scheduled(cron = "0 0 23 * * ?")
    @Scheduled(cron = "0 */1 * * * ?")
    public void updateWaterTotalYear() {
        LocalDateTime now1 = LocalDateTime.now();
        Integer year = now1.getYear();
        Integer monthValue = now1.getMonthValue();
        Integer day = now1.getDayOfMonth();

        //同步waterTotalYear表信息
        String url = new StringBuilder(baseThirdUrl).append("/area/getAreaMonthGrossByTime").append("?year=").append(year).append("&month=").append(monthValue).toString();
        String body = HttpRequest.get(url).execute().body();
        if (!body.isEmpty()) {
            JSONObject jsonObject = JSONUtil.parseObj(body);
            Double gross = Double.valueOf(jsonObject.get("gross").toString());

            LambdaQueryWrapper<WaterTotalYear> waterTotalYearLambdaQueryWrapper = new LambdaQueryWrapper<>();
            waterTotalYearLambdaQueryWrapper.eq(WaterTotalYear::getYear, year).eq(WaterTotalYear::getMonth, monthValue);
            int count = iWaterTotalYearService.count(waterTotalYearLambdaQueryWrapper);
            if (count == 0) {
                WaterTotalYear waterTotalYear = new WaterTotalYear();
                waterTotalYear.setYear(year);
                waterTotalYear.setMonth(monthValue);
                waterTotalYear.setTotal(gross);
                boolean save = iWaterTotalYearService.save(waterTotalYear);
                log.info("同步状态：1：成功 0：失败：" + save);
            } else {
                waterTotalYearLambdaQueryWrapper.last("LIMIT 1");
                WaterTotalYear one = iWaterTotalYearService.getOne(waterTotalYearLambdaQueryWrapper);
                one.setTotal(gross);
                boolean update = iWaterTotalYearService.updateById(one);
                log.info("同步状态：1：成功 0：失败：" + update);
            }
        }

        //同步waterTotalMonth表信息
        String url2 = new StringBuilder(baseThirdUrl).append("/area/getAreaDayGrossByTime").append("?year=").append(year).append("&month=").append(monthValue).append("&day=").append(day).toString();
        String body2 = HttpRequest.get(url2).execute().body();
        if (!body2.isEmpty()) {
            JSONObject jsonObject = JSONUtil.parseObj(body2);
            Double gross = Double.valueOf(jsonObject.get("gross").toString());

            LambdaQueryWrapper<WaterTotalMonth> waterTotalMonthLambdaQueryWrapper = new LambdaQueryWrapper<>();
            waterTotalMonthLambdaQueryWrapper.eq(WaterTotalMonth::getYear, year).eq(WaterTotalMonth::getMonth, monthValue).eq(WaterTotalMonth::getDay, day);
            int count = iWaterTotalMonthService.count(waterTotalMonthLambdaQueryWrapper);
            if (count == 0) {
                WaterTotalMonth waterTotalMonth = new WaterTotalMonth();
                waterTotalMonth.setYear(year);
                waterTotalMonth.setMonth(monthValue);
                waterTotalMonth.setDay(day);
                waterTotalMonth.setTotal(gross);
                boolean save = iWaterTotalMonthService.save(waterTotalMonth);
                log.info("同步状态：1：成功 0：失败：" + save);
            } else {
                waterTotalMonthLambdaQueryWrapper.last("LIMIT 1");
                WaterTotalMonth one = iWaterTotalMonthService.getOne(waterTotalMonthLambdaQueryWrapper);
                one.setTotal(gross);
                boolean update = iWaterTotalMonthService.updateById(one);
                log.info("同步状态：1：成功 0：失败：" + update);
            }
        }

        //同步waterTotalNightMonth表信息
        String url3 = new StringBuilder(baseThirdUrl).append("/area/getAreaDayNightGrossByTime").append("?year=").append(year).append("&month=").append(monthValue).append("&day=").append(day).toString();
        String body3 = HttpRequest.get(url2).execute().body();
        if (!body3.isEmpty()) {
            JSONObject jsonObject = JSONUtil.parseObj(body3);
            Double gross = Double.valueOf(jsonObject.get("gross").toString());

            LambdaQueryWrapper<WaterTotalNightMonth> waterTotalNightMonthLambdaQueryWrapper = new LambdaQueryWrapper<>();
            waterTotalNightMonthLambdaQueryWrapper.eq(WaterTotalNightMonth::getYear, year).eq(WaterTotalNightMonth::getMonth, monthValue).eq(WaterTotalNightMonth::getDay, day);
            int count = iWaterTotalNightMonthService.count(waterTotalNightMonthLambdaQueryWrapper);
            if (count == 0) {
                WaterTotalNightMonth waterTotalNightMonth = new WaterTotalNightMonth();
                waterTotalNightMonth.setYear(year);
                waterTotalNightMonth.setMonth(monthValue);
                waterTotalNightMonth.setDay(day);
                waterTotalNightMonth.setTotal(gross);
                boolean save = iWaterTotalNightMonthService.save(waterTotalNightMonth);
                log.info("同步状态：1：成功 0：失败："+save);
            } else {
                waterTotalNightMonthLambdaQueryWrapper.last("LIMIT 1");
                WaterTotalNightMonth one = iWaterTotalNightMonthService.getOne(waterTotalNightMonthLambdaQueryWrapper);
                one.setTotal(gross);
                boolean update = iWaterTotalNightMonthService.updateById(one);
                log.info("同步状态：1：成功 0：失败：" +update);
            }

        }
    }
}
