package org.bambrikii.examples.sorting.heap;


import org.bambrikii.examples.sorting.ArrayAsStringFactory;
import org.bambrikii.examples.sorting.Sortable;

import static org.bambrikii.examples.sorting.ArrayUtils.swap;

public class HeapSort implements Sortable {
    @Override
    public int[] sort(int[] array) {
        int n = array.length;
        heapify(array, n);

        int end = n - 1;
        while (end >= 0) {
            swap(array, 0, end);
            ArrayAsStringFactory.log(array);
            end--;
            siftDown(array, 0, end);
        }
        return array;
    }

    private void heapify(int[] arr, int count) {
        int start = (count - 1) / 2;
        while (start >= 0) {
            siftDown(arr, start, count - 1);
            start--;
        }
    }

    private void siftDown(int[] arr, int start, int end) {
        int root = start;
        int child = root * 2 + 1;
        while (child <= end) {
            child = root * 2 + 1;
            int swap = root;
            if (child <= end && arr[swap] < arr[child]) {
                swap = child;
            }
            if (child + 1 <= end && arr[swap] < arr[child + 1]) {
                swap = child + 1;
            }
            if (swap == root) {
                return;
            } else {
                swap(arr, root, swap);
                ArrayAsStringFactory.log(arr);
                root = swap;
            }
        }
    }
}
