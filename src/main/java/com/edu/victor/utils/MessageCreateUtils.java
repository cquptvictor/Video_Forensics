package com.edu.victor.utils;

import com.edu.victor.domain.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**分别创建通知、回复、作业发布的模板*/
public class MessageCreateUtils {
    public static Message createHwMessage(Homework homework,String name){
        Message message = new Message();
        message.setContent(homework.getTitle());
        message.setCategory("homework");
        message.setContentId(homework.getId()+"_"+homework.getCourseId());
        message.setName(name);
        message.setTime(new Date());
        return message;
    }
    public static Message createNtMessage(Notice notice,String name){
           Message message = new Message();
           message.setTime(new Date());
           message.setContent(notice.getTitle());
           message.setCategory("notice");
           message.setContentId(notice.getId()+"_"+notice.getCourseId());
           message.setName(name);
           return message;
    }
    public static Message createRyMessage(Answer answer, String name,String type){
        Message message = new Message();
        message.setTime(new Date());
        int length = answer.getContent().length();
        message.setContent(length <= 10 ? answer.getContent().substring(0,length) : answer.getContent().substring(0,10));
        message.setCategory(type);
        message.setContentId(answer.getQsId()+"_"+answer.getCourseId());
        message.setName(name);
        return message;
    }
    public static List<MsgUser> createMsgUser(int msgId, List<User> targetUsers){
        List<MsgUser> list = new ArrayList<>();
        for(User user : targetUsers){
            MsgUser msgUser = new MsgUser(user.getId(),user.getIsTeacher(),msgId);
            list.add(msgUser);
        }
        return list;
    }
}
