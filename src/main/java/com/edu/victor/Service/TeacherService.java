package com.edu.victor.Service;


import com.edu.victor.domain.Teacher;

public interface TeacherService {
    Boolean login(Teacher teacher);
    Boolean updateInfo(Teacher teacher);
}
