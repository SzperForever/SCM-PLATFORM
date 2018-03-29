package com.jabil.scm.dao;

import com.jabil.scm.model.ProjectLib;
import com.jabil.scm.utils.SqlConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@Repository
public class ProjectLibDao {
    private SqlConnection sqlConnection = new SqlConnection();
    public ArrayList<ProjectLib> getProjectLib(){
        ArrayList<ProjectLib> projectLibs = new ArrayList<ProjectLib>();
        Connection connection = sqlConnection.connectSQL();
        Statement statement = sqlConnection.createStatement(connection);
        ResultSet resultSet = null;
        try{
            resultSet = statement.executeQuery("SELECT * FROM ProjectLib");
            while(resultSet.next()){
                ProjectLib projectLib = new ProjectLib(resultSet);
                projectLibs.add(projectLib);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlConnection.closeConnect(connection, statement, resultSet);
        }
        return projectLibs;
    }
    public void insertProjectLib(ProjectLib projectLib){
        Connection connection = sqlConnection.connectSQL();
        Statement statement = sqlConnection.createStatement(connection);
        try{
            String sql = "insert into ProjectLib values(" + "'" + projectLib.getId()+  "','" + projectLib.getFileName() +
                    "','" + projectLib.getFilePath() + "','" +
                    projectLib.getTypeid() + "','" + projectLib.getUserid() +
                    "','" + projectLib.getStatus() + "','" + projectLib.getUploadTime() + "')";
            statement.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            sqlConnection.closeConnect(connection, statement,null);
        }
    }
}
