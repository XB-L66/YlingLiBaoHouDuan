package com.xb.micrweb.controller;


import com.xb.common.Util.CommonUtil;
import com.xb.common.constants.RedisKey;
import com.xb.common.enums.RCode;
import com.xb.micrweb.service.SmsService;
import com.xb.micrweb.view.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Api(tags = "短信业务")
@RestController
@RequestMapping("/v1/sms")
public class SmsController extends BaseController {

    @Resource(name = "smsCodeRegisterImpl")
    private SmsService smsService;

    @Resource(name = "smsCodeLoginImpl")
    private SmsService loginSmsService;

    /**发送注册验证码短信*/
    @GetMapping("/code/register")
    public Result sendCodeRegister(@RequestParam String phone){
        Result result = Result.fail();
        if(CommonUtil.checkPhone(phone)){
            //判断redis中是否有这个手机号的验证码
            String key  = RedisKey.KEY_SMS_CODE_REG + phone;
            if(stringRedisTemplate.hasKey(key)){
                result = Result.ok();
                result.setRCode(RCode.SMS_CODE_CAN_USE);
            } else {
                boolean isSuccess = smsService.sendSms(phone);
                if( isSuccess ){
                    result = Result.ok();
                }
            }
        } else {
            result.setRCode(RCode.PHONE_FORMAT_ERR);
        }
        return result;
    }

    /**发送登录验证码短信*/
    @GetMapping("/code/login")
    public Result sendCodeLogin(@RequestParam String phone){
        Result result = Result.fail();
        if(CommonUtil.checkPhone(phone)){
            //判断redis中是否有这个手机号的验证码
            String key  = RedisKey.KEY_SMS_CODE_LOGIN + phone;
            if(stringRedisTemplate.hasKey(key)){
                result = Result.ok();
                result.setRCode(RCode.SMS_CODE_CAN_USE);
            } else {
                boolean isSuccess = loginSmsService.sendSms(phone);
                if( isSuccess ){
                    result = Result.ok();
                }
            }
        } else {
            result.setRCode(RCode.PHONE_FORMAT_ERR);
        }
        return result;
    }
}
