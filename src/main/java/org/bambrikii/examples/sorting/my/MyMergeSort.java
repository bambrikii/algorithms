package org.bambrikii.examples.sorting.my;

import org.bambrikii.examples.sorting.Sortable;

public class MyMergeSort implements Sortable {

	private int[] arr;

	public int[] sort(int[] array) {
		this.arr = array;

		int step = 2;
		for (int i = 0; i < arr.length; i += step) {
			if (arr[i] > arr[i + 1]) {
				int t = arr[i];
				arr[i] = arr[i + 1];
				arr[i + 1] = t;
			}
		}

		while (step < arr.length) {
			int i = 0;
			while (i < arr.length - 1) {
				int j = i + step > arr.length ? arr.length : i + step;
				int k = i + step * 2 > arr.length ? arr.length : i + step * 2;
				while (i < j && j < k) {
					if (arr[i] > arr[j]) {
						int t = arr[j];
						for (int l = j - 1; l >= i; l--) {
							arr[l + 1] = arr[l];
						}
						arr[i] = t;
						j++;
					}
					i++;
				}
				i = k;
			}
			step *= 2;
		}
		return arr;
	}

}
