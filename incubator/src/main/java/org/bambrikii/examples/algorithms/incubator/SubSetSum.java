package org.bambrikii.examples.algorithms.incubator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Result {

    /*
     * Complete the 'nonDivisibleSubset' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY s
     */

    public static int nonDivisibleSubset(int k, List<Integer> s) {
        // Write your code here
        List<List<Integer>> combinations = new ArrayList<>();
        combine(s.size(), 0, new ArrayList<>(), combinations);
        return maxSubsetLen(k, s, combinations);
    }

    private static int maxSubsetLen(int k, List<Integer> s, List<List<Integer>> combinations) {
        int max = 0;
        for (List<Integer> combination : combinations) {
            int combLen = combination.size();
            int mod = 0;
//            System.out.println(printCombi("aaa: ", combination, s));
            for (int i = 0; i < combLen; i++) {
                for (int j = i + 1; j < combLen; j++) {
                    int val1 = s.get(combination.get(i)) + s.get(combination.get(j));
                    if (val1 % k == 0) {
                        mod++;
                    }
                }
            }
            if (mod == 0) {
                max = Math.max(max, combLen);
//                System.out.println(printCombi("mo*: ", combination, s));
            }
        }
        return max;
    }

    private static String printCombi(String msg, List<Integer> combination, List<Integer> s) {
        StringBuilder sb = new StringBuilder(msg);
        for (Integer i : combination) {
            sb.append(s.get(i)).append(" ");
        }
        return sb.toString();
    }

    private static void combine(int n, int eIx, List<Integer> prev, List<List<Integer>> results) {
        results.add(prev);
//        System.out.println(prev.toString());
        if (prev.size() == n) {
            return;
        }
        for (int i = eIx; i < n; i++) {
            List<Integer> next = new ArrayList<>(prev);
            next.add(i);

            combine(n, i + 1, next, results);
        }
    }
}

public class SubSetSum {
    public static void main(String[] args) throws IOException {
//        print(3, Arrays.asList(1, 3, 5));
        print(3, Arrays.asList(1, 7, 2, 4));
//        print(7, Arrays.asList(278, 576, 496, 727, 410, 124, 338, 149, 209, 702, 282, 718, 771, 575, 436));
//        print(5, Arrays.asList(2, 7, 12, 17, 22));
//        print(1, Arrays.asList(1, 2, 3, 4, 5));
    }

    private static void print(int k, List<Integer> s) {
        int result = Result.nonDivisibleSubset(k, s);
        System.out.print(" " + k + ": ");
        for (Integer sElem : s) {
            System.out.print(" " + sElem);
        }
        System.out.println();
        System.out.println(" = " + result);
    }
}
