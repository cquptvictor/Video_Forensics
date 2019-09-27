package com.edu.victor.Dao;

import com.edu.victor.domain.Message;
import com.edu.victor.domain.MsgUser;

import java.util.List;

public interface MessageDao {
    Boolean addMessage(Message message);
    Boolean addMsgUser(List<MsgUser> msgUser);
    Boolean deleteMessage(int id, String category);
}
