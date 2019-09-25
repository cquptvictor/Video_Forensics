package com.edu.victor.Service;

import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.domain.*;

public interface HWService {
    ResponseData publishHW(Homework homework, Teacher teacher) throws IncompleteInformationException;
    ResponseData getHwList(Page page);
    ResponseData getSpecificPage(int id);
    ResponseData getSubmitedHomework(Page page);
    ResponseData updateHw(Homework homework);
    ResponseData deleteHw(Homework homework);
    ResponseData judgeHw(Judge judge);
}
