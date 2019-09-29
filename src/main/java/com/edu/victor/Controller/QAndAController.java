package com.edu.victor.Controller;

import com.edu.victor.Service.QAndAService;
import com.edu.victor.domain.*;
import com.edu.victor.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**课程的问答区*/
@Controller
@RequestMapping(method = RequestMethod.POST)
public class QAndAController {
    @Autowired
    QAndAService qAndAService;

    /**学生提问和老师提问要区分*/
    @RequestMapping("/pQuestion")
    @ResponseBody
    public ResponseData askQuestion(Question question, HttpServletRequest httpServletRequest){
        User user = null;
        if((user =(Teacher)httpServletRequest.getAttribute("Teacher")) != null)
            return qAndAService.addQustion(question,user);
        else
            return qAndAService.addQustion(question,(Student)httpServletRequest.getAttribute("Student"));
    }
    @RequestMapping("/rQuestion")
    @ResponseBody
    public ResponseData replyQuestion(Answer answer,HttpServletRequest httpServletRequest){
        User user = UserUtils.identifyUser(httpServletRequest);
        return qAndAService.relpy(answer,user);
    }
    @RequestMapping("/questions/{course_id}")
    @ResponseBody
    public ResponseData getQuestions(@PathVariable("course_id") int id, Page page){
        Map map = new HashMap();
        map.put("courseId",id);
        page.setFilter(map);
        return qAndAService.getQuestionList(page);
    }
    @RequestMapping("/question/{question_id}")
    @ResponseBody
    public ResponseData getSpecificQuestionPage(@PathVariable("question_id") int id){
        return qAndAService.getSpecificQuestion(id);
        }
}
