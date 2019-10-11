package com.edu.victor.Dao;

import com.edu.victor.domain.Page;
import com.edu.victor.domain.TeachingClass;


public interface ClassDao {
    Boolean create(TeachingClass teachingClass);
    Boolean update(TeachingClass teachingClass);
    Boolean delete(int id);
    Page getClassByPage(Page page);
}


