package com.xb.api.service;

import com.xb.api.model.BidInfoProduct;

import java.util.List;

public interface InvestService {
    List<BidInfoProduct> queryBidListByProductId(Integer productId,Integer pageNo,Integer pageSize);
}
