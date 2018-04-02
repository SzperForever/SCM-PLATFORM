package com.jabil.scm.dao;

import com.jabil.scm.model.Category;
import com.jabil.scm.model.ProjectLib;
import com.jabil.scm.model.Types;
import com.jabil.scm.model.procedure;
import com.jabil.scm.utils.SqlConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@Repository
public class ProjectLibDao {
    private SqlConnection sqlConnection = new SqlConnection();

    private ArrayList<Category> category;
    private ArrayList<Types> types;

    public ProjectLibDao() {
        categoryDao dao = new categoryDao();
        category = dao.getCategory();
        TypesDao typesDao = new TypesDao();
        types = typesDao.getTypes();
    }

    private void getTypeNameByID(ProjectLib projectLib) {
        int cateIDs = projectLib.getTypeid();

        for (Category category : this.category) {
            if (category.getId() == cateIDs) {
                projectLib.setTypeName(category.getName());
            }
        }

    }


    public ArrayList<ProjectLib> getProjectLib() {
        ArrayList<ProjectLib> projectLibs = new ArrayList<ProjectLib>();
        Connection connection = sqlConnection.connectSQL();
        Statement statement = sqlConnection.createStatement(connection);
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM ProjectLib");
            while (resultSet.next()) {
                ProjectLib projectLib = new ProjectLib(resultSet);
                getTypeNameByID(projectLib);
                projectLibs.add(projectLib);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlConnection.closeConnect(connection, statement, resultSet);
        }
        return projectLibs;
    }

    public void insertProjectLib(ProjectLib projectLib) {
        Connection connection = sqlConnection.connectSQL();
        Statement statement = sqlConnection.createStatement(connection);
        try {
            String sql = "insert into ProjectLib values(" + "'" + projectLib.getId() + "','" + projectLib.getFileName() +
                    "','" + projectLib.getFilePath() + "','" +
                    projectLib.getTypeid() + "','" + projectLib.getUserid() +
                    "','" + projectLib.getStatus() + "','" + projectLib.getUploadTime() + "')";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlConnection.closeConnect(connection, statement, null);
        }
    }
}
