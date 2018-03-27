package com.jabil.scm.service;

import com.jabil.scm.dao.TagDao;
import com.jabil.scm.model.Tags;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TagService {
    private TagDao tagDao = new TagDao();
    public ArrayList<Tags> getTags(){
        return tagDao.getTags();
    }
}
