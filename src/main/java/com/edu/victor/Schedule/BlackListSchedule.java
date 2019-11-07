package com.edu.victor.Schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Set;

@Component
public class BlackListSchedule {
    @Autowired
    RedisTemplate redisTemplate;
    @Scheduled(cron = "0/10 * * * * ?")
    public void clear(){
        Set teachers = redisTemplate.keys("Teacher*");
        Set students = redisTemplate.keys("Student*");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,-1);
        Long oneDayAgo = calendar.getTime().getTime();
        for(Object key : teachers){
            redisTemplate.opsForZSet().removeRangeByScore(key,0,oneDayAgo);
        }
        for(Object key : students){
            redisTemplate.opsForZSet().removeRangeByScore(key,0,oneDayAgo);
        }
    }

}
