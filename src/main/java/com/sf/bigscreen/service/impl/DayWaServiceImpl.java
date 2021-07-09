package com.sf.bigscreen.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sf.bigscreen.mapper.IDayWaMapper;
import com.sf.bigscreen.mapper.ITableConfigMapper;
import com.sf.bigscreen.model.DayWa;
import com.sf.bigscreen.model.TableConfig;
import com.sf.bigscreen.service.IDayWaService;
import com.sf.bigscreen.utils.DateToIntegerUtil;
import com.sf.bigscreen.utils.GetEnergyPlatformTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @Author: bi xuan
 * @Date: 2021/7/9 8:44
 * @Description:
 **/
@Service
public class DayWaServiceImpl extends ServiceImpl<IDayWaMapper, DayWa> implements IDayWaService {
    @Resource
    private IDayWaMapper iDayWaMapper;

    @Resource
    private ITableConfigMapper iTableConfigMapper;

    @Autowired
    private GetEnergyPlatformTokenUtil getEnergyPlatformTokenUtil;

    @Value("${third.url}")
    private String thirdUrl;

    /**
     * 同步当前天数的全校电量信息
     *
     * @return
     */
    @Override
    public Integer addOrUpdateDayWaAllInfo() {
        String accessToken = getToken();
        String today = DateUtil.today();
        StringBuilder builder = new StringBuilder(thirdUrl);
        String url = builder.append("/plat/api/dayWa/getDayWaAllInfo").append("?date=").append("2021-03-27").toString();
        String body = HttpRequest.get(url).header("Authorization", accessToken).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        Object data = jsonObject.get("data");
        JSONObject dataJson = JSONUtil.parseObj(data);
        LambdaQueryWrapper<DayWa> eq = new LambdaQueryWrapper<DayWa>().eq(DayWa::getTheDay, today);
        DayWa dayWaResult = iDayWaMapper.selectOne(eq);
        TableConfig tableConfig = iTableConfigMapper.selectOne(new LambdaQueryWrapper<TableConfig>().eq(TableConfig::getTableName, "daywa").eq(TableConfig::getInfoStyle, "All"));
        DayWa dayWa = new DayWa();
        dayWa.setInfoStyle(tableConfig.getInfoStyle());
        dayWa.setInfoIndex(tableConfig.getInfoIndex());
        dayWa.setInfoName(tableConfig.getInfoName());
        dayWa.setTheDay(DateUtil.parse(today, "yyyy-MM-dd"));
        if (dayWaResult == null) {
            //当天记录不存在，并且同步获取的数据也为空就设置默认值
            if (data == null) {
                dayWa.setZGross(0.0);
                dayWa.setWorkGross(0.0);
                dayWa.setUnWorkGross(0.0);
                dayWa.setWorkDay(true);
                int insert = iDayWaMapper.insert(dayWa);
                return insert;
            }
            dayWa.setZGross(Double.valueOf(dataJson.get("zgross").toString()));
            dayWa.setWorkGross(Double.valueOf(dataJson.get("workData").toString()));
            dayWa.setUnWorkGross(Double.valueOf(dataJson.get("unWorkData").toString()));
            Integer working_day_type = Integer.parseInt(dataJson.get("working_day_type").toString());
            dayWa.setWorkDay(working_day_type.equals(1));
            int insert = iDayWaMapper.insert(dayWa);
            return insert;
        } else {
            if (data != null) {
                dayWa.setZGross(Double.valueOf(dataJson.get("zgross").toString()));
                dayWa.setWorkGross(Double.valueOf(dataJson.get("workData").toString()));
                dayWa.setUnWorkGross(Double.valueOf(dataJson.get("unWorkData").toString()));
                Integer working_day_type = Integer.parseInt(dataJson.get("working_day_type").toString());
                dayWa.setWorkDay(working_day_type.equals(1));
                int update = iDayWaMapper.update(dayWa, new LambdaQueryWrapper<DayWa>().eq(DayWa::getTheDay, today));
                return update;
            }
            //数据库有当天的一条记录，但是新同步的数据集为空，保留之前的数据不做变化
            return 0;
        }
    }

    /**
     * 同步当前天数的区域电量信息
     *
     * @return
     */
    @Override
    public Integer addOrUpdateDayWaAreaInfo() {
        String accessToken = getToken();
        String today = DateUtil.today();
        TableConfig tableConfig = iTableConfigMapper.selectOne(new LambdaQueryWrapper<TableConfig>().eq(TableConfig::getTableName, "daywa").eq(TableConfig::getInfoStyle, "Area"));
        StringBuilder builder = new StringBuilder(thirdUrl);
        String url = builder.append("/plat/api/dayWa/getDayWaAreaInfo").append("?date=").append("2021-03-27").append("&id=").append(tableConfig.getInfoIndex()).toString();
        String body = HttpRequest.get(url).header("Authorization", accessToken).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        Object data = jsonObject.get("data");
        JSONObject dataJson = JSONUtil.parseObj(data);
        LambdaQueryWrapper<DayWa> eq = new LambdaQueryWrapper<DayWa>().eq(DayWa::getTheDay, today);
        DayWa dayWaResult = iDayWaMapper.selectOne(eq);
        DayWa dayWa = new DayWa();
        dayWa.setInfoStyle(tableConfig.getInfoStyle());
        dayWa.setInfoIndex(tableConfig.getInfoIndex());
        dayWa.setInfoName(tableConfig.getInfoName());
        dayWa.setTheDay(DateUtil.parse(today, "yyyy-MM-dd"));
        if (dayWaResult == null) {
            //当天记录不存在，并且同步获取的数据也为空就设置默认值
            if (data == null) {
                dayWa.setZGross(0.0);
                dayWa.setWorkGross(0.0);
                dayWa.setUnWorkGross(0.0);
                dayWa.setWorkDay(true);
                int insert = iDayWaMapper.insert(dayWa);
                return insert;
            }
            dayWa.setZGross(Double.valueOf(dataJson.get("zgross").toString()));
            dayWa.setWorkGross(Double.valueOf(dataJson.get("workData").toString()));
            dayWa.setUnWorkGross(Double.valueOf(dataJson.get("unWorkData").toString()));
            Integer working_day_type = Integer.parseInt(dataJson.get("working_day_type").toString());
            dayWa.setWorkDay(working_day_type.equals(1));
            int insert = iDayWaMapper.insert(dayWa);
            return insert;
        } else {
            if (data != null) {
                dayWa.setZGross(Double.valueOf(dataJson.get("zgross").toString()));
                dayWa.setWorkGross(Double.valueOf(dataJson.get("workData").toString()));
                dayWa.setUnWorkGross(Double.valueOf(dataJson.get("unWorkData").toString()));
                Integer working_day_type = Integer.parseInt(dataJson.get("working_day_type").toString());
                dayWa.setWorkDay(working_day_type.equals(1));
                int update = iDayWaMapper.update(dayWa, new LambdaQueryWrapper<DayWa>().eq(DayWa::getTheDay, today));
                return update;
            }
            //数据库有当天的一条记录，但是新同步的数据集为空，保留之前的数据不做变化
            return 0;
        }
    }

