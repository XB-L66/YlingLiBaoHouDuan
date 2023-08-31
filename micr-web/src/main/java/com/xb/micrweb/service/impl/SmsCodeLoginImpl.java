package com.xb.micrweb.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.xb.common.constants.RedisKey;
import com.xb.micrweb.config.JdwxSmsConfig;
import com.xb.micrweb.service.SmsService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Service(value = "smsCodeLoginImpl")
public class SmsCodeLoginImpl implements SmsService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private JdwxSmsConfig smsConfig;

    @Override
    public boolean sendSms(String phone) {
        boolean send = false;
        String random  = RandomStringUtils.randomNumeric(4);
        System.out.println("登录验证码的随机数 random="+random);
        String content  = String.format(smsConfig.getLoginText(), random);

        CloseableHttpClient client = HttpClients.createDefault();
        String url = smsConfig.getUrl()+"?mobile="+phone
                        +"&content=" + content
                        +"&appkey="+smsConfig.getAppkey();
        HttpGet get  = new HttpGet(url);

        try{
            //CloseableHttpResponse response = client.execute(get);
           // if( response.getStatusLine().getStatusCode() == HttpStatus.SC_OK ){
                //得到返回的数据，json
                //String text = EntityUtils.toString(response.getEntity());
                String text="{\n" +
                        "    \"code\": \"10000\",\n" +
                        "    \"charge\": false,\n" +
                        "    \"remain\": 1305,\n" +
                        "    \"msg\": \"查询成功\",\n" +
                        "    \"result\": {\n" +
                        "        \"ReturnStatus\": \"Success\",\n" +
                        "        \"Message\": \"ok\",\n" +
                        "        \"RemainPoint\": 420842,\n" +
                        "        \"TaskID\": 18424321,\n" +
                        "        \"SuccessCounts\": 1\n" +
                        "    }\n" +
                        "}";
                //解析json
                if(StringUtils.isNotBlank(text)){
                    JSONObject jsonObject = JSONObject.parseObject(text);
                    if("10000".equals(jsonObject.getString("code"))){
                        if("Success".equalsIgnoreCase(
                                jsonObject.getJSONObject("result").getString("ReturnStatus"))){
                            send  = true;

                            String key = RedisKey.KEY_SMS_CODE_LOGIN + phone;
                            stringRedisTemplate.boundValueOps(key).set(random,3 , TimeUnit.MINUTES);

                        }
                    }
                }
           // }
        }catch (Exception e){
            e.printStackTrace();
        }
        return send;
    }

    @Override
    public boolean checkSmsCode(String phone, String code) {
        String key = RedisKey.KEY_SMS_CODE_LOGIN + phone;
        if( stringRedisTemplate.hasKey(key)){
            String querySmsCode = stringRedisTemplate.boundValueOps(key).get();
            if( code.equals(querySmsCode)){
                return true;
            }
        }
        return false;
    }


}
