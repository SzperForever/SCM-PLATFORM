package com.jabil.scm.dao;

import com.jabil.scm.model.Applications;
import com.jabil.scm.model.Category;
import com.jabil.scm.utils.SqlConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@Repository
public class app_dao {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private SqlConnection sqlConnection = new SqlConnection();

    /**
     *
     * @return 所有标签
     */
    public ArrayList<Applications> getApplications(){
        connection = sqlConnection.connectSQL();
        statement = sqlConnection.createStatement(connection);
        ArrayList<Applications> app = new ArrayList<Applications>();
        try{
            resultSet = statement.executeQuery("SELECT * FROM Applications");
            while(resultSet.next()){
                app.add(new Applications(resultSet));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            sqlConnection.closeConnect(connection,statement,resultSet);
        }

        return app;
    }
}
