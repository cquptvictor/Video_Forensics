package com.edu.victor.Service.impl;

import com.edu.victor.Dao.*;
import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.InvalidArgumentsException;
import com.edu.victor.Exception.NotAuthorizedException;
import com.edu.victor.Service.NewsAndNoticeService;
import com.edu.victor.Service.TeacherService;
import com.edu.victor.domain.*;
import com.edu.victor.utils.MessageCreateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsAndNoticeServiceImpl implements NewsAndNoticeService {
    @Autowired
    NewsDao newsDao;
    @Autowired
    TeacherService teacherService;
    @Autowired
    NoticeDao noticeDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    MessageDao messageDao;
    @Autowired
    CourseDao courseDao;
    @Override
    public ResponseData addNews(News news, Teacher teacher) throws IncompleteInformationException {
        /**验证用户信息是否完整*/
        teacher = teacherService.isCompleted(teacher);
        news.setPublisherId(teacher.getId());
        news.setPublisherName(teacher.getName());
        ResponseData responseData = new ResponseData();
        if(newsDao.addNews(news))
        {
            responseData.setCode(200);
        }else
            responseData.setCode(0);
        return responseData;
    }

    @Override
    public ResponseData deleteNews(News news,int tea_id) throws NotAuthorizedException {
        /**不是发布人，没有删除权限*/
        if(news.getPublisherId() != tea_id)
            throw new NotAuthorizedException();
        ResponseData responseData = new ResponseData();
        if(newsDao.deleteNews(news.getId())){
            responseData.setCode(200);
        }else
            responseData.setCode(0);
        return responseData;
    }

    @Override
    public ResponseData updateNews(News news,Teacher teacher) throws NotAuthorizedException {
        /**不是发布人，没有更新权限*/
        if(teacher.getId() != news.getPublisherId()){
            throw  new NotAuthorizedException();
        }
        ResponseData responseData = new ResponseData();
        if(newsDao.updateNews(news))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;
    }

    public NewsDao getNewsDao() {
        return newsDao;
    }

    public void setNewsDao(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Override
    public ResponseData searchNews(Page page, Teacher teacher) {
        Map<String,Object> filter = new HashMap<>();
        filter.put("tea_id",teacher.getId());
        page.setFilter(filter);
        Page<News> page2 = newsDao.searchNewsByPage(page);
        page.setPageData(page2.getPageData());
        ResponseData responseData = new ResponseData(200);
        responseData.setData(page);
        return responseData;
    }

    @Override
    public ResponseData getSpecificNews(int id) {
        ResponseData responseData = new ResponseData(200);
        News news = newsDao.getSpecificNews(id);
        if(news == null)
            responseData.setCode(0);
        responseData.setData(news);
        return responseData;
    }


    /**-----------------------------------------------*/
    /**1.插入通知数据库
     * 2.插入消息数据库
     * 3.查询课程成员
     * 4.插入通知-用户数据库*/
    @Transactional
    @Override
    public ResponseData addNotice(Notice notice,Teacher teacher) {
        noticeDao.addNotice(notice);
        if(teacher.getName() == null)
            teacher = teacherDao.teacherInfo(teacher.getId());
        Message message = MessageCreateUtils.createNtMessage(notice,teacher.getName());
        messageDao.addMessage(message);
        List<Integer> students = courseDao.getStuByCourse(notice.getCourseId());
        List<MsgUser> msgUser = MessageCreateUtils.createMsgUser(message.getId(),students);
        messageDao.addMsgUser(msgUser);
        ResponseData responseData = new ResponseData(200);
        return  responseData;
    }
    /**先删除notice中的消息
     * 再删除message中的消息
     * 数据库自动删除msg-user中的消息*/
    @Transactional
    @Override
    public ResponseData deleteNotice(int id) {
        noticeDao.deleteNotice(id);
        messageDao.deleteMessage(id,"nt");
        ResponseData responseData = new ResponseData(200);
        return responseData;
    }

    @Override
    public ResponseData searchNotice(Page page) throws InvalidArgumentsException {
        Page<Notice> page1 = noticeDao.searchNoticeByPage(page);
        if(page1 == null){
            throw  new InvalidArgumentsException();
        }
        page.setPageData(page1.getPageData());
        ResponseData responseData = new ResponseData(200);
        responseData.setData(page);
        return responseData;

    }
}
