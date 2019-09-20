package com.edu.victor.Service.impl;

import com.edu.victor.Dao.NewsDao;
import com.edu.victor.Service.NewsAndNoticeService;
import com.edu.victor.domain.News;
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
}
