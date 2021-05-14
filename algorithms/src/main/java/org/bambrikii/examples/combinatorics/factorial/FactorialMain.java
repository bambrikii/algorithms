package org.bambrikii.examples.combinatorics.factorial;

public class FactorialMain {
    private int calc(int n) {
        int f = 1;
        for (int i = 2; i <= n; i++) {
            f *= i;
        }
        return f;
    }

    private int[] cache = new int[0];

    private void ensureCacheSize(int n) {
        if (cache.length >= n + 1) {
            return;
        }
        int[] tmp = new int[n + 1];
        System.arraycopy(cache, 0, tmp, 0, cache.length);
        cache = tmp;
    }

    public int calcCached(int n) {
        if (n == 1) {
            return 1;
        }
        ensureCacheSize(n);
        int cached = cache[n];
        if (cached != 0) {
            return cached;
        }
        int val = calcCached(n - 1) * n;
        cache[n] = val;
        return val;
    }

    public static void main(String[] args) {
        FactorialMain main = new FactorialMain();
        System.out.println(main.calc(4));
        System.out.println(main.calc(5));

        System.out.println(main.calcCached(4));
        System.out.println(main.calcCached(5));
    }
}
