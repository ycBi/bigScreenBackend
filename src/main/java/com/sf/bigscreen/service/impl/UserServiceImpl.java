package com.sf.bigscreen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sf.bigscreen.mapper.IUserMapper;
import com.sf.bigscreen.model.User;
import com.sf.bigscreen.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @Author: bi xuan
 * @Date: 2021/6/24 15:28
 * @Description:
 **/
@Service
public class UserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService {
}
