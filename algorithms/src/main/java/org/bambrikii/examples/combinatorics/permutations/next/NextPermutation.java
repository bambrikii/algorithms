package org.bambrikii.examples.combinatorics.permutations.next;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
 */
public class NextPermutation {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        do {
            print(arr, "\n>");
        } while (nextPermutation(arr));
    }

    private static void print(int[] arr, String message) {
        System.out.println(message + " " + Arrays.stream(arr).mapToObj(i -> i + " ").collect(Collectors.joining()));
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        print(array, " ");
    }

    static boolean nextPermutation(int[] array) {
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i])
            i--;

        if (i <= 0)
            return false;

        int j = array.length - 1;
        while (array[j] <= array[i - 1])
            j--;

        swap(array, i - 1, j);

        j = array.length - 1;
        while (i < j) {
            swap(array, i, j);
            i++;
            j--;
        }

        return true;
    }
}
