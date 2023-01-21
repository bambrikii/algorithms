package org.bambrikii.examples.sorting;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ArrayUtils {
    private ArrayUtils() {
    }

    public static void print(int[] arr, String message) {
        System.out.println(message + " " + Arrays.stream(arr).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
    }

    public static void swap(int[] arr, int j, int i) {
        if (ArrayAsStringFactory.DEBUG) {
            System.out.println("swap: " + j + "(" + arr[j] + ") <-> " + i + "(" + arr[i] + ")");
        }
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void reverse(int[] arr, int i, int j) {
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }
}
