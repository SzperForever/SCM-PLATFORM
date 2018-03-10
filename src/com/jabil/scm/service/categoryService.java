package com.jabil.scm.service;

import com.jabil.scm.dao.categoryDao;
import com.jabil.scm.dao.urlDao;
import org.springframework.stereotype.Service;
import com.jabil.scm.model.Category;
import java.util.ArrayList;

@Service
public class categoryService {
    private categoryDao categoryDao = new categoryDao();
    public ArrayList<String> getCategory(){
        return categoryDao.getCategory();
    }
    public void insertCategory(Category category){
        categoryDao.insertCategory(category);
    }
}
