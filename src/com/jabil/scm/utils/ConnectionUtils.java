package com.jabil.scm.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionUtils {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private SqlConnection sqlConnection;

    public ConnectionUtils(){

    }

    public ResultSet getResultSet(String sql){
        this.sqlConnection = new SqlConnection();
        this.connection = this.sqlConnection.connectSQL();
        this.statement = this.sqlConnection.createStatement(this.connection);
        try{
            this.resultSet = this.statement.executeQuery(sql);

        }catch (Exception e){
            e.printStackTrace();
        }
        return this.resultSet;
    }
    public void SQLClose(){
        this.sqlConnection.closeConnect(this.connection, this.statement, this.resultSet);
    }

}
