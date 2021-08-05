package com.sf.bigscreen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sf.bigscreen.mapper.MeterMapper;
import com.sf.bigscreen.model.Meter;
import com.sf.bigscreen.service.IMeterService;
import org.springframework.stereotype.Service;

/**
 * @Author: bi xuan
 * @Date: 2021/8/5 8:32
 * @Description:
 **/
@Service
public class MeterService extends ServiceImpl<MeterMapper, Meter> implements IMeterService {
}
