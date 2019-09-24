package com.edu.victor.Service.impl;

import com.edu.victor.Dao.HWDao;
import com.edu.victor.Service.HWService;
import com.edu.victor.domain.Homework;
import com.edu.victor.domain.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class HWServiceImpl implements HWService {
    @Autowired
    HWDao hwDao;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Override
    public ResponseData publishHW(Homework homework) {
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
        redisTemplate.opsForValue().set(startKey,homework.getStart_time().getTime());
        redisTemplate.opsForValue().set(endKey,homework.getEnd_time().getTime());
        sendNotice(homework.getToUser(),homework.getId());
        return  responseData;
    }
    /**把要发送的通知添加到redis*/
    private void sendNotice(int[] toUser,int noticeId) {
        for (int id : toUser) {
            redisTemplate.opsForList().leftPush("stu_"+ id,noticeId);
        }
    }

}
