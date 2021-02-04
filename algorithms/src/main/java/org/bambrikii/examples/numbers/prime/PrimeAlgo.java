package org.bambrikii.examples.numbers.prime;

import java.util.ArrayList;
import java.util.List;

public class PrimeAlgo {
    public List<Integer> getPrimesUntil(int val) {
        List<Integer> primes = new ArrayList<>();
        getPrimesUntil(val, primes);
        return primes;
    }

    private boolean getPrimesUntil(int val, List<Integer> primes) {
        int nextI = 1;
        for (int i : primes) {
            if (val % i == 0) {
                return false;
            }
            nextI = i;
        }
        for (int i = nextI + 1; i < val / 2; i++) {
            if (!getPrimesUntil(i, primes)) {
                continue;
            }
            if (val % i == 0) {
                return false;
            }
        }
        primes.add(val);
        return true;
    }

    public boolean isPrime(int val) {
        if (val < 2) {
            return false;
        }
        for (int i = 2; i < val / 2; i++) {
            if (val % i == 0) {
                return false;
            }
        }
        return true;
    }
}
