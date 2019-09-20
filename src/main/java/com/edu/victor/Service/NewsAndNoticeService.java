package com.edu.victor.Service;

import com.edu.victor.domain.News;
import com.edu.victor.domain.Page;

public interface NewsAndNoticeService {
     Boolean addNews(News news);
     Boolean deleteNews(int id);
     Boolean updateNews(News news);
     Page<News> searchNews(Page<News> page);
}
