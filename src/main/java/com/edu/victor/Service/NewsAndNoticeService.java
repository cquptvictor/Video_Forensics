package com.edu.victor.Service;

import com.edu.victor.domain.News;

public interface NewsAndNoticeService {
     Boolean addNews(News news);
     Boolean deleteNews(int id);
}
