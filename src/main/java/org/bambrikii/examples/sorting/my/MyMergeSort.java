package org.bambrikii.examples.sorting.my;

import org.bambrikii.examples.sorting.ArrayAsStringFactory;
import org.bambrikii.examples.sorting.Sortable;

public class MyMergeSort implements Sortable {

	private int step = 3;
	private int[] arr;
	private int[] helper;

	public int[] sort(int[] array) {
		this.arr = array;
		helper = new int[array.length / step + 1];
		for (int i = 0; i < array.length; i += step) {
			int low = i;
			int high = i + step >= array.length ? array.length : i + step;
			sort(low, high);
			merge();
		}

		for (int i = 0; i < array.length / step; i++) {
			int ix1 = i * step;
			int ix2 = (i + 1) * step;
			for (int j = 0; j < step; j++) {
				if (arr[ix1] > arr[ix2]) {
					int l = arr[ix2];
					for (int k = ix2; k >= ix1; k--) {
						arr[k] = arr[k - 1];
					}
					arr[ix1] = i;
					ix1++;
				} else {
					ix1++;
					ix2++;
				}
			}
		}
		// System.out.println(i);
		return arr;
	}

	private void sort(int low, int high) {
		for (int i = low; i < high; i++) {
			for (int j = i + 1; j < high; j++) {
				if (arr[i] > arr[j]) {
					exchange(i, j);
				}
			}
		}
	}

	private void exchange(int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
		ArrayAsStringFactory.log(arr);
	}

	private void merge() {
		int min = 0;
		for (int i = 1; i < step; i++) {
			if (arr[helper[i]] < arr[helper[min]]) {
				min = i;
			}
		}
		helper[min]++;
	}
}
