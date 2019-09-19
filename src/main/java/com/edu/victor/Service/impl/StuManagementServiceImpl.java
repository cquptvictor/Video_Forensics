package com.edu.victor.Service.impl;

import com.edu.victor.Dao.StuManagementDao;
import com.edu.victor.Service.StuManagementService;
import com.edu.victor.domain.StuSearch;
import com.edu.victor.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuManagementServiceImpl implements StuManagementService {
    @Autowired
    StuManagementDao stuManagementDao;
    @Override
    public Boolean addStu(String username) {
        return stuManagementDao.addStu(username);
    }

    @Override
    public Boolean updateStu(Student student) {
        return stuManagementDao.updateStu(student);
    }

    @Override
    public List<Student> searchStu(StuSearch stuSearch) {
        return stuManagementDao.searchStu(stuSearch);
    }
}
