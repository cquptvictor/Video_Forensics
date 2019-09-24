package com.edu.victor.Service.impl;

import com.edu.victor.Dao.TeacherDao;
import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.Service.TeacherService;
import com.edu.victor.domain.ResponseData;
import com.edu.victor.domain.Teacher;
import com.edu.victor.utils.JWT;
import com.edu.victor.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherDao teacherDao;
    @Override
    public ResponseData login(Teacher teacher) {
        ResponseData responseData = new ResponseData();
        Teacher teacher1 = teacherDao.teacherLogin(teacher);
        if(teacher1.getId() != -1){
            responseData.setCode(200);
            responseData.setMessage("login Successful");
            Map<String,String> map = new HashMap();
            map.put("token", JWT.sign(teacher1,120000));
            responseData.setData(map);
        }else{
            responseData.setCode(0);
            responseData.setMessage("login Failed");
        }
        return responseData;
    }

    @Override
    public ResponseData updateInfo(Teacher teacher) throws UnsupportedFileTypeException {
        ResponseData responseData = new ResponseData();
        String path = null;
        if(teacher.getAvatarFile() != null)
             path = UploadUtils.saveImage(teacher.getAvatarFile(),String.valueOf(teacher.getId()),"avatar");
        teacher.setAvatar(path);
        if(teacherDao.updateInfo(teacher))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;
    }

   /* @Override
    public ResponseData updateAvatar(Teacher teacher) throws UnsupportedFileTypeException {
        ResponseData responseData = new ResponseData();
        String path = UploadUtils.saveImage(teacher.getAvatarFile(),String.valueOf(teacher.getId()),"avatar");
        teacher.setAvatar(path);
        if(loginDao.updateInfo(teacher))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;
    }*/
    /**验证用户的信息是不是完整的*/
    @Override
    public Teacher isCompleted(Teacher teacher) throws IncompleteInformationException {
        if(teacher.getName() == null || teacher.getEmail() == null){
            teacher = teacherDao.teacherInfo(teacher.getId());
            if(teacher.getName() == null || teacher.getEmail() == null)
                throw new IncompleteInformationException();
        }
        return teacher;
    }

}
