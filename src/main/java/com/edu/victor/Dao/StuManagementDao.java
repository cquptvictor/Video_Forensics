package com.edu.victor.Dao;

import com.edu.victor.domain.Page;
import com.edu.victor.domain.Student;
import com.edu.victor.domain.StudentDto;

import java.util.List;


public interface StuManagementDao {
    Boolean addStu(String username);
    Boolean updateStu(Student student);
    Boolean deleteStudent(Integer id);
    Boolean deleteStudentMessage(Integer id);
    Page<StudentDto> searchStuByPage(Page<Student> page);
    Boolean batchImport(Student student);
    List<String> getSubmittedHomeworkUrlByStu(Integer id);
    String getAvatarUrlByStu(Integer id);
}
