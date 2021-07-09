package org.bambrikii.examples.martix;

/**
 * https://medium.com/@rsinghal757/kadanes-algorithm-dynamic-programming-how-and-why-does-it-work-3fd8849ed73d
 */
public class KadeneAlgorithm {
    public int fndGlobalMaximum(int[] arr) {
        int localMax = 0;
        int globalMax = 0;
        for (int i = 0; i < arr.length; i++) {
            localMax = Math.max(arr[i], localMax + arr[i]);
            if (localMax > globalMax) {
                globalMax = localMax;
            }
        }
        return globalMax;
    }
}
