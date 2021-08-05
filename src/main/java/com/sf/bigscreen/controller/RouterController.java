package com.sf.bigscreen.controller;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sf.bigscreen.entity.Result;
import com.sf.bigscreen.model.Router;
import com.sf.bigscreen.service.IRouterService;
import com.sf.bigscreen.utils.GetEnergyPlatformTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private GetEnergyPlatformTokenUtil getEnergyPlatformTokenUtil;

    @Value("${third.url2}")
    private String baseThirdUrl;

    @GetMapping("/getList")
    public Result getRouterList(){
        String access_token = getEnergyPlatformTokenUtil.getEnergyToken();
        //拿到可视化大屏项目代码为6000的路由json信息
        StringBuilder builder = new StringBuilder(baseThirdUrl);
        String s = builder.append("/plat/auth/subsystem/getModule?token=").append(access_token).append("&projectCode=6000").toString();
        String body1 = HttpRequest.get(s).execute().body();
        JSONObject jsonObject2 = JSONObject.parseObject(body1);
        Object data = jsonObject2.get("data");
        JSONObject dataJSON= (JSONObject)JSONObject.toJSON(data);
        JSONArray powerInfoArray = (JSONArray)dataJSON.get("powerInfo");
        Object powerInfo = powerInfoArray.get(0);
        JSONObject powerInfoJson = (JSONObject)JSONObject.toJSON(powerInfo);
        Object childModuleList = powerInfoJson.get("childModuleList");
        return Result.buildSuccessData(childModuleList);
    }

    @ApiModelProperty("修改路由表(包括对路由的增加、删除、修改)")
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
