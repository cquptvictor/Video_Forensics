package com.edu.victor.Controller;

import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.NotAuthorizedException;
import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.Service.HWService;
import com.edu.victor.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequestMapping(method = RequestMethod.POST)
@Controller
public class HWController {
    @Autowired
    HWService hwService;
    /**发布作业*/
    @RequestMapping(value = "/homework/pub")
    @ResponseBody
    public ResponseData publishHW(@Valid Homework homework, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws IncompleteInformationException {
        Teacher teacher = (Teacher) httpServletRequest.getAttribute("User");
        return hwService.publishHW(homework,teacher);
    }

    /**查看发布的作业的列表*/
    @RequestMapping(value = "/homework/show")
    @ResponseBody
    public ResponseData getHW(Homework homework,Page page,HttpServletRequest httpServletRequest){
        User user = (User)httpServletRequest.getAttribute("User");
        return hwService.getHwList(homework,page,user);
    }
    /**查看某一发布作业的具体内容*/
    @RequestMapping(value = "/homework/{id}")
    @ResponseBody
    public ResponseData getSpecificHomework(@PathVariable int id,HttpServletRequest httpServletRequest){
       User user = (User)httpServletRequest.getAttribute("User");
       return hwService.getSpecificPage(id,user);

    }
    /**查看某一发布作业的提交情况*/
    @RequestMapping(value = "/homework/showSubmit/{hwId}")
    @ResponseBody
    public ResponseData getSubmitHomework(@PathVariable("hwId") int hwId,HttpServletRequest httpServletRequest,Page page){
        Map map = new HashMap<>();
        map.put("hwId",hwId);
        page.setFilter(map);
        return hwService.getSubmitedHomework(page);
    }

    /**修改发布的作业*/
    @RequestMapping(value = "/homework/update")
    @ResponseBody
    public ResponseData updatePbHomework(Homework homework){
        return hwService.updateHw(homework);
    }
    /**删除发布的作业*/
    @RequestMapping(value = "/homework/delete")
    @ResponseBody
    public ResponseData deleteHw(int id,HttpServletRequest httpServletRequest) throws NotAuthorizedException {
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("User");
        Map<String,Integer> map = new HashMap<>();
        map.put("hwId",id);
        map.put("teaId",teacher.getId());
        return hwService.deleteHw(map);
    }
  /*  @RequestMapping(value = "/homework/download/{id}",method = RequestMethod.GET)
    public ResponseEntity<byte[]> Download(@PathVariable("id") int id) throws IOException {
       return hwService.downloadHw(id);
    }*/
    /**评分*/
    @RequestMapping(value = "/homework/judge")
    @ResponseBody
    public ResponseData judge(@Valid Judge judge,BindingResult bindingResult){
       return hwService.judgeHw(judge);
    }
    /**APP端*/
    //学生提交作业
    @RequestMapping("/homework/submit")
    @ResponseBody
    public ResponseData submit(@Valid SubmittedHomework submittedHw, BindingResult bindingResult,HttpServletRequest httpServletRequest) throws UnsupportedFileTypeException {
        User user = (User)httpServletRequest.getAttribute("User");
        return hwService.submitHw(submittedHw,user);
    }

    /**查看我的提交*/
    @RequestMapping("/homework/mySubmittion")
    @ResponseBody
    public ResponseData mySubmittion(Integer hwId,HttpServletRequest httpServletRequest){
        User user = (User)httpServletRequest.getAttribute("User");
        return hwService.getMySubmittion(hwId,user);
    }
}
