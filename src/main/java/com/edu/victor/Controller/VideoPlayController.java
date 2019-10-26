package com.edu.victor.Controller;

import com.edu.victor.Service.VideoPlayService;
import com.edu.victor.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class VideoPlayController {
    @Autowired
    VideoPlayService videoPlayService;
    /**一个视频开始播放*/
    @RequestMapping("/play/start")
    @ResponseBody
    public ResponseData beginPlay(@Valid VideoPlay videoPlay, BindingResult bindingResult, HttpServletRequest httpServletRequest){
        User user = (User)httpServletRequest.getAttribute("User");
        return videoPlayService.beginPlay(videoPlay,user);
    }

    /**视频播放途中不停的更新播放进度*/
    @RequestMapping("/playing")
    @ResponseBody
    public ResponseData updatePlayProgress(@Valid VideoPlay videoPlay,BindingResult bindingResult,HttpServletRequest httpServletRequest){
        User user = (User)httpServletRequest.getAttribute("User");
        return videoPlayService.playing(videoPlay,user);
    }
    @RequestMapping("/play/over")
    @ResponseBody
    public ResponseData playOver(@Valid VideoPlay videoPlay,BindingResult bindingResult,HttpServletRequest httpServletRequest){
        User user = (User)httpServletRequest.getAttribute("User");
        return videoPlayService.finishPlay(videoPlay,user);
    }
    @RequestMapping("/videoComment/pub")
    @ResponseBody
    public ResponseData videoComment(@Valid VideoComment videoComment, BindingResult bindingResult,HttpServletRequest httpServletRequest){
        User user = (User)httpServletRequest.getAttribute("User");
        return videoPlayService.publishVideoComment(videoComment,user);
    }
    @RequestMapping("/videoComment/show")
    @ResponseBody
    public ResponseData showVideoComment(Integer secId, Page page, HttpServletRequest httpServletRequest){
        return videoPlayService.showVideoCommentList(page,secId);
    }
    @RequestMapping("/historyRecord")
    @ResponseBody
    public ResponseData getHistoryRecord(HttpServletRequest httpServletRequest){
        User user = (User)httpServletRequest.getAttribute("User");
        return videoPlayService.getLastRecord(user);
    }
    @RequestMapping("/addRecord")
    @ResponseBody
    public ResponseData addHistoryRecord(Integer id,HttpServletRequest httpServletRequest){
        User user = (User)httpServletRequest.getAttribute("User");
        return videoPlayService.addLastRecord(id,user);
    }
}
