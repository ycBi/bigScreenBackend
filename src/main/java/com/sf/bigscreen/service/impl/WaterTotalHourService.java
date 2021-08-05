package com.sf.bigscreen.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sf.bigscreen.mapper.WaterTotalHourMapper;
import com.sf.bigscreen.model.WaterTotalHour;
import com.sf.bigscreen.service.IWaterTotalHourService;
import org.springframework.stereotype.Service;

/**
 * @Author: bi xuan
 * @Date: 2021/8/5 8:39
 * @Description:
 **/
@Service
public class WaterTotalHourService extends ServiceImpl<WaterTotalHourMapper, WaterTotalHour> implements IWaterTotalHourService {
}
