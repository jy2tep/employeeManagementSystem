package com.example.employeemanagementsystem.From;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;


@Data
public class UserForm {
    @NotEmpty(message = "账号不能为空")
    private String userName;
    @NotEmpty(message = "密码不能为空")
    private String password;
}
