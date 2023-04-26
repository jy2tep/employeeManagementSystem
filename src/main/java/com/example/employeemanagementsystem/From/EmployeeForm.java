package com.example.employeemanagementsystem.From;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;


@Data
public class EmployeeForm extends HeaderF{
    private Integer employeeId;
    private String employeeName;
    private String gender;
    private Integer age;
    private BigDecimal baseSalary;
    private Integer locationId;
    private String locationName;
    private BigDecimal latitude;
    private BigDecimal longitude;
}