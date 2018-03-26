package com.jabil.scm.control;

import com.jabil.scm.model.Reference;
import com.jabil.scm.service.ReferenceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class ReferenceController {
    private ReferenceService referenceService = new ReferenceService();
    @RequestMapping(value = "/getReference")
    @ResponseBody
    public ArrayList<Reference> getReferences(){
        return referenceService.getReference();
    }

}
