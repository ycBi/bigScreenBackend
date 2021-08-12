package com.sf.bigscreen.vo;

import lombok.Data;

/**
 * @Author: bi xuan
 * @Date: 2021/8/11 15:22
 * @Description:
 **/

@Data
public class AreaVo {

    private Integer areaID;

    private String areaName;

    private String areaNum;

    private String DLModeFileAddress;

    private String JGModeFileAddress;

    private Integer SuperiorAreaID;

    private String AreaList;

    private Integer mState;

    private Double regionArea;

    private Integer peopleNumber;

    private Double waterQuota;

    private Double pressureMaxValue;

    private Double pressureMinValue;

    private Double pressureAvgValue;

    private Double flowMaxValue;

    private Double flowMinValue;

    private Double flowAvgValue;

    private Double dailyGrossMaxValue;

    private Double dailyGrossMinValue;

    private Double dailyGrossAvgValue;

    private Double nightFlowMinValue;

    private Double targetLeakageRate;

    private Double leakageRateOneLevelWarnValue;

    private Double leakageRateTwoLevelWarnValue;

    private Double doneTimelyRate;

}

