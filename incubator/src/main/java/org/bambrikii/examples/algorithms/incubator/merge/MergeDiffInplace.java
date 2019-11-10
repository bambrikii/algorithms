package org.bambrikii.examples.algorithms.incubator.merge;

import static org.bambrikii.examples.algorithms.incubator.SortUtils.printArray;

public class MergeDiffInplace {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 6, 7, 1, 2};
        int left = 0;
        int mid = 2;
        int right = mid + 1;
        int len = arr.length - 1;
        while (left <= mid && right <= len) {
            if (arr[left] > arr[right]) {
                int temp = arr[right];
                arr[right] = arr[left];
                arr[left] = temp;
                left++;
            } else {
                right++;
                left++;
            }
        }
        printArray(arr);
    }
}
