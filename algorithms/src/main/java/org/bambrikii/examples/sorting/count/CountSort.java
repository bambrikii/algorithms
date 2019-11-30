package org.bambrikii.examples.sorting.count;

import org.bambrikii.examples.sorting.Sortable;

import java.util.Objects;

public class CountSort implements Sortable {
    @Override
    public int[] sort(int[] array) {
        Objects.requireNonNull(array);

        int length = array.length;
        if (length == 0) {
            return array;
        }

        int max = findMax(array);

        int[] b = new int[max + 1];
        for (int i = 0; i < length; i++) {
            b[array[i]]++;
        }

        for (int i = 1; i < b.length; i++) {
            b[i] = b[i] + b[i - 1];
        }

        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[b[array[i]] - 1] = array[i];
            b[array[i]]--;
        }
        return result;
    }

    private int findMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
}
