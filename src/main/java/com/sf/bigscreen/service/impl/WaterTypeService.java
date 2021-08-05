package com.sf.bigscreen.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sf.bigscreen.mapper.WaterTypeMapper;
import com.sf.bigscreen.model.WaterType;
import com.sf.bigscreen.service.IWaterTypeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: bi xuan
 * @Date: 2021/8/5 8:59
 * @Description:
 **/
@Log4j2
@Service
public class WaterTypeService extends ServiceImpl<WaterTypeMapper, WaterType> implements IWaterTypeService {
    @Resource
    private WaterTypeMapper waterTypeMapper;

    @Value("${third.url}")
    private String baseThirdUrl;

    /**
     * 向waterType表中插入数据
     * @return
     */
    @Override
    public void insertWaterTypeData() {
        String finalUrl = new StringBuilder(baseThirdUrl).append("/K501/getWaterUserStyleDataFullYear?year=2021").toString();
        String body = HttpRequest.get(finalUrl).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        Double teachValue = (Double)jsonObject.get("teachValue");
        Double commonValue = (Double)jsonObject.get("commonValue");
        Double lifeValue = (Double)jsonObject.get("lifeValue");
        Double businessValue = (Double)jsonObject.get("businessValue");
        WaterType waterType = new WaterType();
        waterType.setWaterUseProperties("教学用水");
        waterType.setTotal(teachValue);
        WaterType waterType1 = new WaterType();
        waterType1.setWaterUseProperties("公共用水");
        waterType1.setTotal(commonValue);
        WaterType waterType2 = new WaterType();
        waterType2.setWaterUseProperties("生活用水");
        waterType2.setTotal(lifeValue);
        WaterType waterType3 = new WaterType();
        waterType3.setWaterUseProperties("商业用水");
        waterType3.setTotal(businessValue);
        List<WaterType> waterTypes = new ArrayList<>();
        waterTypes.add(waterType);
        waterTypes.add(waterType1);
        waterTypes.add(waterType2);
        waterTypes.add(waterType3);
        int insert = waterTypeMapper.insert(waterType);
        log.info("Insert: "+insert+"  值为1：代表插入成功；值为0代表插入失败");
        int insert1 = waterTypeMapper.insert(waterType1);
        log.info("Insert: "+insert1+" 值为1：代表插入成功；值为0代表插入失败");
        int insert2 = waterTypeMapper.insert(waterType2);
        log.info("insert: "+insert2+" 值为1：代表插入成功；值为0代表插入失败");
        int insert3 = waterTypeMapper.insert(waterType3);
        log.info("insert: "+insert3+" 值为1：代表插入成功；值为0代表插入失败");
    }
}
