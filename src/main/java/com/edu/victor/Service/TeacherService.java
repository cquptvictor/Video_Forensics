package com.edu.victor.Service;


import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.domain.ResponseData;
import com.edu.victor.domain.Teacher;

public interface TeacherService {
    ResponseData login(Teacher teacher);
    ResponseData updateInfo(Teacher teacher) throws UnsupportedFileTypeException;
    Teacher isCompleted(Teacher teacher) throws IncompleteInformationException;
  //  ResponseData updateAvatar(Teacher teacher) throws UnsupportedFileTypeException;
}
