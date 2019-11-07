package org.bambrikii.examples.sorting.insertion;

import org.bambrikii.examples.sorting.ArrayAsStringFactory;
import org.bambrikii.examples.sorting.Sortable;

public class InsertionSort implements Sortable {

	public int[] sort(int[] array) {
		for (int j = 1; j < array.length; j++) {
			int key = array[j];
			int i = j - 1;
			while (i > -1 && key < array[i]) {
				array[i + 1] = array[i];
				i--;
				ArrayAsStringFactory.log(array);
			}
			array[i + 1] = key;
		}
		return array;
	}
}
