package com.jabil.scm.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class controller {
    @RequestMapping("/scm")
    public String getIndex(){
        return "/scm/scm";
    }
}
