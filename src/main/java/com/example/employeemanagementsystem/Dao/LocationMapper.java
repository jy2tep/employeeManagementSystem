package com.example.employeemanagementsystem.Dao;

import com.example.employeemanagementsystem.Model.Employee;
import com.example.employeemanagementsystem.Model.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LocationMapper {
    int insertLocation(@Param("Location") Location location);

    Location selectLocation(@Param("Location") Location location);

    int UpdateLocation(@Param("Location") Location location);

    int deleteLocation(@Param("Location") Location location);
}
