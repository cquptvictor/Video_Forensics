package com.edu.victor.Service.impl;

import com.edu.victor.Dao.VideoPlayDao;
import com.edu.victor.Service.VideoPlayService;
import com.edu.victor.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class VideoPlayServiceImpl implements VideoPlayService {
    @Autowired
    VideoPlayDao videoPlayDao;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public ResponseData beginPlay(VideoPlay videoPlay, User user) {
        ResponseData responseData = new ResponseData(200);
        if(user.getIsTeacher().equals("0")){
            videoPlay.setStuId(user.getId());
            videoPlay.setOver("0");
            videoPlay.setLocation("000.00");
            if(!videoPlayDao.addNewPlayRecord(videoPlay))
                responseData.setCode(0);
        }else{
            responseData.setCode(0);
        }
        return responseData;
    }

    @Override
    public ResponseData playing(VideoPlay videoPlay, User user) {
        ResponseData responseData = new ResponseData(200);
        String key = String.format("playProgress_%d_%d",videoPlay.getSecId(),user.getId());
        Map<String,Object> map = new HashMap<>();
        map.put("location",videoPlay.getLocation());
        map.put("stuId",user.getId()+"");
        map.put("secId",videoPlay.getSecId()+"");
        map.put("time",new Date().getTime()+"");
        //存入值，同时设置过期时间
        redisTemplate.opsForHash().putAll(key,map);
        redisTemplate.expire(key,60,TimeUnit.SECONDS);
        return responseData;
    }

    @Override
    public ResponseData finishPlay(VideoPlay videoPlay, User user) {
        ResponseData responseData = new ResponseData(200);
        videoPlay.setStuId(user.getId());
        videoPlay.setOver("1");
        if(!videoPlayDao.playOver(videoPlay))
            responseData.setCode(0);
        return responseData;
    }

    @Override
    public ResponseData publishVideoComment(VideoComment videoComment, User user) {
        ResponseData responseData = new ResponseData(200);
        videoComment.setStuId(user.getId());
        if(!videoPlayDao.pubComment(videoComment))
            responseData.setCode(0);
        return responseData;
    }

    @Override
    public ResponseData showVideoCommentList(Page page, Integer secId) {
        ResponseData responseData = new ResponseData(200);
        //封装条件
        Map<String,Object> map = new HashMap<>();
        map.put("secId",secId);
        page.setFilter(map);
        Page<VideoCommentDto> page1 = videoPlayDao.getVideoCommentListByPage(page);
        page.setPageData(page1 != null ? page1.getPageData():null);
        responseData.setData(page);
        return responseData;
    }
    /**
     * 从redis中读取历史记录的secitonId，
     * 根据SectionId到数据库中进行查询数据*/
    @Override
    public ResponseData getLastRecord(User user) {
        ResponseData responseData = new ResponseData(200);
        String key = String.format("history%d", user.getId());
        List<Integer> idList = redisTemplate.opsForList().range(key,0,10);
        Map<String, Object> map = new HashMap<>();
        map.put("idList",idList);
        map.put("stuId",user.getId());
        responseData.setData(videoPlayDao.getHistoryRecord(map));
        return responseData;
    }
    /**添加入redis历史记录中*/
    @Override
    public ResponseData addLastRecord(Integer id, User user) {
        ResponseData responseData = new ResponseData(200);
        String key = String.format("history%d", user.getId());
        //连续两次看同一个视频，就不变
        if(id == redisTemplate.opsForList().index(key,0)){
         return responseData;
        }
        redisTemplate.opsForList().leftPush(key,id +"");
        /**超过了10就从右边删除*/
        while(redisTemplate.opsForList().size(key) > 10){
            redisTemplate.opsForList().rightPop(key);
        }
        return responseData;
    }
}
