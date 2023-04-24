package com.example.employeemanagementsystem.Dao;

import com.example.employeemanagementsystem.Model.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    int insertEmployee(@Param("Employee") Employee employee);

    List<Employee> selectEmployee(@Param("Employee") Employee employee);
}
