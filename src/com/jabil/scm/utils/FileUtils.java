package com.jabil.scm.utils;
import com.jabil.scm.model.ProjectLib;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.print.attribute.standard.Media;

public class FileUtils {
    public static void resourceFiles(String path){
        File file = new File(path);
        File files[] = file.listFiles();
        if (files == null){
            return;
        }
        for(File f : files){

            if(f.isDirectory()){
                System.out.print("文件夹: ");
                System.out.println(f.getAbsolutePath());

                // 为 文件夹继续遍历
                resourceFiles(f.getAbsolutePath());


                // 判断是否为 文件
            } else if(f.isFile()){

                System.out.print("文件: ");
                System.out.println(f.getAbsolutePath());

            } else {
                System.out.print("未知错误文件");
            }
        }
    }
    public static void insertEntity(Class c, Object obj)throws IllegalAccessException{
        // System.out.println("1");
        if (obj == null || c.getSimpleName().equals(obj.getClass().getName()))
            return;
        Field[] fields = obj.getClass().getDeclaredFields();
        int fieldSize = fields.length;
        String tableName = c.getSimpleName().toLowerCase();// person
        String[] types1 = { "int", "java.lang.String", "boolean", "char",
                "float", "double", "long", "short", "byte" };
        String[] types2 = { "Integer", "java.lang.String", "java.lang.Boolean",
                "java.lang.Character", "java.lang.Float", "java.lang.Double",
                "java.lang.Long", "java.lang.Short", "java.lang.Byte" };

        StringBuffer sql = new StringBuffer("insert into " + tableName
                + " values(");
        for (Field field : fields) {

            sql.append((String)field.get(obj)+",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        System.out.println(sql);

    }
    public static Method getGetMethod(Class objectClass, String fieldName) {
        StringBuffer sb = new StringBuffer();
        sb.append("get");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        try {
            return objectClass.getMethod(sb.toString());
        } catch (Exception e) {
        }
        return null;
    }

    public static int createTable(Class c) {
        //Class c = object.getClass();
        String tableName = c.getSimpleName().toLowerCase();// person
        System.out.println(tableName);
        return 0;

    }
    public static void mkdir(){
        File f = new File("/Users/szper/Desktop/SCM/test/haha", "test.txt");
        if(!f.getParentFile().exists()){
            f.getParentFile().mkdirs();
            System.out.println("创建成功");
        }
        /*File file = new File( "/Users/szper/Desktop/SCM/test.txt");
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }

        }*/
    }
    public static void main(String args[])throws IllegalAccessException{

        System.out.println("file Go...");
        test t = new test();
        t.setId(3);
        t.setName("haha");
        mkdir();
        // 这里改成你要遍历的目录路径
        //resourceFiles("/Users/szper/Desktop/SQL");
        //insertEntity(test.class, t);
        //System.out.println(getGetMethod(test.class, "name").invoke(t));
        System.out.println("file End.");

    }

}
