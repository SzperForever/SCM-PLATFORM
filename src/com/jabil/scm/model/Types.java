package com.jabil.scm.model;

import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Types {
    private int id;
    private String TypeName;

    public Types(int id, String typeName) {
        this.id = id;
        TypeName = typeName;
    }
    public Types(ResultSet resultSet){
        super();
        try{
            id = resultSet.getInt(1);
            TypeName = resultSet.getString(2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }
}
