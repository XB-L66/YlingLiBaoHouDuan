package com.xb.micrweb.controller;

import com.xb.api.vo.PlatInfoVo;
import com.xb.micrweb.view.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@Api(tags = "平台基本信息功能")
public class PlatInfoController extends BaseController {
    @GetMapping("/plat/info")
    @ApiOperation(value = "平台三项基本信息",notes = "注册人数，平均的利率，总投资金额")
    public Result queryPlatBaseInfo(){
        PlatInfoVo platInfoVo = platBaseInfoService.queryPlatInfoVo();
        Result result=new Result();
        result.setCode(1000);
        result.setMsg("查询平台信息成功");
        result.setData(platInfoVo);
        return result;
    }
}
