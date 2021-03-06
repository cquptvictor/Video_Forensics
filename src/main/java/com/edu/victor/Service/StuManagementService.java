package com.edu.victor.Service;

import com.edu.victor.Exception.NotAuthorizedException;
import com.edu.victor.Exception.StuNumNotFound;
import com.edu.victor.domain.Page;
import com.edu.victor.domain.ResponseData;
import com.edu.victor.domain.Student;
import com.edu.victor.domain.User;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Response;

import java.util.List;
import java.util.Map;

public interface StuManagementService {
    ResponseData addStu(String username);
    ResponseData updateStu(Student student);
    ResponseData deleteStu(Integer stuId, User user) throws NotAuthorizedException;
    ResponseData searchStu(Page page);
    ResponseData batchImport(MultipartFile excel) throws StuNumNotFound;
}
