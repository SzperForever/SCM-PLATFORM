package com.jabil.scm.model;
import java.sql.ResultSet;
public class Tags {
    private int id;
    private String name;

    public Tags(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tags(ResultSet resultSet){
        super();
        try{
            this.id = resultSet.getInt(1);
            this.name = resultSet.getString(2);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
