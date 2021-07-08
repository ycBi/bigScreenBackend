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
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    @Test
    public void contextLoads() {
//        String body = HttpRequest.get("http://192.168.7.151:9090/plat/auth/user/getModulePathList").header("JSESSIONID", "C956B6F587E747DE691688EE46563C39")
//                .header("Authorization", "760e66eb-01bb-45b1-920d-bde93f480a25").header("token_type", "bearer")
//                .execute().body();
//        System.out.println(body);
//        String body = HttpRequest.post("http://192.168.7.151:9090/plat/auth/oauth/token").form("username", "admin")
//                .form("password", "123").form("validateCode", "0").form("grant_type", "password")
//                .form("client_id", "web").form("client_secret", "123").execute().body();
//        System.out.println(body);
//        JSONObject jsonObject = new JSONObject(body);
//        String access_token = (String)jsonObject.get("access_token");
//        System.out.println(access_token);
//
//        String body1 = HttpRequest.get("http://192.168.7.219:9000/plat/auth/subsystem/getModule?token=ed3b0701-49af-4e9d-8590-2d9b384ccb5d&projectCode=6000").execute().body();
//        System.out.println(body1);
//        JSONObject routerListJson = new JSONObject(body1);
//        Object data = routerListJson.get("data");
//        JSONObject jsonObject1 = new JSONObject(data);
//        Object powerInfo = jsonObject1.get("powerInfo");
//        JSONArray objects = new JSONArray(powerInfo);
//        System.out.println(objects);
//        JSONArray objects = new JSONArray();
//        String a = "{\"success\":true,\"code\":200,\"total\":null,\"message\":\"修改路由列表成功\",\"data\":200}";
//        JSONObject jsonObject = new JSONObject();
//        ArrayList<String> strings = new ArrayList<>();
//        strings.add("byc");
//        strings.add("xq");
//        strings.add("hy");
//        jsonObject.set("name",strings);
//        objects.add(jsonObject);
//        System.out.println(jsonObject);
//        System.out.println(objects);

        DateTime yesterday = DateUtil.yesterday();
        System.out.println(yesterday);
        String format = DateUtil.format(yesterday, "yyyy-MM-dd");
        System.out.println(format);
        String s="1244";
        System.out.println(s.replaceAll("^[0]", ""));
        if (Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$",format)){
            System.out.println("rrrr");

        }
//
//        LinkedHashSet<String> strings = DateUtil.yearAndQuarter(DateUtil.parseDate("2021-05-10"), DateUtil.parseDate("2021-06-06"));
//        System.out.println(strings);
        String authorization = HttpRequest.get("http://192.168.7.219:9000/plat/api/dayAm/getDayAmAllInfoByTimeRange?dateStart=2021-03-28&dateEnd=2021-04-28")
                .header("Authorization", "33fc2a2f-cab7-4102-b57d-572179f40a91").execute().body();
                JSONObject jsonObject = JSONUtil.parseObj(authorization);
        Object data = jsonObject.get("data");
        JSONArray dataJsonArray = JSONUtil.parseArray(data);
        dataJsonArray.forEach(x-> System.out.println(x));
        dataJsonArray.forEach(x->{
            JSONObject jsonObject1 = JSONUtil.parseObj(x);
            System.out.println(jsonObject1.get("zgross"));
        });
    }



}
