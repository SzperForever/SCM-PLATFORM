package com.jabil.rollingboard.service;

import com.jabil.rollingboard.dao.DBConnection;
import com.jabil.rollingboard.model.LineModel;
import com.jabil.rollingboard.model.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;


@org.springframework.stereotype.Service
public class Service {
	private DBConnection dbConnection = new DBConnection();
	private ArrayList<LineModel> data_;
	private int building_, loadingCompleted_;

	public Service() {
		super();
	}

	//排序 根据数据库中的BaySeq排序
    private Comparator<LineModel> comparator = new Comparator<LineModel>(){
        public int compare(LineModel s1, LineModel s2) {
            return s1.getBaySeq_() - s2.getBaySeq_();
        }
    };

	private Comparator<Product> comparatorOfProduct = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            if (o1.getPlanBuildTime() != o2.getPlanBuildTime()) {
                return (int) (o1.getPlanBuildTime().getTime() - o2.getPlanBuildTime().getTime());
            } else {
                if (o1.getRouteStep().equals("Bottom")) {
                    return -1;
                }
                else if (o2.getRouteStep().equals("Bottom")) {
                    return 1;
                }
            }
            return 0;
        }
    };

	private void addData(boolean addNullLine) {
		ArrayList<Product> source = dbConnection.getData();
		int bayCount = 0;
		building_ = 0;
		loadingCompleted_ = 0;
		HashMap<String, Integer> hashMap = new HashMap<String,Integer>();
		//利用HashMap将同一产线的model放在一起
		for (Product product : source) {
			String bayNum = product.getBayNum();
			if(!hashMap.containsKey(bayNum)) {
				hashMap.put(bayNum, bayCount++);
				data_.add(new LineModel(bayNum,product.getBaySeq()));
			}
			data_.get((Integer)hashMap.get(bayNum)).addProduct(product);
			//Count 
			if(product.getProgress().equals("Building")) {
				building_++;
			}
			else if(product.getProgress().equals("LoadingCompleted")){
				loadingCompleted_++;
			}
		}


		//计算每一条产线的状态
		for (LineModel lineModel : data_) {
			lineModel.calStatus();
			lineModel.getBuilding_().sort(comparatorOfProduct);
			lineModel.getLoadingComplete_().sort(comparatorOfProduct);
		}

        if(addNullLine){
            ArrayList<LineModel> fullLine = dbConnection.getLineData();
            for(LineModel lineModel: fullLine){
                String bayNum = lineModel.getBayNum_();
                if(!hashMap.containsKey(bayNum)) {
                    hashMap.put(bayNum, bayCount++);
                    data_.add(lineModel);
                }
            }
        }

        data_.sort(comparator);
	}
	
	public int getBuildingCount() {
		return building_;
	}
	
	public int getLoadingCompletedCount() {
		return loadingCompleted_;
	}
	
	public ArrayList<LineModel> getData(boolean addNullLine){
		data_ = new ArrayList<LineModel>(35);
		addData(addNullLine);
		return data_;
	}	
}
