package com.sf.bigscreen.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sf.bigscreen.mapper.AreaMapper;
import com.sf.bigscreen.model.Area;
import com.sf.bigscreen.service.IAreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: bi xuan
 * @Date: 2021/8/7 9:29
 * @Description:
 **/
@Slf4j
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements IAreaService {

    @Value("${third.url}")
    private String baseThirdUrl;

    @Resource
    private AreaMapper areaMapper;

    /**
     * 同步区域信息过来
     */
    @Override
    public void setAreaData(){
        String finalUrl = new StringBuilder(baseThirdUrl).append("/area/getAllAreas").toString();
        String body = HttpRequest.get(finalUrl).execute().body();
        JSONArray jsonArray = JSONUtil.parseArray(body);
        for (Object o : jsonArray) {
            JSONObject jsonObject = JSONUtil.parseObj(o);
            Integer areaID = (Integer)jsonObject.get("areaID");
            String areaName = jsonObject.get("areaName").toString();
            Integer superiorAreaID = (Integer) jsonObject.get("superiorAreaID");

            Area area = new Area();
            area.setAreaId(areaID);
            area.setAreaName(areaName);
            area.setParent(superiorAreaID);

            int insert = areaMapper.insert(area);
            log.info("Area表记录插入结果(0:失败 1：成功):"+ insert);
        }
    }
}