    /**
     * 获取当前天数的部门的用电信息
     *
     * @return
     */
    @Override
    public Integer addOrUpdateDayWaDepartmentInfo() {
        String accessToken = getToken();
        String today = DateUtil.today();
        TableConfig tableConfig = iTableConfigMapper.selectOne(new LambdaQueryWrapper<TableConfig>().eq(TableConfig::getTableName, "daywa").eq(TableConfig::getInfoStyle, "Part"));
        StringBuilder builder = new StringBuilder(thirdUrl);
        String url = builder.append("/plat/api/dayWa/getDayWaDepartmentInfo").append("?date=").append("2021-03-27").append("&id=").append(tableConfig.getInfoIndex()).toString();
        String body = HttpRequest.get(url).header("Authorization", accessToken).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        Object data = jsonObject.get("data");
        JSONObject dataJson = JSONUtil.parseObj(data);
        LambdaQueryWrapper<DayWa> eq = new LambdaQueryWrapper<DayWa>().eq(DayWa::getTheDay, today);
        DayWa dayWaResult = iDayWaMapper.selectOne(eq);
        DayWa dayWa = new DayWa();
        dayWa.setInfoStyle(tableConfig.getInfoStyle());
        dayWa.setInfoIndex(tableConfig.getInfoIndex());
        dayWa.setInfoName(tableConfig.getInfoName());
        dayWa.setTheDay(DateUtil.parse(today, "yyyy-MM-dd"));
        if (dayWaResult == null) {
            //当天记录不存在，并且同步获取的数据也为空就设置默认值
            if (data == null) {
                dayWa.setZGross(0.0);
                dayWa.setWorkGross(0.0);
                dayWa.setUnWorkGross(0.0);
                dayWa.setWorkDay(true);
                int insert = iDayWaMapper.insert(dayWa);
                return insert;
            }
            dayWa.setZGross(Double.valueOf(dataJson.get("zgross").toString()));
            dayWa.setWorkGross(Double.valueOf(dataJson.get("workData").toString()));
            dayWa.setUnWorkGross(Double.valueOf(dataJson.get("unWorkData").toString()));
            Integer working_day_type = Integer.parseInt(dataJson.get("working_day_type").toString());
            dayWa.setWorkDay(working_day_type.equals(1));
            int insert = iDayWaMapper.insert(dayWa);
            return insert;
        } else {
            if (data != null) {
                dayWa.setZGross(Double.valueOf(dataJson.get("zgross").toString()));
                dayWa.setWorkGross(Double.valueOf(dataJson.get("workData").toString()));
                dayWa.setUnWorkGross(Double.valueOf(dataJson.get("unWorkData").toString()));
                Integer working_day_type = Integer.parseInt(dataJson.get("working_day_type").toString());
                dayWa.setWorkDay(working_day_type.equals(1));
                int update = iDayWaMapper.update(dayWa, new LambdaQueryWrapper<DayWa>().eq(DayWa::getTheDay, today));
                return update;
            }
            //数据库有当天的一条记录，但是新同步的数据集为空，保留之前的数据不做变化
            return 0;
        }
    }

    /**
     * 同步当前天数的建筑下的电量信息
     *
     * @return
     */
    @Override
    public Integer addOrUpdateDayWaArcInfo() {
        String accessToken = getToken();
        String today = DateUtil.today();
        TableConfig tableConfig = iTableConfigMapper.selectOne(new LambdaQueryWrapper<TableConfig>().eq(TableConfig::getTableName, "daywa").eq(TableConfig::getInfoStyle, "Arc"));
        StringBuilder builder = new StringBuilder(thirdUrl);
        String url = builder.append("/plat/api/dayWa/getDayWaArcInfo").append("?date=").append("2021-03-27").append("&id=").append(tableConfig.getInfoIndex()).toString();
        String body = HttpRequest.get(url).header("Authorization", accessToken).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        Object data = jsonObject.get("data");
        JSONObject dataJson = JSONUtil.parseObj(data);
        LambdaQueryWrapper<DayWa> eq = new LambdaQueryWrapper<DayWa>().eq(DayWa::getTheDay, today);
        DayWa dayWaResult = iDayWaMapper.selectOne(eq);
        DayWa dayWa = new DayWa();
        dayWa.setInfoStyle(tableConfig.getInfoStyle());
        dayWa.setInfoIndex(tableConfig.getInfoIndex());
        dayWa.setInfoName(tableConfig.getInfoName());
        dayWa.setTheDay(DateUtil.parse(today, "yyyy-MM-dd"));
        if (dayWaResult == null) {
            //当天记录不存在，并且同步获取的数据也为空就设置默认值
            if (data == null) {
                dayWa.setZGross(0.0);
                dayWa.setWorkGross(0.0);
                dayWa.setUnWorkGross(0.0);
                dayWa.setWorkDay(true);
                return iDayWaMapper.insert(dayWa);
            }
            dayWa.setZGross(Double.valueOf(dataJson.get("zgross").toString()));
            dayWa.setWorkGross(Double.valueOf(dataJson.get("workData").toString()));
            dayWa.setUnWorkGross(Double.valueOf(dataJson.get("unWorkData").toString()));
            Integer working_day_type = Integer.parseInt(dataJson.get("working_day_type").toString());
            dayWa.setWorkDay(working_day_type.equals(1));
            return iDayWaMapper.insert(dayWa);
        } else {
            if (data != null) {
                dayWa.setZGross(Double.valueOf(dataJson.get("zgross").toString()));
                dayWa.setWorkGross(Double.valueOf(dataJson.get("workData").toString()));
                dayWa.setUnWorkGross(Double.valueOf(dataJson.get("unWorkData").toString()));
                Integer working_day_type = Integer.parseInt(dataJson.get("working_day_type").toString());
                dayWa.setWorkDay(working_day_type.equals(1));
                return iDayWaMapper.update(dayWa, new LambdaQueryWrapper<DayWa>().eq(DayWa::getTheDay, today));
            }
            //数据库有当天的一条记录，但是新同步的数据集为空，保留之前的数据不做变化
            return 0;
        }
    }

