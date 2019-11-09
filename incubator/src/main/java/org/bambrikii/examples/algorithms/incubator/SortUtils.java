package org.bambrikii.examples.algorithms.incubator;

import java.util.Random;

public class SortUtils {
    public static void prepareArray(int[] arr) {
        Random random = new Random();
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextInt(length);
        }
        System.out.println();
    }

    public static void printArray(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    }
}
