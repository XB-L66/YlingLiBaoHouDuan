package com.xb.dataservice.mapper;

import com.xb.api.model.FinanceAccount;

public interface FinanceAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FinanceAccount row);

    int insertSelective(FinanceAccount row);

    FinanceAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FinanceAccount row);

    int updateByPrimaryKey(FinanceAccount row);
}