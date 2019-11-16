package org.bambrikii.examples.algorithms.incubator;

import java.util.Random;

public class SortUtils {
    public static int[] prepareArray(int n) {
        int[] arr = new int[n];
        Random random = new Random();
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextInt(length * 2);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    }


    public static void swap(int[] arr, int i1, int i2) {
        System.out.println("swap: " + arr[i1] + " [" + i1 + "] <-> " + arr[i2] + " [" + i2 + "]");
        int temp = arr[i2];
        arr[i2] = arr[i1];
        arr[i1] = temp;
        printArray(arr);
    }
}
