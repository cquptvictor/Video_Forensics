package com.edu.victor.Dao;

import com.edu.victor.domain.Page;
import com.edu.victor.domain.Student;

import java.util.List;


public interface StuManagementDao {
    Boolean addStu(String username);
    Boolean updateStu(Student student);
    Page<Student> searchStuByPage(Page<Student> page);
    Boolean batchImport(Student student);
    List<Student> test();
}