    @Override
    public Integer addOrUpdateDayWaInfo() {
        String accessToken = getToken();
        String today = DateUtil.today();
        TableConfig tableConfig = iTableConfigMapper.selectOne(new LambdaQueryWrapper<TableConfig>().eq(TableConfig::getTableName, "daywa").eq(TableConfig::getInfoStyle, "Meter"));
        StringBuilder builder = new StringBuilder(thirdUrl);
        String url = builder.append("/plat/api/dayWa/getDayWaInfo").append("?date=").append("2021-03-27").append("&id=").append(tableConfig.getInfoIndex()).toString();
        String body = HttpRequest.get(url).header("Authorization", accessToken).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        Object data = jsonObject.get("data");
        JSONObject dataJson = JSONUtil.parseObj(data);
        LambdaQueryWrapper<DayWa> eq = new LambdaQueryWrapper<DayWa>().eq(DayWa::getTheDay, today);
        DayWa dayWaResult = iDayWaMapper.selectOne(eq);
        DayWa dayWa = new DayWa();
        dayWa.setInfoStyle(tableConfig.getInfoStyle());
        dayWa.setInfoIndex(tableConfig.getInfoIndex());
        dayWa.setInfoName(tableConfig.getInfoName());
        dayWa.setTheDay(DateUtil.parse(today, "yyyy-MM-dd"));
        if (dayWaResult == null) {
            //当天记录不存在，并且同步获取的数据也为空就设置默认值
            if (data == null) {
                dayWa.setZGross(0.0);
                dayWa.setWorkGross(0.0);
                dayWa.setUnWorkGross(0.0);
                dayWa.setWorkDay(true);
                int insert = iDayWaMapper.insert(dayWa);
                return insert;
            }
            dayWa.setZGross(Double.valueOf(dataJson.get("zgross").toString()));
            dayWa.setWorkGross(Double.valueOf(dataJson.get("workData").toString()));
            dayWa.setUnWorkGross(Double.valueOf(dataJson.get("unWorkData").toString()));
            Integer working_day_type = Integer.parseInt(dataJson.get("working_day_type").toString());
            dayWa.setWorkDay(working_day_type.equals(1));
            int insert = iDayWaMapper.insert(dayWa);
            return insert;
        } else {
            if (data != null) {
                dayWa.setZGross(Double.valueOf(dataJson.get("zgross").toString()));
                dayWa.setWorkGross(Double.valueOf(dataJson.get("workData").toString()));
                dayWa.setUnWorkGross(Double.valueOf(dataJson.get("unWorkData").toString()));
                Integer working_day_type = Integer.parseInt(dataJson.get("working_day_type").toString());
                dayWa.setWorkDay(working_day_type.equals(1));
                int update = iDayWaMapper.update(dayWa, new LambdaQueryWrapper<DayWa>().eq(DayWa::getTheDay, today));
                return update;
            }
            //数据库有当天的一条记录，但是新同步的数据集为空，保留之前的数据不做变化
            return 0;
        }
    }

    /**
     * =================================================================================================================================
     * 同步时间段内的多条数据
     */

