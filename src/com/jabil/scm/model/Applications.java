package com.jabil.scm.model;

import java.sql.ResultSet;

public class Applications extends Reference{
    public Applications(int id, String name, String url) {
        super(id, name, url);
    }

    public Applications(ResultSet resultSet) {
        super(resultSet);
    }
}
