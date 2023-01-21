package org.bambrikii.examples.combinatorics.permutations.next;

import static org.bambrikii.examples.sorting.ArrayUtils.print;
import static org.bambrikii.examples.sorting.ArrayUtils.reverse;
import static org.bambrikii.examples.sorting.ArrayUtils.swap;

/**
 * https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
 */
public class NextPermutation {

    public static void main(String[] args) {
        nextAll(new int[]{1, 2, 3, 4, 5});
    }

    public static void nextAll(int[] arr) {
        do {
            print(arr, "\n>>>>>");
        } while (nextPermutation(arr));
    }

    static boolean nextPermutation(int[] array) {
        int i = array.length - 1; // find smaller elem before first max
        while (i > 0 && array[i - 1] >= array[i])
            i--;

        if (i <= 0) // if reached zero then iterations are over
            return false;

        int j = array.length - 1; // find first greater than smaller elem
        while (array[j] <= array[i - 1])
            j--;
        swap(array, i - 1, j); // swap these elements
        print(array, "  %  ");

        reverse(array, i, array.length - 1); // reverse
        print(array, "  <->");

        return true;
    }
}
