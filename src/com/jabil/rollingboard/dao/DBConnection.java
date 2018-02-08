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

    //���ز�����
    //����MySQL���ݿ�
//    private static String DRIVER = "com.mysql.jdbc.Driver";
//    private static String URL = "jdbc:mysql://localhost:3306/Jabil";
//    private static String USER_NAME = "root";
//    private static String USER_PWD = "199804";


    //�������ݿ�
    private static void connectSQL() {
        //��ʼ�����ݿ�����
        //����SQLServer����
        try {  
            Class.forName(DRIVER);  
        } catch (ClassNotFoundException e) {  
            System.out.println("Load Drive failed!");
            e.printStackTrace();  
        }  
        //��������
        try {
            connection = DriverManager.getConnection(URL, USER_NAME,USER_PWD);
            System.out.println("Connect to SQL Server success!");
        } catch (SQLException e) {  
            System.out.println("Failed to connect to SQL Server!");
            e.printStackTrace();  
        }  
  
        
    }
    //�������� ����ִ�в�ѯ���
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
        //�ر�����
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
    //�����ݿ��ж�ȡ����
	public ArrayList<Product> getData() {
    	connectSQL();
        createStatement();
    	//�����ݿ��е�ÿһ�д���Productģ���У��ٷ���List��
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
    	//�ر�����
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
