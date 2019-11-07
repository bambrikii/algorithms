package org.bambrikii.examples.algorithms.incubator;

import java.util.Random;

public class InsertionSort {
    public static void main(String[] args) {
        int n = 15;
        int[] arr = new int[n];
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            arr[i] = random.nextInt(n * n);
        }

        for (int i = 1; i < n; i++) {
            int curr = arr[i];
            int j = i - 1;
            while (j > -1 && curr <= arr[j]) {
                arr[i] = arr[j];
                arr[j] = curr;
                j--;
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(" " + arr[i]);
        }
    }
}
