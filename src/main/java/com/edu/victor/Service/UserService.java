package com.edu.victor.Service;


import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.domain.*;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    ResponseData login(User user);
    ResponseData logout(String token,User user);
    ResponseData updateTeaInfo(Teacher teacher);
    ResponseData updateStuInfo(Student student);
    Teacher isCompleted(Teacher teacher) throws IncompleteInformationException;
   // ResponseData getStuInfo(int id);
    //ResponseData getTeaInfo(int id);
    ResponseData getInfo(User user);
    ResponseData updateAvatar(MultipartFile multipartFile, User user, Boolean isTeacher) throws UnsupportedFileTypeException, IOException;
    ResponseData getMessages(Page page, User user);
    ResponseData getUnreadMessageNum(User user);
    //  ResponseData updateAvatar(Teacher teacher) throws UnsupportedFileTypeException;
    ResponseData MarkUnreadAsRead(Integer[] array , User user);
}
