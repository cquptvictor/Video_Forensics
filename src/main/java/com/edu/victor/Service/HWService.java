package com.edu.victor.Service;

import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.NotAuthorizedException;
import com.edu.victor.Service.impl.DownloadFileNotFoundException;
import com.edu.victor.domain.*;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface HWService {
    ResponseData publishHW(Homework homework, Teacher teacher) throws IncompleteInformationException;
    ResponseData getHwList(Page page);
    ResponseData getSpecificPage(int id);
    ResponseData getSubmitedHomework(Page page);
    ResponseData updateHw(Homework homework);
    ResponseData deleteHw(Map<String,Integer> map) throws NotAuthorizedException;
    ResponseData judgeHw(Judge judge);
    ResponseEntity<byte[]> downloadHw(int id) throws DownloadFileNotFoundException;
}
