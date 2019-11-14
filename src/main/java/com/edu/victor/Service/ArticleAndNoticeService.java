package com.edu.victor.Service;

import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.InvalidArgumentsException;
import com.edu.victor.Exception.NotAuthorizedException;
import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.domain.*;

public interface ArticleAndNoticeService {
     //文章
     ResponseData addArticle(Article news, Teacher teacher);
     ResponseData deleteArticle(Integer id);
     ResponseData updateArticle(Article article,Teacher teacher) throws NotAuthorizedException;
     ResponseData searchArticle(Article article,Integer isApp,Page page);
     ResponseData getSpecificArticle(int id,String type);

     //小说
     ResponseData addBook(Book book, Teacher teacher) throws UnsupportedFileTypeException;
     ResponseData updateBook(Book book) throws UnsupportedFileTypeException;
     ResponseData deleteBook(Integer id);
     //通知
     ResponseData addNotice(Notice notice,Teacher teacher);
     ResponseData deleteNotice(int id);
     ResponseData searchNotice(Page page) throws InvalidArgumentsException;
     ResponseData getSpecificNotice(int id);

}
