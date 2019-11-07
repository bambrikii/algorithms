package org.bambrikii.examples.sorting.my;

import org.bambrikii.examples.sorting.ArrayAsStringFactory;
import org.bambrikii.examples.sorting.Sortable;

public class MyBubbleSort implements Sortable {

	private int[] arr;

	public int[] sort(int[] array) {
		this.arr = array;
		for (int i = array.length - 1; i >= 0; i--) {
			int maxi = i;
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[maxi]) {
					maxi = j;
				}
			}
			if (maxi != i) {
				exchange(maxi, i);
			}
		}
		return arr;
	}

	private void exchange(int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
		ArrayAsStringFactory.log(arr);
	}
}
