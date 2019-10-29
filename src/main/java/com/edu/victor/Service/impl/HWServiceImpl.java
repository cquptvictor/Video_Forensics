package com.edu.victor.Service.impl;

import com.edu.victor.Dao.CourseDao;
import com.edu.victor.Dao.HWDao;
import com.edu.victor.Dao.MessageDao;
import com.edu.victor.Dao.UserDao;
import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.NotAuthorizedException;
import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.Service.HWService;
import com.edu.victor.Service.UserService;
import com.edu.victor.domain.*;
import com.edu.victor.utils.FileUtils;
import com.edu.victor.utils.MessageCreateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HWServiceImpl implements HWService {
    @Autowired
    HWDao hwDao;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Autowired
    UserService teacherService;
    @Autowired
    CourseDao courseDao;
    @Autowired
    UserDao teacherDao;
    @Autowired
    MessageDao messageDao;
    /**hw_id_start : value
     * hw_id_end : value*/
    @Transactional
    @Override
    public ResponseData publishHW(Homework homework, Teacher teacher) throws IncompleteInformationException {
        teacherService.isCompleted(teacher);
        ResponseData responseData = new ResponseData(200);
        //添加到作业表
        if(!hwDao.publishHW(homework))
        {
            responseData.setCode(0);
            return responseData;
        }
        int id = homework.getId();
        //查出老师名字，创建message，添加到message表
        teacher = teacherDao.teacherInfo(teacher.getId());
        Message message = MessageCreateUtils.createHwMessage(homework,teacher.getName());
        messageDao.addMessage(message);
        //查询课程下的学生id，存入通知-学生表
        List<User> students = courseDao.getUngraduatedStuByCourse(homework.getCourseId());
        List<MsgUser> list = MessageCreateUtils.createMsgUser(message.getId(),students);
        if(list != null && list.size() != 0)
        messageDao.addMsgUser(list);
        /**构造redis中的key*/
        String startKey = String.format("hw_%d_start",id);
        String endKey = String.format("hw_%d_end",id);
        /**添加时间限制和发送消息*/
        redisTemplate.opsForValue().set(startKey,String.valueOf(homework.getStartTime()));
        redisTemplate.opsForValue().set(endKey,String.valueOf(homework.getEndTime()));
        return  responseData;
    }

    @Override
    public ResponseData getHwList(Homework homework,Page page,User user) {
        Map map = new HashMap<>();
        Page page1 = null;
        map.put("courseId",homework.getCourseId());
        map.put("category",homework.getCategory());
        ResponseData responseData = new ResponseData(200);
        if(user.getIsTeacher().equals("1")) {
            page.setFilter(map);
            page1 = hwDao.getHwByCourseByPage(page);
        }else{
            map.put("stuId",user.getId());
            page.setFilter(map);
            page1 = hwDao.getHwByCourseByPageForStu(page);
        }
        page.setPageData(page1 != null ? page1.getPageData() : null);
        responseData.setData(page);
        return responseData;
    }

    @Override
    public ResponseData getSpecificPage(int id, User user) {

        ResponseData responseData = new ResponseData(200);
        if(user.getIsTeacher().equals("0")){
            String key = String.format("hw_%d_start",id);
            Long startTime = Long.valueOf((String)redisTemplate.opsForValue().get(key));
            if(startTime > new Date().getTime()){
                responseData.setCode(0);
                responseData.setMessage("未到开放时间");
                return responseData;
            }
        }
        responseData.setData(hwDao.getSpecificHomework(id));
        return responseData;
    }
    /*老师可以任意查看，学生只能在开始后查看**/
    @Override
    public ResponseData getSubmitedHomework(Page page) {
        ResponseData responseData = new ResponseData(200);
        Page page1 = hwDao.getSubmitedByPage(page);
        page.setPageData(page1 != null ? page1.getPageData():null);
        responseData.setData(page);
        return responseData;
    }

    @Override
    public ResponseData updateHw(Homework homework) {
        ResponseData responseData = new ResponseData();
        if(hwDao.updateHw(homework))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;
    }
    /**1.检查用户是否有权限删除
     * 2.查询关联的提交作业的附件url
     * 3.删除关联的提交作业(数据库记录和文件)
     * 4.删除作业（数据库记录和文件）*/
    @Transactional
    @Override
    public ResponseData deleteHw(Map<String,Integer> map) throws NotAuthorizedException {
        /**权限校验*/
        if(hwDao.isPermitted(map) == 0)
            throw new NotAuthorizedException();
        /**删除学生提交的文件*/
        List<String> hwList = hwDao.getSubmittedHwByHw(map.get("hwId"));
        //UploadUtils.deleteFile(hwList,"homework");
        /**删除作业和提交作业的数据库记录*/
        hwDao.deleteHw(map.get("hwId"));
        return new ResponseData(200);
    }

    @Override
    public ResponseData judgeHw(Judge judge) {
        ResponseData responseData = new ResponseData();
        if(hwDao.judgeHw(judge))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;
    }

  /*  @Override
    public ResponseEntity<byte[]> downloadHw(int id) throws DownloadFileNotFoundException {
        String url = hwDao.getUrlBySubmittedHw(id);
        return FileUtils.dowmloadSubmittedHomework(url);
    }*/

    //APP端
    /**学生提交作业
     * 1.检查是否是第一次提交
     * 2.文件上传
     * 3.赋值url
     * 4.插入数据库
     * */
    @Override
    public ResponseData submitHw(SubmittedHomework submittedHomework, User user) throws UnsupportedFileTypeException {
        ResponseData responseData = new ResponseData(0);
        String key = String.format("hw_%d_end",submittedHomework.getHwId());
        Long endTime =Long.valueOf((String)redisTemplate.opsForValue().get(key));
        if(endTime > new Date().getTime())
        {
        if(user.getIsTeacher().equals("0")){
            submittedHomework.setStuId(user.getId());
            String url = hwDao.getSubmittedHomeworkUrl(submittedHomework);
            if(url == null) {
                    //第一次
                url = FileUtils.saveImage(submittedHomework.getFile(), "homework");
                submittedHomework.setUrl(url);
                if(hwDao.addSubmittedHomework(submittedHomework)){
                    responseData.setCode(200);
                }
            }else{
                url = FileUtils.reSubmit(submittedHomework.getFile(),url);
                submittedHomework.setUrl(url);
                if(hwDao.updateSubmittedHomework(submittedHomework)){
                    responseData.setCode(200);
                }
            }
        }
        }else{
            responseData.setCode(0);
            responseData.setMessage("超过提交截止时间");
        }
        return responseData;
    }

    @Override
    public ResponseData getMySubmittion(int id, User user) {
         Map<String,Object> map  = new HashMap<>();
         map.put("hwId",id);
         map.put("stuId",user.getId());
         ResponseData responseData = new ResponseData(200);
         StudentSubmittion studentSubmittion = hwDao.getStudentSubmittion(map);
         responseData.setData(studentSubmittion);
         return responseData;

    }
}
