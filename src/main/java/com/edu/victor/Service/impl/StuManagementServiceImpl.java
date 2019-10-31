package com.edu.victor.Service.impl;

import com.edu.victor.Dao.StuManagementDao;
import com.edu.victor.Dao.UserDao;
import com.edu.victor.Exception.NotAuthorizedException;
import com.edu.victor.Exception.StuNumNotFound;
import com.edu.victor.Service.StuManagementService;
import com.edu.victor.domain.*;
import com.edu.victor.utils.ExcelUtils;
import com.edu.victor.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class StuManagementServiceImpl implements StuManagementService {
    @Autowired
    StuManagementDao stuManagementDao;
    @Autowired
    UserDao userDao;
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
    /**1.校验是否有权限删除
     * 2.删除学生对应的头像文件，作业文件
     * 3.数据库中删除学生记录,以及msg_user表的记录*/
    @Override
    @Transactional
    public ResponseData deleteStu(Integer stuId, User user) throws NotAuthorizedException {
        ResponseData responseData = new ResponseData(0);
        //校验是否有权限
        if(userDao.teacherLogin(user).getId().equals(-1))
            throw new NotAuthorizedException();
        if(user.getIsTeacher().equals("1")){
            //查询，并删除文件
            String avatar = stuManagementDao.getAvatarUrlByStu(stuId);
            List<String> list = stuManagementDao.getSubmittedHomeworkUrlByStu(stuId);
            //默认头像不删除
            if(!avatar.equals("default.jpg"))
                FileUtils.deleteFile(avatar,"avatar");
            FileUtils.deleteFile(list,"homework");
            //删除数据库记录
            stuManagementDao.deleteStudentMessage(stuId);
            stuManagementDao.deleteStudent(stuId);
            responseData.setCode(200);
        }
        return responseData;
    }

    @Override
    public ResponseData searchStu(Page page) {
        ResponseData<Page> responseData = new ResponseData(200);
        Page<StudentDto> page2 = stuManagementDao.searchStuByPage(page);
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
