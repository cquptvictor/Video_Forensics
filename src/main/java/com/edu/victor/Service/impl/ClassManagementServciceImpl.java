package com.edu.victor.Service.impl;

import com.edu.victor.Dao.ClassDao;
import com.edu.victor.Service.ClassManagementService;
import com.edu.victor.domain.Page;
import com.edu.victor.domain.ResponseData;
import com.edu.victor.domain.TeachingClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassManagementServciceImpl implements ClassManagementService {
    @Autowired
    ClassDao classDao;
    @Override
    public ResponseData createClass(TeachingClass teachingClass) {
        ResponseData responseData = new ResponseData(200);
        if(!classDao.create(teachingClass)){
            responseData.setCode(0);
        }
        return responseData;
    }

    @Override
    public ResponseData updateClass(TeachingClass teachingClass) {
        ResponseData responseData = new ResponseData(200);
        if(!classDao.update(teachingClass)){
            responseData.setCode(0);
        }
        return responseData;
    }

    @Override
    public ResponseData deleteClass(int id) {
        ResponseData responseData = new ResponseData(200);
        if(!classDao.delete(id)){
            responseData.setCode(0);
        }
        return responseData;
    }

    @Override
    public ResponseData showClass(Page<TeachingClass> page) {
            Page page1 = classDao.getClassByPage(page);
            page.setPageData(page1 != null ? page1.getPageData():null);
            ResponseData<Page> responseData = new ResponseData<>(200);
            responseData.setData(page);
            return responseData;
    }
}
