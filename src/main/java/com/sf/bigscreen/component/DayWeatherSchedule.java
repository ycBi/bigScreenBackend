package com.sf.bigscreen.component;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.sf.bigscreen.mapper.IDayWeatherMapper;
import com.sf.bigscreen.model.DayWeather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: bi xuan
 * @Date: 2021/7/12 14:35
 * @Description:定时任务同步天气信息
 **/
@Slf4j
@Component
public class DayWeatherSchedule {
    @Resource
    private IDayWeatherMapper dayWeatherMapper;

    @Value("${weather.key}")
    private String key;

    @Value("${weather.url}")
    private String url;

    @Value("${weather.location}")
    private String location;

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateWeatherInfo(){
        String url = new StringBuilder(this.url).append("?location=").append(location).append("&key=").append(key).toString();
        String body = HttpRequest.get(url).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        if ("200".equals(jsonObject.get("code"))){
            Object now = jsonObject.get("now");
            JSONObject res = JSONUtil.parseObj(now);
            String icon = (String)res.get("icon");
            String temp = (String)res.get("temp");
            String humidity = (String) res.get("humidity");
            String text = (String)res.get("text");
            DateTime time = DateUtil.parse(DateUtil.now(), "yyyy-MM-dd");
            DayWeather dayWeather = new DayWeather();
            dayWeather.setTheDay(time);
            dayWeather.setIcon(icon);
            dayWeather.setTemperature(temp);
            dayWeather.setHumidity(humidity);
            dayWeather.setText(text);
            int insert = dayWeatherMapper.insert(dayWeather);
            if (insert==0)
                log.warn("天气数据更新失败"+new Date());
            log.info("天气数据更新成功"+new Date());
        }
    }
}
