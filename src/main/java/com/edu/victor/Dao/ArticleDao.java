package com.edu.victor.Dao;

import com.edu.victor.domain.Article;
import com.edu.victor.domain.BookDto;
import com.edu.victor.domain.Page;


public interface ArticleDao {
    Boolean addArticle(Article article);
    Boolean deleteArticle(int id);
    Boolean updateArticle(Article article);
    Page searchArticleForWebByPage(Page page);
    Page searchArticleForAppByPage(Page page);
    Article getSpecificArticle(int id);
    BookDto getSpecificBook(Integer id);
}
