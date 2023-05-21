package org.bambrikii.examples.sorting.insertion;

import org.bambrikii.examples.sorting.ArrayAsStringFactory;
import org.bambrikii.examples.sorting.Sortable;

public class InsertionSort implements Sortable {

    public int[] sort(int[] arr) {
        for (int pos = 1; pos < arr.length; pos++) {
            int min = arr[pos];
            int prev = pos - 1;
            while (prev > -1 && min < arr[prev]) {
                arr[prev + 1] = arr[prev];
                prev--;
                ArrayAsStringFactory.log(arr);
            }
            arr[prev + 1] = min;
        }
        return arr;
    }
}
