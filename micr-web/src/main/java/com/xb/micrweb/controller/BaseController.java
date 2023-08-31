package com.xb.micrweb.controller;

import com.xb.api.service.InvestService;
import com.xb.api.service.PlatBaseInfoService;
import com.xb.api.service.ProductInfoService;
import com.xb.api.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

public class  BaseController {
    @DubboReference(interfaceClass = PlatBaseInfoService.class,version = "1.0")
    protected PlatBaseInfoService platBaseInfoService;
    @DubboReference(interfaceClass = ProductInfoService.class,version = "1.0")
    protected ProductInfoService productInfoService;
    @Resource
    protected StringRedisTemplate stringRedisTemplate;
    @DubboReference(interfaceClass = InvestService.class,version = "1.0")
    protected InvestService investService;
    @DubboReference(interfaceClass = UserService.class,version = "1.0")
    protected UserService userService;
}
