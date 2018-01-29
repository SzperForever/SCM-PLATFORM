package com.jabil.rollingboard.model;

import java.sql.ResultSet;
import java.sql.Timestamp;

//产品模型
public class Product {
	private String OrderID, Workcell, BayNum, ModelName, Progress, RouteStep;
	private Timestamp PlanBuildTime, PlanCompleteTime;
	private int Sets, BaySeq;

	public Product(ResultSet resultSet) {
		super();
		try {
		    //trim去除首尾空格
			OrderID = resultSet.getString(1).trim();
	        Workcell = resultSet.getString(2).trim();
	        BayNum = resultSet.getString(3);
	        ModelName= resultSet.getString(4).trim();
	        PlanBuildTime = resultSet.getTimestamp(5);
	        PlanCompleteTime= resultSet.getTimestamp(6);
	        Sets = resultSet.getInt(7);
	        Progress = resultSet.getString(8).trim();
	        RouteStep = resultSet.getString(9).trim();
	        BaySeq = resultSet.getInt(10);
	        BayNum = BayNum.trim().replace(" ","-");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "BayModel [OrderID=" + OrderID + ", Workcell=" + Workcell + ", BayNum=" + BayNum + ", ModelName="
				+ ModelName + ",PlanBuildTime=" + PlanBuildTime + ",PlanCompleteTime=" + PlanCompleteTime + ", Sets="
				+ Sets + ", Progress=" + Progress + ", RouteStep=" + RouteStep + ", BaySeq=" + BaySeq + "]";
	}


    //Get 方法
    public String getOrderID() {
        return OrderID;
    }

    public String getWorkcell() {
        return Workcell;
    }

    public String getBayNum() {
        return BayNum;
    }

    public String getModelName() {
        return ModelName;
    }

    public String getProgress() {
        return Progress;
    }

    public String getRouteStep() {
        return RouteStep;
    }

    public Timestamp getPlanBuildTime() {
        return PlanBuildTime;
    }

    public Timestamp getPlanCompleteTime() {
        return PlanCompleteTime;
    }

    public String getFormatPlanBuildTime(){
        return (new java.text.SimpleDateFormat("yyyy-M-d H:mm:ss")).format(PlanBuildTime);
    }

    public String getFormatPlaneCompleteTime(){
        return (new java.text.SimpleDateFormat("yyyy-M-d H:mm:ss")).format(PlanCompleteTime);
    }

    public int getSets() {
        return Sets;
    }

    public int getBaySeq() {
        return BaySeq;
    }
}
