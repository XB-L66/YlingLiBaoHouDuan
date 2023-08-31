package com.xb.api.service;

import com.xb.api.model.User;

public interface UserService {
    User queryByPhone(String phone);

    boolean modifyRealname(String phone, String name, String idCard);

    int userRegister(String phone, String pword);

    User userLogin(String phone, String pword);
}
