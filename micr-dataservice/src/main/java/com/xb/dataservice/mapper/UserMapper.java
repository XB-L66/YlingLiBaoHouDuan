package com.xb.dataservice.mapper;

import com.xb.api.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User row);

    int insertSelective(User row);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User row);

    int updateByPrimaryKey(User row);

    int selectCountUser();

    User selectByPhone(@Param("phone") String phone);

    int updateRealname(String phone, String name, String idCard);

    int insertReturnPrimaryKey(User user);

    User selectLogin(@Param("phone") String phone, @Param("loginPassword") String newPassword);
}