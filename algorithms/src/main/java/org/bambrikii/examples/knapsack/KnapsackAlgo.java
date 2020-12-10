package org.bambrikii.examples.knapsack;

/**
 * https://www.youtube.com/watch?v=xOlhR_2QCXY&t=2s&ab_channel=CSDojo
 */
public class KnapsackAlgo {
    private int[] v;
    private int[] w;

    public int calc(int[] v, int[] w, int c) {
        this.v = v;
        this.w = w;
        return calc(v.length - 1, c);
    }

    protected int calc(int n, int c) {
        if (n == 0 || c == 0) {
            return 0;
        } else if (w[n] > c) {
            return calc(n - 1, c);
        } else {
            int tmp1 = calc(n - 1, c);
            int tmp2 = calc(n - 1, c - w[n]) + v[n];
            return Math.max(tmp1, tmp2);
        }
    }
}
