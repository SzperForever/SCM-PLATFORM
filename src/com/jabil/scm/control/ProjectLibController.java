package com.jabil.scm.control;

import com.jabil.scm.model.ProjectLib;
import com.jabil.scm.service.ProjectLibService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class ProjectLibController {
    private ProjectLibService projectLibService = new ProjectLibService();
    @RequestMapping(value = "/getProLibs")
    @ResponseBody
    public ArrayList<ProjectLib> getProLibs(){
        return projectLibService.getProjectLibs();
    }
    @RequestMapping(value = "/addProLib")
    public void addProLibs(ProjectLib projectLib){
        projectLibService.addProjectLib(projectLib);
    }
}
