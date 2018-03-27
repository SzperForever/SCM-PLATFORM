package com.jabil.scm.dao;

import com.jabil.scm.model.Configuration;
import com.jabil.scm.model.Links;
import com.jabil.scm.utils.SqlConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@Repository
public class configurationDao {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private SqlConnection sqlConnection = new SqlConnection();
    public ArrayList<Configuration> getConf(){
        connection = sqlConnection.connectSQL();
        statement = sqlConnection.createStatement(connection);
        ArrayList<Configuration> conf = new ArrayList<Configuration>();
        try{
            resultSet = statement.executeQuery("SELECT * FROM Configuration");
            while(resultSet.next()){
                conf.add(new Configuration(resultSet));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlConnection.closeConnect(connection,statement, resultSet);
        }
        return conf;
    }
}
