package org.bambrikii.examples.sorting.bucket;

import org.bambrikii.examples.sorting.Sortable;

import java.util.Objects;

public class BucketSort implements Sortable {
    private final int n;

    public BucketSort(int n) {
        this.n = n;
    }

    public BucketSort() {
        this(5);
    }

    @Override
    public int[] sort(int[] array) {
        Objects.requireNonNull(array);
        int len = array.length;
        if (len == 0) {
            return array;
        }

        int[][] buckets = new int[n][];
        int bucketLen = len / n;
        for (int i = 0; i < n; i++) {
            buckets[i] = new int[bucketLen];
        }

        for (int i = 0; i < bucketLen; i++) {
            for (int j = 0; j < n; j++) {
                int i1 = i * n + j;
                if (i1 >= len) {
                    break;
                }
                buckets[j][i] = array[i1];
            }
        }

        for (int i = 0; i < n; i++) {
            int maxLen = getBucketMaxLen(len, i);
            insertionSort(buckets[i], maxLen);
        }

        merge(buckets, array);

        return array;
    }

    private int getBucketMaxLen(int len, int bucketNum) {
        int bucketMaxLen = len / n;
        int lenLimit = bucketNum * n;
        return lenLimit <= len ? bucketMaxLen : bucketMaxLen - (bucketNum * (n + 1)) % bucketMaxLen;
    }

    private void merge(int[][] buckets, int[] array) {
        int n = buckets.length;
        int len = array.length;

        int[] positions = new int[n];
        int k = 0;
        while (k < len) {
            int maxBucketNum = -1;
            for (int i = 0; i < n; i++) {
                int currPos = positions[i];
                if (currPos >= getBucketMaxLen(len, i)) {
                    continue;
                }
                if (maxBucketNum == -1) {
                    maxBucketNum = i;
                    continue;
                }
                int maxPos = positions[maxBucketNum];
                if (maxPos >= getBucketMaxLen(len, maxBucketNum)) {
                    continue;
                }
                if (buckets[i][currPos] < buckets[maxBucketNum][maxPos]) {
                    maxBucketNum = i;
                }
            }
            array[k++] = buckets[maxBucketNum][positions[maxBucketNum]];
            positions[maxBucketNum]++;
        }
    }

    private void insertionSort(int[] array, int len) {
        for (int i = 1; i < len; i++) {
            int k = array[i];
            int j = i - 1;
            while (j > -1 && k < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = k;
        }
    }
}
