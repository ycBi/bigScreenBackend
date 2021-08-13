package com.sf.bigscreen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sf.bigscreen.model.WaterTotalNightMonth;

/**
 * @Author: bi xuan
 * @Date: 2021/8/5 8:47
 * @Description:
 **/
public interface IWaterTotalNightMonthService extends IService<WaterTotalNightMonth> {
    /**
     * 往waterTotalNightMonthInfo表中插入信息
     */
    void insertWaterNightMonthInfo();
}
