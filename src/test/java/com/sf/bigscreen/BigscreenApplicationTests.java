package com.sf.bigscreen;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BigscreenApplicationTests {

    @Test
    public void contextLoads() {
        JSONArray objects = new JSONArray();
        String a = "{\"success\":true,\"code\":200,\"total\":null,\"message\":\"修改路由列表成功\",\"data\":200}";
        JSONObject jsonObject = new JSONObject(a);
//        jsonObject.set("name","byc");
        objects.add(jsonObject);
        System.out.println(jsonObject);
    }



}
