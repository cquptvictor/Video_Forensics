package com.edu.victor.Exception;

import com.edu.victor.Service.impl.DownloadFileNotFoundException;
import com.edu.victor.domain.ResponseData;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Time;

@ControllerAdvice
public class GlobalException {
    /**处理账号重复的问题*/
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public ResponseData duplicatedUsername(DuplicateKeyException e){
        ResponseData responseData = new ResponseData();
        responseData.setCode(0);
        responseData.setMessage("Duplicated");
        return responseData;
    }
    /**权限不够的问题*/
    @ExceptionHandler(NotAuthorizedException.class)
    @ResponseBody
    public ResponseData notAuthorized(NotAuthorizedException e){
        ResponseData responseData = new ResponseData();
        responseData.setCode(403);
        responseData.setMessage("You do not have permission to perform this operation");
        return responseData;
    }

    /**信息不完善的问题*/
    @ExceptionHandler(IncompleteInformationException.class)
    @ResponseBody
    public ResponseData incompleteInformation(){
        ResponseData responseData = new ResponseData();
        responseData.setCode(0);
        responseData.setMessage("You need to complete your information first");
        return responseData;
    }
    /**文件上传类型不符合规定*/
    @ExceptionHandler(UnsupportedFileTypeException.class)
    @ResponseBody
    public ResponseData unsupportedFileType(){
        ResponseData responseData = new ResponseData();
        responseData.setCode(0);
        responseData.setMessage("You need to upload correct file type");
        return responseData;
    }
    /**参数不合法*/
    @ExceptionHandler(InvalidArgumentsException.class)
    @ResponseBody
    public ResponseData invalidArguments(){
        ResponseData responseData = new ResponseData();
        responseData.setCode(0);
        responseData.setMessage("Please enter a valid argument");
        return responseData;
    }
    /**下载的文件找不到*/
    @ExceptionHandler(DownloadFileNotFoundException.class)
    @ResponseBody
    public ResponseData DownloadNotFound(){
        ResponseData responseData = new ResponseData();
        responseData.setCode(0);
        responseData.setMessage("The File you want to download not Found");
        return responseData;
    }
    /**传入的参数错误*/
    @ExceptionHandler(WrongArgumentsException.class)
    @ResponseBody
    public ResponseData WrongArguments(){
        ResponseData responseData = new ResponseData();
        responseData.setCode(0);
        responseData.setMessage("Please enter correct arguments");
        return responseData;
    }
    /**作业发布设置的时间错误*/
    @ExceptionHandler(TimeSettingException.class)
    @ResponseBody
    public ResponseData TimeSettingException(){
        ResponseData responseData = new ResponseData();
        responseData.setCode(0);
        responseData.setMessage("start time must before the end time");
        return responseData;
    }}
