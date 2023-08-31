package com.xb.dataservice.service;

import com.xb.api.model.FinanceAccount;
import com.xb.api.model.User;
import com.xb.api.service.UserService;
import com.xb.common.Util.CommonUtil;
import com.xb.dataservice.mapper.FinanceAccountMapper;
import com.xb.dataservice.mapper.UserMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@DubboService(interfaceClass = UserService.class,version = "1.0")
public class UserServiceImpl implements UserService{
    @Resource
    private UserMapper userMapper;
    @Resource
    private FinanceAccountMapper financeAccountMapper;
    @Value("${ylb.config.password-salt}")
    private String passwordSalt;
    @Override
    public User queryByPhone(String phone) {
        User user=null;
        if(CommonUtil.checkPhone(phone)){
           user= userMapper.selectByPhone(phone);
        }
        return user;
    }

    @Override
    public boolean modifyRealname(String phone, String name, String idCard) {
        int rows = 0;
        if(!StringUtils.isAnyBlank(phone,name,idCard)){
            rows  = userMapper.updateRealname(phone,name,idCard);
        }
        return rows > 0 ;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized int userRegister(String phone, String pword) {
        int result=0;
        if(CommonUtil.checkPhone(phone)&&(pword!=null&&pword.length()==32)){
            if(userMapper.selectByPhone(phone)==null){
                String s = DigestUtils.md5Hex(pword+passwordSalt);
                User user=new User();
                user.setPhone(phone);
                user.setLoginPassword(pword);
                user.setAddTime(new Date());
                userMapper.insertReturnPrimaryKey(user);
                FinanceAccount account=new FinanceAccount();
                account.setUid(user.getId());
                account.setAvailableMoney(new BigDecimal("0"));
                financeAccountMapper.insertSelective(account);
                result=1;
            }else{
                result=2;
            }
        }
        return result;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User userLogin(String phone, String password) {
        User user=new User();
        if(CommonUtil.checkPhone(phone)&&(password!=null&&password.length()==32)){
         String newPassword=DigestUtils.md5Hex(password+passwordSalt);
            user=userMapper.selectLogin(phone,newPassword);
            if(user!=null){
                user.setLastLoginTime(new Date());
                userMapper.updateByPrimaryKeySelective(user);
            }
        }
        return user;
    }
}
