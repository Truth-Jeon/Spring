package org.galapagos.domain;

import lombok.Data;
import weka.core.DenseInstance;
import weka.core.Instance;

@Data
public class BasketBallVO {
	private double p3;
	private double p2;
	private double trb;
	private double ast;
	private double stl;
	private double blk;
	
	public Instance toInstance() {
		double[] values = {p3, p2, trb, ast, stl, blk};
		return new DenseInstance(1, values);
	}
}
