package com.edu.victor.Dao;

import com.edu.victor.domain.News;
import com.edu.victor.domain.Page;

public interface NewsDao {
    Boolean addNews(News news);
    Boolean deleteNews(int id);
    Boolean updateNews(News news);
    Page<News> searchNewsByPage(Page<News> page);
}
