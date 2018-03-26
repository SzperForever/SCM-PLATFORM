package com.jabil.scm.service;

import com.jabil.scm.model.Reference;
import org.springframework.stereotype.Service;
import com.jabil.scm.dao.ReferenceDao;

import java.util.ArrayList;

@Service
public class ReferenceService {
    private ReferenceDao referenceDao = new ReferenceDao();

    public ArrayList<Reference> getReference(){
        return referenceDao.getReference();
    }
}
