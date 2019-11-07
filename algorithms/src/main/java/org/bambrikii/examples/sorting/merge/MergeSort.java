package org.bambrikii.examples.sorting.merge;

import org.bambrikii.examples.sorting.ArrayAsStringFactory;
import org.bambrikii.examples.sorting.Sortable;

public class MergeSort implements Sortable {

	private int[] arr;

	public int[] sort(int[] array) {
		this.arr = array;
		sort(0, arr.length - 1);
		return arr;
	}

	private void sort(int low, int high) {
		if (low < high) {
			int middle = low + (high - low) / 2;
			sort(low, middle);
			sort(middle + 1, high);
			sort(low, middle, high);
		}
	}

	private void sort(int low, int middle, int high) {
		int i = low;
		int j = middle + 1;
		int k = low;

		while (i <= middle && j <= high) {
			if (arr[i] < arr[j]) {
				exchange(k, i);
				i++;
			} else {
				exchange(k, j);
				j++;
			}
			k++;
		}

		while (i < high) {
			k++;
			i++;
		}
	}

	private void exchange(int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
		ArrayAsStringFactory.log(arr);
	}

}
