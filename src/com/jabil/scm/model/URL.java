package com.jabil.scm.model;

import java.sql.ResultSet;

public class URL {
    private String link;
    private String title;
    private String PicPath;
    private String Description;
    private String category;
    private String tag;

    public URL(String link, String title, String picPath, String description, String category, String tag) {
        this.link = link;
        this.title = title;
        this.PicPath = picPath;
        this.Description = description;
        this.category = category;
        this.tag = tag;
    }
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
            this.category = resultSet.getString(5).trim();
            this.tag = resultSet.getString(6).trim();

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
