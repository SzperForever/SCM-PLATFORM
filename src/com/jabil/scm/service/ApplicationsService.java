package com.jabil.scm.service;

import com.jabil.scm.dao.AppDao;
import com.jabil.scm.model.Applications;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ApplicationsService {
    private AppDao appDao = new AppDao();
    public ArrayList<Applications> getApplications(){
        return appDao.getApplications();
    }
}
