package com.edu.victor.Service;


import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.domain.ResponseData;
import com.edu.victor.domain.Student;
import com.edu.victor.domain.Teacher;
import com.edu.victor.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    ResponseData login(Teacher teacher);
    ResponseData updateTeaInfo(Teacher teacher);
    ResponseData updateStuInfo(Student student);
    Teacher isCompleted(Teacher teacher) throws IncompleteInformationException;
    ResponseData getStuInfo(int id);
    ResponseData getTeaInfo(int id);
    ResponseData updateAvatar(MultipartFile multipartFile, User user, Boolean isTeacher) throws UnsupportedFileTypeException, IOException;
    //  ResponseData updateAvatar(Teacher teacher) throws UnsupportedFileTypeException;
}
