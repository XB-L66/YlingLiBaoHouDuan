package com.xb.api.vo;

import com.xb.api.model.ProductInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class MultiProductVo implements Serializable {
    private List<ProductInfo> xinShouBao;
    private List<ProductInfo> youXuan;
    private List<ProductInfo> sanBiao;
}
