package com.jabil.scm.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class FileUploadController {
    @RequestMapping(value = "upload")
    public String loginForm(){
        return "scm/uploadform";
    }
    @RequestMapping(value = "uploadfile", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        if(!file.isEmpty()){
            String contextPath = request.getContextPath();//"/SpringMvcFileUpload"
            String servletPath = request.getServletPath();//"/gotoAction"
            String scheme = request.getScheme();//"http"
            String storePath="/Users/szper/Desktop";

            String filename = file.getOriginalFilename();
            File filepath = new File(storePath, filename);
            if(!filepath.getParentFile().exists()){
                filepath.getParentFile().mkdir();
            }
            try{
                file.transferTo(new File(storePath+File.separator+filename));
            }catch (Exception e){
                e.printStackTrace();
                return "scm/error";
            }
            return "scm/success";

        }else{
            return "scm/error";
        }

    }
}
