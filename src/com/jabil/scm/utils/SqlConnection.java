package com.jabil.scm.utils;

import com.jabil.scm.model.Category;
import org.springframework.stereotype.Repository;
import com.jabil.scm.model.URL;

import java.sql.*;
import java.util.ArrayList;


public class SqlConnection {
    private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String URL = "jdbc:sqlserver://123.207.49.64:1433";
    private static String USER_NAME = "sa";
    private static String USER_PWD = "Jabil456";

//    private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//    private static String URL = "jdbc:sqlserver://CNSHAH0MROSQL01:1433";
//    private static String USER_NAME = "ePlatform";
//    private static String USER_PWD = "Jabil456";
//
    //private static Connection connection;
    private static java.sql.Statement statement;
    private static ResultSet resultSet;

    /**
     * sql server链接测试
     */
    public Connection connectSQL() {
        Connection connection = null;
        //初始化数据库连接
        //加载SQLServer驱动
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Load Drive failed!");
            e.printStackTrace();
        }
        //创建连接
        try {
            connection = DriverManager.getConnection(URL, USER_NAME,USER_PWD);
            System.out.println("Connect to SQL Server success!");
        } catch (SQLException e) {
            System.out.println("Failed to connect to SQL Server!");
            e.printStackTrace();
        }
        return connection;

    }

    /**
     * 执行查询语句
     */
    public Statement createStatement(Connection connection) {
        try {
            statement = connection.createStatement();
        }
        catch (SQLException e) {
            System.out.println("Create Statement failed");
            e.printStackTrace();
        }
        return statement;
    }


    public void closeConnect(Connection connection, Statement statement, ResultSet resultSet){
        try{
            if(resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
            if(connection != null)
                connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            System.out.println("Close success");
        }
    }

}
