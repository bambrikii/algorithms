package org.bambrikii.examples.sorting.heap;

import org.bambrikii.examples.sorting.ArrayAsStringFactory;
import org.bambrikii.examples.sorting.Sortable;

import static org.bambrikii.examples.sorting.ArrayUtils.swap;

public class HeapSortUp implements Sortable {
    @Override
    public int[] sort(int[] array) {
        int n = array.length;
        heapify(array, n);

        int end = n - 1;
        while (end >= 0) {
            swap(array, 0, end);
            ArrayAsStringFactory.log(array);
            end--;
            siftUp(array, 0, end);
        }
        return array;
    }

    private void heapify(int[] array, int count) {
        int end = 1;
        while (end < count) {
            System.out.println("while: end = " + end);
            siftUp(array, 0, end);
            end++;
        }
    }

    private void siftUp(int[] array, int start, int end) {
        int child = end;
        while (child > start) {
            int parent = (child - 1) / 2;
            if (array[parent] < array[child]) {
                swap(array, parent, child);
                child = parent;
            } else {
                return;
            }
        }
    }
}
