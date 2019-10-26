package com.edu.victor.Service;

import com.edu.victor.domain.*;

public interface VideoPlayService {
    ResponseData beginPlay(VideoPlay videoPlay, User user);
    ResponseData playing(VideoPlay videoPlay, User user);
    ResponseData finishPlay(VideoPlay videoPlay,User user);
    ResponseData publishVideoComment(VideoComment videoComment, User user);
    ResponseData showVideoCommentList(Page page,Integer secId);
    ResponseData getLastRecord(User user);
    ResponseData addLastRecord(Integer id, User user);
}
