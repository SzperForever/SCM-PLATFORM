package com.jabil.scm.control;

import com.jabil.scm.dao.configurationDao;
import com.jabil.scm.model.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class baseController {
    @Autowired
    configurationDao dao;
    /**
     *
     * @return 图片路径
     */
    @RequestMapping(value = "/getBackground")
    @ResponseBody
    public String getBackground(){
        return "/IMG/SCM/bsc-menupic.jpg";
    }

    @RequestMapping(value = "/getConf")
    @ResponseBody
    public ArrayList<Configuration> getConf(){
        return dao.getConf();
    }

    @RequestMapping(value = "/getName")
    @ResponseBody
    public String getName(){
        return dao.getConf().get(0).getName();
    }

    @RequestMapping(value = "/getDescription")
    @ResponseBody
    public String getDescription(){
        return dao.getConf().get(0).getDescription();
    }

}
