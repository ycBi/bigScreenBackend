package com.sf.bigscreen.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sf.bigscreen.mapper.WaterTotalMonthMapper;
import com.sf.bigscreen.mapper.WaterTotalYearMapper;
import com.sf.bigscreen.model.WaterTotalMonth;
import com.sf.bigscreen.model.WaterTotalYear;
import com.sf.bigscreen.service.IWaterTotalMonthService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: bi xuan
 * @Date: 2021/8/5 8:45
 * @Description:
 **/
@Log4j2
@Service
public class WaterTotalMonthService extends ServiceImpl<WaterTotalMonthMapper, WaterTotalMonth> implements IWaterTotalMonthService {
    @Value("${third.url}")
    private String baseThirdUrl;

    @Resource
    private WaterTotalMonthMapper waterTotalMonthMapper;

    /**
     * 往waterMonthInfo表中插入信息
     */
    @Override
    public void insertWaterMonthInfo() {
        String finalUrl = new StringBuilder(baseThirdUrl).append("/area/getAreaDayGrossByDay").toString();
        String body = HttpRequest.get(finalUrl).execute().body();
        JSONArray jsonArray = JSONUtil.parseArray(body);
        for (Object o : jsonArray) {
            JSONObject jsonObject = JSONUtil.parseObj(o);
            Integer selectYear = (Integer) jsonObject.get("selectYear");
            Integer selectMonth = (Integer) jsonObject.get("selectMonth");
            Integer selectDay = (Integer) jsonObject.get("selectDay");
            Double gross =Double.valueOf(jsonObject.get("gross").toString());

            WaterTotalMonth waterTotalMonth = new WaterTotalMonth();
            waterTotalMonth.setYear(selectYear);
            waterTotalMonth.setMonth(selectMonth);
            waterTotalMonth.setDay(selectDay);
            waterTotalMonth.setTotal(gross);
            int insert = waterTotalMonthMapper.insert(waterTotalMonth);
            log.info("waterTotalMonth表记录插入结果(0:失败 1：成功):" + insert);
        }
    }
}
