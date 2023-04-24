package com.example.employeemanagementsystem.From;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;


@Data
public class UserNameFrom {
    @NotEmpty(message = "账号不能为空")
    private String name;
    @NotEmpty(message = "密码不能为空")
    private String age;
}