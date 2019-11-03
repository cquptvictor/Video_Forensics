package com.edu.victor.Service;

import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.NotAuthorizedException;
import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.Service.impl.DownloadFileNotFoundException;
import com.edu.victor.domain.*;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface HWService {
    ResponseData publishHW(Homework homework, Teacher teacher) throws IncompleteInformationException;
    ResponseData getHwList(Homework homework,Page page, User user);
    ResponseData getSpecificPage(int id,User user);
    ResponseData getSubmitedHomework(Homework homework,Page page);
    ResponseData updateHw(Homework homework);
    ResponseData deleteHw(Map<String,Integer> map) throws NotAuthorizedException;
    ResponseData judgeHw(Judge judge);
    //ResponseEntity<byte[]> downloadHw(int id) throws DownloadFileNotFoundException;
    //APP
    ResponseData submitHw(SubmittedHomework submittedHw,User user) throws UnsupportedFileTypeException;
    ResponseData getMySubmittion(int id,User user);
}
