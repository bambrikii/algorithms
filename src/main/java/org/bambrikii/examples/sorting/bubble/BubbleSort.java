package org.bambrikii.examples.sorting.bubble;

import org.bambrikii.examples.sorting.ArrayAsStringFactory;
import org.bambrikii.examples.sorting.Sortable;

public class BubbleSort implements Sortable {

	public int[] sort(int[] array) {
		int t;
		for (int i = array.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (array[j] > array[j + 1]) {
					t = array[j];
					array[j] = array[j + 1];
					array[j + 1] = t;
					ArrayAsStringFactory.log(array);
				}
			}
		}
		return array;
	}

}
