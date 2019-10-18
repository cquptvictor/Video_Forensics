package com.edu.victor.Controller;

import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.NotAuthorizedException;
import com.edu.victor.Service.HWService;
import com.edu.victor.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
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
    public ResponseData getHW(Homework homework,Page page){
        Map map = new HashMap<>();
        map.put("id",homework.getCourseId());
        map.put("category",homework.getCategory());
        page.setFilter(map);
        return hwService.getHwList(page);
    }
    /**查看某一发布作业的具体内容*/
    @RequestMapping(value = "/homework/{id}")
    @ResponseBody
    public ResponseData getSpecificHomework(@PathVariable int id){
       return hwService.getSpecificPage(id);
    }
    /**查看某一发布作业的提交情况*/
    @RequestMapping(value = "/homework/showSubmit/{hwId}")
    @ResponseBody
    public ResponseData getSubmitHomework(@PathVariable("hwId") int hwId,Page page){
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
    @RequestMapping(value = "/homework/download/{id}",method = RequestMethod.GET)
    public ResponseEntity<byte[]> Download(@PathVariable("id") int id) throws IOException {
       return hwService.downloadHw(id);
    }
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
    public ResponseData submit(@Valid SubmittedHomework submittedHw, BindingResult bindingResult){
        return null;
    }
}
