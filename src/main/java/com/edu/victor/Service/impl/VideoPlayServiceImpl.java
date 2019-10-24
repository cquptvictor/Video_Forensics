package com.edu.victor.Service.impl;

import com.edu.victor.Dao.VideoPlayDao;
import com.edu.victor.Service.VideoPlayService;
import com.edu.victor.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
            videoPlay.setLocation("00:00");
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

        redisTemplate.opsForHash().putAll(key,map);
        return responseData;
    }

    @Override
    public ResponseData finishPlay(VideoPlay videoPlay, User user) {
        ResponseData responseData = new ResponseData(200);
        videoPlay.setStuId(user.getId());
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
}
