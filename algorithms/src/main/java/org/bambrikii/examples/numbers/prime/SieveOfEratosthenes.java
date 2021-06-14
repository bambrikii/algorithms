package org.bambrikii.examples.numbers.prime;

import java.util.ArrayList;
import java.util.List;

/**
 * https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
 */
public class SieveOfEratosthenes {
    public static void main(String[] args) {
        SieveOfEratosthenes algo = new SieveOfEratosthenes();
        List<Integer> primes = algo.findPrimes(150);
        algo.print(primes);
    }

    private void print(List<Integer> primes) {
        for (int i = 0; i < primes.size(); i++) {
            System.out.print(primes.get(i) + " ");
        }
        System.out.println();
    }

    public List<Integer> findPrimes(int upTo) {
        boolean[] arr = new boolean[upTo + 1];
        for (int i = 2; i <= upTo; i++) {
            if (arr[i]) {
                continue;
            }
            for (int j = 2; i * j <= upTo; j++) {
                int ind = i * j;
                if (!arr[ind]) {
                    arr[ind] = true;
                }
            }
        }
        List<Integer> results = new ArrayList<>();
        for (int i = 1; i <= upTo; i++) {
            if (arr[i]) {
                continue;
            }
            results.add(i);
        }
        return results;
    }
}
