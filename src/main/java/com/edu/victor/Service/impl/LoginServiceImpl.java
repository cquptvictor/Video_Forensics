package com.edu.victor.Service.impl;

import com.edu.victor.Dao.LoginDao;
import com.edu.victor.Service.LoginService;
import com.edu.victor.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginDao loginDao;
    @Override
    public Boolean login(Teacher teacher) {
        Teacher teacher1 = loginDao.teacherLogin(teacher);
        if(teacher1.getId() == -1) {
            return false;
        }else{
            teacher.setId(teacher1.getId());
            teacher.setEmail(teacher1.getEmail());
            teacher.setName(teacher1.getName());
            teacher.setUsername(null);
            return true;
        }
    }

}
