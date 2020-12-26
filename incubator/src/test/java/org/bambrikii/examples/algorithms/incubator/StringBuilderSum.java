package org.bambrikii.examples.algorithms.incubator;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringBuilderSum {
    /*
     * Complete the buildString function below.
     */
    static int buildString(int a, int b, String s) {
        /*
         * Write your code here.
         */
        int len = s.length();
        Map<Integer, Integer> pos = new HashMap<>();
        Map<Integer, Integer> posl = new HashMap<>();
        int i = 0;
        while (i < len) {
            int j = i + 1;
            while (j < len) {
                int l = 0;
                while (l < len - j && i + l < j) {
//                    System.out.println(s.charAt(i + l) + "->" + s.charAt(j + l) + " (" + i + "->" + j + ":" + l + ") ");
                    if (s.charAt(i + l) != s.charAt(j + l)) {
                        break;
                    }
                    if (pos.get(j) != null) {
                        if (l > posl.get(j)) {
//                            System.out.println("    match put " + i + ":" + j + ":" + l + " " + s.substring(j, j + l + 1));
                            pos.put(j, i);
                            posl.put(j, l);
                        }
                    } else {
//                        System.out.println("    new match put " + i + ":" + j + ":" + l + " " + s.substring(j, j + l + 1));
                        pos.put(j, i);
                        posl.put(j, l);
                    }
                    l++;
                }
                j++;
            }
            i++;
        }
        System.out.println(s + " " + a + "," + b);
        Map<Integer, Integer> sums = new HashMap<>();
        return calcMinSum(a, b, s, pos, posl, 0, sums);
    }

    private static int calcMinSum(int a, int b, String s,
                                  Map<Integer, Integer> pos,
                                  Map<Integer, Integer> posl,
                                  int pos2,
                                  Map<Integer, Integer> sums) {
        int len = s.length();
        if (pos2 >= len) {
            return 0;
        }
        if (sums.containsKey(pos2)) {
            return sums.get(pos2);
        }
        int sum = 0;
        if (pos.containsKey(pos2)) {
            int p = pos.get(pos2);
            int l = posl.get(pos2) + 1;

//            System.out.println(" pos: " + pos2 + "+" + l + "(" + b + ") (" + s.substring(pos2, pos2 + l) + ")");
            int sum1 = b + calcMinSum(a, b, s, pos, posl, pos2 + l, sums);

//            System.out.println(" pos: " + pos2 + "+1(" + a + ") (" + s.substring(pos2, pos2 + 1) + ")");
            int sum2 = a + calcMinSum(a, b, s, pos, posl, pos2 + 1, sums);
            if (sum1 < sum2) {
                sum += sum1;
            } else {
                sum += sum2;
            }
        } else {
//            System.out.println(" pos: " + pos2 + "+1(" + a + ") (" + s.substring(pos2, pos2 + 1) + ")");
            int sum1 = calcMinSum(a, b, s, pos, posl, pos2 + 1, sums);
            sum += a + sum1;
        }
        if (!sums.containsKey(pos2) || sums.get(pos2) > sum) {
            sums.put(pos2, sum);
        }
        return sum;
    }

    private static void print(int a, int b, String aabaacaba, int expected) {
        int x = buildString(a, b, aabaacaba);
        System.out.println(x);
        assertEquals(expected, x);
    }

    @Test
    public void shouldTest() {
        print(4, 5, "aabaacaba", 26); // 26
        print(8, 9, "bacbacacb", 42); // 42

        print(1, 3, "acbbqbbqbb", 10); // 10
        print(2, 4, "cbabecbahe", 18); // 18
    }

    @Test
    public void shouldTestCaaahqcqes() {
        print(2, 3, "caaahqcqes", 20);
    }

    @Test
    public void shouldTestbaaceacmbaaceam() {
        print(2, 4, "baaceacmbaaceam", 22);
        print(1, 1, "acabsbccbgfeaca", 13);
        print(2, 4, "acabccadeljadel", 26);
    }

    @Test
    public void shouldTestLongStr1() {
        print(2709, 2712, "caackncaacknggikncaacknggaacknggikncaackggikncaacknggaacknggikncakqoaacknggikncacggihikncaomhikncaom", 65040);
    }

    @Test
    public void shouldTestLongStr2() {
        print(7890, 7891, "acbcrsjcrscrsjcrcbcrsjcrscrsjccbcrsjcrscrsjcrcbcrsjrscrsjcrcbcrsjcrscrsjccbcrsjcrscrsjcrcbcsbcbcrsjh", 126246);
    }

    @Test
    public void shouldTestLongStr3() {
        print(7078, 7078, "abbciabbcabciabbcmabbciabbcahlbchgcmabbcmggcmababciabbcagerafrciabbcsrhgcmcabciabbchgcmabbcmsfabcmsr", 268964);
    }
}
