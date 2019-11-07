package org.bambrikii.examples.sorting.selection;

import org.bambrikii.examples.sorting.Sortable;

import static org.bambrikii.examples.sorting.ArrayAsStringFactory.log;

/**
 * Created by Alexander Arakelyan on 25/03/17 21:03.
 */
public class SelectionSort implements Sortable {
	@Override
	public int[] sort(int[] array) {
		for (int i = array.length - 1; i >= 0; i--) {
			int max = i;
			for (int j = 0; j <= i; j++) {
				if (array[j] > array[max]) {
					max = j;
				}
			}
			int tmp = array[i];
			array[i] = array[max];
			array[max] = tmp;
			log(array);
		}
		return array;
	}
}
