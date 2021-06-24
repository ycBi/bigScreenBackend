package com.sf.bigscreen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sf.bigscreen.mapper.IAlarmInfoMapper;
import com.sf.bigscreen.model.AlarmInfo;
import com.sf.bigscreen.service.IAlarmInfoService;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;

/**
 * @Author: bi xuan
 * @Date: 2021/6/24 15:44
 * @Description:
 **/
@Service
public class AlarmInfoServiceImpl extends ServiceImpl<IAlarmInfoMapper, AlarmInfo> implements IAlarmInfoService {
}
