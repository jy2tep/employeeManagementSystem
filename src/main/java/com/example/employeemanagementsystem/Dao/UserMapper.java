package com.example.employeemanagementsystem.Dao;

import com.example.employeemanagementsystem.Model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insertUser(User user);

    int selectCount(User user);

    User selectUser(String userName);
}
