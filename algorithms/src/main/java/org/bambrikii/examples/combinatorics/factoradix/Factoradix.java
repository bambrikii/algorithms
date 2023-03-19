package org.bambrikii.examples.combinatorics.factoradix;

import java.util.LinkedList;

/**
 * Lehmer codes
 * <p>
 * https://2ality.com/2013/03/permutations.html
 */
public class Factoradix {
    public int toNumber(int[] num) {
        int result = 0;
        int len = num.length;
        for (int i = 0; i < len; i++) {
            int digit = num[i];
            result = result * (len - i) + digit;
        }
        return result;
    }

    public int[] toFactoradic(int factoradic) {
        int n = factoradic;
        LinkedList<Integer> nums = new LinkedList<>();
        int i = 1;
        while (n > 0) {
            nums.addFirst(n % i);
            n /= i;
            i++;
        }
        int[] result = new int[nums.size()];
        for (int j = 0; j < nums.size(); j++) {
            result[j] = nums.get(j);
        }
        return result;
    }
}
