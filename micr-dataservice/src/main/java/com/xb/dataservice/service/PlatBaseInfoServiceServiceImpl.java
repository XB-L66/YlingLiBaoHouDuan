package com.xb.dataservice.service;

import com.xb.api.service.PlatBaseInfoService;
import com.xb.api.vo.PlatInfoVo;
import com.xb.dataservice.mapper.BidInfoMapper;
import com.xb.dataservice.mapper.ProductInfoMapper;
import com.xb.dataservice.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.math.BigDecimal;

@DubboService(interfaceClass = PlatBaseInfoService.class,version = "1.0")
public class PlatBaseInfoServiceServiceImpl implements PlatBaseInfoService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private ProductInfoMapper productInfoMapper;
    @Resource
    private BidInfoMapper bidInfoMapper;
    @Override
    public PlatInfoVo queryPlatInfoVo() {
        PlatInfoVo platInfoVo=new PlatInfoVo();
        int registerUserCount =userMapper.selectCountUser();
        BigDecimal avgRate = productInfoMapper.selectAvgRate();
        BigDecimal sumBidMoney = bidInfoMapper.selectSumBidMoney();
        platInfoVo.setRegisterUsers(registerUserCount);
        platInfoVo.setHistoryAvgRate(avgRate);
        platInfoVo.setSumBidMoney(sumBidMoney);
        return platInfoVo;
    }
}
