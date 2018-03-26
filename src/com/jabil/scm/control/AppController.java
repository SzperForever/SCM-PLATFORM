package com.jabil.scm.control;

import com.jabil.scm.model.Applications;
import com.jabil.scm.service.ApplicationsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class AppController {
    private ApplicationsService applicationsService = new ApplicationsService();
    @RequestMapping(value = "/getApp")
    @ResponseBody
    public ArrayList<Applications> getApp(){
        return applicationsService.getApplications();
    }

}
