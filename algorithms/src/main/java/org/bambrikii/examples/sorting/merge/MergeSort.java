package org.bambrikii.examples.sorting.merge;

import org.bambrikii.examples.sorting.Sortable;

public class MergeSort implements Sortable {
    public int[] sort(int[] array) {
        sort(array, 0);
        return array;
    }

    private void sort(int[] arr, int depth) {
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

        merge(arr, arrLeft, arrRight);
    }

    private void merge(int[] arr, int[] arrLeft, int[] arrRight) {
        int left = 0;
        int right = 0;
        int pos = 0;
        while (left < arrLeft.length && right < arrRight.length) {
            if (arrLeft[left] < arrRight[right]) {
                arr[pos] = arrLeft[left];
                left++;
            } else if (arrRight[right] < arrLeft[left]) {
                arr[pos] = arrRight[right];
                right++;
            } else {
                arr[pos] = arrLeft[left];
                left++;
            }
            pos++;
        }
        while (left < arrLeft.length) {
            arr[pos] = arrLeft[left];
            left++;
            pos++;
        }
        while (right < arrRight.length) {
            arr[pos] = arrRight[right];
            right++;
            pos++;
        }
    }
}
