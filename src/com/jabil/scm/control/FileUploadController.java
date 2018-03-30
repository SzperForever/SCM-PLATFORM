package com.jabil.scm.control;

import com.jabil.scm.model.ProjectLib;
import com.jabil.scm.service.ProjectLibService;
import com.jabil.scm.service.TypesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.PrintStream;
import java.util.Date;
import java.sql.Timestamp;


@Controller
public class FileUploadController {
    private TypesService typesService = new TypesService();
    private ProjectLibService projectLibService = new ProjectLibService();
    @RequestMapping(value = "upload")
    public String loginForm(){
        return "scm/uploadform";
    }
    @RequestMapping(value = "uploadfile", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file, @RequestParam("type") int type, HttpServletRequest request){
        if(!file.isEmpty()){
            String contextPath = request.getContextPath();//"/SpringMvcFileUpload"
            String servletPath = request.getServletPath();//"/gotoAction"
            String scheme = request.getScheme();//"http"
            String storePath="/SCM";
            int size = projectLibService.getProjectLibs().size() + 1;
            Date date = new Date();
            String TypePath= "/" + typesService.getTypes().get(type).getTypeName() + "/";
            storePath = storePath + TypePath;
            System.out.println(storePath);
            String filename = file.getOriginalFilename();
            File filepath = new File(storePath, filename);
            ProjectLib projectLib = new ProjectLib(size, filename, storePath, type, 4, "existing", new Timestamp(date.getTime()));
            projectLibService.addProjectLib(projectLib);
            if(!filepath.getParentFile().exists()){
                filepath.getParentFile().mkdirs();
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
