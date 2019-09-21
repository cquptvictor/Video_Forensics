package com.edu.victor.Controller;

import com.edu.victor.Dao.LoginDao;
import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.NotAuthorizedException;
import com.edu.victor.Service.NewsAndNoticeService;
import com.edu.victor.domain.News;
import com.edu.victor.domain.Page;
import com.edu.victor.domain.ResponseData;
import com.edu.victor.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class NewsAndNoticeController {
    @Autowired
    NewsAndNoticeService newsAndNoticeService;
    /**新闻添加*/
    @RequestMapping(value = "/aNews",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addNews(News news, HttpServletRequest httpServletRequest) throws IncompleteInformationException {
        Teacher teacher =(Teacher)httpServletRequest.getAttribute("Teacher");
        news.setPublisherId(teacher.getId());
        news.setPublisherName(teacher.getName());
        if(teacher.getName() == null || teacher.getEmail() == null){
            if(!newsAndNoticeService.teacherInfo(teacher))
                throw new IncompleteInformationException();
        }
        ResponseData responseData = new ResponseData();
        if(newsAndNoticeService.addNews(news))
        {
            responseData.setCode(200);
        }else
            responseData.setCode(0);
        return responseData;
    }
    /**新闻删除*/
    @RequestMapping(value = "/dNews",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData deleteNews(Integer id, Integer publisher_id, HttpServletRequest httpServletRequest) throws NotAuthorizedException {
        Teacher teacher =(Teacher) httpServletRequest.getAttribute("Teacher");
        /**不是发布人，没有删除权限*/
        if(publisher_id != teacher.getId())
            throw new NotAuthorizedException();
        ResponseData responseData = new ResponseData();
        if(newsAndNoticeService.deleteNews(id)){
            responseData.setCode(200);
        }else
            responseData.setCode(0);
        return responseData;
    }
    /**新闻修改*/
    @RequestMapping(value = "/uNews",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData updateNews(News news,HttpServletRequest httpServletRequest) throws NotAuthorizedException {
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("Teacher");
        if(teacher.getId() != news.getPublisherId()){
            throw  new NotAuthorizedException();
        }
        ResponseData responseData = new ResponseData();
        if(newsAndNoticeService.updateNews(news))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;
    }

    /**新闻查看*/
    @RequestMapping(value = "/sNews",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData searchNews(@RequestParam("page") int currentPage){
        Page<News> page = new Page<>();
        page.setCurrentPage(currentPage);
        page = newsAndNoticeService.searchNews(page);
        ResponseData responseData = new ResponseData(200);
        responseData.setData(page);
        return  responseData;

    }
}
