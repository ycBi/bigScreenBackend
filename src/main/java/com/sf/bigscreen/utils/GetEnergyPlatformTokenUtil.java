package com.sf.bigscreen.utils;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: bi xuan
 * @Date: 2021/7/8 8:52
 * @Description:拿到第三方平台（能源监管平台的token）
 **/

@Component
public class GetEnergyPlatformTokenUtil {

    @Value("${third.url}")
    private  String baseThirdUrl;

    public  String getEnergyToken(){
        //拿到能源监管平台的access token
        StringBuilder builder1 = new StringBuilder(baseThirdUrl);
        String s1 = builder1.append("/plat/auth/oauth/token").toString();
        String body = HttpRequest.post(s1).form("username", "admin")
                .form("password", "123").form("validateCode", "0").form("grant_type", "password")
                .form("client_id", "web").form("client_secret", "123").execute().body();
        JSONObject jsonObject = JSONObject.parseObject(body);
        String access_token = (String)jsonObject.get("access_token");
        return access_token;
    }
}
