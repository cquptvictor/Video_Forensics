package com.edu.victor.Dao;

import com.edu.victor.domain.Student;
import com.edu.victor.domain.Teacher;
import com.edu.victor.domain.User;

import java.util.List;


public interface UserDao {
    Teacher teacherLogin(User user);
    Student studentLogin(User user);
    Boolean updateTeaInfo(Teacher teacher);
    Boolean updateStuInfo(Student student);
    Teacher teacherInfo(Integer id);
    Student studentInfo(Integer id);
    Boolean updateTeaAvatar(User user);
    Boolean updateStuAvatar(User user);
  /*  List<User> getAllTeachers();
    List<User> getAllStudents();*/
}
