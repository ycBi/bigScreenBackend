package com.sf.bigscreen.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.sf.bigscreen.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: bi xuan
 * @Date: 2021/7/12 10:40
 * @Description:
 **/
@Api
@RestController
@RequestMapping("/dayWeather")
public class DayWeatherController {
    @Value("${weather.key}")
    private String key;

    @Value("${weather.url}")
    private String url;

    @Value("${weather.location}")
    private String location;

    @ApiModelProperty("获取实时天气数据的接口")
    @GetMapping("/getWeatherInfo")
    public Result getWeatherInfo(){
        String url = new StringBuilder(this.url).append("?location=").append(location).append("&key=").append(key).toString();
        String body = HttpRequest.get(url).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        if (!"200".equals(jsonObject.get("code"))){
            return Result.buildFailedMessage("数据获取异常",(Integer) jsonObject.get("code"));
        }
        Object now = jsonObject.get("now");
        JSONObject res = JSONUtil.parseObj(now);
        return Result.buildSuccessData(res);
    }

    @ApiModelProperty("获取地区的location code")
    @GetMapping("/getLocationCode")
    public Result getLocationCode(@RequestParam("areaName") String areaName){
        String locationUrl = new StringBuilder("https://geoapi.qweather.com/v2/city/lookup?location=").append(areaName).append("&key=").append(key).toString();
        String body = HttpRequest.get(locationUrl).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        if (!"200".equals(jsonObject.get("code"))){
            return Result.buildFailedMessage("数据获取异常",(Integer) jsonObject.get("code"));
        }
        Object location = jsonObject.get("location");
        JSONArray res = JSONUtil.parseArray(location);
        Object id = JSONUtil.parseObj(res.get(0)).get("id");
        return Result.buildSuccessData(id);
    }
}