    /**
     * 同步时间段内的全校电量信息
     *
     * @return
     */
    @Override
    public Integer addOrUpdateDayWaAllInfoRangeTime() {
        String accessToken = getToken();
        String today = DateUtil.today();
        StringBuilder builder = new StringBuilder(thirdUrl);
        TableConfig tableConfig = iTableConfigMapper.selectOne(new LambdaQueryWrapper<TableConfig>().eq(TableConfig::getTableName, "daywa").eq(TableConfig::getInfoStyle, "All"));
        String url = builder.append("/plat/api/dayWater/getDayWaterAllInfoByTimeRange").append("?dateStart=").append(DateUtil.format(tableConfig.getStartDate(),"yyyy-MM-dd"))
                .append("&dateEnd=").append(DateUtil.format(tableConfig.getEndDate(),"yyyy-MM-dd")).toString();
        String body = HttpRequest.get(url).header("Authorization", accessToken).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        Object data = jsonObject.get("data");
        JSONArray dataJsonArray = JSONUtil.parseArray(data);
        LambdaQueryWrapper<DayWa> eq = new LambdaQueryWrapper<DayWa>().between(DayWa::getTheDay, tableConfig.getStartDate(), tableConfig.getEndDate());
        //根据tableConfig中配置的时间段来从dayWa表获取数据集
        List<DayWa> dayWaRes = iDayWaMapper.selectList(eq);
        List<DayWa> dayWas = new ArrayList<>();
        List<DateTime> dateTimes = DateUtil.rangeToList(tableConfig.getStartDate(), tableConfig.getEndDate(), DateField.DAY_OF_YEAR);
        List<DateTime> dates = new ArrayList<>();
        dateTimes.stream().forEach(x -> {
            DateTime parse = DateUtil.parse(DateUtil.format(x, "yyyy-MM-dd"));
            dates.add(parse);
        });
        dates.stream().forEach(x -> {
            DayWa dayWa = new DayWa();
            dayWa.setTheDay(x);
            dayWas.add(dayWa);
        });
        AtomicInteger result = new AtomicInteger();
        //目标数据库根据tableConfig配置的时间段获取的数据为空
        if (dayWaRes.isEmpty()) {
            //从源数据库获取的指定时间段的数据集为空
            if (dataJsonArray.isEmpty()) {
                dayWas.stream().forEach(x -> {
                    x.setInfoIndex(tableConfig.getInfoIndex());
                    x.setInfoStyle(tableConfig.getInfoStyle());
                    x.setInfoName(tableConfig.getInfoName());
                    x.setZGross(0.0);
                    x.setWorkGross(0.0);
                    x.setUnWorkGross(0.0);
                    x.setWorkDay(true);
                    result.set(iDayWaMapper.insert(x));
                });
            } else {
                dayWas.stream().forEach(y -> {
                    for (Object jsonArray: dataJsonArray
                    ) {
                        JSONObject jsonObject1 = JSONUtil.parseObj(jsonArray);
                        Integer dateInteger = Integer.valueOf(jsonObject1.get("selectYear").toString()) * 10000 + Integer.valueOf(jsonObject1.get("selectMonth").toString()) * 100 + Integer.valueOf(jsonObject1.get("selectDay").toString());
                        //当源数据库的数据和目标数据库的一行记录的时间相等，就把改行记录同步过来
                        if (DateToIntegerUtil.dateTransferInteger(y.getTheDay()).equals(dateInteger)) {
                            y.setInfoIndex(tableConfig.getInfoIndex());
                            y.setInfoStyle(tableConfig.getInfoStyle());
                            y.setInfoName(tableConfig.getInfoName());
                            y.setZGross(Double.valueOf(jsonObject1.get("zgross").toString()));
                            y.setWorkGross(Double.valueOf(jsonObject1.get("workData").toString()));
                            y.setUnWorkGross(Double.valueOf(jsonObject1.get("unWorkData").toString()));
                            Integer working_day_type = Integer.parseInt(jsonObject1.get("working_day_type").toString());
                            y.setWorkDay(working_day_type.equals(1));
                            result.set(iDayWaMapper.insert(y));
                            break;
                        }
                    }
                });
            }
        } else {
            //目标数据库在指定的时间段的结果不为空，但是可能存在数据不全的情况，例如同步要求是2021-06-20到2021-08-20,，但是目标数据库可能存在2021-07-01到2021-07-30
            //这里同步的情况可能比较复杂，存在一种情况，获取到的源数据可能在6-20到8-20中的某天假设7.1是数据为空（不存在），那么不是使用填充数据0代替，而是保持之前的同步数据不变
            //从源数据库获取的指定时间段的数据集为空
            if (dataJsonArray.isEmpty()) {
                List<Date> dateRes = dayWas.stream().map(x -> x.getTheDay()).collect(Collectors.toList());
                dayWaRes.stream().forEach(x -> {
                    boolean contains = dateRes.contains(x.getTheDay());
                    if (!contains) {
                        DayWa dayWa = new DayWa();
                        dayWa.setInfoIndex(tableConfig.getInfoIndex());
                        dayWa.setInfoStyle(tableConfig.getInfoStyle());
                        dayWa.setInfoName(tableConfig.getInfoName());
                        dayWa.setZGross(0.0);
                        dayWa.setWorkGross(0.0);
                        dayWa.setUnWorkGross(0.0);
                        dayWa.setWorkDay(true);
                        result.set(iDayWaMapper.insert(dayWa));
                    }
                });
            } else {
                //如果从数据源中获取的新数据日期和目标数据库中的数据一致就采用最新获取的日期数据进行替换
                dayWas.stream().forEach(y -> {
                    for (Object o : dataJsonArray
                    ) {
                        JSONObject jsonObject1 = JSONUtil.parseObj(o);
                        Integer dateInteger = Integer.parseInt(jsonObject1.get("selectYear").toString()) * 10000 + Integer.valueOf(jsonObject1.get("selectMonth").toString()) * 100 + Integer.valueOf(jsonObject1.get("selectDay").toString());
                        //当源数据库的数据和目标数据库的一行记录的时间相等，就把获取新的行记录同步更新过来
                        if (DateToIntegerUtil.dateTransferInteger(y.getTheDay()).equals(dateInteger)) {
                            y.setInfoIndex(tableConfig.getInfoIndex());
                            y.setInfoStyle(tableConfig.getInfoStyle());
                            y.setInfoName(tableConfig.getInfoName());
                            y.setZGross(Double.valueOf(jsonObject1.get("zgross").toString()));
                            y.setWorkGross(Double.valueOf(jsonObject1.get("workData").toString()));
                            y.setUnWorkGross(Double.valueOf(jsonObject1.get("unWorkData").toString()));
                            Integer working_day_type = Integer.parseInt(jsonObject1.get("working_day_type").toString());
                            y.setWorkDay(working_day_type.equals(1));
                            int update = iDayWaMapper.update(y, new LambdaQueryWrapper<DayWa>().eq(DayWa::getTheDay, y.getTheDay()));
                            //update为0，说明该同步日期目标数据库不存在
                            if (update==0){
                                result.set(iDayWaMapper.insert(y));
                                break;
                            }
                            result.set(update);
                            break;
                        }
                    }
                });
            }
        }
        return result.intValue();
    }

