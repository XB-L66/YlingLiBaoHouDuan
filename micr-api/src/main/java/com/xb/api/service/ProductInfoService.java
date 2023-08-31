package com.xb.api.service;

import com.xb.api.model.ProductInfo;
import com.xb.api.vo.MultiProductVo;

import java.util.List;

public interface ProductInfoService {
    List<ProductInfo> queryByTypeLimit(Integer pType,Integer pageNo,Integer pageSize);
    MultiProductVo queryIndexPageProducts();
    Integer queryRecordNumsByType(Integer pType);
    ProductInfo queryById(Integer id);
}
