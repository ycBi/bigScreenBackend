package com.sf.bigscreen.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sf.bigscreen.mapper.WaterTotalMonthMapper;
import com.sf.bigscreen.mapper.WaterTotalNightMonthMapper;
import com.sf.bigscreen.model.WaterTotalMonth;
import com.sf.bigscreen.model.WaterTotalNightMonth;
import com.sf.bigscreen.service.IWaterTotalMonthService;
import com.sf.bigscreen.service.IWaterTotalNightMonthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: bi xuan
 * @Date: 2021/8/5 8:48
 * @Description:
 **/
@Slf4j
@Service
public class WaterTotalNightMonthService extends ServiceImpl<WaterTotalNightMonthMapper, WaterTotalNightMonth> implements IWaterTotalNightMonthService {
    @Value("${third.url}")
    private String baseThirdUrl;

    @Resource
    private WaterTotalNightMonthMapper waterTotalNightMonthMapper;

    /**
     * 往waterTotalNightMonthInfo表中插入信息
     */
    @Override
    public void insertWaterNightMonthInfo() {
        String finalUrl = new StringBuilder(baseThirdUrl).append("/area/getAreaDayNightGross").toString();
        String body = HttpRequest.get(finalUrl).execute().body();
        JSONArray jsonArray = JSONUtil.parseArray(body);
        for (Object o : jsonArray) {
            JSONObject jsonObject = JSONUtil.parseObj(o);
            Integer selectYear = (Integer) jsonObject.get("selectYear");
            Integer selectMonth = (Integer) jsonObject.get("selectMonth");
            Integer selectDay = (Integer) jsonObject.get("selectDay");
            Double gross =Double.valueOf(jsonObject.get("gross").toString());

            WaterTotalNightMonth waterTotalNightMonth = new WaterTotalNightMonth();
            waterTotalNightMonth.setYear(selectYear);
            waterTotalNightMonth.setMonth(selectMonth);
            waterTotalNightMonth.setDay(selectDay);
            waterTotalNightMonth.setTotal(gross);
            int insert = waterTotalNightMonthMapper.insert(waterTotalNightMonth);
            log.info("waterTotalNightMonth表记录插入结果(0:失败 1：成功):" + insert);
        }
    }
}