    /**
     * 同步时间段的区域电量信息
     *
     * @return
     */
    @Override
    public Integer addOrUpdateDayWaAreaInfoRangeTime() {
        String accessToken = getToken();
        String today = DateUtil.today();
        StringBuilder builder = new StringBuilder(thirdUrl);
        TableConfig tableConfig = iTableConfigMapper.selectOne(new LambdaQueryWrapper<TableConfig>().eq(TableConfig::getTableName, "daywa").eq(TableConfig::getInfoStyle, "Area"));
        String url = builder.append("/plat/api/dayWater/getDayWaterAllInfoByTimeRange").append("?dateStart=").append(DateUtil.format(tableConfig.getStartDate(),"yyyy-MM-dd"))
                .append("&dateEnd=").append(DateUtil.format(tableConfig.getEndDate(),"yyyy-MM-dd")).toString();
        String body = HttpRequest.get(url).header("Authorization", accessToken).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        Object data = jsonObject.get("data");
        JSONArray dataJsonArray = JSONUtil.parseArray(data);
        LambdaQueryWrapper<DayWa> eq = new LambdaQueryWrapper<DayWa>().between(DayWa::getTheDay, tableConfig.getStartDate(), tableConfig.getEndDate());
        //根据tableConfig中配置的时间段来从dayWa表获取数据集
        List<DayWa> dayWaRes = iDayWaMapper.selectList(eq);
        List<DayWa> dayWas = new ArrayList<>();
        List<DateTime> dateTimes = DateUtil.rangeToList(tableConfig.getStartDate(), tableConfig.getEndDate(), DateField.DAY_OF_YEAR);
        List<DateTime> dates = new ArrayList<>();
        dateTimes.stream().forEach(x -> {
            DateTime parse = DateUtil.parse(DateUtil.format(x, "yyyy-MM-dd"));
            dates.add(parse);
        });
        dates.stream().forEach(x -> {
            DayWa dayWa = new DayWa();
            dayWa.setTheDay(x);
            dayWas.add(dayWa);
        });
        AtomicInteger result = new AtomicInteger();
        //目标数据库根据tableConfig配置的时间段获取的数据为空
        if (dayWaRes.isEmpty()) {
            //从源数据库获取的指定时间段的数据集为空
            if (dataJsonArray.isEmpty()) {
                dayWas.stream().forEach(x -> {
                    x.setInfoIndex(tableConfig.getInfoIndex());
                    x.setInfoStyle(tableConfig.getInfoStyle());
                    x.setInfoName(tableConfig.getInfoName());
                    x.setZGross(0.0);
                    x.setWorkGross(0.0);
                    x.setUnWorkGross(0.0);
                    x.setWorkDay(true);
                    result.set(iDayWaMapper.insert(x));
                });
            } else {
                dayWas.stream().forEach(y -> {
                    for (Object jsonArray: dataJsonArray
                    ) {
                        JSONObject jsonObject1 = JSONUtil.parseObj(jsonArray);
                        Integer dateInteger = Integer.valueOf(jsonObject1.get("selectYear").toString()) * 10000 + Integer.valueOf(jsonObject1.get("selectMonth").toString()) * 100 + Integer.valueOf(jsonObject1.get("selectDay").toString());
                        //当源数据库的数据和目标数据库的一行记录的时间相等，就把改行记录同步过来
                        if (DateToIntegerUtil.dateTransferInteger(y.getTheDay()).equals(dateInteger)) {
                            y.setInfoIndex(tableConfig.getInfoIndex());
                            y.setInfoStyle(tableConfig.getInfoStyle());
                            y.setInfoName(tableConfig.getInfoName());
                            y.setZGross(Double.valueOf(jsonObject1.get("zgross").toString()));
                            y.setWorkGross(Double.valueOf(jsonObject1.get("workData").toString()));
                            y.setUnWorkGross(Double.valueOf(jsonObject1.get("unWorkData").toString()));
                            Integer working_day_type = Integer.parseInt(jsonObject1.get("working_day_type").toString());
                            y.setWorkDay(working_day_type.equals(1));
                            result.set(iDayWaMapper.insert(y));
                            break;
                        }
                    }
                });
            }
        } else {
            //目标数据库在指定的时间段的结果不为空，但是可能存在数据不全的情况，例如同步要求是2021-06-20到2021-08-20,，但是目标数据库可能存在2021-07-01到2021-07-30
            //这里同步的情况可能比较复杂，存在一种情况，获取到的源数据可能在6-20到8-20中的某天假设7.1是数据为空（不存在），那么不是使用填充数据0代替，而是保持之前的同步数据不变
            //从源数据库获取的指定时间段的数据集为空
            if (dataJsonArray.isEmpty()) {
                List<Date> dateRes = dayWas.stream().map(x -> x.getTheDay()).collect(Collectors.toList());
                dayWaRes.stream().forEach(x -> {
                    boolean contains = dateRes.contains(x.getTheDay());
                    if (!contains) {
                        DayWa dayWa = new DayWa();
                        dayWa.setInfoIndex(tableConfig.getInfoIndex());
                        dayWa.setInfoStyle(tableConfig.getInfoStyle());
                        dayWa.setInfoName(tableConfig.getInfoName());
                        dayWa.setZGross(0.0);
                        dayWa.setWorkGross(0.0);
                        dayWa.setUnWorkGross(0.0);
                        dayWa.setWorkDay(true);
                        result.set(iDayWaMapper.insert(dayWa));
                    }
                });
            } else {
                //如果从数据源中获取的新数据日期和目标数据库中的数据一致就采用最新获取的日期数据进行替换
                dayWas.stream().forEach(y -> {
                    for (Object o : dataJsonArray
                    ) {
                        JSONObject jsonObject1 = JSONUtil.parseObj(o);
                        Integer dateInteger = Integer.parseInt(jsonObject1.get("selectYear").toString()) * 10000 + Integer.valueOf(jsonObject1.get("selectMonth").toString()) * 100 + Integer.valueOf(jsonObject1.get("selectDay").toString());
                        //当源数据库的数据和目标数据库的一行记录的时间相等，就把获取新的行记录同步更新过来
                        if (DateToIntegerUtil.dateTransferInteger(y.getTheDay()).equals(dateInteger)) {
                            y.setInfoIndex(tableConfig.getInfoIndex());
                            y.setInfoStyle(tableConfig.getInfoStyle());
                            y.setInfoName(tableConfig.getInfoName());
                            y.setZGross(Double.valueOf(jsonObject1.get("zgross").toString()));
                            y.setWorkGross(Double.valueOf(jsonObject1.get("workData").toString()));
                            y.setUnWorkGross(Double.valueOf(jsonObject1.get("unWorkData").toString()));
                            Integer working_day_type = Integer.parseInt(jsonObject1.get("working_day_type").toString());
                            y.setWorkDay(working_day_type.equals(1));
                            int update = iDayWaMapper.update(y, new LambdaQueryWrapper<DayWa>().eq(DayWa::getTheDay, y.getTheDay()));
                            //update为0，说明该同步日期目标数据库不存在
                            if (update==0){
                                result.set(iDayWaMapper.insert(y));
                                break;
                            }
                            result.set(update);
                            break;
                        }
                    }
                });
            }
        }
        return result.intValue();
    }

