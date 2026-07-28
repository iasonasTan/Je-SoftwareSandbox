package app.avcalc.logic;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
	private final List<Double> mValues = new ArrayList<>();

	public Calculator() {
	}
	
	public void add(double value) {
		mValues.add(value);
	}
	
	public int count() {
		return mValues.size();
	}
	
	public double calculate() {
		int n = mValues.size();
		if(n==0)
			return 0;
		double sum = 0;
		for(Double value: mValues) {
			sum += value;
		}
		return sum/n;
	}

	public void reset() {
		mValues.clear();
	}
}
