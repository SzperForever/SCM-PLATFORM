package com.jabil.scm.service;

import org.springframework.stereotype.Service;
import com.jabil.scm.model.URL;
import com.jabil.scm.dao.urlDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class urlService {
    private urlDao urlDao = new urlDao();

    public ArrayList<URL> getUrls() {
        ArrayList<URL> urls = urlDao.getUrl();
        Collections.sort(urls, new Comparator<URL>() {
            @Override
            public int compare(URL o1, URL o2) {
                if(o1.getTitle().toLowerCase().charAt(0) >= o2.getTitle().toLowerCase().charAt(0)) {
                    return 1;
                }
                else {
                    return -1;
                }
            }
        });
        for(URL each : urls){
            System.out.println(each.getTitle());
        }
        return urls;

    }

    public void insertUrl(URL url) {
        urlDao.insertUrl(url);
    }
}
