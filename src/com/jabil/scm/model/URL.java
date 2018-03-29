package com.jabil.scm.model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class URL {
    private String link;
    private String title;
    private String PicPath;
    private String Description;
    private ArrayList<String> category;
    private String tagID;
    private int id;
    private String category_id;

    public URL(String link, String title, String picPath, String description, ArrayList<String> category, String tagID, int id, String category_id) {
        this.link = link;
        this.title = title;
        this.PicPath = picPath;
        this.Description = description;
        this.category = category;
        this.tagID = tagID;
        this.id = id;
        this.category_id = category_id;
    }

    /**
     * 从数据库中读取数据，但注意这里的category并没有数据，仅仅是初始化
     * @param resultSet 数据库查询结果集
     */
    public URL(ResultSet resultSet){
        super();
        try{
            this.link = resultSet.getString(1).trim();
            this.title = resultSet.getString(2).trim();

            if(resultSet.getString(3)==null){
                this.PicPath = "null";
            }
            else {
                this.PicPath = resultSet.getString(3);
            }
            if(resultSet.getString(4).trim() != null)
                this.Description = resultSet.getString(4).trim();
            else
                this.Description = "judge in";
            this.tagID = resultSet.getString(5).trim();
            this.id = resultSet.getInt(6);
            this.category_id = resultSet.getString(7);
            category = new ArrayList<>();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPicPath() {
        return PicPath;
    }

    public void setPicPath(String picPath) {
        PicPath = picPath;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTagID() {
        return tagID;
    }

    public void setTagID(String tagID) {
        this.tagID = tagID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }

    public void addCategory(String category){
        this.category.add(category);
    }
}