    /**
     * 获取时间段的部门下的用电信息
     *
     * @return
     */
    @Override
    public Integer addOrUpdateDayWaDepartmentInfoRangeTime() {
        String accessToken = getToken();
        String today = DateUtil.today();
        StringBuilder builder = new StringBuilder(thirdUrl);
        TableConfig tableConfig = iTableConfigMapper.selectOne(new LambdaQueryWrapper<TableConfig>().eq(TableConfig::getTableName, "daywa").eq(TableConfig::getInfoStyle, "Part"));
        String url = builder.append("/plat/api/dayWater/getDayWaterAllInfoByTimeRange").append("?dateStart=").append(DateUtil.format(tableConfig.getStartDate(),"yyyy-MM-dd"))
                .append("&dateEnd=").append(DateUtil.format(tableConfig.getEndDate(),"yyyy-MM-dd")).toString();
        String body = HttpRequest.get(url).header("Authorization", accessToken).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        Object data = jsonObject.get("data");
        JSONArray dataJsonArray = JSONUtil.parseArray(data);
        LambdaQueryWrapper<DayWa> eq = new LambdaQueryWrapper<DayWa>().between(DayWa::getTheDay, tableConfig.getStartDate(), tableConfig.getEndDate());
        //根据tableConfig中配置的时间段来从dayWa表获取数据集
        List<DayWa> dayWaRes = iDayWaMapper.selectList(eq);
        List<DayWa> dayWas = new ArrayList<>();
        List<DateTime> dateTimes = DateUtil.rangeToList(tableConfig.getStartDate(), tableConfig.getEndDate(), DateField.DAY_OF_YEAR);
        List<DateTime> dates = new ArrayList<>();
        dateTimes.stream().forEach(x -> {
            DateTime parse = DateUtil.parse(DateUtil.format(x, "yyyy-MM-dd"));
            dates.add(parse);
        });
        dates.stream().forEach(x -> {
            DayWa dayWa = new DayWa();
            dayWa.setTheDay(x);
            dayWas.add(dayWa);
        });
        AtomicInteger result = new AtomicInteger();
        //目标数据库根据tableConfig配置的时间段获取的数据为空
        if (dayWaRes.isEmpty()) {
            //从源数据库获取的指定时间段的数据集为空
            if (dataJsonArray.isEmpty()) {
                dayWas.stream().forEach(x -> {
                    x.setInfoIndex(tableConfig.getInfoIndex());
                    x.setInfoStyle(tableConfig.getInfoStyle());
                    x.setInfoName(tableConfig.getInfoName());
                    x.setZGross(0.0);
                    x.setWorkGross(0.0);
                    x.setUnWorkGross(0.0);
                    x.setWorkDay(true);
                    result.set(iDayWaMapper.insert(x));
                });
            } else {
                dayWas.stream().forEach(y -> {
                    for (Object jsonArray: dataJsonArray
                    ) {
                        JSONObject jsonObject1 = JSONUtil.parseObj(jsonArray);
                        Integer dateInteger = Integer.valueOf(jsonObject1.get("selectYear").toString()) * 10000 + Integer.valueOf(jsonObject1.get("selectMonth").toString()) * 100 + Integer.valueOf(jsonObject1.get("selectDay").toString());
                        //当源数据库的数据和目标数据库的一行记录的时间相等，就把改行记录同步过来
                        if (DateToIntegerUtil.dateTransferInteger(y.getTheDay()).equals(dateInteger)) {
                            y.setInfoIndex(tableConfig.getInfoIndex());
                            y.setInfoStyle(tableConfig.getInfoStyle());
                            y.setInfoName(tableConfig.getInfoName());
                            y.setZGross(Double.valueOf(jsonObject1.get("zgross").toString()));
                            y.setWorkGross(Double.valueOf(jsonObject1.get("workData").toString()));
                            y.setUnWorkGross(Double.valueOf(jsonObject1.get("unWorkData").toString()));
                            Integer working_day_type = Integer.parseInt(jsonObject1.get("working_day_type").toString());
                            y.setWorkDay(working_day_type.equals(1));
                            result.set(iDayWaMapper.insert(y));
                            break;
                        }
                    }
                });
            }
        } else {
            //目标数据库在指定的时间段的结果不为空，但是可能存在数据不全的情况，例如同步要求是2021-06-20到2021-08-20,，但是目标数据库可能存在2021-07-01到2021-07-30
            //这里同步的情况可能比较复杂，存在一种情况，获取到的源数据可能在6-20到8-20中的某天假设7.1是数据为空（不存在），那么不是使用填充数据0代替，而是保持之前的同步数据不变
            //从源数据库获取的指定时间段的数据集为空
            if (dataJsonArray.isEmpty()) {
                List<Date> dateRes = dayWas.stream().map(x -> x.getTheDay()).collect(Collectors.toList());
                dayWaRes.stream().forEach(x -> {
                    boolean contains = dateRes.contains(x.getTheDay());
                    if (!contains) {
                        DayWa dayWa = new DayWa();
                        dayWa.setInfoIndex(tableConfig.getInfoIndex());
                        dayWa.setInfoStyle(tableConfig.getInfoStyle());
                        dayWa.setInfoName(tableConfig.getInfoName());
                        dayWa.setZGross(0.0);
                        dayWa.setWorkGross(0.0);
                        dayWa.setUnWorkGross(0.0);
                        dayWa.setWorkDay(true);
                        result.set(iDayWaMapper.insert(dayWa));
                    }
                });
            } else {
                //如果从数据源中获取的新数据日期和目标数据库中的数据一致就采用最新获取的日期数据进行替换
                dayWas.stream().forEach(y -> {
                    for (Object o : dataJsonArray
                    ) {
                        JSONObject jsonObject1 = JSONUtil.parseObj(o);
                        Integer dateInteger = Integer.parseInt(jsonObject1.get("selectYear").toString()) * 10000 + Integer.valueOf(jsonObject1.get("selectMonth").toString()) * 100 + Integer.valueOf(jsonObject1.get("selectDay").toString());
                        //当源数据库的数据和目标数据库的一行记录的时间相等，就把获取新的行记录同步更新过来
                        if (DateToIntegerUtil.dateTransferInteger(y.getTheDay()).equals(dateInteger)) {
                            y.setInfoIndex(tableConfig.getInfoIndex());
                            y.setInfoStyle(tableConfig.getInfoStyle());
                            y.setInfoName(tableConfig.getInfoName());
                            y.setZGross(Double.valueOf(jsonObject1.get("zgross").toString()));
                            y.setWorkGross(Double.valueOf(jsonObject1.get("workData").toString()));
                            y.setUnWorkGross(Double.valueOf(jsonObject1.get("unWorkData").toString()));
                            Integer working_day_type = Integer.parseInt(jsonObject1.get("working_day_type").toString());
                            y.setWorkDay(working_day_type.equals(1));
                            int update = iDayWaMapper.update(y, new LambdaQueryWrapper<DayWa>().eq(DayWa::getTheDay, y.getTheDay()));
                            //update为0，说明该同步日期目标数据库不存在
                            if (update==0){
                                result.set(iDayWaMapper.insert(y));
                                break;
                            }
                            result.set(update);
                            break;
                        }
                    }
                });
            }
        }
        return result.intValue();
    }

