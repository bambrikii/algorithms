package org.bambrikii.examples.algorithms.incubator.combinatorics;

import java.util.ArrayList;
import java.util.Arrays;

/***
 * https://medium.com/@aiswaryamathur/find-the-n-th-permutation-of-an-ordered-string-using-factorial-number-system-9c81e34ab0c8
 */
public class FactoradicPermutation {
    // function to get Factoradic representation of a number,n.
    static int[] getFactoradic(int n) {
        int[] factoradic = new int[13];
        int i = 1;
        while (n != 0) {
            factoradic[13 - i] = n % i;
            n = n / i;
            i++;
        }
        return factoradic;
    }

    // function takes a String,str and Factoradic representation of a number n.
    // returns the nth lexicographic permutaion of character array, str.
    static String getPermutation(char[] str, int[] factoradic) {
        Arrays.sort(str);
        ArrayList<Character> res = new ArrayList<Character>();
        StringBuilder sb = new StringBuilder();
        int pos;
        char c;
        String perm = "";
        boolean[] used = new boolean[str.length]; // by default values are initialised to false.
        for (int i = 0; i < factoradic.length; i++) {
            pos = factoradic[i];
            c = getUnusedCharAtPos(str, pos, used);
            res.add(c);
        }
        for (char some_c : res) {
            sb.append(some_c);
        }
        return (sb.toString());
    }

    //function to get the yet unused character at a given position in a character array.
    private static char getUnusedCharAtPos(char[] str, int pos, boolean[] used) {
        int count = -1;
        for (int i = 0; i < str.length; i++) {
            if (!used[i]) {
                count++;
                if (count == pos) {
                    used[i] = true;
                    return str[i];
                }
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            print(i);
        }
    }

    private static void print(int n) {
        System.out.print("" + n + " => ");
        int[] ints = getFactoradic(n);
        for (int i : ints) {
            System.out.print(" " + i);
        }
        System.out.println();
    }
}
