package org.bambrikii.examples.sorting;

import org.bambrikii.examples.sorting.merge.MergeSort2;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

public class MergeSort2Test {
    @Test
    public void shouldTest() {
        int[] arr = generateArr(25, 9);
        int[] output = new MergeSort2(3).sort(arr);
        System.out.printf(Arrays.toString(output));
    }

    private static int[] generateArr(int size, int maxVal) {
        int[] arr = new int[new Random().nextInt(size)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(maxVal);
        }
        return arr;
    }
}
