package com.jabil.scm.model;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;

public class procedure {
    private int id;
    private String FilePath;
    private String title;
    private String picPath;
    private String description;
    private String Category_id;
    private String type_id;
    private int number;
    private ArrayList<String> category;
    private ArrayList<String> type;

    public procedure(int id, String filePath, String title, String description, String category_id, String tag_id, int number) {
        this.id = id;
        FilePath = filePath;
        this.title = title;
        this.description = description;
        Category_id = category_id;
        this.number = number;
    }

    public procedure(ResultSet resultSet) {
        super();
        try {
            if (System.getProperty("os.name").equals("Mac OS X")) {
                id = resultSet.getInt(1);
                FilePath = resultSet.getString(2);
                title = resultSet.getString(3);
                description = resultSet.getString(4);
                Category_id = resultSet.getString(5);
                type_id = resultSet.getString(6);
                number = resultSet.getInt(7);
            } else {
                id = resultSet.getInt(1);
                FilePath = resultSet.getString(2);
                title = resultSet.getString(3);
                description = resultSet.getString(4);
                Category_id = resultSet.getString(5);
                type_id = resultSet.getString(6);
                number = resultSet.getInt(7);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        category = new ArrayList<>();
        type = new ArrayList<>();
    }

    public void addCategory(String category) {
        this.category.add(category);
    }

    public void addType(String type) {
        this.type.add(type);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory_id() {
        return Category_id;
    }

    public void setCategory_id(String category_id) {
        Category_id = category_id;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }

    public ArrayList<String> getType() {
        return type;
    }

    public void setType(ArrayList<String> type) {
        this.type = type;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }
}
