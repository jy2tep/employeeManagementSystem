package com.example.employeemanagementsystem.Service;

import com.alibaba.fastjson.JSONObject;
import com.example.employeemanagementsystem.Common.JwtUtils;
import com.example.employeemanagementsystem.Common.ResultCode;
import com.example.employeemanagementsystem.Dao.UserMapper;
import com.example.employeemanagementsystem.Excep.UserException;
import com.example.employeemanagementsystem.From.UserForm;
import com.example.employeemanagementsystem.Model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;


import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);
    @Resource
    private RedisTemplate redisTemplate;
    @Autowired
    UserMapper userMapper;
    @Autowired
    JwtUtils jwtUtils;

    //注册会员
    public Map userCreate(UserForm userForm){
        User mallUser = new User();
        Map map = new HashMap();
        mallUser.setUserName(userForm.getUserName());
        String str = "-"+userForm.getPassword()+"-";
        mallUser.setPassword(md5(str).toUpperCase());
        Integer i  = userMapper.selectCount(mallUser);
        if (i>0){
            throw new UserException(new ResultCode(false,-1,"当前注册的用户已经存在!"));
        }
        userMapper.insertUser(mallUser);
        String xAuthtoken = jwtUtils.createJwt(mallUser);
        map.put("xAuthtoken",xAuthtoken);
        return map;
    }
    //会员登录
    public Map userSignin(UserForm userForm){
        User mallUser = new User();
        mallUser.setUserName(userForm.getUserName());
        User userTemp  = userMapper.selectUser(mallUser.getUserName());
        String str = "-"+userForm.getPassword()+"-";
        str = md5(str).toUpperCase();
        if (ObjectUtils.isEmpty(userTemp)){
            throw new UserException(new ResultCode(false,-1,"需要登录的账号不存在"));
        }
        else if (!str.equals(userTemp.getPassword())) {
            throw new UserException(new ResultCode(false,-1,"密码错误"));
        }
        String xAuthtoken = jwtUtils.createJwt(userTemp);
        Map map = new HashMap();
        map.put("xAuthtoken",xAuthtoken);
        map.put("account",userTemp);
        return map;
    }

    //md5加密封装
    public String md5(String str){
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

}
