package com.jabil.scm.dao;

import com.jabil.scm.model.procedure;
import com.jabil.scm.utils.SqlConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
@Repository
public class ProcedureDao {
    SqlConnection sqlConnection = new SqlConnection();
    public ArrayList<procedure> getProcedure(){
        ArrayList<procedure> procedures = new ArrayList<procedure>();
        Connection connection = sqlConnection.connectSQL();
        Statement statement = sqlConnection.createStatement(connection);
        ResultSet resultSet = null;
        try{
            resultSet = statement.executeQuery("SELECT * FROM [procedure]");
            while(resultSet.next()){
                procedure pro = new procedure(resultSet);
                procedures.add(pro);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            sqlConnection.closeConnect(connection, statement, resultSet);
        }
        return procedures;
    }
}
