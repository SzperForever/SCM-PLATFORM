package com.jabil.scm.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class baseController {
    /**
     *
     * @return 图片路径
     */
    @RequestMapping(value = "/getBackground")
    @ResponseBody
    public String getBackground(){
        return "/IMG/SCM/bsc-menupic.jpg";
    }
}
