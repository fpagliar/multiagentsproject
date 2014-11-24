package utils;

import java.util.ArrayList;
import java.util.List;

public class RandomGenerator {

//	private static final long INITIAL_SEED = 1;
//	private static Random randomNumberGenerator = new Random(INITIAL_SEED);
	private static final List<Double> values = new ArrayList<>();
	private static int index = 0;
	
	static {
		for(int i = 2; i < 1000; i++)
			values.add(1.0/i);
		for(int i = 2; i < 200; i++)
			values.add(i/(i+1.0));
		for (int i = 0; i < values.size(); i += 5) {
			values.add(values.get(i));
		}
		for (int i = 0; i < values.size(); i += 13) {
			values.add(values.get(i));
		}
		for (int i = 0; i < values.size(); i += 23) {
			values.add(values.get(i));
		}
		for (int i = 0; i < values.size(); i += 37) {
			values.add(values.get(i));
		}
		for (int i = 0; i < values.size(); i += 31) {
			values.add(values.get(i));
		}
	}
	
	
	public static double getNext() {
		index++;
		return values.get(index % values.size());
//		return randomNumberGenerator.nextDouble();
	}
	
}
