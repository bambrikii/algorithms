package org.bambrikii.examples.combinatorics.combinations;

public class PermutationsRecursiveMain {
    public static void main(String[] args) {
        permute(new int[]{1, 2, 3, 4});
    }

    public static void permute(int[] arr) {
        permute(arr, 0);
    }

    private static void permute(int[] arr, int k) {
        if (k == arr.length) {
            print(arr);
        }
        for (int i = k; i < arr.length; i++) {
            swap(arr, i, k);
            permute(arr, k + 1);
            swap(arr, i, k);
        }
    }

    static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    static void swap(int[] arr, int i, int i1) {
        int tmp = arr[i];
        arr[i] = arr[i1];
        arr[i1] = tmp;
    }
}
