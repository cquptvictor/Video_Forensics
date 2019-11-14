package com.edu.victor.Controller;

import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.InvalidArgumentsException;
import com.edu.victor.Exception.NotAuthorizedException;
import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.Service.ArticleAndNoticeService;
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
public class ArticleAndNoticeController {
    @Autowired
    ArticleAndNoticeService articleAndNoticeService;
    /**添加*/
    @RequestMapping(value = "/article/add")
    @ResponseBody
    public ResponseData addNews(@Valid Article article, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws IncompleteInformationException {
        Teacher teacher =(Teacher)httpServletRequest.getAttribute("User");
        return articleAndNoticeService.addArticle(article,teacher);
    }
    /**删除*/
    @RequestMapping(value = "/article/delete")
    @ResponseBody
    public ResponseData deleteNews(Integer id) {
        return articleAndNoticeService.deleteArticle(id);
    }
    /**修改*/
    @RequestMapping(value = "/article/update")
    @ResponseBody
    public ResponseData updateNews(Article article,HttpServletRequest httpServletRequest) throws NotAuthorizedException {
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("User");
        return articleAndNoticeService.updateArticle(article,teacher);
    }

    /**查看*/
    @RequestMapping(value = "/article/search")
    @ResponseBody
    public ResponseData searchNews(Article article ,Integer isApp,Page page,HttpServletRequest httpServletRequest){
        return articleAndNoticeService.searchArticle(article,isApp,page);
    }

    @RequestMapping(value="/article/{article_id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData specificNews(@PathVariable("article_id") Integer articleId){
        return articleAndNoticeService.getSpecificArticle(articleId);
    }
 /**电子书*/
 //添加小说
    @RequestMapping(value = "/book/add")
    @ResponseBody
     public ResponseData addBook(@Valid Book book, BindingResult bindingResult,HttpServletRequest httpServletRequest) throws UnsupportedFileTypeException {
         Teacher teacher  = (Teacher)httpServletRequest.getAttribute("User");
        return articleAndNoticeService.addBook(book,teacher);
    }

    //修改小说
    @RequestMapping(value = "/book/update")
    @ResponseBody
    public ResponseData updateBook(Book book) throws UnsupportedFileTypeException {
        return articleAndNoticeService.updateBook(book);
    }
    //删除小说
    @RequestMapping(value = "/book/delete")
    @ResponseBody
    public ResponseData deleteBook(Integer id){
        return articleAndNoticeService.deleteArticle(id);
    }

    /**通知开发*/
    @RequestMapping(value = "/pNotice")
    @ResponseBody
    public ResponseData publishNotice(@Valid Notice notice,BindingResult bindingResult, HttpServletRequest httpServletRequest){
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("User");
        return articleAndNoticeService.addNotice(notice,teacher);
    }
    @RequestMapping(value = "/dNotice")
    @ResponseBody
    public ResponseData deleteNotice(int id){
        return articleAndNoticeService.deleteNotice(id);
    }
    @RequestMapping(value = "/notices")
    @ResponseBody
    public ResponseData searchNotices(Page page,int courseId, HttpServletRequest httpServletRequest) throws InvalidArgumentsException {
        Map map = new HashMap<>();
        map.put("courseId",courseId);
        page.setFilter(map);
        return articleAndNoticeService.searchNotice(page);
    }
    @RequestMapping("/notice/{notice_id}")
    @ResponseBody
    public ResponseData getSpecificNotice(@PathVariable("notice_id") int id){
        return articleAndNoticeService.getSpecificNotice(id);
    }

}
