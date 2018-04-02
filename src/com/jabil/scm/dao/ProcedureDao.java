package com.jabil.scm.dao;

import com.jabil.scm.model.*;
import com.jabil.scm.utils.SqlConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

@Repository
public class ProcedureDao {
    SqlConnection sqlConnection = new SqlConnection();

    private ArrayList<Category> category;
    private ArrayList<Tags> tag;

    public ProcedureDao(){
        categoryDao dao = new categoryDao();
        category = dao.getCategory();
        TagDao tagDao = new TagDao();
        tag = tagDao.getTags();
    }

    private void getCategoryNameByID(procedure procedure){
        String []cateIDs = procedure.getCategory_id().split(",");
        for(int i = 0; i < cateIDs.length; ++i){
            for(Category category: this.category){
                if(category.getId() == Integer.parseInt(cateIDs[i])){
                    procedure.addCategory(category.getName());
                }
            }
        }
    }

    private void getTypeNameByID(procedure procedure){
        String []cateIDs = procedure.getType_id().split(",");
        for(int i = 0; i < cateIDs.length; ++i){
            for(Tags tags: this.tag){
                if(tags.getId() == Integer.parseInt(cateIDs[i])){
                    procedure.addType(tags.getName());
                }
            }
        }
    }


    public ArrayList<procedure> getProcedure(){
        ArrayList<procedure> procedures = new ArrayList<procedure>();
        Connection connection = sqlConnection.connectSQL();
        Statement statement = sqlConnection.createStatement(connection);
        ResultSet resultSet = null;
        try{
            resultSet = statement.executeQuery("SELECT * FROM V_Doc");
            while(resultSet.next()){
                procedure pro = new procedure(resultSet);
                this.getCategoryNameByID(pro);
                this.getTypeNameByID(pro);
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
