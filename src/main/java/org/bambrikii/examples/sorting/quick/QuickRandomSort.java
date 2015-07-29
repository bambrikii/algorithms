package org.bambrikii.examples.sorting.quick;

import java.util.Random;

public class QuickRandomSort extends QuickSort {
	private Random rnd = new Random();

	@Override
	protected int calcPivotIndex(int low, int high) {
		return low + rnd.nextInt(high - low);
	}
}
