package com.sf.bigscreen.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sf.bigscreen.entity.Result;
import com.sf.bigscreen.model.User;
import com.sf.bigscreen.utils.TokenUtil;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: bi xuan
 * @Date: 2021/6/21 16:12
 * @Description:
 **/
@RestController
@RequestMapping("/user")
public class UserController{

    @PostMapping(value = "/login")
    public Result login(@RequestBody Map<String,Object> para) throws JsonProcessingException {
        String username=(String)para.get("username");
        String password=(String)para.get("password");
        String token= TokenUtil.sign(new User(username,password));
        return Result.buildSuccessData(token,"post success");
    }

    @ApiModelProperty(value = "拿到用户的基本信息")
    @GetMapping("/getUserInfo")
    public Result getUserInfo(){
//        List<String> strings = new ArrayList<>();
//        strings.add("admin");
        return Result.buildSuccessData("[\"admin\"]");
    }

    @GetMapping("/getRouteList")
    public Result getRouteList(){
//        String routeList = "[{"path": "/permission","component": "Layout","redirect": "/permission/page","alwaysShow": true,"name": "Permission","meta": {"title": "大屏展示","icon": "lock","roles": ["admin", "editor"]},"children": [{"path": "page","component": "() => import('@/views/permission/role')","name": "PagePermission","meta": {"title": "一号大厅展示","src": "http://localhost:50401/analysis/dashboard/show/03590db461799c1f107b/","roles": ["admin"]}},{"path": "directive","component": "() => import('@/views/permission/role')","name": "DirectivePermission","meta": {"title": "二号大厅展示","src":"http://localhost:50401/analysis/dashboard/show/05cd39547179a1a1b489"}},{"path": "role","component": "() => import('@/views/permission/role')","name": "RolePermission","meta": {"title": "三号大厅展示","src": "http://localhost:50401/analysis/dashboard/show/09d756e23179a12580fb/","roles": ["admin"]}}, {"path": "firstHall","component": "() => import('@/views/permission/role')","name": "carousel","meta": {"src": "http://localhost:50401/analysis/dashboard/show/03590db461799c1f107b","title": "四号大厅展示","roles": ["admin"]}}]},{ "path": "*", "redirect": "/404", "hidden": true }];
        return Result.buildSuccessData(Collections.emptyList());
    }
}
