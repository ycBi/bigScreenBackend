package com.sf.bigscreen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sf.bigscreen.model.WaterType;

/**
 * @Author: bi xuan
 * @Date: 2021/8/5 8:57
 * @Description:
 **/
public interface IWaterTypeService extends IService<WaterType> {

    /**
     * 向waterType表中插入数据
     * @return
     */
    void insertWaterTypeData();
}
