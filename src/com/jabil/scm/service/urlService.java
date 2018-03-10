package com.jabil.scm.service;

import org.springframework.stereotype.Service;
import com.jabil.scm.model.URL;
import com.jabil.scm.dao.urlDao;
import java.util.ArrayList;

@Service
public class urlService {
    private urlDao urlDao = new urlDao();
    public ArrayList<URL> getUrls(){
        return urlDao.getUrl();
    }
    public void insertUrl(URL url){
        urlDao.insertUrl(url);
    }
}
