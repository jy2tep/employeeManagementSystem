package com.example.employeemanagementsystem.Api;

import com.example.employeemanagementsystem.Common.ResponseResult;
import com.example.employeemanagementsystem.Common.ResultCode;
import com.example.employeemanagementsystem.From.EmployeeForm;
import com.example.employeemanagementsystem.From.UserForm;
import com.example.employeemanagementsystem.Service.EmployeeService;
import com.example.employeemanagementsystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/employee/handle/")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    HttpServletRequest httpServletRequest;
    @RequestMapping(value = "insert")
    public ResponseResult<Map> userinsert(@Valid @RequestBody EmployeeForm employeeForm){
        employeeForm.setToken(httpServletRequest.getHeader("xAuthtoken"));
        employeeForm.setUserName(httpServletRequest.getHeader("userName"));
        Map map = employeeService.userCreate(employeeForm);
        return new ResponseResult<Map>(new ResultCode(true,0,"注册成功"),map);
    }

//    @RequestMapping(value = "update")
//    public ResponseResult<Map> userupdate(@Valid @RequestBody EmployeeForm employeeForm){
//        employeeForm.setToken(httpServletRequest.getHeader("xAuthtoken"));
//        employeeForm.setUserName(httpServletRequest.getHeader("userName"));
//        Map map = userService.userSignin(userForm);
//        httpServletResponse.setHeader("xAuthtoken",String.valueOf(map.get("xAuthtoken")));
//        return new ResponseResult<Map>(new ResultCode(true,0,"登录成功"),map);
//    }
//
//    @RequestMapping(value = "select")
//    public ResponseResult<Map> userselect(@Valid @RequestBody EmployeeForm employeeForm){
//        employeeForm.setToken(httpServletRequest.getHeader("xAuthtoken"));
//        employeeForm.setUserName(httpServletRequest.getHeader("userName"));
//        Map map = userService.userSignin(userForm);
//        httpServletResponse.setHeader("xAuthtoken",String.valueOf(map.get("xAuthtoken")));
//        return new ResponseResult<Map>(new ResultCode(true,0,"登录成功"),map);
//    }
//
//    @RequestMapping(value = "delete")
//    public ResponseResult<Map> userdelete(@Valid @RequestBody EmployeeForm employeeForm){
//        employeeForm.setToken(httpServletRequest.getHeader("xAuthtoken"));
//        employeeForm.setUserName(httpServletRequest.getHeader("userName"));
//        Map map = userService.userSignin(userForm);
//        httpServletResponse.setHeader("xAuthtoken",String.valueOf(map.get("xAuthtoken")));
//        return new ResponseResult<Map>(new ResultCode(true,0,"登录成功"),map);
//    }

}
