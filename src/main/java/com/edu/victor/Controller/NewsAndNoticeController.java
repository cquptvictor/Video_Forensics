package com.edu.victor.Controller;

import com.edu.victor.Exception.NotAuthorizedException;
import com.edu.victor.Service.NewsAndNoticeService;
import com.edu.victor.domain.News;
import com.edu.victor.domain.ResponseData;
import com.edu.victor.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;

@Controller
@RequestMapping("/admin")
public class NewsAndNoticeController {
    @Autowired
    NewsAndNoticeService newsAndNoticeService;
    @RequestMapping(value = "/aNews",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addNews(News news, HttpServletRequest httpServletRequest){
        Teacher teacher =(Teacher)httpServletRequest.getAttribute("Teacher");
        news.setPublisher_id(teacher.getId());
        news.setPublisher_name(teacher.getName());
        ResponseData responseData = new ResponseData();
        if(newsAndNoticeService.addNews(news))
        {
            responseData.setCode(200);
        }else
            responseData.setCode(0);
        return responseData;
    }
    @RequestMapping(value = "/dNews",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData deleteNews(int id, int publisher_id, HttpServletRequest httpServletRequest) throws NotAuthorizedException {
        Teacher teacher =(Teacher) httpServletRequest.getAttribute("Teacher");
        if(publisher_id != teacher.getId())
            throw new NotAuthorizedException();
    }
}
