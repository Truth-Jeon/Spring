package org.galapagos.service;

import java.io.FileInputStream;

import org.galapagos.domain.BasketBallVO;
import org.springframework.stereotype.Service;

import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

@Service
public class BasketBallServiceImpl implements BasketBallService{
	Classifier model;
	Instances dataSet;
	
	public BasketBallServiceImpl() {
		try {
			String[] classLabels = {"SG", "C"};
			dataSet = WekaUtil.makeInstances(6, classLabels);
			
			model = (Classifier)SerializationHelper.read(
					new FileInputStream("/Users/jeonhayoon/temp/basketball_rf.model"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String predict(BasketBallVO value) {
		Instance data = value.toInstance();
		data.setDataset(dataSet);
		
		//분류하기
		double result = 0;
		try {
			result = model.classifyInstance(data);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return dataSet.classAttribute().value((int)result);
	}
}
