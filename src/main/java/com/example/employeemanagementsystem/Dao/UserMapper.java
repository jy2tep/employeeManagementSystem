package com.example.employeemanagementsystem.Dao;

import com.example.employeemanagementsystem.Model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int insertUser(@Param("user")User user);

    int selectCount(@Param("user")User user);

    User selectUser(@Param("userName")String userName);
}
