package com.sf.bigscreen.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sf.bigscreen.entity.Result;
import com.sf.bigscreen.model.User;
import com.sf.bigscreen.utils.TokenUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: bi xuan
 * @Date: 2021/6/21 16:12
 * @Description:
 **/
@RestController
@RequestMapping("/user")
public class UserController{
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody Map<String,Object> para) throws JsonProcessingException {
        String username=(String)para.get("username");
        String password=(String)para.get("password");
        String token= TokenUtil.sign(new User(username,password));
        return Result.buildSuccessData(token,"post success");
    }
}
