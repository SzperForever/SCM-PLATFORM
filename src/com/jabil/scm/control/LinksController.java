package com.jabil.scm.control;

import com.jabil.scm.model.Links;
import com.jabil.scm.service.LinksService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class LinksController {
    private LinksService linksService = new LinksService();
    @RequestMapping(value = "/getLinks")
    @ResponseBody
    public ArrayList<Links> getLinks(){
        return linksService.getLinks();
    }
}
