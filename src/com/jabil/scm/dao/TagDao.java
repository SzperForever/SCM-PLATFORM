package com.jabil.scm.dao;

import com.jabil.scm.model.Tags;
import com.jabil.scm.utils.SqlConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@Repository
public class TagDao {
    //private ConnectionUtils connectionUtils;
    private SqlConnection sqlConnection = new SqlConnection();
    public ArrayList<Tags> getTags(){
        //connectionUtils = new ConnectionUtils();
        Connection connection =  sqlConnection.connectSQL();
        Statement statement = sqlConnection.createStatement(connection);
        ResultSet resultSet = null;
        ArrayList<Tags> tags = new ArrayList<Tags>();
        try{
            resultSet = statement.executeQuery("SELECT * FROM Tags");
            while(resultSet.next()){
                Tags tag = new Tags(resultSet);
                tags.add(tag);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlConnection.closeConnect(connection,statement, resultSet);
        }
        return tags;
    }


}
