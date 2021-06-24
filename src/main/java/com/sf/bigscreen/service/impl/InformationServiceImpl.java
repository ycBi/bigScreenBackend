package com.sf.bigscreen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sf.bigscreen.mapper.IInformationMapper;
import com.sf.bigscreen.model.Information;
import com.sf.bigscreen.service.IInformationService;
import org.springframework.stereotype.Service;

/**
 * @Author: bi xuan
 * @Date: 2021/6/24 16:38
 * @Description:
 **/
@Service
public class InformationServiceImpl extends ServiceImpl<IInformationMapper, Information> implements IInformationService {
}
