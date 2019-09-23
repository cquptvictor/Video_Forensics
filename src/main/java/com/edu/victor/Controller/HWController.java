package com.edu.victor.Controller;

import com.edu.victor.domain.Homework;
import com.edu.victor.domain.ResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(method = RequestMethod.POST)
public class HWController {
    @RequestMapping(value = "/pHomework")
    public ResponseData publishHW(Homework homework){
        return null;
    }
}
