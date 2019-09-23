package com.edu.victor.Service;

import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.NotAuthorizedException;
import com.edu.victor.domain.News;
import com.edu.victor.domain.Page;
import com.edu.victor.domain.ResponseData;
import com.edu.victor.domain.Teacher;

public interface NewsAndNoticeService {
     ResponseData addNews(News news, Teacher teacher) throws IncompleteInformationException;
     ResponseData deleteNews(int id, int publisher_id,int tea_id) throws NotAuthorizedException;
     ResponseData updateNews(News news,Teacher teacher) throws NotAuthorizedException;
     ResponseData searchNews(Page<News> page);
     ResponseData getSpecificNews(int id);
}
