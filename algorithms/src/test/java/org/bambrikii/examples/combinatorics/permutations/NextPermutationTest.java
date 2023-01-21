package org.bambrikii.examples.combinatorics.permutations;

import org.bambrikii.examples.combinatorics.permutations.next.NextPermutation;
import org.bambrikii.examples.sorting.ArrayUtils;
import org.junit.jupiter.api.Test;

import static org.bambrikii.examples.sorting.ArrayUtils.reverse;
import static org.bambrikii.examples.sorting.ArrayUtils.swap;

public class NextPermutationTest {
    @Test
    public void testNextAll() {
        NextPermutation.nextAll(new int[]{1, 2, 3, 4, 5});
    }

    @Test
    public void testAgain() {
        var algo = new NextPermutationTest();
        int[] arr = {1, 2, 3, 4, 5};
        do {
            ArrayUtils.print(arr, "");
        } while (algo.next(arr));
    }

    private boolean next(int[] arr) {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] > arr[i])
            i--;

        if (i <= 0)
            return false;

        int j = arr.length - 1;
        while (arr[i - 1] > arr[j])
            j--;
        swap(arr, i - 1, j);
        reverse(arr, i, j);

        return true;
    }
}
