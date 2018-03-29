package com.jabil.scm.control;

import com.jabil.scm.model.Users;
import com.jabil.scm.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
@Controller
public class UsersController {
    private UsersService usersService = new UsersService();
    @RequestMapping(value = "getUsers")
    @ResponseBody
    public ArrayList<Users> getUsers(){
        return usersService.getUsers();
    }

}
