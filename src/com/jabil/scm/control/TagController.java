package com.jabil.scm.control;

import com.jabil.scm.model.Tags;
import com.jabil.scm.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class TagController {
    private TagService tagService = new TagService();
    @RequestMapping(value = "/getTag")
    @ResponseBody
    public ArrayList<Tags> getTags(){
        return tagService.getTags();
    }
}
