package com.edu.victor.Dao;

import com.edu.victor.domain.News;
import com.edu.victor.domain.Page;
import com.edu.victor.domain.Teacher;

public interface NewsDao {
    Boolean addNews(News news);
    Boolean deleteNews(int id);
    Boolean updateNews(News news);
    Page searchNewsForWebByPage(Page page);
    Page searchNewsForAppByPage(Page page);

    News getSpecificNews(int id);

}
