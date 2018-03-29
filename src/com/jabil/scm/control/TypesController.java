package com.jabil.scm.control;

import com.jabil.scm.model.Types;
import com.jabil.scm.service.TypesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class TypesController {
    private TypesService typesService = new TypesService();
    @RequestMapping(value = "getTypes")
    @ResponseBody
    public ArrayList<Types> getType(){
        return typesService.getTypes();
    }
}
