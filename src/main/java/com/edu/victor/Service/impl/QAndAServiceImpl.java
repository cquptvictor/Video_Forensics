package com.edu.victor.Service.impl;

import com.edu.victor.Dao.MessageDao;
import com.edu.victor.Dao.QAndADao;
import com.edu.victor.Service.QAndAService;
import com.edu.victor.domain.*;
import com.edu.victor.utils.MessageCreateUtils;
import org.aspectj.bridge.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QAndAServiceImpl implements QAndAService {

    @Autowired
    QAndADao qAndADao;
    @Autowired
    MessageDao messageDao;
    /**区分用户是学生还是老师*/
    @Override
    public ResponseData addQustion(Question question, User user) {
        if(user instanceof Teacher){
            question.setUser(user);
            question.setIsTeacher('1');
        }else{
            question.setUser(user);
            question.setIsTeacher('0');
        }
        ResponseData responseData = new ResponseData(200);

        if(!qAndADao.addQuestion(question))
            responseData.setCode(0);
        return responseData;
    }
    /**回复消息，先把内容添加到回复表
     * 创建Message，添加到message表
     * 然后创建通知对象，添加到通知表*/
    @Override
    public ResponseData relpy(Answer answer, User user) {
        answer.setAsrId(user.getId());
        if(user instanceof Teacher){
            answer.setIsTeacher("1");
        }else{
            answer.setIsTeacher("0");
        }
        ResponseData responseData = new ResponseData(200);
        if(qAndADao.addReply(answer))
        {
            /**创建并添加message*/
            Map<String,Object> map = new HashMap<>();
            map.put("isTeacher",answer.getIsTeacher());
            map.put("id",user.getId());
            user = qAndADao.getUser(map);
            Message message = MessageCreateUtils.createRyMessage(answer,user.getName());
            if(messageDao.addMessage(message)) {   /**创建并添加messageUser*/
                int superiorId = answer.getSuperiorId();
                User targetUser;
                if (superiorId == -1) {
                    //根据问题id得到提问者的id
                    targetUser = qAndADao.getTargetIdByQsId(answer.getQsId());
                } else {
                    //根据回复id得到回复的人的id
                    targetUser = qAndADao.getTargetIdByRpId(superiorId);
                }
                List<User> targetList = new ArrayList<>();
                targetList.add(targetUser);
                List<MsgUser> msgUserList = MessageCreateUtils.createMsgUser(message.getId(), targetList);
                if(!messageDao.addMsgUser(msgUserList))
                    responseData.setCode(0);
            }else
                responseData.setCode(0);
        }else {
            responseData.setCode(0);
        }
        return responseData;
    }

    @Override
    public ResponseData getQuestionList(Page page) {
        Page page1 = qAndADao.getQuestionByPage(page);
        page.setPageData(page1 != null ? page1.getPageData() : null);
        ResponseData responseData = new ResponseData(200);
        responseData.setData(page);
        return responseData;
    }

    @Override
    public ResponseData getSpecificQuestion(int id) {
        ResponseData responseData = new ResponseData(200);
        QuestionDtoForSpecific questionDtoForSpecific = qAndADao.getSpecificQuestion(id);
        responseData.setData(questionDtoForSpecific);
        return responseData;
    }
}
