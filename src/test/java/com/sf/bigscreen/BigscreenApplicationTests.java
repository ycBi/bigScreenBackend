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

import java.util.*;
import java.util.regex.Pattern;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BigscreenApplicationTests {
    @Autowired
    private GetEnergyPlatformTokenUtil getEnergyPlatformTokenUtil;

    @Test
    public void contextLoads() {
//        Object a = null;
//        if (a.toString().equals("null")){
//            System.out.println("111");
//        }
//        Object a = null;
//        String s = Optional.ofNullable(a).orElse("1").toString();
//        System.out.println(s);
//        HashSet<String> tmp = new HashSet<>();
//
//        boolean add = Optional.ofNullable(tmp).orElse(new HashSet<>()).add("1111");
//
        HashMap<String, HashSet<String>> stringHashSetHashMap = new HashMap<>();
        HashSet<String> strings = new HashSet<>();
        strings.add("byc");
        strings.add("hy");
        strings.add("xq");
        HashSet<String> strings1 = new HashSet<>();
        strings1.add("bx");
        strings1.add("xmw");
        strings1.add("jsz");
        stringHashSetHashMap.put("first",strings);
        stringHashSetHashMap.put("second",strings1);
        addValue(stringHashSetHashMap,"yp","ypp");
    }

    private static void addValue(Map<String,HashSet<String>> map, String key,String value){
        HashSet<String> strings1 = map.computeIfAbsent(key, (k) -> new HashSet<>());
        strings1.add(value);
    }

    @Test
    public void getToken(){
        String energyToken = getEnergyPlatformTokenUtil.getEnergyToken();
        System.out.println(energyToken);

        ArrayList<String> strings = new ArrayList<>();
//        strings.forEach(x->{
//            if ("byc".equals(x)){
//                break;
//            }
//        });
    }



}
