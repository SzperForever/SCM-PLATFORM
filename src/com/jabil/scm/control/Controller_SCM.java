package com.jabil.scm.control;

import com.jabil.scm.model.Category;
import com.jabil.scm.model.URL;
import com.jabil.scm.service.testService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;


@Controller
public class Controller_SCM {
    private testService testservice = new testService();
    @RequestMapping(value = "/scm")
    public String getIndex(){
        return "scm/scm";
    }

    @RequestMapping(value = "/procedure")
    public String getProcedureDocument(){
        return "scm/procedure-document";
    }

    @RequestMapping(value = "/workflow")
    public String getWorkFlow(){
        return "scm/WorkFlows";
    }

    @RequestMapping(value = "/file")
    public String getFile(){
        return "scm/file";
    }

}
