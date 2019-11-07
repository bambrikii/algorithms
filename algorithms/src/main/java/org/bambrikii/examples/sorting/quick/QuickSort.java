package org.bambrikii.examples.sorting.quick;

import org.bambrikii.examples.sorting.ArrayAsStringFactory;
import org.bambrikii.examples.sorting.Sortable;

public class QuickSort implements Sortable {

	private int[] arr;

	public int[] sort(int[] array) {
		this.arr = array;
		int low = 0;
		int high = arr.length - 1;
		sort(low, high);
		return arr;
	}

	private void sort(int low, int high) {
		int pivot = arr[calcPivotIndex(low, high)];
		int i = low;
		int j = high;
		while (i <= j) {
			while (arr[i] < pivot) {
				i++;
			}
			while (arr[j] > pivot) {
				j--;
			}
			if (i <= j) {
				int t = arr[i];
				arr[i] = arr[j];
				arr[j] = t;
				ArrayAsStringFactory.log(arr);
				i++;
				j--;
			}
		}
		if (low < j) {
			sort(low, j);
		}
		if (high > i) {
			sort(i, high);
		}
	}

	protected int calcPivotIndex(int low, int high) {
		return low + (high - low) / 2;
	}
}
