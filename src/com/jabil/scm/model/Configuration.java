package com.jabil.scm.model;

import java.sql.ResultSet;

public class Configuration {
    private String name;
    private String description;

    public Configuration(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Configuration(ResultSet resultSet){
        super();
        try{
            this.name = resultSet.getString(1);
            this.description = resultSet.getString(2);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
