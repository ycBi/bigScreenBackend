package com.sf.bigscreen.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sf.bigscreen.mapper.TaskMapper;
import com.sf.bigscreen.model.Task;
import com.sf.bigscreen.service.ITaskService;
import org.springframework.stereotype.Service;

/**
 * @Author: bi xuan
 * @Date: 2021/8/5 8:35
 * @Description:
 **/
@Service
public class TaskService extends ServiceImpl<TaskMapper, Task> implements ITaskService {
}
