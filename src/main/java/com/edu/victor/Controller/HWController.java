package com.edu.victor.Controller;

import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.NotAuthorizedException;
import com.edu.victor.Service.HWService;
import com.edu.victor.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping(method = RequestMethod.POST)
@Controller
public class HWController {
    @Autowired
    HWService hwService;
    /**发布作业*/
    @RequestMapping(value = "/pHomework")
    @ResponseBody
    public ResponseData publishHW(Homework homework,HttpServletRequest httpServletRequest) throws IncompleteInformationException {
        Teacher teacher = (Teacher) httpServletRequest.getAttribute("User");
        return hwService.publishHW(homework,teacher);
    }

    /**查看发布的作业的列表*/
    @RequestMapping(value = "/sHomework")
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
    @RequestMapping(value = "/sSubmit")
    @ResponseBody
    public ResponseData getSubmitHomework(@RequestParam int hwId,Page page){
        Map map = new HashMap<>();
        map.put("hw_id",hwId);
        page.setFilter(map);
        return hwService.getSubmitedHomework(page);
    }

    /**修改发布的作业*/
    @RequestMapping(value = "/uHomework")
    @ResponseBody
    public ResponseData updatePbHomework(Homework homework){
        return hwService.updateHw(homework);
    }
    /**删除发布的作业*/
    @RequestMapping(value = "/dHomework")
    @ResponseBody
    public ResponseData deleteHw(int id,HttpServletRequest httpServletRequest) throws NotAuthorizedException {
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("User");
        Map<String,Integer> map = new HashMap();
        map.put("hwId",id);
        map.put("teaId",teacher.getId());
        return hwService.deleteHw(map);
    }
    /**评分*/
    @RequestMapping(value = "/judge")
    @ResponseBody
    public ResponseData judge(Judge judge){
       return hwService.judgeHw(judge);
    }
}
