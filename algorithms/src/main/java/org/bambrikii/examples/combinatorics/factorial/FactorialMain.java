package org.bambrikii.examples.combinatorics.factorial;

public class FactorialMain {
    public static void main(String[] args) {
        FactorialMain main = new FactorialMain();
        System.out.println(main.calc(4));
        System.out.println(main.calc(5));
    }

    private int calc(int n) {
        int f = 1;
        for (int i = 2; i <= n; i++) {
            f *= i;
        }
        return f;
    }
}
