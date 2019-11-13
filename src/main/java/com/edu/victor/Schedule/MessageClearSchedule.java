package com.edu.victor.Schedule;

import com.edu.victor.Dao.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;


//每隔一段时间清理我的消息
@Component
public class MessageClearSchedule {
    @Autowired
    MessageDao messageDao;

    @Scheduled(cron = "0 0 0/7 * * ?")
    public void clear(){
        Calendar calendar = Calendar.getInstance();
        //calendar.add(Calendar.MONTH,- 3);
        calendar.add(Calendar.DAY_OF_YEAR,-1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        messageDao.clearExpiredMessages(simpleDateFormat.format(calendar.getTime()));
    }

}
