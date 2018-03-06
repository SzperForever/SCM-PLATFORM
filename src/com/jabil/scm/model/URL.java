package com.jabil.scm.model;

public class URL {
    private String link;
    private String title;
    private String PicPath;
    private String Description;
    private String category;

    public URL(String link, String title, String picPath, String description, String category) {
        this.link = link;
        this.title = title;
        PicPath = picPath;
        Description = description;
        this.category = category;
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
}
