package org.bambrikii.examples.algorithms.incubator;

import static org.bambrikii.examples.algorithms.incubator.SortUtils.printArray;
import static org.bambrikii.examples.algorithms.incubator.SortUtils.swap;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = SortUtils.prepareArray(10);
//        int[] arr = new int[]{10, 18, 15, 0, 0, 19, 12, 18, 9, 0};

        printArray(arr);

        int n = arr.length;
        heapify(arr, n);

        System.out.println("while: ---");

        int end = n - 1;
        while (end >= 0) {
            System.out.println("sort: " + end);
            swap(arr, 0, end);
            end--;
            siftDown(arr, 0, end);
        }

        printArray(arr);
    }

    private static void siftDown(int[] arr, int start, int end) {
        System.out.println("siftDown: " + start + "..." + end);
        int root = start;
        int child = root * 2 + 1;
        while (child <= end) {
            child = root * 2 + 1;
            System.out.println("siftDown:*" + child + "..." + end + ". " + arr[root] + "->" + (child <= end ? arr[child] : "") + "->" + (child + 1 <= end ? arr[child + 1] : ""));
            int swap = root;
            if (child <= end && arr[swap] < arr[child]) {
                System.out.println("left: " + arr[swap] + " [" + swap + "] <-> " + arr[child] + " [" + (child) + "]");
                swap = child;
            }
            if (child + 1 <= end && arr[swap] < arr[child + 1]) {
                System.out.println("right: " + arr[swap] + " [" + swap + "] <->" + arr[child + 1] + " [" + (child + 1) + "]");
                swap = child + 1;
            }
            if (swap == root) {
                return;
            } else {
                swap(arr, root, swap);
                root = swap;
            }
        }
    }

    private static void heapify(int[] arr, int count) {
        int start = (count - 1) / 2;
        while (start >= 0) {
            siftDown(arr, start, count - 1);
            start--;
        }
    }
}
