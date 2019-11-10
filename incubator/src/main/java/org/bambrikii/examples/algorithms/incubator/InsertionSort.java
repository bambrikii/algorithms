package org.bambrikii.examples.algorithms.incubator;

import static org.bambrikii.examples.algorithms.incubator.SortUtils.prepareArray;
import static org.bambrikii.examples.algorithms.incubator.SortUtils.printArray;

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = prepareArray(15);
        printArray(arr);

        for (int i = 1; i < arr.length; i++) {
            int curr = arr[i];
            int j = i - 1;
            while (j > -1 && curr < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = curr;
        }

        printArray(arr);
    }
}
