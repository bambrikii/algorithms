package org.bambrikii.examples.algorithms.incubator.merge;

import static org.bambrikii.examples.algorithms.incubator.SortUtils.printArray;

public class MergeEqualLengthInplace {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 8, 9, 3, 4, 5, 6};
        inplace(arr);
        printArray(arr);
    }

    private static void inplace(int[] arr) {
        int left = 0;
        int mid = arr.length / 2 - 1;
        int right = mid + 1;
        int len = arr.length - 1;
        while (left <= len && right <= len) {
            if (arr[left] > arr[right]) {
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
                right++;
            }
            left++;
        }
    }
}
