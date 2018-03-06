package com.jabil.scm.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Controller_scm {
    @RequestMapping(value = "/scm")
    public String getIndex(){
        System.out.println("Success");
        return "scm/scm";
    }
}
