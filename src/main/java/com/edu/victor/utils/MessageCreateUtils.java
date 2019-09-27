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
        message.setCategory("hw");
        message.setContentId(homework.getId());
        message.setName(name);
        message.setTime(new Date());
        return message;
    }
    public static Message createNtMessage(Notice notice,String name){
           Message message = new Message();
           message.setTime(new Date());
           System.out.println(new Date());
           message.setContent(notice.getTitle());
           message.setCategory("nt");
           message.setContentId(notice.getId());
           message.setName(name);
           return message;
    }
    public Message createRyMessage(){
        return null;
    }
    public static List<MsgUser> createMsgUser(int msgId, List<Integer> userId){
        List<MsgUser> list = new ArrayList<>();
        for(int id : userId){
            MsgUser msgStu = new MsgUser(id,msgId);
            list.add(msgStu);
        }
        return list;
    }
}
