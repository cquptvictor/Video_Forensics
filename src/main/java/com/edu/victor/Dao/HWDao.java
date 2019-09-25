package com.edu.victor.Dao;

import com.edu.victor.domain.*;

public interface HWDao {
    Boolean publishHW(Homework homework);
    Page<Homework> getHwByCourseByPage(Page page);
    Homework getSpecificHomework(int id);
    Page<SbHomework> getSubmitedByPage(Page page);
    Boolean updateHw(Homework homework);
    Boolean judgeHw(Judge judge);

}
