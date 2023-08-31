package com.xb.dataservice.service;

import com.xb.api.model.BidInfoProduct;
import com.xb.api.service.InvestService;
import com.xb.common.Util.CommonUtil;
import com.xb.dataservice.mapper.BidInfoMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@DubboService(interfaceClass = InvestService.class,version = "1.0")
public class InvestServiceImpl implements InvestService{

    @Resource
    BidInfoMapper bidInfoMapper;
    @Override
    public List<BidInfoProduct> queryBidListByProductId(Integer productId, Integer pageNo, Integer pageSize) {
        List<BidInfoProduct> bidList=new ArrayList<>();

        if(productId!=null&&productId>0){
            pageNo= CommonUtil.defaultPageNo(pageNo);
            pageSize=CommonUtil.defaultPageSize(pageSize);
            int offset=(pageNo-1)*pageSize;
            bidList=bidInfoMapper.selectByProductId(productId,offset,pageSize);
        }
        return bidList;
    }
}
