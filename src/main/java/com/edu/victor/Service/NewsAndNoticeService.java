package com.edu.victor.Service;

import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.InvalidArgumentsException;
import com.edu.victor.Exception.NotAuthorizedException;
import com.edu.victor.domain.*;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.bind.annotation.ResponseBody;

public interface NewsAndNoticeService {
     ResponseData addNews(News news, Teacher teacher) throws IncompleteInformationException;
     ResponseData deleteNews(int id, int publisher_id,int tea_id) throws NotAuthorizedException;
     ResponseData updateNews(News news,Teacher teacher) throws NotAuthorizedException;
     ResponseData searchNews(Page page,Teacher teacher);
     ResponseData getSpecificNews(int id);

     //通知
     ResponseData addNotice(Notice notice,Teacher teacher);
     ResponseData deleteNotice(int id);
     ResponseData searchNotice(Page page) throws InvalidArgumentsException;
}
