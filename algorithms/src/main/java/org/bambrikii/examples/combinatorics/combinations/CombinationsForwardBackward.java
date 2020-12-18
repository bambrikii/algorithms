package org.bambrikii.examples.combinatorics.combinations;

/**
 * https://hmkcode.com/calculate-find-all-possible-combinations-of-an-array-using-java/
 */
public class CombinationsForwardBackward {
    public static void combination(Object[] elements, int k) {
        int N = elements.length; // get the length of the array // e.g. for {'A','B','C','D'} => N = 4
        if (k > N) {
            System.out.println("Invalid input, K > N");
            return;
        }

        int[] pointers = new int[k]; // init combination index array

        int r = 0; // index for combination array
        int i = 0; // index for elements array

        while (r >= 0) {
            if (i <= (N + (r - k))) { // forward step if i < (N + (r-K))
                pointers[r] = i;


                if (r == k - 1) { // if combination array is full print and increment i;
                    print(pointers, elements);
                    i++;
                } else { // if combination is not full yet, select next element
                    i = pointers[r] + 1;
                    r++;
                }
            } else { // backward step
                r--;
                if (r >= 0) {
                    i = pointers[r] + 1;
                }
            }
        }
    }

    private static void print(int[] pointers, Object[] elements) {
        for (int i : pointers) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }
}
