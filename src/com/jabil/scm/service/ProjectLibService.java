package com.jabil.scm.service;

import com.jabil.scm.dao.ProjectLibDao;
import com.jabil.scm.model.ProjectLib;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProjectLibService {
    private ProjectLibDao projectLibDao = new ProjectLibDao();
    public ArrayList<ProjectLib> getProjectLibs(){
        return projectLibDao.getProjectLib();
    }
    public void addProjectLib(ProjectLib projectLib){
        projectLibDao.insertProjectLib(projectLib);
    }
}
