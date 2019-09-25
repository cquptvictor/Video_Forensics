package com.edu.victor.Service.impl;

import com.edu.victor.Dao.HWDao;
import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Service.HWService;
import com.edu.victor.Service.TeacherService;
import com.edu.victor.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
    TeacherService teacherService;
    /**hw_id_start : value
     * hw_id_end : value*/
    @Override
    public ResponseData publishHW(Homework homework, Teacher teacher) throws IncompleteInformationException {
        teacherService.isCompleted(teacher);
        ResponseData responseData = new ResponseData(200);
        if(!hwDao.publishHW(homework))
        {
            responseData.setCode(0);
            return responseData;
        }
        int id = homework.getId();
        /**构造redis中的key*/
        String startKey = String.format("hw_%d_start",id);
        String endKey = String.format("hw_%d_end",id);
        /**添加时间限制和发送消息*/
        redisTemplate.opsForValue().set(startKey,String.valueOf(homework.getStartTime().getTime()));
        redisTemplate.opsForValue().set(endKey,String.valueOf(homework.getEndTime().getTime()));
        sendNotice(homework.getToUser(),String.valueOf(homework.getId()));
        return  responseData;
    }
    /**把要发送的通知添加到redis
     *stu_id list*/
    private void sendNotice(int[] toUser,String noticeId) {
        for (int id : toUser) {
            redisTemplate.opsForList().leftPush("stu_"+ id,noticeId);
        }
    }

    @Override
    public ResponseData getHwList(Page page) {
        ResponseData responseData = new ResponseData(200);
        page.setPageData(hwDao.getHwByCourseByPage(page).getPageData());
        responseData.setData(page);
        return responseData;
    }

    @Override
    public ResponseData getSpecificPage(int id) {
        ResponseData responseData = new ResponseData();
        responseData.setData(hwDao.getSpecificHomework(id));
        return responseData;
    }

    @Override
    public ResponseData getSubmitedHomework(Page page) {
        ResponseData responseData = new ResponseData(200);
        page.setPageData(hwDao.getSubmitedByPage(page).getPageData());
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
     * 3.删除关联的提交作业
     * 4.删除提交的文件*/
    @Override
    public ResponseData deleteHw(Homework homework) {
        return null;
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
}
