package org.bambrikii.examples.sorting;

public class MySort implements Sortable {

	int[] arr;

	public int[] sort(int[] array) {
		this.arr = array;
		advance(0);
		return this.arr;
	}

	private void advance(int i) {
		int j = i + 1;
		if (i > -1 && j < arr.length) {
			check(i, j);
			advance(j);
		}
	}

	private void check(int i, int j) {
		if (arr[i] > arr[j]) {
			exchange(i, j);
			advance(i - 1);
		}
	}

	private void exchange(int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
		ArrayAsStringFactory.log(arr);
	}
}
