package com.jabil.scm.dao;

import com.jabil.rollingboard.model.LineModel;
import com.jabil.scm.model.Category;
import org.springframework.stereotype.Repository;
import com.jabil.scm.model.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class DConnection {
    private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String URL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=scm";
    private static String USER_NAME = "sa";
    private static String USER_PWD = "@@Szp686521";

    private static Connection connection;
    private static java.sql.Statement statement;
    private static ResultSet resultSet;

    /**
     * sql server链接测试
     */
    public void connectSQL() {
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


    }

    /**
     * 执行查询语句
     */
    private static void createStatement() {
        try {
            statement = connection.createStatement();
        }
        catch (SQLException e) {
            System.out.println("Create Statement failed");
            e.printStackTrace();
        }
    }

    public ArrayList<URL> getUrl(){
        connectSQL();
        createStatement();
        ArrayList<URL> list = new ArrayList<URL>();
        try {
            resultSet = statement.executeQuery("SELECT * FROM URL");
            while(resultSet.next()){
                URL url = new URL(resultSet);
                System.out.println(url.getDescription());
                list.add(url);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //关闭连接
        finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
                System.out.println("Close success");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return list;
    }
    public ArrayList<Category> getCategory(){
        connectSQL();
        createStatement();
        ArrayList<Category> categories = new ArrayList<Category>();
        try{
            resultSet = statement.executeQuery("SELECT * FROM Category");
            while(resultSet.next()){
                Category category = new Category(resultSet);
                categories.add(category);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
                System.out.println("Close success");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return categories;
    }
     public void insertCategory(){
        Category category = new Category(5, "ABAFDFDF");
        connectSQL();
        createStatement();
        String sql = "insert into Category values(" + category.getId() + "," + "'" + category.getName() + "')";
        try{
            statement.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
                System.out.println("Close success");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
     }
}
