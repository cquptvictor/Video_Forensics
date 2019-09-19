package com.edu.victor.Service;

import com.edu.victor.domain.StuSearch;
import com.edu.victor.domain.Student;

import java.util.List;

public interface StuManagementService {
    Boolean addStu(String username);
    Boolean updateStu(Student student);
    List<Student> searchStu(StuSearch stuSearch);
}
