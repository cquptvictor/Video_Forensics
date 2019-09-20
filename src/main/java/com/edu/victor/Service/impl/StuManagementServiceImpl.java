package com.edu.victor.Service.impl;

import com.edu.victor.Dao.StuManagementDao;
import com.edu.victor.Service.StuManagementService;
import com.edu.victor.domain.Page;
import com.edu.victor.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
    public Page<Student> searchStu(Page<Student> page) {
        Page<Student> page2 = stuManagementDao.searchStuByPage(page);
        page.setContent(page2.getContent());
        return page;
    }

   /* @Override
    public Boolean batchImport(List<Student> list) {
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
        StuManagementDao mapper = sqlSession.getMapper(StuManagementDao.class);
        for(Student student : list){
            mapper.batchImport(student);
        }
        sqlSession.commit();
        return true;
    }*/
   /**捕获已经存在的账号重复导入的错误*/
    @Override
    public int batchImport(List<Student> list) {
        int duplicateNum = 0;
        for(Student student : list){
            try{
            stuManagementDao.batchImport(student);
            }catch (DuplicateKeyException e){
                duplicateNum++;
            }
        }
        return duplicateNum;
    }

    @Override
    public List<Student> test() {
        return stuManagementDao.test();
    }
}
