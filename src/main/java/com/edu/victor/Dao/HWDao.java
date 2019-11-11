package com.edu.victor.Dao;

import com.edu.victor.domain.*;

import java.util.List;
import java.util.Map;

public interface HWDao {
    Boolean publishHW(Homework homework);
    Page<Homework> getHwByCourseByPage(Page page);
    Homework getSpecificHomework(int id);
    Page<SubmittedHomeworkDto> getSubmitedByPage(Page page);
    Boolean updateHw(Homework homework);
    Boolean judgeHw(Judge judge);
    List<String> getSubmittedHwByHw(int id);
    Boolean deleteHw(int id);
    Integer isPermitted(Map map);
    String getUrlBySubmittedHw(int id);
    //APP
    Page getStuHwByCourseByPage(Page page);
    Boolean addSubmittedHomework(SubmittedHomework submittedHomework);
    Boolean updateSubmittedHomework(SubmittedHomework submittedHomework);
    String getSubmittedHomeworkUrl(SubmittedHomework submittedHomework);
    StudentSubmittion getStudentSubmittion(Map<String,Object> map);
}
