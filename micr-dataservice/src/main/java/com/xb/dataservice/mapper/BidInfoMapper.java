package com.xb.dataservice.mapper;

import com.xb.api.model.BidInfo;
import com.xb.api.model.BidInfoProduct;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BidInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BidInfo row);

    int insertSelective(BidInfo row);

    BidInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BidInfo row);

    int updateByPrimaryKey(BidInfo row);
    BigDecimal selectSumBidMoney();

    List<BidInfoProduct> selectByProductId(@Param("productId") Integer productId,
                                           @Param("offset") int offset,
                                           @Param("rows") Integer rows);
}