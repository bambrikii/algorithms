package org.bambrikii.examples.numbers;

public class GreatestCommonDivisorRecursive {
    public static void main(String[] args) {
        System.out.println(gcd(12, 8));
        System.out.println(gcd(15, 3));
        System.out.println(gcd(15, 9));
        System.out.println(gcd(24, 36));
    }

    static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        int min = a < b ? a : b;
        return gcd2(a, b, min, 1);
    }

    static int gcd2(int a, int b, int min, int i) {
        int mini = min / i;
        if (a % mini == 0 && b % mini == 0) {
            return mini;
        }
        if (i == min) {
            return -1;
        }
        return gcd2(a, b, min, i + 1);
    }
}
