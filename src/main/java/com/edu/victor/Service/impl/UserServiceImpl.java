package com.edu.victor.Service.impl;

import com.edu.victor.Dao.UserDao;
import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.Service.UserService;
import com.edu.victor.domain.*;
import com.edu.victor.utils.JWT;
import com.edu.victor.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Override
    public ResponseData login(Teacher teacher) {
        ResponseData responseData = new ResponseData();
        Teacher teacher1 = userDao.teacherLogin(teacher);
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

    /**每个用户对应一个固定的key
     * 设置一个为期20分钟的black list*/
    @Override
    public ResponseData logout(String token,User user) {
        String key = String.format("%s%d",user.getIsTeacher().equals("1")?"Teacher":"Student",user.getId());
        redisTemplate.opsForValue().set(key,token,20,TimeUnit.MINUTES);
        ResponseData responseData = new ResponseData(200);
        return responseData;
    }

    @Override
    public ResponseData updateTeaInfo(Teacher teacher) {
        ResponseData responseData = new ResponseData();
        if(userDao.updateTeaInfo(teacher))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;
    }

    @Override
    public ResponseData updateStuInfo(Student student) {
        ResponseData responseData = new ResponseData();
        if(userDao.updateStuInfo(student))
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
    /**验证老师的信息是不是完整的*/
    @Override
    public Teacher isCompleted(Teacher teacher) throws IncompleteInformationException {
        if(teacher.getName() == null || teacher.getEmail() == null){
            teacher = userDao.teacherInfo(teacher.getId());
            if(teacher.getName() == null || teacher.getEmail() == null)
                throw new IncompleteInformationException();
        }
        return teacher;
    }

    @Override
    public ResponseData getStuInfo(int id) {
        return null;
    }

    @Override
    public ResponseData getTeaInfo(int id) {
        ResponseData responseData = new ResponseData(200);
        Teacher teacher = userDao.teacherInfo(id);
        if(teacher == null) {
            responseData.setCode(0);
            responseData.setData(null);
        }else{
            responseData.setData(teacher);
        }
        return responseData;
    }

    @Override
    public ResponseData updateAvatar(MultipartFile multipartFile, User user, Boolean isTeacher) throws UnsupportedFileTypeException, IOException {
            /**先查询，保证信息是新的再更新*/
            if(isTeacher){
                user = userDao.teacherInfo(user.getId());
            }else
                user = userDao.studentInfo(user.getId());
            String url = UploadUtils.updateAvatar(multipartFile, user.getAvatar()+"");
            user.setAvatar(url);
            Boolean result;
            if(isTeacher){
                result = userDao.updateTeaAvatar(user);
            }else{
                result = userDao.updateStuAvatar(user);
            }
            ResponseData responseData = new ResponseData();
            if(result){
                responseData.setCode(200);
            }else
                responseData.setCode(0);
            return responseData;
     }

    @Override
    public ResponseData getMessages(Page page) {
        ResponseData responseData = new ResponseData(200);
        Page page1 = userDao.getUserMessagesByPage(page);
        page.setPageData(page1 != null ? page1.getPageData():null);
        responseData.setData(page);
        return responseData;
    }
}
