package com.edu.victor.Service.impl;

import com.edu.victor.Dao.*;
import com.edu.victor.Exception.NotAuthorizedException;
import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.Service.ArticleAndNoticeService;
import com.edu.victor.Service.UserService;
import com.edu.victor.domain.*;
import com.edu.victor.utils.FileUtils;
import com.edu.victor.utils.MessageCreateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleAndNoticeServiceImpl implements ArticleAndNoticeService {
    @Autowired
    ArticleDao articleDao;
    @Autowired
    UserService teacherService;
    @Autowired
    NoticeDao noticeDao;
    @Autowired
    UserDao teacherDao;
    @Autowired
    MessageDao messageDao;
    @Autowired
    CourseDao courseDao;
    @Override
    public ResponseData addArticle(Article article, Teacher teacher) {
        /**验证用户信息是否完整*/
        //teacher = teacherService.isCompleted(teacher);
        article.setPublisherId(teacher.getId());
        article.setPublisherName(teacher.getName());
        ResponseData responseData = new ResponseData();
        if(articleDao.addArticle(article))
        {
            responseData.setCode(200);
        }else
            responseData.setCode(0);
        return responseData;
    }

   /**存封面的图片存
    * 存书
    * 设定Article
    * */
    @Override
    public ResponseData addBook(Book book, Teacher teacher) throws UnsupportedFileTypeException {
        String bookPath = FileUtils.saveImage(book.getBook(),"book");
        String imagePath = FileUtils.saveImage(book.getImage(),"image");
        Article article = new Article();
        article.setPublisherName(teacher.getName());
        article.setBrief(book.getDescription());
        article.setContent(bookPath);
        article.setImage(imagePath);
        article.setTitle(book.getTitle());
        article.setPublisherId(teacher.getId());
        article.setType("book");
        ResponseData responseData = new ResponseData(200);
        if(!articleDao.addArticle(article))
            responseData.setCode(0);
        return responseData;
    }
 /**如果有图片和新书，先把旧的覆盖掉
  * */
    @Override
    public ResponseData updateBook(Book book) throws UnsupportedFileTypeException {
        Article article = articleDao.getSpecificArticle(book.getId());
        if(book.getImage() != null){
            FileUtils.updateCourseImage(book.getImage(),article.getImage());
        }
        if(book.getBook() != null){
            FileUtils.reSubmit(book.getBook(),article.getContent(),"book");
        }
        Article article1 = new Article();
        article1.setTitle(book.getTitle());
        article1.setBrief(book.getDescription());
        article1.setId(book.getId());
        System.out.println(book.getId());
        System.out.println(book.getDescription());

        ResponseData responseData = new ResponseData(200);
        if(!articleDao.updateArticle(article1))
            responseData.setCode(0);
        return responseData;
    }
   /**查询出内容
    * 删除文件
    * 删除数据库记录*/
    @Override
    public ResponseData deleteBook(Integer id) {
        ResponseData responseData = new ResponseData(200);
        Article article = articleDao.getSpecificArticle(id);
        FileUtils.deleteFile(article.getImage(),"image");
        FileUtils.deleteFile(article.getContent(),"book");
        if(!articleDao.deleteArticle(id))
            responseData.setCode(0);
        return responseData;
    }

    @Override
    public ResponseData deleteArticle(Integer id) {
        /**不是发布人，没有删除权限*/
      /*  if(news.getPublisherId() != tea_id)
            throw new NotAuthorizedException();*/
        ResponseData responseData = new ResponseData();
        if(articleDao.deleteArticle(id)){
            responseData.setCode(200);
        }else
            responseData.setCode(0);
        return responseData;
    }

    @Override
    public ResponseData updateArticle(Article article,Teacher teacher) throws NotAuthorizedException {
        /**不是发布人，没有更新权限*/
        /*if(teacher.getId() != article.getPublisherId()){
            throw  new NotAuthorizedException();
        }*/
        ResponseData responseData = new ResponseData();
        if(articleDao.updateArticle(article))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;
    }

    @Override
    public ResponseData searchArticle(Article article,Integer isApp, Page page) {
        Page page2  = null;
        Map<String, Object> map = new HashMap<>();
        map.put("type", article.getType());
        if(article.getPublisherName() != null)
            map.put("publisherName",article.getPublisherName());
        if(article.getTitle() != null)
            map.put("title",article.getTitle());
        page.setFilter(map);
        if(isApp == 1){
            page2 = articleDao.searchArticleForAppByPage(page);
        }else
            page2 = articleDao.searchArticleForWebByPage(page);
        page.setPageData(page2 != null ? page2.getPageData() : null);
        ResponseData responseData = new ResponseData(200);
        responseData.setData(page);
        return responseData;
    }

    @Override
    public ResponseData getSpecificArticle(int id, String type) {
        ResponseData responseData = new ResponseData(200);
        if(type.equals("book")){
            BookDto book = articleDao.getSpecificBook(id);
            if(book == null)
                responseData.setCode(0);
            responseData.setData(book);
        }else {
            Article article = articleDao.getSpecificArticle(id);
            if (article == null)
                responseData.setCode(0);
            responseData.setData(article);
        }
        return responseData;
    }


    /**-----------------------------------------------*/
    /**1.插入通知数据库
     * 2.插入消息数据库
     * 3.查询课程成员
     * 4.插入通知-用户数据库*/
    @Transactional
    @Override
    public ResponseData addNotice(Notice notice,Teacher teacher) {
        noticeDao.addNotice(notice);
        if(teacher.getName() == null)
            teacher = teacherDao.teacherInfo(teacher.getId());
        Message message = MessageCreateUtils.createNtMessage(notice,teacher.getName());
        messageDao.addMessage(message);
        List<User> students = courseDao.getUngraduatedStuByCourse(notice.getCourseId());
        List<MsgUser> msgUser = MessageCreateUtils.createMsgUser(message.getId(),students);
        if(msgUser != null && msgUser.size() != 0)
            messageDao.addMsgUser(msgUser);
        ResponseData responseData = new ResponseData(200);
        return  responseData;
    }
    /**先删除notice中的消息
     * 再删除message中的消息
     * 数据库自动删除msg-user中的消息*/
    @Transactional
    @Override
    public ResponseData deleteNotice(int id) {
        noticeDao.deleteNotice(id);
        String contentId = id+"_";
        Map<String,Object> map = new HashMap<>();
        map.put("contentId",contentId);
        map.put("category","notice");
        messageDao.deleteMessage(map);
        ResponseData responseData = new ResponseData(200);
        return responseData;
    }

    @Override
    public ResponseData searchNotice(Page page){
        Page<NoticeDto> page1 = noticeDao.searchNoticeByPage(page);
        page.setPageData(page1 != null ? page1.getPageData() : null);
        ResponseData responseData = new ResponseData(200);
        responseData.setData(page);
        return responseData;

    }

    @Override
    public ResponseData getSpecificNotice(int id) {
        Notice notice = noticeDao.getNotice(id);
        ResponseData responseData = new ResponseData(200);
        responseData.setData(notice);
        return responseData;
    }

}
