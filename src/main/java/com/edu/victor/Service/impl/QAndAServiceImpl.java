package com.edu.victor.Service.impl;

import com.edu.victor.Dao.QAndADao;
import com.edu.victor.Service.QAndAService;
import com.edu.victor.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QAndAServiceImpl implements QAndAService {

    @Autowired
    QAndADao qAndADao;
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

    @Override
    public ResponseData relpy(Answer answer, User user) {
        if(user instanceof Teacher){
            answer.setRespondent(user);
            answer.setIsTeacher("1");
        }else{
            Student student = (Student)user;
            answer.setRespondent(user);
            answer.setIsTeacher("0");
        }
        ResponseData responseData = new ResponseData(200);
        if(!qAndADao.addReply(answer))
            responseData.setCode(0);
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
