package com.jabil.scm.dao;

import com.jabil.scm.model.Tags;
import com.jabil.scm.utils.ConnectionUtils;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.util.ArrayList;

@Repository
public class TagDao {
    private ConnectionUtils connectionUtils;
    public ArrayList<Tags> getTags(){
        connectionUtils = new ConnectionUtils();
        ArrayList<Tags> tags = new ArrayList<Tags>();
        try{
            ResultSet resultSet = connectionUtils.getResultSet("SELECT * FROM Tags");
            while(resultSet.next()){
                Tags tag = new Tags(resultSet);
                tags.add(tag);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connectionUtils.SQLClose();
        }
        return tags;
    }


}
