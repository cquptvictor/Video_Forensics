package com.edu.victor.Dao;

import com.edu.victor.domain.StuSearch;
import com.edu.victor.domain.Student;

import java.util.List;

public interface StuManagementDao {
    Boolean addStu(String username);
    Boolean updateStu(Student student);
    List<Student> searchStu(StuSearch stuSearch);
    Boolean batchImport(Student student);
}
