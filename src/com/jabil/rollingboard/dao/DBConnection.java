package com.jabil.rollingboard.dao;

import com.jabil.rollingboard.model.LineModel;
import com.jabil.rollingboard.model.Product;
import org.springframework.stereotype.Repository;

import javax.sound.sampled.Line;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class DBConnection {
    private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String URL = "jdbc:sqlserver://cnshah0sql01:1433";
    private static String USER_NAME = "epull";
    private static String USER_PWD = "Jabil456";
    
    private static Connection connection;
    private static java.sql.Statement statement;
    private static ResultSet resultSet;

    //本地测试用
    //连接MySQL数据库
//    private static String DRIVER = "com.mysql.jdbc.Driver";
//    private static String URL = "jdbc:mysql://localhost:3306/Jabil";
//    private static String USER_NAME = "root";
//    private static String USER_PWD = "199804";


    //连接数据库
    private static void connectSQL() {
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
    //创建声明 用于执行查询语句
    private static void createStatement() {
    	try {
    		statement = connection.createStatement();
    	}
    	catch (SQLException e) {
    		System.out.println("Create Statement failed");
    		e.printStackTrace();
    	}
    }



    @SuppressWarnings("finally")
    public ArrayList<LineModel> getLineData(){
        connectSQL();
        createStatement();
        ArrayList<LineModel> list = new ArrayList<LineModel>();
        try {
            resultSet = statement.executeQuery("SELECT * FROM Bas_LineNo");
            while(resultSet.next()){
                LineModel lineModel = new LineModel(resultSet);
                if(lineModel.getBaySeq_()!=99)
                    list.add(lineModel);
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
            return list;
        }
    }

    @SuppressWarnings("finally")
    //从数据库中读取数据
	public ArrayList<Product> getData() {
    	connectSQL();
        createStatement();
    	//将数据库中的每一行存在Product模型中，再放入List中
    	ArrayList<Product> list = new ArrayList<Product>();
    	try {
    		resultSet = statement.executeQuery("SELECT * FROM V_BI_Feeder_BayNum_Status");
    		while(resultSet.next()){
    			Product product = new Product(resultSet);
    			list.add(product);
            }  
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	//关闭连接
    	finally {
    		try {   
                if(resultSet!=null){  
                	resultSet.close();  
                }  
                if(statement!=null){  
                	statement.close();  
                }  
                if(connection!=null){  
                	connection.close();  
                }
                System.out.println("Close success");
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
			return list;
		}
    }
    
}
