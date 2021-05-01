package org.bambrikii.examples.sorting.quick;

import org.bambrikii.examples.sorting.Sortable;

import static org.bambrikii.examples.sorting.ArrayUtils.swap;

public class QuickSortLastElementAsPivot implements Sortable {
  @Override
  public int[] sort(int[] array) {
    int[] result = new int[array.length];
    System.arraycopy(array, 0, result, 0, array.length);

    sort(array, 0, array.length - 1);

    return result;
  }

  private void sort(int[] arr, int from, int to) {
    if (from >= to) {
      return;
    }
    int pivot = pivot(arr, from, to);
    sort(arr, from, pivot - 1);
    sort(arr, pivot + 1, to);
  }

  private int pivot(int[] arr, int from, int to) {
    int pivotVal = arr[to];
    int currPivot = from;
    for (int j = from; j <= to; j++) {
      if (arr[j] < pivotVal) {
        swap(arr, currPivot, j);
        currPivot++;
      }
    }
    swap(arr, currPivot, to);
    return currPivot;
  }
}
