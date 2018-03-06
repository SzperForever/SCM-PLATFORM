package com.jabil.scm.control;

import com.jabil.scm.model.URL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;


@Controller
public class Controller_SCM {
    @RequestMapping(value = "/scm")
    public String getIndex(){
        return "scm/scm";
    }

    /**
     *
     * @return 返回网站的描述信息
     */
    @RequestMapping(value = "/getDescription")
    @ResponseBody
    public String getDescription(){
        return "This is SCM PLATFORM.This platform collects commonly used links in the work, Excel macros, FTP files, to facilitate daily use, improve work efficiency";
    }

    /**
     *
     * @return 返回所有类别的链表
     */
    @RequestMapping(value = "/getCategory")
    @ResponseBody
    public ArrayList getCategory(){
        ArrayList<String> result = new ArrayList<>();
        result.add("Planning");
        result.add("Purchasing");
        result.add("InventoryControl");
        result.add("MRO");
        return result;
    }

    @RequestMapping(value = "/getURL")
    @ResponseBody
    public ArrayList<URL> getURL(){
        ArrayList<URL> result = new ArrayList<>();
        result.add(new URL("https://www.baidu.com","Baidu","null","Baidu, Inc incorporated on 18 January 2000, is a Chinese multinational technology company specializing in Internet-related services and products, and artificial intelligence, headquartered at the Baidu Campus in Beijing's Haidian District.","Planning"));
        result.add(new URL("https://www.google.com","Google","null","Search the world's information, including webpages, images, videos and more. Google has many special features to help you find exactly what you're looking for.","Planning"));
        result.add(new URL("https://www.bing.com","Bing","null","Bing helps you turn information into action, making it faster and easier to go from searching to doing.","Planning"));
        result.add(new URL("https://www.sougo.com","Sougo","null","Pape Amodou \"Modou\" Sougou (born 18 December 1984) is a Senegalese professional footballer who plays as a right winger.","Planning"));
        result.add(new URL("https://www.zhihu.com","Zhihu","null","Zhihu is a Chinese question-and-answer website where questions are created, answered, edited and organized by the community of its users.","Purchasing"));
        result.add(new URL("https://www.bbs.pcbeta.com","Yuanjing","null","The mission of Yuan-Jing is to provide you with good audio products at comparative price","Purchasing"));
        result.add(new URL("https://www.wikipedia.org","Wiki","null","Wikipedia is a free online encyclopedia, created and edited by volunteers around the world and hosted by the Wikimedia Foundation.","Purchasing"));
        result.add(new URL("https://www.tonymacx86.com/","tonymacx","null","UniBeast has been updated to version 8.2 for macOS High Sierra. This tool creates a bootable USB drive from your Mac App Store purchased copy of macOS. The resulting USB drive allows for a clean install, upgrade or use as a rescue boot drive.","InventoryControl"));
        return result;
    }

}
