package com.example.employeemanagementsystem.Common;


import com.example.employeemanagementsystem.Excep.UserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(UserException.class)
    @ResponseBody
    public ResponseResult<Void> exceptionHandle(UserException e){
        ResultCode rc = e.getExceptionEnum();
        return new ResponseResult<>(rc);
    }
}