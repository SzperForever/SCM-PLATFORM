package com.jabil.scm.model;

import java.sql.ResultSet;

public class Category {
    private int id;
    private String name;
    public Category(int id, String name){
        this.id = id;
        this.name = name;
    }
    public Category(ResultSet resultSet){
        super();
        try{
            this.id = resultSet.getInt(1);
            this.name = resultSet.getString(2).trim();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
