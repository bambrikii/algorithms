package org.bambrikii.examples.numbers;

public class GreatestCommonDivisor {
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
        for (int i = 1; i <= min; i++) {
            int mini = min / i;
            if (a % mini == 0 && b % mini == 0) {
                return mini;
            }
        }
        return -1;
    }
}
