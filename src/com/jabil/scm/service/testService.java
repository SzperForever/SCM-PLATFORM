package com.jabil.scm.service;

import com.jabil.scm.dao.DConnection;
import com.jabil.scm.model.Category;
import org.springframework.stereotype.Service;
import com.jabil.scm.model.URL;
import java.util.ArrayList;

@Service
public class testService {
    private DConnection dbConnection = new DConnection();
    public void testSql(){
        dbConnection.connectSQL();
    }
    public ArrayList<URL> getUrl(){
        return dbConnection.getUrl();
    }
    public ArrayList<Category> getCategory(){
        return dbConnection.getCategory();
    }
    public void insertCategory(){
        dbConnection.insertCategory();
    }
}
