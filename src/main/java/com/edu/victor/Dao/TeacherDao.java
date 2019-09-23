package com.edu.victor.Dao;

import com.edu.victor.domain.Teacher;

public interface TeacherDao {
    Teacher teacherLogin(Teacher teacher);
    Boolean updateInfo(Teacher teacher);
    Teacher teacherInfo(Integer id);

}
