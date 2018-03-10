package com.jabil.scm.control;

import com.jabil.scm.model.URL;
import com.jabil.scm.service.urlService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class urlController {
    private urlService urlService = new urlService();
    /**
     *
     * @return 返回所有的链接
     */
    @RequestMapping(value = "getURL")
    @ResponseBody
    public ArrayList<URL> getUrl(){
        return urlService.getUrls();
    }

    /**
     *
     * @return 插入链接并返回
     */

    @RequestMapping(value = "addUrl")
    @ResponseBody
    public ArrayList<URL> insertUrls(){
        urlService.insertUrl(new URL("https://www.baidu.com","Baidu","null","Baidu, Inc incorporated on 18 January 2000, is a Chinese multinational technology company specializing in Internet-related services and products, and artificial intelligence, headquartered at the Baidu Campus in Beijing's Haidian District.","Planning"));
        urlService.insertUrl(new URL("https://www.google.com","Google","null","Search the world's information, including webpages, images, videos and more. Google has many special features to help you find exactly what you're looking for.","Planning"));
        urlService.insertUrl(new URL("https://www.bing.com","Bing","null","Bing helps you turn information into action, making it faster and easier to go from searching to doing.","Planning"));
        urlService.insertUrl(new URL("https://www.sougo.com","Sougo","null","Pape Amodou \"Modou\" Sougou (born 18 December 1984) is a Senegalese professional footballer who plays as a right winger.","Planning"));
        urlService.insertUrl(new URL("https://www.zhihu.com","Zhihu","null","Zhihu is a Chinese question-and-answer website where questions are created, answered, edited and organized by the community of its users.","Purchasing"));
        urlService.insertUrl(new URL("https://www.bbs.pcbeta.com","Yuanjing","null","The mission of Yuan-Jing is to provide you with good audio products at comparative price","Purchasing"));
        urlService.insertUrl(new URL("https://www.wikipedia.org","Wiki","null","Wikipedia is a free online encyclopedia, created and edited by volunteers around the world and hosted by the Wikimedia Foundation.","Purchasing"));
        urlService.insertUrl(new URL("https://www.tonymacx86.com/","tonymacx","null","UniBeast has been updated to version 8.2 for macOS High Sierra. This tool creates a bootable USB drive from your Mac App Store purchased copy of macOS. The resulting USB drive allows for a clean install, upgrade or use as a rescue boot drive.","InventoryControl"));

        return urlService.getUrls();
    }
}
