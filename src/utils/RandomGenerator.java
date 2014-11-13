package utils;

import java.util.Random;

public class RandomGenerator {

	private static final long INITIAL_SEED = 1;
	private static Random randomNumberGenerator = new Random(INITIAL_SEED);
	
	public static double getNext() {
		return randomNumberGenerator.nextDouble();
	}
	
}
