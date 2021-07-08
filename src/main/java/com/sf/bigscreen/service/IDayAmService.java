package com.sf.bigscreen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sf.bigscreen.model.DayAm;

/**
 * @Author: bi xuan
 * @Date: 2021/6/24 15:57
 * @Description:
 **/
public interface IDayAmService extends IService<DayAm> {
    /**
     * 同步当天的全校的电量信息
     * @return
     */
    Integer addOrUpdateDayAmAllInfo();

    /**
     * 同步当天的全校的区域信息
     * @return
     */
    Integer addOrUpdateDayAmAreaInfo();

    /**
     * 同步当天的部门的电量信息
     * @return
     */
    Integer addOrUpdateDayAmDepartmentInfo();


    /**
     * 同步当天的建筑的电量信息
     * @return
     */
    Integer addOrUpdateDayAmArcInfo();

    /**
     * 同步当天的单表的电量信息
     * @return
     */
    Integer addOrUpdateDayAmInfo();

    /**
     * 同步时间段内的全校电量信息
     * @return
     */
    Integer addOrUpdateDayAmAllInfoRangeTime();

    /**
     * 同步时间段的区域电量信息
     * @return
     */
    Integer addOrUpdateDayAmAreaInfoRangeTime();

    /**
     * 获取时间段的部门下的用电信息
     * @return
     */
    Integer addOrUpdateDayAmDepartmentInfoRangeTime();

    /**
     * 同步时间段下的建筑的电量信息
     * @return
     */
    Integer addOrUpdateDayAmArcInfoRangeTime();

    /**
     * 同步时间段下的电表的用电信息
     * @return
     */
    Integer addOrUpdateDayAmInfoRangeTime();

}
