package org.galapagos.domain;

import lombok.Data;
import weka.core.DenseInstance;
import weka.core.Instance;

@Data
public class TitanicVO {
	private double sex;
	private double age;
	private double sibsp;
	private double fare;
	private double class3;
	private double class1;
	private double class2;
	
	public Instance toInstance() {
		double[] values = {sex, age, sibsp, fare, class3, class1, class2};
		return new DenseInstance(1, values);
	}
}