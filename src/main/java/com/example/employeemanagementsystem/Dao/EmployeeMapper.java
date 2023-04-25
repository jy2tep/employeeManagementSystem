package com.example.employeemanagementsystem.Dao;

import com.example.employeemanagementsystem.Model.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    int insertEmployee(@Param("Employee") Employee employee);

    List<Employee> selectEmployee();

    Employee selectEmployeeByOne(@Param("Employee") Employee employee);

    int UpdateEmployee(@Param("Employee") Employee employee);

    int deleteEmployee(@Param("Employee") Employee employee);
}
