package com.xb.dataservice.service;

import com.xb.api.model.ProductInfo;
import com.xb.api.service.ProductInfoService;
import com.xb.api.vo.MultiProductVo;
import com.xb.common.Util.CommonUtil;
import com.xb.common.constants.YLBConstant;
import com.xb.dataservice.mapper.ProductInfoMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@DubboService(interfaceClass = ProductInfoService.class,version = "1.0")
public class ProductInfoServiceImpl implements ProductInfoService {
    @Resource
    private ProductInfoMapper productInfoMapper;
    @Override
    public List<ProductInfo> queryByTypeLimit(Integer pType, Integer pageNo, Integer pageSize) {
        List<ProductInfo> productInfos=new ArrayList<>();
        if(pType==0||pType==1||pType==2){
            pageNo= CommonUtil.defaultPageNo(pageNo);
            pageSize=CommonUtil.defaultPageSize(pageSize);
            int offset=(pageNo-1)*pageSize;
            productInfos= productInfoMapper.selectByTypeLimit(pType, offset, pageSize);
        }
        return productInfos;
    }

    @Override
    public MultiProductVo queryIndexPageProducts() {
        MultiProductVo result=new MultiProductVo();
        List<ProductInfo> xinShouBaoList=productInfoMapper.selectByTypeLimit(YLBConstant.PRODUCT_TYPE_XINSHOUBAO,0,1);
        List<ProductInfo> youXuanList=productInfoMapper.selectByTypeLimit(YLBConstant.PRODUCT_TYPE_YOUXUAN,0,3);
        List<ProductInfo> sanBiaoList=productInfoMapper.selectByTypeLimit(YLBConstant.PRODUCT_TYPE_SANBIAO,0,3);
        result.setXinShouBao(xinShouBaoList);
        result.setYouXuan(youXuanList);
        result.setSanBiao(sanBiaoList);
        return result;
    }

    @Override
    public Integer queryRecordNumsByType(Integer pType) {
        Integer count=0;
        if(pType==0||pType==1||pType==2) {
             count = productInfoMapper.selectCountByType(pType);
        }
        return count;
    }

    @Override
    public ProductInfo queryById(Integer id) {
        ProductInfo productInfo=null;
        if(id!=null&&id>0){
            productInfo = productInfoMapper.selectByPrimaryKey(id);
        }
        return productInfo;
    }
}