    /**
     * 同步时间段的建筑下的电量信息
     *
     * @return
     */
    @Override
    public Integer addOrUpdateDayWaArcInfoRangeTime() {
        String accessToken = getToken();
        String today = DateUtil.today();
        StringBuilder builder = new StringBuilder(thirdUrl);
        TableConfig tableConfig = iTableConfigMapper.selectOne(new LambdaQueryWrapper<TableConfig>().eq(TableConfig::getTableName, "daywa").eq(TableConfig::getInfoStyle, "Arc"));
        String url = builder.append("/plat/api/dayWater/getDayWaterAllInfoByTimeRange").append("?dateStart=").append(DateUtil.format(tableConfig.getStartDate(),"yyyy-MM-dd"))
                .append("&dateEnd=").append(DateUtil.format(tableConfig.getEndDate(),"yyyy-MM-dd")).toString();
        String body = HttpRequest.get(url).header("Authorization", accessToken).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        Object data = jsonObject.get("data");
        JSONArray dataJsonArray = JSONUtil.parseArray(data);
        LambdaQueryWrapper<DayWa> eq = new LambdaQueryWrapper<DayWa>().between(DayWa::getTheDay, tableConfig.getStartDate(), tableConfig.getEndDate());
        //根据tableConfig中配置的时间段来从dayWa表获取数据集
        List<DayWa> dayWaRes = iDayWaMapper.selectList(eq);
        List<DayWa> dayWas = new ArrayList<>();
        List<DateTime> dateTimes = DateUtil.rangeToList(tableConfig.getStartDate(), tableConfig.getEndDate(), DateField.DAY_OF_YEAR);
        List<DateTime> dates = new ArrayList<>();
        dateTimes.stream().forEach(x -> {
            DateTime parse = DateUtil.parse(DateUtil.format(x, "yyyy-MM-dd"));
            dates.add(parse);
        });
        dates.stream().forEach(x -> {
            DayWa dayWa = new DayWa();
            dayWa.setTheDay(x);
            dayWas.add(dayWa);
        });
        AtomicInteger result = new AtomicInteger();
        //目标数据库根据tableConfig配置的时间段获取的数据为空
        if (dayWaRes.isEmpty()) {
            //从源数据库获取的指定时间段的数据集为空
            if (dataJsonArray.isEmpty()) {
                dayWas.stream().forEach(x -> {
                    x.setInfoIndex(tableConfig.getInfoIndex());
                    x.setInfoStyle(tableConfig.getInfoStyle());
                    x.setInfoName(tableConfig.getInfoName());
                    x.setZGross(0.0);
                    x.setWorkGross(0.0);
                    x.setUnWorkGross(0.0);
                    x.setWorkDay(true);
                    result.set(iDayWaMapper.insert(x));
                });
            } else {
                dayWas.stream().forEach(y -> {
                    for (Object jsonArray: dataJsonArray
                    ) {
                        JSONObject jsonObject1 = JSONUtil.parseObj(jsonArray);
                        Integer dateInteger = Integer.valueOf(jsonObject1.get("selectYear").toString()) * 10000 + Integer.valueOf(jsonObject1.get("selectMonth").toString()) * 100 + Integer.valueOf(jsonObject1.get("selectDay").toString());
                        //当源数据库的数据和目标数据库的一行记录的时间相等，就把改行记录同步过来
                        if (DateToIntegerUtil.dateTransferInteger(y.getTheDay()).equals(dateInteger)) {
                            y.setInfoIndex(tableConfig.getInfoIndex());
                            y.setInfoStyle(tableConfig.getInfoStyle());
                            y.setInfoName(tableConfig.getInfoName());
                            y.setZGross(Double.valueOf(jsonObject1.get("zgross").toString()));
                            y.setWorkGross(Double.valueOf(jsonObject1.get("workData").toString()));
                            y.setUnWorkGross(Double.valueOf(jsonObject1.get("unWorkData").toString()));
                            Integer working_day_type = Integer.parseInt(jsonObject1.get("working_day_type").toString());
                            y.setWorkDay(working_day_type.equals(1));
                            result.set(iDayWaMapper.insert(y));
                            break;
                        }
                    }
                });
            }
        } else {
            //目标数据库在指定的时间段的结果不为空，但是可能存在数据不全的情况，例如同步要求是2021-06-20到2021-08-20,，但是目标数据库可能存在2021-07-01到2021-07-30
            //这里同步的情况可能比较复杂，存在一种情况，获取到的源数据可能在6-20到8-20中的某天假设7.1是数据为空（不存在），那么不是使用填充数据0代替，而是保持之前的同步数据不变
            //从源数据库获取的指定时间段的数据集为空
            if (dataJsonArray.isEmpty()) {
                List<Date> dateRes = dayWas.stream().map(x -> x.getTheDay()).collect(Collectors.toList());
                dayWaRes.stream().forEach(x -> {
                    boolean contains = dateRes.contains(x.getTheDay());
                    if (!contains) {
                        DayWa dayWa = new DayWa();
                        dayWa.setInfoIndex(tableConfig.getInfoIndex());
                        dayWa.setInfoStyle(tableConfig.getInfoStyle());
                        dayWa.setInfoName(tableConfig.getInfoName());
                        dayWa.setZGross(0.0);
                        dayWa.setWorkGross(0.0);
                        dayWa.setUnWorkGross(0.0);
                        dayWa.setWorkDay(true);
                        result.set(iDayWaMapper.insert(dayWa));
                    }
                });
            } else {
                //如果从数据源中获取的新数据日期和目标数据库中的数据一致就采用最新获取的日期数据进行替换
                dayWas.stream().forEach(y -> {
                    for (Object o : dataJsonArray
                    ) {
                        JSONObject jsonObject1 = JSONUtil.parseObj(o);
                        Integer dateInteger = Integer.parseInt(jsonObject1.get("selectYear").toString()) * 10000 + Integer.valueOf(jsonObject1.get("selectMonth").toString()) * 100 + Integer.valueOf(jsonObject1.get("selectDay").toString());
                        //当源数据库的数据和目标数据库的一行记录的时间相等，就把获取新的行记录同步更新过来
                        if (DateToIntegerUtil.dateTransferInteger(y.getTheDay()).equals(dateInteger)) {
                            y.setInfoIndex(tableConfig.getInfoIndex());
                            y.setInfoStyle(tableConfig.getInfoStyle());
                            y.setInfoName(tableConfig.getInfoName());
                            y.setZGross(Double.valueOf(jsonObject1.get("zgross").toString()));
                            y.setWorkGross(Double.valueOf(jsonObject1.get("workData").toString()));
                            y.setUnWorkGross(Double.valueOf(jsonObject1.get("unWorkData").toString()));
                            Integer working_day_type = Integer.parseInt(jsonObject1.get("working_day_type").toString());
                            y.setWorkDay(working_day_type.equals(1));
                            int update = iDayWaMapper.update(y, new LambdaQueryWrapper<DayWa>().eq(DayWa::getTheDay, y.getTheDay()));
                            //update为0，说明该同步日期目标数据库不存在
                            if (update==0){
                                result.set(iDayWaMapper.insert(y));
                                break;
                            }
                            result.set(update);
                            break;
                        }
                    }
                });
            }
        }
        return result.intValue();
    }

