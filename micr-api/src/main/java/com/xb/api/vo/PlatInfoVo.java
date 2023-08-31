package com.xb.api.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
public class PlatInfoVo implements Serializable {
    private BigDecimal historyAvgRate;
    private BigDecimal sumBidMoney;
    private Integer registerUsers;
}
