package com.jabil.scm.model;

import java.sql.ResultSet;

public class Users {
    private int Userid;
    private String NTid;
    private String Alias;
    private String MailAddress;
    private String DisplayName;

    public Users(int userid, String NTid, String alias, String mailAddress, String displayName) {
        Userid = userid;
        this.NTid = NTid;
        Alias = alias;
        MailAddress = mailAddress;
        DisplayName = displayName;
    }
    public Users(ResultSet resultSet){
        super();
        try{
            Userid = resultSet.getInt(1);
            NTid = resultSet.getString(2);
            Alias = resultSet.getString(3);
            MailAddress = resultSet.getString(4);
            DisplayName = resultSet.getString(5);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getUserid() {
        return Userid;
    }

    public void setUserid(int userid) {
        Userid = userid;
    }

    public String getNTid() {
        return NTid;
    }

    public void setNTid(String NTid) {
        this.NTid = NTid;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public String getMailAddress() {
        return MailAddress;
    }

    public void setMailAddress(String mailAddress) {
        MailAddress = mailAddress;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }
}
