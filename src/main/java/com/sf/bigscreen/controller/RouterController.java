package com.sf.bigscreen.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sf.bigscreen.entity.Result;
import com.sf.bigscreen.model.Router;
import com.sf.bigscreen.service.IRouterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: bi xuan
 * @Date: 2021/6/28 10:47
 * @Description:
 **/
@Api("路由相关接口")
@RestController
@RequestMapping("/router")
public class RouterController {
    @Autowired
    private IRouterService iRouterService;

    @ApiModelProperty("拿到路由的权限列表字符串，前端用json.parse解析为对象")
    @GetMapping("/getList")
    public Result getRouterList(@RequestParam("role") String role){
            Router one = iRouterService.getOne(new LambdaQueryWrapper<Router>().eq(Router::getRole, role).last("LIMIT 1"));
        if (one!=null){
            String routes = one.getRoutes();
            return Result.buildSuccessData(routes);
        }
        return Result.buildFailedMessage("获取路由列表失败");
    }

    @ApiModelProperty("修改路由表")
    @PostMapping("/updateRoutes")
    public Result updateRouteList(@RequestBody Router routeInfo){
        System.out.println(routeInfo);
        boolean update = iRouterService.update(routeInfo,new LambdaQueryWrapper<Router>().eq(Router::getRole, routeInfo.getRole()));
        if (update) {
            return Result.buildSuccessMessage("修改路由列表成功");
        }
        return Result.buildFailedMessage("添加路由列表失败");
    }
}
