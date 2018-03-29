package com.jabil.scm.control;

import com.jabil.scm.model.ProjectLib;
import com.jabil.scm.service.ProjectLibService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class ProjectLibController {
    private ProjectLibService projectLibService = new ProjectLibService();
    @RequestMapping(value = "add")
    public String addPr(){
        return "scm/addpj";
    }
    @RequestMapping(value = "/getProLibs")
    @ResponseBody
    public ArrayList<ProjectLib> getProLibs(){
        return projectLibService.getProjectLibs();
    }
    @RequestMapping(value = "/addLib", method = RequestMethod.GET)
    public String addProLibs(ProjectLib projectLib){
        System.out.println(projectLib);
        projectLibService.addProjectLib(projectLib);
        return "scm/success";
    }
}
