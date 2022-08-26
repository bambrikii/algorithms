package org.bambrikii.examples.sorting.merge;

import org.bambrikii.examples.sorting.Sortable;

import java.util.Arrays;

public class MergeSort2 implements Sortable {
    private final int chunk;

    public MergeSort2() {
        this(5);
    }

    public MergeSort2(int chunk) {
        this.chunk = chunk;
    }

    public int[] sort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i += chunk) {
            int from = i;
            int to = i + chunk;
            if (to > len) {
                to = len;
            }
            inPlaceSort(arr, from, to);
        }
        int chunk2 = chunk;
        while (chunk2 < len) {
            for (int i = 0; i < len; i += chunk2 * 2) {
                merge(arr, i, chunk2);
            }
            chunk2 *= 2;
        }
        return arr;
    }

    private void merge(int[] arr, int from, int size) {
        int posLeft = from;
        int toLeft = from + size;
        if (toLeft > arr.length) {
            toLeft = arr.length;
        }

        int posRight = toLeft;
        int toRight = from + size * 2;
        if (toRight > arr.length) {
            toRight = arr.length;
        }

        int[] tmp = new int[toRight - from];
        int i = 0;
        while (posLeft < toLeft && posRight < toRight) {
            if (arr[posLeft] < arr[posRight]) {
                tmp[i] = arr[posLeft];
                posLeft++;
            } else {
                tmp[i] = arr[posRight];
                posRight++;
            }
            i++;
        }
        i = append(arr, posLeft, toLeft, tmp, i);
        i = append(arr, posRight, toRight, tmp, i);
        for (int i1 = 0; i1 < i; i1++) {
            arr[i1 + from] = tmp[i1];
        }
    }

    private static int append(int[] arr, int from, int to, int[] tmp, int i) {
        if (from < to) {
            for (int i1 = from; i1 < to; i1++) {
                tmp[i++] = arr[i1];
            }
        }
        return i;
    }

    private void inPlaceSort(int[] arr, int from, int to) {
        Arrays.sort(arr, from, to);
    }
}
