package com.jabil.scm.service;

import com.jabil.scm.dao.TypesDao;
import com.jabil.scm.model.Types;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class TypesService {
    private TypesDao typesDao = new TypesDao();
    public ArrayList<Types> getTypes(){
        return typesDao.getTypes();
    }
}
