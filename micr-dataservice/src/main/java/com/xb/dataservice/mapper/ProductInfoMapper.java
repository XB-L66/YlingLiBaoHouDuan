package com.xb.dataservice.mapper;

import com.xb.api.model.ProductInfo;

import java.math.BigDecimal;
import java.util.List;

public interface ProductInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductInfo row);

    int insertSelective(ProductInfo row);

    ProductInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductInfo row);

    int updateByPrimaryKey(ProductInfo row);
//    查询平均利率
    BigDecimal selectAvgRate();
//
    List<ProductInfo> selectByTypeLimit(Integer ptype,Integer offset,Integer rows);

    Integer selectCountByType(Integer ptype);
}