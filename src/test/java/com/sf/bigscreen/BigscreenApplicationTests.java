package com.sf.bigscreen;

import com.sf.bigscreen.quorta.WaterTotalInfoSchedule;
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

    @Autowired
    private WaterTotalInfoSchedule waterTotalInfoSchedule;

    @Test
    public void contextLoads() {
        waterTotalInfoSchedule.updateWaterTotalYear();
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
