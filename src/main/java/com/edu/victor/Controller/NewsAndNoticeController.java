package com.edu.victor.Controller;

import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.InvalidArgumentsException;
import com.edu.victor.Exception.NotAuthorizedException;
import com.edu.victor.Service.NewsAndNoticeService;
import com.edu.victor.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(method = RequestMethod.POST)
public class NewsAndNoticeController {
    @Autowired
    NewsAndNoticeService newsAndNoticeService;
    /**新闻添加*/
    @RequestMapping(value = "/aNews")
    @ResponseBody
    public ResponseData addNews(@Valid News news, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws IncompleteInformationException {
        Teacher teacher =(Teacher)httpServletRequest.getAttribute("User");
        return newsAndNoticeService.addNews(news,teacher);
    }
    /**新闻删除*/
    @RequestMapping(value = "/dNews")
    @ResponseBody
    public ResponseData deleteNews(News news, HttpServletRequest httpServletRequest) throws NotAuthorizedException {
        Teacher teacher =(Teacher) httpServletRequest.getAttribute("User");

        return newsAndNoticeService.deleteNews(news,teacher.getId());
    }
    /**新闻修改*/
    @RequestMapping(value = "/uNews")
    @ResponseBody
    public ResponseData updateNews(News news,HttpServletRequest httpServletRequest) throws NotAuthorizedException {
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("User");
        return newsAndNoticeService.updateNews(news,teacher);
    }

    /**新闻查看*/
    @RequestMapping(value = "/news")
    @ResponseBody
    public ResponseData searchNews(Page page,HttpServletRequest httpServletRequest){
       // User user = (User)httpServletRequest.getAttribute("User");
        return newsAndNoticeService.searchNews(page);
    }
    @RequestMapping(value="/news/{news_id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData specificNews(@PathVariable("news_id") Integer news_id){
        return newsAndNoticeService.getSpecificNews(news_id);
    }
    /**通知开发*/
    @RequestMapping(value = "/pNotice")
    @ResponseBody
    public ResponseData publishNotice(@Valid Notice notice,BindingResult bindingResult, HttpServletRequest httpServletRequest){
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("User");
        return newsAndNoticeService.addNotice(notice,teacher);
    }
    @RequestMapping(value = "/dNotice")
    @ResponseBody
    public ResponseData deleteNotice(int id){
        return newsAndNoticeService.deleteNotice(id);
    }
    @RequestMapping(value = "/notices")
    @ResponseBody
    public ResponseData searchNotices(Page page,int courseId, HttpServletRequest httpServletRequest) throws InvalidArgumentsException {
        Map map = new HashMap<>();
        map.put("courseId",courseId);
        page.setFilter(map);
        return newsAndNoticeService.searchNotice(page);
    }
    @RequestMapping("/notice/{notice_id}")
    @ResponseBody
    public ResponseData getSpecificNotice(@PathVariable("notice_id") int id){
        return newsAndNoticeService.getSpecificNotice(id);
    }
}
