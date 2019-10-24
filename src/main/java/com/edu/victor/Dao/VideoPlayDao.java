package com.edu.victor.Dao;

import com.edu.victor.domain.Page;
import com.edu.victor.domain.VideoComment;
import com.edu.victor.domain.VideoCommentDto;
import com.edu.victor.domain.VideoPlay;

import java.util.Map;

public interface VideoPlayDao {
    Boolean addNewPlayRecord(VideoPlay videoPlay);
//    Boolean updatePlayRecord(VideoPlay videoPlay);
    Boolean updatePlayRecord(Map<String,Object> map);
    Boolean playOver(VideoPlay videoPlay);
    Boolean pubComment(VideoComment videoComment);
    Page<VideoCommentDto> getVideoCommentListByPage(Page page);
}
