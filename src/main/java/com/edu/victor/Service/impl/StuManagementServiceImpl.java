package com.edu.victor.Service.impl;

import com.edu.victor.Dao.StuManagementDao;
import com.edu.victor.Exception.StuNumNotFound;
import com.edu.victor.Service.StuManagementService;
import com.edu.victor.domain.Page;
import com.edu.victor.domain.ResponseData;
import com.edu.victor.domain.Student;
import com.edu.victor.domain.stuDto;
import com.edu.victor.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class StuManagementServiceImpl implements StuManagementService {
    @Autowired
    StuManagementDao stuManagementDao;
    @Override
    public ResponseData addStu(String username)
    {
        ResponseData responseData = new ResponseData();
        if(stuManagementDao.addStu(username))
        {
            responseData.setCode(200);
        }else
            responseData.setCode(0);
        return responseData;
    }

    @Override
    public ResponseData updateStu(Student student) {
        ResponseData responseData = new ResponseData();
        if(stuManagementDao.updateStu(student))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;
    }



    @Override
    public ResponseData searchStu(Page page) {
        ResponseData<Page> responseData = new ResponseData(200);
        Page<stuDto> page2 = stuManagementDao.searchStuByPage(page);
        page.setPageData(page2 != null ? page2.getPageData():null);
        responseData.setData(page);
        return responseData;
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
    public ResponseData batchImport(MultipartFile excel) throws StuNumNotFound {

        List<Student> list = ExcelUtils.excelToStudent(excel);
        int duplicateNum = 0;
        for(Student student : list){
            try{
            stuManagementDao.batchImport(student);
            }catch (DuplicateKeyException e){
                duplicateNum++;
            }
        }
        ResponseData responseData = new ResponseData(200);
        String message = String.format("一共导入%d个，已存在%d个",list.size(),duplicateNum);
        responseData.setMessage(message);
        return responseData;
    }

}
