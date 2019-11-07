package org.bambrikii.examples.sorting.my;

import org.bambrikii.examples.sorting.Sortable;

/**
 * Created by Alexander Arakelyan on 09/08/15.
 */
public class MyMinMaxSort implements Sortable {
	private int[] arr;

	public int[] sort(int[] array) {
		// find min find max
		arr = array;
		for (int i = 0; i < arr.length / 2; i++) {
			int min = i;
			int max = i;
			for (int j = i; j < arr.length - i; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
				if (arr[j] > arr[max]) {
					max = j;
				}
			}
			if (i != min) {
				int t = arr[i];
				arr[i] = arr[min];
				arr[min] = t;
				if (i == max) {
					max = min;
				}
			}

			if (i != max) {
				int i2 = arr.length - i - 1;
				int t = arr[i2];
				arr[i2] = arr[max];
				arr[max] = t;
			}
		}
		return arr;
	}
}
