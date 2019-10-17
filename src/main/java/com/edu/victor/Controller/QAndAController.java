package com.edu.victor.Controller;

import com.edu.victor.Service.QAndAService;
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
import java.util.HashMap;
import java.util.Map;


/**课程的问答区*/
@Controller
@RequestMapping(method = RequestMethod.POST)
public class QAndAController {
    @Autowired
    QAndAService qAndAService;

    /**学生提问和老师提问要区分*/
    @RequestMapping("/courseDiscussion/pub")
    @ResponseBody
    public ResponseData askQuestion(@Valid CourseDiscussionQuestion question, BindingResult bindingResult, HttpServletRequest httpServletRequest){
        User user = (User)httpServletRequest.getAttribute("User");
        return qAndAService.addQustion(question,user);
    }
    /**回复*/
    @RequestMapping("/courseDiscussion/reply")
    @ResponseBody
    public ResponseData replyQuestion(@Valid CourseDiscussionAnswer answer,BindingResult bindingResult, HttpServletRequest httpServletRequest){
        User user = (User)httpServletRequest.getAttribute("User");
        return qAndAService.relpy(answer,user);
    }
    /**展示提问列表*/
    @RequestMapping("/courseDiscussion/questions/{course_id}")
    @ResponseBody
    public ResponseData getQuestions(@PathVariable("course_id") int id, Page page){
        Map map = new HashMap();
        map.put("courseId",id);
        page.setFilter(map);
        return qAndAService.getQuestionList(page);
    }
    /**进入具体的问题页面*/
    @RequestMapping("/courseDiscussion/question/{question_id}")
    @ResponseBody
    public ResponseData getSpecificQuestionPage(@PathVariable("question_id") int id){
        return qAndAService.getSpecificQuestion(id);
        }
}
