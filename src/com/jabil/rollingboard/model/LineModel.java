package com.jabil.rollingboard.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

//代表生产线的模型
public class LineModel {
    private int status_;                        //此生产线的状态  小于 red_hour是红色 大于green_hour是黄色 其余是绿色
    private String bayNum_;                     //生产线Num
    private int baySeq_;                        //排序索引
    private ArrayList<Product> building_;       //储存 building状态的List
    private ArrayList<Product> loadingComplete_;//储存 loadingComplete状态的list
    private final static int red_hour = 1;      //red_hour值
    private final static int green_hour = 12;


    public LineModel(String bayNum, int baySeq) {
        super();
        this.bayNum_ = bayNum;
        this.baySeq_ = baySeq;
        this.building_ = new ArrayList<Product>();
        this.loadingComplete_ = new ArrayList<Product>();
    }

    public LineModel(ResultSet resultSet){ //由 查询数据库 返回的 结果集 构造
        super();
        try {
            bayNum_ = resultSet.getString(2).trim().replace(' ','-');
            baySeq_ = resultSet.getInt(10);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.building_ = new ArrayList<Product>();
        this.loadingComplete_ = new ArrayList<Product>();
    }

    //比较Timestamp类型的时间， 换算算成时间戳 然后比较
    private boolean CompareTime(Timestamp timeA, Timestamp timeB) {
        return timeA.getTime() > timeB.getTime();
    }

    //计算Bay的颜色
    public void calStatus() {
        long sumTime = 0;
        HashMap<String, Boolean> map = new HashMap<>();
        for (Product product: loadingComplete_) {
            if(map.containsKey(product.getOrderID())) continue;
            map.put(product.getOrderID(),Boolean.TRUE);
            sumTime += (product.getPlanCompleteTime().getTime() - product.getPlanBuildTime().getTime());
        }
        if(sumTime == 0){
            status_ = 1;
        }
        else{
            if(sumTime / 1000 <= red_hour * 3600){
                status_ = 0;
            }
            else if(sumTime / 1000 <= green_hour * 3600){
                status_ = 1;
            }
            else {
                status_ = 2;
            }
        }

    }

    //提供一个接口用于添加产品
    public void addProduct(Product product) {
        if (product.getProgress().equals("Building")) {
            building_.add(product);
        } else {
            loadingComplete_.add(product);
        }
    }

    //Get 方法
    public int getStatus_() {
        return status_;
    }

    public String getBayNum_() {
        return bayNum_;
    }

    public int getBaySeq_() {
        return baySeq_;
    }

    public ArrayList<Product> getBuilding_() {
        return building_;
    }

    public ArrayList<Product> getLoadingComplete_() {
        return loadingComplete_;
    }

}
