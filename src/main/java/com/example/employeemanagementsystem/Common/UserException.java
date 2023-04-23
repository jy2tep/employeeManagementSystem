package com.example.employeemanagementsystem.Common;

import lombok.Data;

@Data
public class UserException extends RuntimeException{

    private ResultCode resultCode;

    public UserException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public UserException() {
    }

    public ResultCode getExceptionEnum() {
        return resultCode;
    }

    public void setExceptionEnum(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

}
