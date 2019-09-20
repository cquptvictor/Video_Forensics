package com.edu.victor.Dao;

import com.edu.victor.domain.News;

public interface NewsDao {
    Boolean addNews(News news);
    Boolean deleteNews(int id);
}
