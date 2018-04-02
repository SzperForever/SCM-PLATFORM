package com.jabil.scm.dao;

import com.jabil.scm.model.Category;
import com.jabil.scm.model.Types;
import com.jabil.scm.model.URL;
import com.jabil.scm.model.procedure;
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
    private ArrayList<Types> types;

    public ProcedureDao(){
        categoryDao dao = new categoryDao();
        category = dao.getCategory();
        TypesDao typesDao = new TypesDao();
        types = typesDao.getTypes();
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
            for(Types types : this.types){
                if(types.getId() == Integer.parseInt(cateIDs[i])){
                    procedure.addType(types.getTypeName());
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
