package com.sf.bigscreen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sf.bigscreen.mapper.IDayWeatherMapper;
import com.sf.bigscreen.model.DayWeather;
import com.sf.bigscreen.service.IDayWeatherService;
import org.springframework.stereotype.Service;

/**
 * @Author: bi xuan
 * @Date: 2021/6/24 16:10
 * @Description:
 **/
@Service
public class DayWeatherServiceImpl extends ServiceImpl<IDayWeatherMapper, DayWeather> implements IDayWeatherService {
}
