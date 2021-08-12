package com.sf.bigscreen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sf.bigscreen.model.Area;

/**
 * @Author: bi xuan
 * @Date: 2021/8/7 9:29
 * @Description:
 **/
public interface IAreaService  extends IService<Area> {

    /**
     * 同步区域信息过来
     */
    void setAreaData();
}
