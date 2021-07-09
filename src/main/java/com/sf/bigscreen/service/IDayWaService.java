package com.sf.bigscreen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sf.bigscreen.model.DayWa;

/**
 * @Author: bi xuan
 * @Date: 2021/7/9 8:42
 * @Description:
 **/
public interface IDayWaService extends IService<DayWa> {
    /**
     * 同步当天的全校的水量信息
     * @return
     */
    Integer addOrUpdateDayWaAllInfo();

    /**
     * 同步当天的全校的区域信息
     * @return
     */
    Integer addOrUpdateDayWaAreaInfo();

    /**
     * 同步当天的部门的水量信息
     * @return
     */
    Integer addOrUpdateDayWaDepartmentInfo();


    /**
     * 同步当天的建筑的水量信息
     * @return
     */
    Integer addOrUpdateDayWaArcInfo();

    /**
     * 同步当天的单表的水量信息
     * @return
     */
    Integer addOrUpdateDayWaInfo();

    /**
     * 同步时间段内的全校水量信息
     * @return
     */
    Integer addOrUpdateDayWaAllInfoRangeTime();

    /**
     * 同步时间段的区域水量信息
     * @return
     */
    Integer addOrUpdateDayWaAreaInfoRangeTime();

    /**
     * 获取时间段的部门下的用水信息
     * @return
     */
    Integer addOrUpdateDayWaDepartmentInfoRangeTime();

    /**
     * 同步时间段下的建筑的水量信息
     * @return
     */
    Integer addOrUpdateDayWaArcInfoRangeTime();

    /**
     * 同步时间段下的水表的用水信息
     * @return
     */
    Integer addOrUpdateDayWaInfoRangeTime();
}
