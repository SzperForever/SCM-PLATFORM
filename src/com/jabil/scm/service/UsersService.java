package com.jabil.scm.service;

import com.jabil.scm.dao.UsersDao;
import com.jabil.scm.model.Users;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class UsersService {
    private UsersDao usersDao = new UsersDao();
    public ArrayList<Users> getUsers(){
        return usersDao.getUsers();
    }
}
