package com.jabil.scm.model;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
public class ProjectLib {
    private long id;
    private String FileName;
    private String FilePath;
    private int Typeid;
    private int Userid;
    private String Status;
    private Timestamp UploadTime;

    public ProjectLib(long id, String fileName, String filePath, int typeid, int userid, String status, Timestamp uploadTime) {
        this.id = id;
        FileName = fileName;
        FilePath = filePath;
        Typeid = typeid;
        Userid = userid;
        Status = status;
        UploadTime = uploadTime;
    }
    public ProjectLib(ResultSet resultSet){
        super();
        try{
            id = resultSet.getInt(1);
            FileName = resultSet.getString(2);
            FilePath = resultSet.getString(3);
            Typeid = resultSet.getInt(4);
            Userid = resultSet.getInt(5);
            Status = resultSet.getString(6);
            UploadTime = resultSet.getTimestamp(7);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public int getTypeid() {
        return Typeid;
    }

    public void setTypeid(int typeid) {
        Typeid = typeid;
    }

    public int getUserid() {
        return Userid;
    }

    public void setUserid(int userid) {
        Userid = userid;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Timestamp getUploadTime() {
        return UploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        UploadTime = uploadTime;
    }
    public String getFormatUploadTime(){
        return (new java.text.SimpleDateFormat("yyyy-M-d H:mm:ss")).format(UploadTime);
    }
}
