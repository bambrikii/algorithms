package org.bambrikii.examples.prime;

import java.util.ArrayList;
import java.util.List;

public class PrimeAlgo {
    private List<Integer> primes = new ArrayList<>();

    public boolean isPrime(int val) {
        int nextI = 1;
        for (int i : primes) {
            if (val % i == 0) {
                return false;
            }
            nextI = i;
        }
        for (int i = nextI + 1; i < val / 2; i++) {
            if (!isPrime(i)) {
                continue;
            }
            if (val % i == 0) {
                return false;
            }
        }
        primes.add(val);
        return true;
    }
}
