package com.sf.bigscreen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sf.bigscreen.model.WaterTotalYear;

/**
 * @Author: bi xuan
 * @Date: 2021/8/5 8:54
 * @Description:
 **/
public interface IWaterTotalYearService extends IService<WaterTotalYear> {
    /**
     * 往waterYearInfo表中插入信息
     */
    void insertWaterYearInfo();
}
