package com.edu.victor.Exception;

import com.edu.victor.domain.ResponseData;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {
    /**处理账号重复的问题*/
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public ResponseData duplicatedUsername(DuplicateKeyException e){
        ResponseData responseData = new ResponseData();
        responseData.setCode(0);
        responseData.setMessage("Duplicate usernames");
        return responseData;
    }
    /**权限不够的问题*/
    @ExceptionHandler(NotAuthorizedException.class)
    @ResponseBody
    public ResponseData duplicatedUsername(NotAuthorizedException e){
        ResponseData responseData = new ResponseData();
        responseData.setCode(0);
        responseData.setMessage("You do not have permission to perform this operation");
        return responseData;
    }
}