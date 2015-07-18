package org.bambrikii.examples.sorting.bubble;

import org.bambrikii.examples.sorting.ArrayAsStringFactory;
import org.bambrikii.examples.sorting.Sortable;

public class BubbleSort implements Sortable {

	public int[] sort(int[] array) {
		int t;
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] > array[j]) {
					t = array[i];
					array[i] = array[j];
					array[j] = t;
					ArrayAsStringFactory.log(array);
				}
			}
		}
		return array;
	}

}
