package com.edu.victor.Dao;

import com.edu.victor.domain.*;

import java.util.List;
import java.util.Map;

public interface VideoPlayDao {
    Boolean addNewPlayRecord(VideoPlay videoPlay);
//    Boolean updatePlayRecord(VideoPlay videoPlay);
    Boolean updatePlayRecord(Map<String,Object> map);
    Boolean playOver(VideoPlay videoPlay);
    Boolean pubComment(VideoComment videoComment);
    Page<VideoCommentDto> getVideoCommentListByPage(Page page);
    List<HistoryRecord> getHistoryRecord(Map<String,Object> map);
}
