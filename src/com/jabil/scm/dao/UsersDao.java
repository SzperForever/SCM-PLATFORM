package com.jabil.scm.dao;

import com.jabil.scm.model.Users;
import com.jabil.scm.utils.SqlConnection;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
@Repository
public class UsersDao {
    private SqlConnection sqlConnection = new SqlConnection();
    public ArrayList<Users> getUsers(){
        Connection connection = sqlConnection.connectSQL();
        Statement statement = sqlConnection.createStatement(connection);
        ArrayList<Users> users = new ArrayList<Users>();
        ResultSet resultSet = null;
        try{
            resultSet = statement.executeQuery("SELECT * FROM Users");
            while(resultSet.next()){
                Users user = new Users(resultSet);
                users.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            sqlConnection.closeConnect(connection, statement, resultSet);
        }
        return users;
    }
}