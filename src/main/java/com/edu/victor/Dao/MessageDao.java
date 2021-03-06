package com.edu.victor.Dao;

import com.edu.victor.domain.Message;
import com.edu.victor.domain.MsgUser;
import com.edu.victor.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MessageDao {
    Boolean addMessage(Message message);
    Boolean addMsgUser(List<MsgUser> msgUser);
    Boolean deleteMessage(Map<String,Object> map);
    Page getUserMessagesByPage(Page page);
    Integer getUnreadMessageNum(Map map);
    Boolean setAlreadyRead(@Param("map") Map map);
    Boolean markAsRead(Map<String,Object> map);
    String getContentId(Integer id);
    List<Integer> getMsgUserIdList(Map<String,Object> map);
    Boolean clearExpiredMessages(String date);

}
