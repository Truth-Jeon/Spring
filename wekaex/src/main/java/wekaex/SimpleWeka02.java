package wekaex;

import java.io.File;
import java.util.Random;

import org.netlib.util.doubleW;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class SimpleWeka02 {

	public static void main(String[] args) throws Exception{
		int numfolds = 10;
		int numfold = 0;
		int seed = 1;
		
		CSVLoader csvLoader = new CSVLoader();
		csvLoader.setSource(new File("/Users/jeonhayoon/temp/basketball_stat2.csv"));
		
		//나중에 많이 접할 객체 : Instances -> data를 의미함.
		//데이터셋
		Instances data = csvLoader.getDataSet();
		Instances train = data.trainCV(numfolds, numfold, new Random(seed));
		Instances test = data.testCV(numfolds, numfold);
		
		RandomForest model = new RandomForest();
		
		train.setClassIndex(train.numAttributes()-1);
		test.setClassIndex(test.numAttributes()-1);
		
		Evaluation eval = new Evaluation(train);
				
		eval.crossValidateModel(model, train, numfolds, new Random(seed));
		
		
		model.buildClassifier(train);
		
		//평가
		eval.evaluateModel(model, test);
		
		//결과 출력
		System.out.println(model);
		System.out.println(eval.toSummaryString());
		System.out.println(eval.toMatrixString());
		
		//실제 1개의 데이터 판정
//		Instance indata = test.get(0);
		Instance indata = test.get(1);
		
		System.out.println(indata);
		
		//예측(predict)
		double result = model.classifyInstance(indata);
		System.out.println(result);
		System.out.println(test.classAttribute().value((int)result));
	}

}
