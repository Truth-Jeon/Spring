package wekaex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java_cup.internal_error;
import weka.core.Attribute;
import weka.core.Instances;

public class WekaUtil {
	public static Instances makeInstances(String[] attrNames, String[]classLabels) {
		ArrayList<Attribute> attrList = new ArrayList<>();
		for(String name: attrNames) {
			attrList.add(new Attribute(name));
		}
		return makeInstances(attrList, classLabels);
	}
	
	public static Instances makeInstances(int length, String[] classLabels) {
		ArrayList<Attribute> attrList = new ArrayList<>();
		for(int i = 0; i < length; i++) {
			attrList.add(new Attribute("attr_" + i));
		}
		return makeInstances(attrList, classLabels);
	}
	
	public static Instances makeInstances(ArrayList<Attribute> attrList, String[] classLabels) {
		List<String> labelList = Arrays.asList(classLabels);
		attrList.add(new Attribute("@@class@@", labelList));
		
		Instances dataSet = new Instances("dataset", attrList, 0);
		dataSet.setClassIndex(attrList.size()-1);
		return dataSet;
	}
	
	public static void main(String[] args) throws Exception {
//		String[] classLabels = {"no", "yes"};
		
		String[] classLabels = {"SG", "C"};
		
//		String[] attrNames = {
//				"sex", "age", "sibsp", "fare", "class3", "class1", "class2"
//		};
		
//		Instances dataSet = makeInstances(attrNames, classLabels); //속성명 목록으로 생성
		
//		Instances dataSet = makeInstances(7, classLabels); //속성 개수로 생성
		
		Instances dataSet = makeInstances(6, classLabels); //속성 개수로 생성
	}
}
