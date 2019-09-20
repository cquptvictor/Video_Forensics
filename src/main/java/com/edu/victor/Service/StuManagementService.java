package com.edu.victor.Service;

import com.edu.victor.domain.Page;
import com.edu.victor.domain.Student;

import java.util.List;

public interface StuManagementService {
    Boolean addStu(String username);
    Boolean updateStu(Student student);
    Page<Student> searchStu(Page<Student> page);
    int batchImport(List<Student> list);
    List<Student> test();
}
