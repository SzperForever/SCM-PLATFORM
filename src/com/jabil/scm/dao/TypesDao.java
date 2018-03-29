package com.jabil.scm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.jabil.scm.model.Types;
import com.jabil.scm.utils.SqlConnection;
import org.springframework.stereotype.Repository;

@Repository
public class TypesDao {
    private SqlConnection sqlConnection = new SqlConnection();
    public ArrayList<Types> getTypes(){
        Connection connection = sqlConnection.connectSQL();
        Statement statement = sqlConnection.createStatement(connection);
        ArrayList<Types> types = new ArrayList<Types>();
        ResultSet resultSet = null;
        try{
            resultSet = statement.executeQuery("SELECT * FROM Types");
            while(resultSet.next()){
                Types type = new Types(resultSet);
                types.add(type);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            sqlConnection.closeConnect(connection, statement, resultSet);
        }
        return types;
    }
}
