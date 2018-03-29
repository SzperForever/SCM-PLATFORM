package com.jabil.scm.control;

import com.jabil.scm.model.procedure;
import com.jabil.scm.service.ProcedureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class ProcedureController {
    private ProcedureService procedureService = new ProcedureService();
    @RequestMapping(value = "getProdures")
    @ResponseBody
    public ArrayList<procedure> getProdure(){
        return procedureService.getProcedures();
    }
}
