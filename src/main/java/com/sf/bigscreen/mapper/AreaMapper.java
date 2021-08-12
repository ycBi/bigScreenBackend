package com.sf.bigscreen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.sf.bigscreen.model.Area;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: bi xuan
 * @Date: 2021/8/11 16:16
 * @Description:
 **/
@Mapper
public interface AreaMapper extends BaseMapper<Area> {
}
