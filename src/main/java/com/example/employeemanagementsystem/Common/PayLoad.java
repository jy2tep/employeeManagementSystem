package com.example.employeemanagementsystem.Common;

import com.example.employeemanagementsystem.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * jwt荷载数据实体类
 */
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Data
public class PayLoad {
    /**
     * tocken的id
     */
    private String jti;

    /**
     * 用户数据
     */
    private User user;
}
