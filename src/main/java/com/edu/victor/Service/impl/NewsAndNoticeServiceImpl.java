package com.edu.victor.Service.impl;

import com.edu.victor.Dao.NewsDao;
import com.edu.victor.Service.NewsAndNoticeService;
import com.edu.victor.domain.News;
import com.edu.victor.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsAndNoticeServiceImpl implements NewsAndNoticeService {
    @Autowired
    NewsDao newsDao;
    @Override
    public Boolean addNews(News news) {
        return newsDao.addNews(news);
    }

    @Override
    public Boolean deleteNews(int id) {
        return newsDao.deleteNews(id);
    }

    @Override
    public Boolean updateNews(News news) {
        return newsDao.updateNews(news);
    }

    public NewsDao getNewsDao() {
        return newsDao;
    }

    public void setNewsDao(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Override
    public Page<News> searchNews(Page<News> page) {
        Page<News> page2 = newsDao.searchNewsByPage(page);
        page.setContent(page2.getContent());
        return page;
    }
}
