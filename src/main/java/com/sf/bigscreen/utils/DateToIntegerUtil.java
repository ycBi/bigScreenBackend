package com.sf.bigscreen.utils;

import cn.hutool.core.date.DateUtil;
import io.swagger.models.auth.In;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * @Author: bi xuan
 * @Date: 2021/7/8 15:04
 * @Description:将日期类型数据转为整数：例如：2021-07-12 => 20210712
 **/
public class DateToIntegerUtil {
    public static Integer dateTransferInteger(String date){
        if (!Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$",date)){
            Assert.notNull(date,"日期格式不对");
        }
        String[] start = date.split("-");
        Integer yearStart = Integer.valueOf(start[0]);
        Integer monthStart = Integer.valueOf(start[1].replaceAll("^[0]", ""));
        Integer dayStart = Integer.valueOf(start[2].replaceAll("^[0]", ""));
        //转为数字格式放到数据库判断：eg： 2021-06-23 ----> 20210623
        Integer dateStartNumber = yearStart*10000+monthStart*100+dayStart;
        return dateStartNumber;
    }

    public static Integer dateTransferInteger(Date date){
        String format = DateUtil.format(date, "yyyy-MM-dd");
        String[] start = format.split("-");
        Integer yearStart = Integer.valueOf(start[0]);
        Integer monthStart = Integer.valueOf(start[1].replaceAll("^[0]", ""));
        Integer dayStart = Integer.valueOf(start[2].replaceAll("^[0]", ""));
        //转为数字格式放到数据库判断：eg： 2021-06-23 ----> 20210623
        Integer dateStartNumber = yearStart*10000+monthStart*100+dayStart;
        return dateStartNumber;
    }
}
