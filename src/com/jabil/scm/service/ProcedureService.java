package com.jabil.scm.service;

import com.jabil.scm.dao.ProcedureDao;
import com.jabil.scm.dao.ProjectLibDao;
import com.jabil.scm.model.procedure;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProcedureService {
    private ProcedureDao procedureDao = new ProcedureDao();
    public ArrayList<procedure> getProcedures(){
        return procedureDao.getProcedure();
    }
}
