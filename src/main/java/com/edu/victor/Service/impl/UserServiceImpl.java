package com.edu.victor.Service.impl;

import com.edu.victor.Dao.MessageDao;
import com.edu.victor.Dao.UserDao;
import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.Service.UserService;
import com.edu.victor.domain.*;
import com.edu.victor.utils.JWT;
import com.edu.victor.utils.FileUtils;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    MessageDao messageDao;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Override
    public ResponseData login(User user) {
        ResponseData responseData = new ResponseData();
        Teacher teacher;
        Student student;
        if(user.getIsTeacher().equals("1")) {
            teacher = userDao.teacherLogin(user);
            teacher.setIsTeacher("1");
            if(teacher.getId() != -1){
                responseData.setCode(200);
                responseData.setMessage("login Successful");
                Map<String,String> map = new HashMap();
                map.put("token", JWT.sign(teacher,12000000));
                responseData.setData(map);
            }else{
                responseData.setCode(0);
                responseData.setMessage("login Failed");
                }
        }else{
            student = userDao.studentLogin(user);
            student.setIsTeacher("0");
            if(student.getId() != -1){
                responseData.setCode(200);
                responseData.setMessage("login Successful");
                Map<String,String> map = new HashMap();
                map.put("token", JWT.sign(student,12000000));
                responseData.setData(map);
            }else{
                responseData.setCode(0);
                responseData.setMessage("login Failed");
            }
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
    public ResponseData getInfo(User user) {
        ResponseData responseData = new ResponseData(200);
        if (user.getIsTeacher().equals("1")) {
            Teacher teacher = userDao.teacherInfo(user.getId());
            if(teacher == null){
                responseData.setCode(0);
            }else{
                responseData.setData(teacher);
            }
        } else {
            Student student = userDao.studentInfo(user.getId());
            if(student == null){
                responseData.setCode(0);
            }else {
                responseData.setData(student);
            }
        }
        return responseData;
    }

    /*@Override
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
*/
    @Override
    public ResponseData updateAvatar(MultipartFile multipartFile, User user, Boolean isTeacher) throws UnsupportedFileTypeException, IOException {
            /**先查询，保证信息是新的再更新*/
            if(isTeacher){
                user = userDao.teacherInfo(user.getId());
            }else
                user = userDao.studentInfo(user.getId());
            String url = FileUtils.updateAvatar(multipartFile, user.getAvatar()+"");
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
    @Transactional
    public ResponseData getMessages(Page page,User user) {
        /**封装条件*/
        Map<String,Object> map = new HashMap<>();
        map.put("isTeacher",user.getIsTeacher());
        map.put("targetId",user.getId());
        page.setFilter(map);
        ResponseData responseData = new ResponseData(200);
        Page page1 = messageDao.getUserMessagesByPage(page);
        List<Message> messageList = page1 != null ? page1.getPageData() : null;

        page.setPageData(page1 != null ? page1.getPageData():null);
        responseData.setData(page);
        return responseData;
    }

    @Override
    public ResponseData getUnreadMessageNum(User user) {
        Map<String,Object> map = new HashMap<>();
        map.put("isTeacher",user.getIsTeacher());
        map.put("targetId",user.getId());

        Map<String,Object> resultMap = new HashMap<>();
        int nums = messageDao.getUnreadMessageNum(map);
        resultMap.put("numbers",nums);
        ResponseData responseData = new ResponseData(200);
        responseData.setData(resultMap);
        return responseData;
    }
    /**对于非聊天消息，直接标记为已读
     * 对于聊天消息，把该帖子的所有消息直接标记为已读*/
    @Override
    public ResponseData MarkUnreadAsRead( MarkRead markRead,User user) {
        //0表示非问答
        Map<String, Object> map = new HashMap<>();
        List<Integer> idList = new ArrayList<>();
        if(markRead.getType().equals("1")) {/**
         * 1.根据id查询出ContentId
         * 2.根据targetId和ContentId得到所有的id*/
            String contentId = messageDao.getContentId(markRead.getMsgUserId());
            map.put("contentId", contentId);
            map.put("targetId", user.getId());
            map.put("isTeacher", user.getIsTeacher());
            idList = messageDao.getMsgUserIdList(map);
        }else{
            idList.add(markRead.getMsgUserId());
        }
        map.put("idList", idList);
        ResponseData responseData = new ResponseData(200);
        if(!messageDao.markAsRead(map))
            responseData.setCode(0);
        return responseData;

    }
}
