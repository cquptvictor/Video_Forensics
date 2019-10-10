package com.edu.victor.Dao;

import com.edu.victor.domain.Page;
import com.edu.victor.domain.Student;
import com.edu.victor.domain.Teacher;
import com.edu.victor.domain.User;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface UserDao {
    Teacher teacherLogin(Teacher teacher);
    Boolean updateTeaInfo(Teacher teacher);
    Boolean updateStuInfo(Student student);
    Teacher teacherInfo(Integer id);
    Student studentInfo(Integer id);
    Boolean updateTeaAvatar(User user);
    Boolean updateStuAvatar(User user);
    Page getUserMessagesByPage(Page page);
    Integer getUnreadMessageNum(Map map);
    Boolean setAlreadyRead(@Param("map") Map map);
}
