package com.edu.victor.Service;

import com.edu.victor.domain.Page;
import com.edu.victor.domain.ResponseData;
import com.edu.victor.domain.TeachingClass;

public interface ClassManagementService {
    ResponseData createClass(TeachingClass teachingClass);
    ResponseData updateClass(TeachingClass teachingClass);
    ResponseData deleteClass(int id);
    ResponseData showClass(Page<TeachingClass> page);
}
