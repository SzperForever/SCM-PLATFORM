package com.jabil.scm.model;

import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;

public class Links {
    private String name;
    private String url;

    public Links(String name, String url) {
        this.name = name;
        this.url = url;
    }
    public Links(ResultSet resultSet){
        super();
        try{
            this.name = resultSet.getString(1);
            this.url = resultSet.getString(2);
        }catch (Exception e){
            e.printStackTrace();
        }
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
