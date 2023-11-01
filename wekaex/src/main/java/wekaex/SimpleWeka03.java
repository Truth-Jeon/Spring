package wekaex;

import java.io.FileInputStream;

import weka.classifiers.trees.RandomForest;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

public class SimpleWeka03 {

	public static void main(String[] args) throws Exception {
//		String[] classLabels = {"no", "yes"};
		String[] classLabels = {"SG", "C"};
//		Instances dataSet = WekaUtil.makeInstances(7, classLabels);
		Instances dataSet = WekaUtil.makeInstances(6, classLabels);
		
		//모델 불러오기
		//RandomForest model = (RandomForest) SerializationHelper.read(new FileInputStream("/Users/jeonhayoon/temp/titanic-001.model"));
		RandomForest model = (RandomForest) SerializationHelper.read(new FileInputStream("/Users/jeonhayoon/temp/basketball_rf.model"));
		
		//분류할 데이터 준비
		//double[] values = new double[] {0.0, 22.0, 1.0, 7.25, 1.0, 0.0, 0.0}; //no
		//double[] values = new double[] {1.0, 22.0, 1.0, 7.25, 1.0, 0.0, 1.0}; //yes
		
		double[] values = new double[] {2.0, 2.9, 3.3, 2.5, 1.2, 0.2}; //no
		
		
		Instance data1 = new DenseInstance(1, values);
		data1.setDataset(dataSet);
		
		//분류하기
		double result = model.classifyInstance(data1);
		
		//정답 출력
		System.out.println(result);
		System.out.println(dataSet.classAttribute().value((int)result));
	}
}
