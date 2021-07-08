package com.sf.bigscreen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sf.bigscreen.mapper.ITableConfigMapper;
import com.sf.bigscreen.model.TableConfig;
import com.sf.bigscreen.service.ITableConfigService;
import org.springframework.stereotype.Service;

/**
 * @Author: bi xuan
 * @Date: 2021/7/6 15:20
 * @Description:
 **/
@Service
public class TableConfigImpl extends ServiceImpl<ITableConfigMapper, TableConfig> implements ITableConfigService {
}
