package com.sf.bigscreen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sf.bigscreen.model.Task;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: bi xuan
 * @Date: 2021/8/4 19:41
 * @Description:
 **/
@Mapper
public interface TaskMapper extends BaseMapper<Task> {
}
