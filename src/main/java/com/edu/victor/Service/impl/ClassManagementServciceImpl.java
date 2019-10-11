package com.edu.victor.Service.impl;

import com.edu.victor.Dao.ClassDao;
import com.edu.victor.Dao.MessageDao;
import com.edu.victor.Service.ClassManagementService;
import com.edu.victor.domain.*;
import com.edu.victor.utils.MessageCreateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassManagementServciceImpl implements ClassManagementService {
    @Autowired
    ClassDao classDao;
    @Autowired
    MessageDao messageDao;
    @Override
    public ResponseData createClass(TeachingClass teachingClass) {
        ResponseData responseData = new ResponseData(200);
        if(!classDao.create(teachingClass)){
            responseData.setCode(0);
        }
        return responseData;
    }

    @Override
    public ResponseData updateClass(TeachingClass teachingClass) {
        ResponseData responseData = new ResponseData(200);
        if(!classDao.update(teachingClass)){
            responseData.setCode(0);
        }
        return responseData;
    }

    @Override
    public ResponseData deleteClass(int id) {
        ResponseData responseData = new ResponseData(200);
        if(!classDao.delete(id)){
            responseData.setCode(0);
        }
        return responseData;
    }

    @Override
    public ResponseData showClass(Page<TeachingClass> page) {
            Page page1 = classDao.getClassByPage(page);
            page.setPageData(page1 != null ? page1.getPageData():null);
            ResponseData<Page> responseData = new ResponseData<>(200);
            responseData.setData(page);
            return responseData;
    }

    @Override
    public ResponseData addQustion(ClassDiscussionQuestion classDiscussionQuestion, User user) {
        classDiscussionQuestion.setQsrId(user.getId());
        if(user.getIsTeacher().equals("1")){
            classDiscussionQuestion.setIsTeacher('1');
        }else{
            classDiscussionQuestion.setIsTeacher('0');
        }
        ResponseData responseData = new ResponseData(200);

        if(!classDao.addQuestion(classDiscussionQuestion))
            responseData.setCode(0);
        return responseData;
    }

    @Override
    public ResponseData relpy(ClassDiscussionAnswer answer, User user) {
        answer.setAsrId(user.getId());
        if(user instanceof Teacher){
            answer.setIsTeacher("1");
        }else{
            answer.setIsTeacher("0");
        }
        ResponseData responseData = new ResponseData(200);
        if(classDao.addReply(answer))
        {
            /**创建并添加message*/
            Map<String,Object> map = new HashMap<>();
            map.put("isTeacher",answer.getIsTeacher());
            map.put("id",user.getId());
            UserDto userDto = classDao.getUser(map);
            Message message = MessageCreateUtils.createRyMessage(answer,userDto.getName());
            if(messageDao.addMessage(message)) {   /**创建并添加messageUser*/
                int superiorId = answer.getSuperiorId();
                User targetUser;
                if (superiorId == -1) {
                    //根据问题id得到提问者的id
                    targetUser = classDao.getTargetIdByQsId(answer.getQsId());
                } else {
                    //根据回复id得到回复的人的id
                    targetUser = classDao.getTargetIdByRpId(superiorId);
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
    public ResponseData getQuestionList(Page page, int id) {
        Map<String,Object> map = new HashMap();
        map.put("classId",id);
        page.setFilter(map);
        Page page1 = classDao.getQuestionByPage(page);
        page.setPageData(page1 != null ? page1.getPageData() : null);
        ResponseData responseData = new ResponseData(200);
        responseData.setData(page);
        return responseData;    }

    @Override
    public ResponseData getSpecificQuestion(int id) {
        ResponseData responseData = new ResponseData(200);
        ClassDiscussionQuestionDtoForSpecific classDiscussionQuestionDtoForSpecific = classDao.getSpecificQuestion(id);
        responseData.setData(classDiscussionQuestionDtoForSpecific);
        return responseData;
    }
}
