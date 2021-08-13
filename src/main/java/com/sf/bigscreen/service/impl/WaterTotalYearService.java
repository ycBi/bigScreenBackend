package com.sf.bigscreen.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sf.bigscreen.mapper.WaterTotalYearMapper;
import com.sf.bigscreen.model.WaterTotalYear;
import com.sf.bigscreen.service.IWaterTotalYearService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: bi xuan
 * @Date: 2021/8/5 8:55
 * @Description:
 **/
@Log4j2
@Service
public class WaterTotalYearService extends ServiceImpl<WaterTotalYearMapper, WaterTotalYear> implements IWaterTotalYearService {

    @Value("${third.url}")
    private String baseThirdUrl;

    @Resource
    private WaterTotalYearMapper waterTotalYearMapper;

    /**
     * 往waterYearInfo表中插入信息
     */
    @Override
    public void insertWaterYearInfo() {
        String finalUrl = new StringBuilder(baseThirdUrl).append("/area/getAreaDayGrossByMonth").toString();
        String body = HttpRequest.get(finalUrl).execute().body();
        JSONArray jsonArray = JSONUtil.parseArray(body);
        for (Object o : jsonArray) {
            JSONObject jsonObject = JSONUtil.parseObj(o);
            Integer selectYear = (Integer) jsonObject.get("selectYear");
            Integer selectMonth = (Integer) jsonObject.get("selectMonth");
            Double gross =Double.valueOf(jsonObject.get("gross").toString());

            WaterTotalYear waterTotalYear = new WaterTotalYear();
            waterTotalYear.setYear(selectYear);
            waterTotalYear.setMonth(selectMonth);
            waterTotalYear.setTotal(gross);
            int insert = waterTotalYearMapper.insert(waterTotalYear);
            log.info("waterTotalYear表记录插入结果(0:失败 1：成功):" + insert);
        }
    }
}
