package com.jabil.rollingboard.control;

import com.jabil.rollingboard.model.LineModel;
import com.jabil.rollingboard.service.Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Controller
public class controller {
    private Service service = new Service();

    //add count num to page
    private void addCount(ModelMap model) {
        int building = service.getBuildingCount();
        int complete = service.getLoadingCompletedCount();
        model.addAttribute("buildingCount", building);
        model.addAttribute("loadingCompleteCount", complete);
    }

    // request mapping --> detail.form, add all bay data to page
    @RequestMapping(value = "/detail")
    public String watchDetail(ModelMap model) {
        return "result";
    }

    @RequestMapping(value = "/showBoard")
    public String showDetail() {
        return "showBoard";
    }

    @RequestMapping("/getCountNum")
    @ResponseBody
    public Map getCountNum() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Building", service.getBuildingCount());
        map.put("LoadingComplete", service.getLoadingCompletedCount());
        return map;
    }

    @RequestMapping("/getBayLine")
    @ResponseBody
    public ArrayList<LineModel> getBayLine() {
        return service.getData(false);
    }

    @RequestMapping("/getFullBayLine")
    @ResponseBody
    public ArrayList<LineModel> getFullBayLine() {
        return service.getData(true);
    }
}
