package org.bambrikii.examples.combinatorics.factoradix;

public class Factoradix {
    private int getLen(int num, int radix) {
        int len = 0;
        while (num > 0) {
            num /= radix;
            len++;
        }
        return len;
    }

    public int toFactoradic(int num, int radix) {
        int result = 0;
        int i = 1;
        int pow = 1;
        while (num > 0) {
            result += (num % i) * pow;
            pow *= radix;
            num /= i;
            i++;
        }
        return result;
    }

    public int toNumber(int factoradic, int radix) {
        int result = 0;
        int len = getLen(factoradic, radix);
        for (int i = len; i >= 1; i--) {
            double pow = Math.pow(radix, i - 1);
            int digit = ((int) (factoradic / pow)) % radix;
            result = result * i + digit;
        }
        return result;
    }
}
