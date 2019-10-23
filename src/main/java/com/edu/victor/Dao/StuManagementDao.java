package com.edu.victor.Dao;

import com.edu.victor.domain.Page;
import com.edu.victor.domain.Student;
import com.edu.victor.domain.StudentDto;


public interface StuManagementDao {
    Boolean addStu(String username);
    Boolean updateStu(Student student);
    Page<StudentDto> searchStuByPage(Page<Student> page);
    Boolean batchImport(Student student);
}
