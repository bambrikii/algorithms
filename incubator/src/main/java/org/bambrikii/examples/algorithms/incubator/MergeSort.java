package org.bambrikii.examples.algorithms.incubator;

import static org.bambrikii.examples.algorithms.incubator.SortUtils.prepareArray;
import static org.bambrikii.examples.algorithms.incubator.SortUtils.printArray;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = prepareArray(10);
//        int[] arr = {8, 6, 3, 5, 1, 4, 17, 2, 7, 9};
        printArray(arr);

        sort(arr, 0);

        printArray(arr);
    }

    private static void sort(int[] arr, int depth) {
        int len = arr.length;
        if (len == 1) {
            return;
        }
        int mid = len / 2;

        int[] arrLeft = new int[mid];
        System.arraycopy(arr, 0, arrLeft, 0, mid);

        int[] arrRight = new int[len - mid];
        System.arraycopy(arr, mid, arrRight, 0, arrRight.length);

        sort(arrLeft, ++depth);
        sort(arrRight, depth);

        merge(arr, arrLeft, arrRight, depth);
    }

    private static void merge(int[] arr, int[] arrLeft, int[] arrRight, int depth) {
        int left = 0;
        int right = 0;
        int pos = 0;
        while (left < arrLeft.length && right < arrRight.length) {
            if (arrLeft[left] < arrRight[right]) {
                move(arr, pos, arrLeft, left);
                left++;
            } else if (arrRight[right] < arrLeft[left]) {
                move(arr, pos, arrRight, right);
                right++;
            } else {
                move(arr, pos, arrLeft, left);
                left++;
            }
            pos++;
        }
        while (left < arrLeft.length) {
            move(arr, pos, arrLeft, left);
            left++;
            pos++;
        }
        while (right < arrRight.length) {
            move(arr, pos, arrRight, right);
            right++;
            pos++;
        }
//        printArray(arr);
    }

    private static void printMaskedArray(int[] arr, int from, int to) {
        StringBuilder sb = new StringBuilder();
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            if (i >= from && i <= to) {
                sb.append(" ").append(arr[i]);
            } else {
                sb.append(" *");
            }
        }
        System.out.println(sb.toString());
    }

    private static void move(int[] arr, int pos, int[] arrLeft, int left) {
        arr[pos] = arrLeft[left];
//        printArray(arr);
    }
}
