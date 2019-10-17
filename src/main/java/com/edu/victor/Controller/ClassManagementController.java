package com.edu.victor.Controller;

import com.edu.victor.Service.ClassManagementService;
import com.edu.victor.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@RequestMapping(method = RequestMethod.POST)
public class ClassManagementController {
    @Autowired
    ClassManagementService classManagementService;
    /**创建*/
    @RequestMapping("/class/create")
    @ResponseBody
    public ResponseData createClass(@Valid TeachingClass teachingClass, BindingResult bindingResult){
        return classManagementService.createClass(teachingClass);
    }
    /**更新*/
    @RequestMapping("/class/update")
    @ResponseBody
    public ResponseData updateClassInfo(TeachingClass teachingClass){
        return classManagementService.updateClass(teachingClass);
    }
    /**删除*/
    @RequestMapping("/class/delete")
    @ResponseBody
    public ResponseData deleteClass(@NotNull int id,BindingResult bindingResult){
        return classManagementService.deleteClass(id);
    }
    /**展示班级*/
    @RequestMapping("/class/show")
    @ResponseBody
    public ResponseData showClass(Page page){
        return classManagementService.showClass(page);
    }

    /**学生提问和老师提问要区分*/
    @RequestMapping("/ClassDiscussion/pub")
    @ResponseBody
    public ResponseData askQuestion(@Valid ClassDiscussionQuestion classDiscussionQuestion,BindingResult bindingResult, HttpServletRequest httpServletRequest){
        User user = (User)httpServletRequest.getAttribute("User");
        return classManagementService.addQustion(classDiscussionQuestion,user);
    }
    /**回复*/
    @RequestMapping("/ClassDiscussion/reply")
    @ResponseBody
    public ResponseData replyQuestion(@Valid ClassDiscussionAnswer classDiscussionAnswer,BindingResult bindingResult, HttpServletRequest httpServletRequest){
        User user = (User)httpServletRequest.getAttribute("User");
        return classManagementService.relpy(classDiscussionAnswer,user);
    }
    /**展示提问列表*/
    @RequestMapping("/ClassDiscussion/questions/{classId}")
    @ResponseBody
    public ResponseData getQuestions(@PathVariable("classId") int id, Page page){
        return classManagementService.getQuestionList(page,id);
    }
    /**进入具体的问题页面*/
    @RequestMapping("/ClassDiscussion/question/{questionId}")
    @ResponseBody
    public ResponseData getSpecificQuestionPage(@PathVariable("questionId") int id){
        return classManagementService.getSpecificQuestion(id);
    }
}