    /**
     * 同步时间段下的电表的用电信息
     *
     * @return
     */
    @Override
    public Integer addOrUpdateDayWaInfoRangeTime() {
        String accessToken = getToken();
        String today = DateUtil.today();
        StringBuilder builder = new StringBuilder(thirdUrl);
        TableConfig tableConfig = iTableConfigMapper.selectOne(new LambdaQueryWrapper<TableConfig>().eq(TableConfig::getTableName, "daywa").eq(TableConfig::getInfoStyle, "Meter"));
        String url = builder.append("/plat/api/dayWater/getDayWaterAllInfoByTimeRange").append("?dateStart=").append(DateUtil.format(tableConfig.getStartDate(),"yyyy-MM-dd"))
                .append("&dateEnd=").append(DateUtil.format(tableConfig.getEndDate(),"yyyy-MM-dd")).toString();
        String body = HttpRequest.get(url).header("Authorization", accessToken).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        Object data = jsonObject.get("data");
        JSONArray dataJsonArray = JSONUtil.parseArray(data);
        LambdaQueryWrapper<DayWa> eq = new LambdaQueryWrapper<DayWa>().between(DayWa::getTheDay, tableConfig.getStartDate(), tableConfig.getEndDate());
        //根据tableConfig中配置的时间段来从dayWa表获取数据集
        List<DayWa> dayWaRes = iDayWaMapper.selectList(eq);
        List<DayWa> dayWas = new ArrayList<>();
        List<DateTime> dateTimes = DateUtil.rangeToList(tableConfig.getStartDate(), tableConfig.getEndDate(), DateField.DAY_OF_YEAR);
        List<DateTime> dates = new ArrayList<>();
        dateTimes.stream().forEach(x -> {
            DateTime parse = DateUtil.parse(DateUtil.format(x, "yyyy-MM-dd"));
            dates.add(parse);
        });
        dates.stream().forEach(x -> {
            DayWa dayWa = new DayWa();
            dayWa.setTheDay(x);
            dayWas.add(dayWa);
        });
        AtomicInteger result = new AtomicInteger();
        //目标数据库根据tableConfig配置的时间段获取的数据为空
        if (dayWaRes.isEmpty()) {
            //从源数据库获取的指定时间段的数据集为空
            if (dataJsonArray.isEmpty()) {
                dayWas.stream().forEach(x -> {
                    x.setInfoIndex(tableConfig.getInfoIndex());
                    x.setInfoStyle(tableConfig.getInfoStyle());
                    x.setInfoName(tableConfig.getInfoName());
                    x.setZGross(0.0);
                    x.setWorkGross(0.0);
                    x.setUnWorkGross(0.0);
                    x.setWorkDay(true);
                    result.set(iDayWaMapper.insert(x));
                });
            } else {
                dayWas.stream().forEach(y -> {
                    for (Object jsonArray: dataJsonArray
                    ) {
                        JSONObject jsonObject1 = JSONUtil.parseObj(jsonArray);
                        Integer dateInteger = Integer.valueOf(jsonObject1.get("selectYear").toString()) * 10000 + Integer.valueOf(jsonObject1.get("selectMonth").toString()) * 100 + Integer.valueOf(jsonObject1.get("selectDay").toString());
                        //当源数据库的数据和目标数据库的一行记录的时间相等，就把改行记录同步过来
                        if (DateToIntegerUtil.dateTransferInteger(y.getTheDay()).equals(dateInteger)) {
                            y.setInfoIndex(tableConfig.getInfoIndex());
                            y.setInfoStyle(tableConfig.getInfoStyle());
                            y.setInfoName(tableConfig.getInfoName());
                            y.setZGross(Double.valueOf(jsonObject1.get("zgross").toString()));
                            y.setWorkGross(Double.valueOf(jsonObject1.get("workData").toString()));
                            y.setUnWorkGross(Double.valueOf(jsonObject1.get("unWorkData").toString()));
                            Integer working_day_type = Integer.parseInt(jsonObject1.get("working_day_type").toString());
                            y.setWorkDay(working_day_type.equals(1));
                            result.set(iDayWaMapper.insert(y));
                            break;
                        }
                    }
                });
            }
        } else {
            //目标数据库在指定的时间段的结果不为空，但是可能存在数据不全的情况，例如同步要求是2021-06-20到2021-08-20,，但是目标数据库可能存在2021-07-01到2021-07-30
            //这里同步的情况可能比较复杂，存在一种情况，获取到的源数据可能在6-20到8-20中的某天假设7.1是数据为空（不存在），那么不是使用填充数据0代替，而是保持之前的同步数据不变
            //从源数据库获取的指定时间段的数据集为空
            if (dataJsonArray.isEmpty()) {
                List<Date> dateRes = dayWas.stream().map(x -> x.getTheDay()).collect(Collectors.toList());
                dayWaRes.stream().forEach(x -> {
                    boolean contains = dateRes.contains(x.getTheDay());
                    if (!contains) {
                        DayWa dayWa = new DayWa();
                        dayWa.setInfoIndex(tableConfig.getInfoIndex());
                        dayWa.setInfoStyle(tableConfig.getInfoStyle());
                        dayWa.setInfoName(tableConfig.getInfoName());
                        dayWa.setZGross(0.0);
                        dayWa.setWorkGross(0.0);
                        dayWa.setUnWorkGross(0.0);
                        dayWa.setWorkDay(true);
                        result.set(iDayWaMapper.insert(dayWa));
                    }
                });
            } else {
                //如果从数据源中获取的新数据日期和目标数据库中的数据一致就采用最新获取的日期数据进行替换
                dayWas.stream().forEach(y -> {
                    for (Object o : dataJsonArray
                    ) {
                        JSONObject jsonObject1 = JSONUtil.parseObj(o);
                        Integer dateInteger = Integer.parseInt(jsonObject1.get("selectYear").toString()) * 10000 + Integer.valueOf(jsonObject1.get("selectMonth").toString()) * 100 + Integer.valueOf(jsonObject1.get("selectDay").toString());
                        //当源数据库的数据和目标数据库的一行记录的时间相等，就把获取新的行记录同步更新过来
                        if (DateToIntegerUtil.dateTransferInteger(y.getTheDay()).equals(dateInteger)) {
                            y.setInfoIndex(tableConfig.getInfoIndex());
                            y.setInfoStyle(tableConfig.getInfoStyle());
                            y.setInfoName(tableConfig.getInfoName());
                            y.setZGross(Double.valueOf(jsonObject1.get("zgross").toString()));
                            y.setWorkGross(Double.valueOf(jsonObject1.get("workData").toString()));
                            y.setUnWorkGross(Double.valueOf(jsonObject1.get("unWorkData").toString()));
                            Integer working_day_type = Integer.parseInt(jsonObject1.get("working_day_type").toString());
                            y.setWorkDay(working_day_type.equals(1));
                            int update = iDayWaMapper.update(y, new LambdaQueryWrapper<DayWa>().eq(DayWa::getTheDay, y.getTheDay()));
                            //update为0，说明该同步日期目标数据库不存在
                            if (update==0){
                                result.set(iDayWaMapper.insert(y));
                                break;
                            }
                            result.set(update);
                            break;
                        }
                    }
                });
            }
        }
        return result.intValue();
    }


    /**
     * 获取token
     *
     * @return
     */
    private String getToken() {
        return getEnergyPlatformTokenUtil.getEnergyToken();
    }
}
