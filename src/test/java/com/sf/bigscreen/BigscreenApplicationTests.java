package com.sf.bigscreen;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.sf.bigscreen.utils.GetEnergyPlatformTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.regex.Pattern;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BigscreenApplicationTests {
    @Autowired
    private GetEnergyPlatformTokenUtil getEnergyPlatformTokenUtil;

    @Test
    public void contextLoads() {
        Object a = null;
        if (a.toString().equals("null")){
            System.out.println("111");
        }
    }

    @Test
    public void getToken(){
        String energyToken = getEnergyPlatformTokenUtil.getEnergyToken();
        System.out.println(energyToken);
    }



}
