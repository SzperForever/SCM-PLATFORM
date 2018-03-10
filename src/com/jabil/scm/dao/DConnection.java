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
    private static String URL = "jdbc:sqlserver://123.207.49.64:1433";
    private static String USER_NAME = "sa";
    private static String USER_PWD = "Jabil456";

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

    /**
     *
     * @return Urls
     */
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

    /**
     *
     * @return
     */
    public ArrayList<String> getCategory(){
        connectSQL();
        createStatement();
        ArrayList<String> cates = new ArrayList<String>();
        try{
            resultSet = statement.executeQuery("SELECT * FROM Category");
            while(resultSet.next()){
                Category category = new Category(resultSet);
                cates.add(category.getName());
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

        return cates;
    }

    /**
     *
     * @param category
     */
     public void insertCategory(Category category){
        //Category category = new Category(5, "ABAFDFDF");
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

    /**
     *
     * @param url
     */
     public void insertUrl(URL url){
         connectSQL();
         createStatement();
         String sql = "insert into URL values(" + "'" + url.getLink() + "','" + url.getTitle() + "','" + url.getPicPath() + "','" + url.getDescription() + "','" + url.getCategory() + "')";
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
