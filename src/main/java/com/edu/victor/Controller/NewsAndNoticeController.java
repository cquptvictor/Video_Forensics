package com.edu.victor.Controller;

import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.NotAuthorizedException;
import com.edu.victor.Service.NewsAndNoticeService;
import com.edu.victor.domain.News;
import com.edu.victor.domain.Page;
import com.edu.victor.domain.ResponseData;
import com.edu.victor.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        return newsAndNoticeService.addNews(news,teacher);
    }
    /**新闻删除*/
    @RequestMapping(value = "/dNews",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData deleteNews(Integer id, Integer publisher_id, HttpServletRequest httpServletRequest) throws NotAuthorizedException {
        Teacher teacher =(Teacher) httpServletRequest.getAttribute("Teacher");

        return newsAndNoticeService.deleteNews(id,publisher_id,teacher.getId());
    }
    /**新闻修改*/
    @RequestMapping(value = "/uNews",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData updateNews(News news,HttpServletRequest httpServletRequest) throws NotAuthorizedException {
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("Teacher");
        return newsAndNoticeService.updateNews(news,teacher);
    }

    /**新闻查看*/
    @RequestMapping(value = "/sNews",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData searchNews(@RequestParam("page") int currentPage){
        Page<News> page = new Page<>();
        page.setCurrentPage(currentPage);
        return newsAndNoticeService.searchNews(page);
    }
    @RequestMapping(value="/news/{news_id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData specificNews(@PathVariable("news_id") Integer news_id){
        return newsAndNoticeService.getSpecificNews(news_id);
    }
}
