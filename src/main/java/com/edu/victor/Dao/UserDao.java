package com.edu.victor.Dao;

import com.edu.victor.domain.Student;
import com.edu.victor.domain.Teacher;
import com.edu.victor.domain.User;

public interface UserDao {
    Teacher teacherLogin(Teacher teacher);
    Boolean updateInfo(Teacher teacher);
    Teacher teacherInfo(Integer id);
    Student studentInfo(Integer id);
    Boolean updateTeaAvatar(User user);
    Boolean updateStuAvatar(User user);

}
