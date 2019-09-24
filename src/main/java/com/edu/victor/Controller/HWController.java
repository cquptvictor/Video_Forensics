package com.edu.victor.Controller;

import com.edu.victor.Service.HWService;
import com.edu.victor.domain.Homework;
import com.edu.victor.domain.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/admin",method = RequestMethod.POST)
@Controller
public class HWController {
    @Autowired
    HWService hwService;



    @RequestMapping(value = "/pHomework")
    @ResponseBody
    public ResponseData publishHW(Homework homework){
        return hwService.publishHW(homework);
    }
}
