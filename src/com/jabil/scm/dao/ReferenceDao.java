package com.jabil.scm.dao;

import com.jabil.scm.model.Category;
import com.jabil.scm.model.Reference;
import com.jabil.scm.utils.SqlConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@Repository
public class ReferenceDao {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private SqlConnection sqlConnection = new SqlConnection();

    /**
     *
     * @return 所有标签
     */
    public ArrayList<Reference> getReference(){
        connection = sqlConnection.connectSQL();
        statement = sqlConnection.createStatement(connection);
        ArrayList<Reference> ref = new ArrayList<Reference>();
        try{
            resultSet = statement.executeQuery("SELECT * FROM Reference");
            while(resultSet.next()){
                ref.add(new Reference(resultSet));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            sqlConnection.closeConnect(connection,statement,resultSet);
        }

        return ref;
    }
}
