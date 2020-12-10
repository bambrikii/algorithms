package org.bambrikii.examples.knapsack;

public class KnapsackDynamicAlgo extends KnapsackAlgo {
    private Integer[][] arr;

    public int calc(int[] v, int[] w, int c) {
        this.arr = new Integer[v.length][c + 1];
        return super.calc(v, w, c);
    }

    protected int calc(int n, int c) {
        if (arr[n] == null) {
            arr[n] = new Integer[c];
        }
        if (arr[n][c] != null) {
            System.out.println("hit arr[" + n + "][" + c + "] = " + arr[n][c]);
            return arr[n][c];
        }
        return arr[n][c] = super.calc(n, c);
    }
}
