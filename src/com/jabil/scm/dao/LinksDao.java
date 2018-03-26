package com.jabil.scm.dao;

import com.jabil.scm.model.Links;
import com.jabil.scm.utils.SqlConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Repository
public class LinksDao {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private SqlConnection sqlConnection = new SqlConnection();
    public ArrayList<Links> getLinks(){
        connection = sqlConnection.connectSQL();
        statement = sqlConnection.createStatement(connection);
        ArrayList<Links> Lins = new ArrayList<Links>();
        try{
            resultSet = statement.executeQuery("SELECT * FROM Links");
            while(resultSet.next()){
                Lins.add(new Links(resultSet));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlConnection.closeConnect(connection,statement, resultSet);
        }
        return Lins;
    }
}
