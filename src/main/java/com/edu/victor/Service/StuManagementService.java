package com.edu.victor.Service;

import com.edu.victor.Exception.StuNumNotFound;
import com.edu.victor.domain.Page;
import com.edu.victor.domain.ResponseData;
import com.edu.victor.domain.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface StuManagementService {
    ResponseData addStu(String username);
    ResponseData updateStu(Student student);
    ResponseData searchStu(Page page);
    ResponseData batchImport(MultipartFile excel) throws StuNumNotFound;
}
