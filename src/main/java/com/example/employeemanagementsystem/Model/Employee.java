package com.example.employeemanagementsystem.Model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Employee {
    private Integer employeeId;
    private String employeeName;
    private String gender;
    private Integer age;
    private BigDecimal baseSalary;
    private Integer locationId;
}