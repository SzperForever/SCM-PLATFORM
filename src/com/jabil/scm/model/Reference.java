package com.jabil.scm.model;

import java.sql.ResultSet;

public class Reference {
    private int id;
    private String name;
    private String url;

    public Reference() {
    }

    public Reference(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Reference(ResultSet resultSet) {
        super();
        try{
            this.id = resultSet.getInt(1);
            this.name = resultSet.getString(2).trim();
            this.url = resultSet.getString(3).trim();
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
