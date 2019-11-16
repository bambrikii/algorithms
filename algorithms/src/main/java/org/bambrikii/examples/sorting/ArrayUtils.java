package org.bambrikii.examples.sorting;

public class ArrayUtils {
    private ArrayUtils() {
    }

    public static void swap(int[] arr, int i1, int i2) {
        int temp = arr[i2];
        arr[i2] = arr[i1];
        arr[i1] = temp;
    }
}
