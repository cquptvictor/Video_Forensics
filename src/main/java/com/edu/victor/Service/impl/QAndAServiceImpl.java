package com.edu.victor.Service.impl;

import com.edu.victor.Dao.QAndADao;
import com.edu.victor.Service.QAndAService;
import com.edu.victor.domain.*;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class QAndAServiceImpl implements QAndAService {

    @Autowired
    QAndADao qAndADao;
    /**区分用户是学生还是老师*/
    @Override
    public ResponseData addQustion(Question question, User user) {
        if(user instanceof Teacher){
            Teacher teacher = (Teacher) user;
            question.setQsrId(teacher.getId());
            question.setQsrName(teacher.getName());
            question.setIsTeacher('1');
        }else{
            Student student = (Student) user;
            question.setQsrId(student.getId());
            question.setQsrName(student.getName());
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
            Teacher teacher = (Teacher)user;
            answer.setAsrId(teacher.getId());
            answer.setAsrName(teacher.getName());
            answer.setIsTeacher('1');
        }else{
            Student student = (Student)user;
            answer.setAsrId(student.getId());
            answer.setAsrName(student.getName());
            answer.setIsTeacher('0');
        }
        ResponseData responseData = new ResponseData(200);
        if(!qAndADao.addReply(answer))
            responseData.setCode(0);
        return responseData;
    }

    @Override
    public ResponseData getQuestionList(Page page) {
        Page page1 = qAndADao.getQuestionByPage(page);
        page.setPageData(page1.getPageData());
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
