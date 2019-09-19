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
        int id = loginDao.teacherLogin(teacher);
        if(id == -1) {
            return false;
        }else{
            teacher.setId(id);
            return true;
        }

    }
}
