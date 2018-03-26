package com.jabil.scm.service;

import com.jabil.scm.dao.LinksDao;
import com.jabil.scm.model.Links;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LinksService {
    private LinksDao linksDao = new LinksDao();
    public ArrayList<Links> getLinks(){
        return linksDao.getLinks();
    }
}
