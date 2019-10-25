package com.edu.victor.Schedule;

import com.edu.victor.Dao.VideoPlayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class playProgressSchedule {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    VideoPlayDao videoPlayDao;
    /**1.同步redis数据到mysql
     * 2.删除redis中的过期数据*/
    @Scheduled(cron = "0 0/1 * * * ?")
    public void updateStuProgressToMysql(){
        Set<String> keys = redisTemplate.keys("playProgress*");
        System.out.println(keys.size());
       // Long now = new Date().getTime();
        for(String key : keys){
            Map<String,Object> map = redisTemplate.opsForHash().entries(key);
            //时间间隔大于5分钟，说明已经更新到数据库过了,需要删除
            //System.out.println(Long.valueOf((String)map.get("time")) + 60000L - now);
            //if(Long.valueOf((String)map.get("time")) + 60000L > now){
                videoPlayDao.updatePlayRecord(map);
            //}
        }
    }
}
