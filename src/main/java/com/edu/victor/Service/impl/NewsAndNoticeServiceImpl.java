package com.edu.victor.Service.impl;

import com.edu.victor.Dao.NewsDao;
import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.NotAuthorizedException;
import com.edu.victor.Service.NewsAndNoticeService;
import com.edu.victor.Service.TeacherService;
import com.edu.victor.domain.News;
import com.edu.victor.domain.Page;
import com.edu.victor.domain.ResponseData;
import com.edu.victor.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsAndNoticeServiceImpl implements NewsAndNoticeService {
    @Autowired
    NewsDao newsDao;
    @Autowired
    TeacherService teacherService;
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
    public ResponseData deleteNews(int id,int publisher_id,int tea_id) throws NotAuthorizedException {
        /**不是发布人，没有删除权限*/
        if(publisher_id != tea_id)
            throw new NotAuthorizedException();
        ResponseData responseData = new ResponseData();
        if(newsDao.deleteNews(id)){
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
    public ResponseData searchNews(Page<News> page) {
        Page<News> page2 = newsDao.searchNewsByPage(page);
        page.setContent(page2.getContent());
        ResponseData responseData = new ResponseData(200);
        responseData.setData(page);
        return responseData;
    }

    @Override
    public ResponseData getSpecificNews(int id) {
        ResponseData responseData = new ResponseData();
        responseData.setData(newsDao.getSpecificNews(id));
        return responseData;
    }
}
