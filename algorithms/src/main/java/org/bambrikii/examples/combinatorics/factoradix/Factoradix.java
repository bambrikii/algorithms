package org.bambrikii.examples.combinatorics.factoradix;

public class Factoradix {
    private int getLen(int num) {
        int len = 0;
        while (num > 0) {
            num /= 10;
            len++;
        }
        return len;
    }

    public int to(int num) {
        int result = 0;
        int len = getLen(num);
        for (int i = len; i >= 1; i--) {
            double pow = Math.pow(10, i - 1);
            int digit = ((int) (num / pow)) % 10;
            result = result * i + digit;
        }
        return result;
    }

    public int from(int factoradix) {
        int n = factoradix;
        int result = 0;
        int i = 1;
        while (n > 0) {
            result += (n % i) * Math.pow(10, i - 1);
            n /= i;
            i++;
        }
        return result;
    }
}
