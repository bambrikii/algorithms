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
    private int max;

    public static int nonDivisibleSubset(int k, List<Integer> s) {
        // Write your code here
        Result r = new Result();
        combine(k, s.size(), 0, new ArrayList<>(), s, r);
        return r.max;
    }

    private static void maxSubsetLen(int k, List<Integer> combination, Result r) {
        int combLen = combination.size();
        int mod = 0;
        for (int i = 0; i < combLen; i++) {
            for (int j = i + 1; j < combLen; j++) {
                int val1 = combination.get(i) + combination.get(j);
                if (val1 % k == 0) {
                    mod++;
                }
            }
        }
        if (mod == 0) {
            r.max = Math.max(r.max, combLen);
        }
    }

    private static String printCombi(String msg, List<Integer> combination, List<Integer> s) {
        StringBuilder sb = new StringBuilder(msg);
        for (Integer i : combination) {
            sb.append(s.get(i)).append(" ");
        }
        return sb.toString();
    }

    private static void combine(int k, int n, int eIx, List<Integer> prev, List<Integer> s, Result r) {
        maxSubsetLen(k, prev, r);
        if (prev.size() == n) {
            return;
        }
        for (int i = eIx; i < n; i++) {
            List<Integer> next = new ArrayList<>(prev);
            next.add(s.get(i));

            combine(k, n, i + 1, next, s, r);
        }
    }
}

public class SubSetSum {
    public static void main(String[] args) throws IOException {
//        print(3, Arrays.asList(1, 3, 5));
//        print(3, Arrays.asList(1, 7, 2, 4));
//        print(7, Arrays.asList(278, 576, 496, 727, 410, 124, 338, 149, 209, 702, 282, 718, 771, 575, 436));
//        print(5, Arrays.asList(2, 7, 12, 17, 22));
//        print(1, Arrays.asList(1, 2, 3, 4, 5));
        print(9, Arrays.asList(
                61197933, 56459859, 319018589, 271720536, 358582070, 849720202, 481165658, 675266245, 541667092, 615618805, 129027583, 755570852, 437001718, 86763458, 791564527,
                163795318, 981341013, 516958303, 592324531, 611671866, 157795445, 718701842, 773810960, 72800260, 281252802, 404319361, 757224413, 682600363, 606641861, 986674925,
                176725535, 256166138, 827035972, 124896145, 37969090, 136814243, 274957936, 980688849, 293456190, 141209943, 346065260, 550594766, 132159011, 491368651, 3772767,
                131852400, 633124868, 148168785, 339205816, 705527969, 551343090, 824338597, 241776176, 286091680, 919941899, 728704934, 37548669, 513249437, 888944501, 239457900,
                977532594, 140391002, 260004333, 911069927, 586821751, 113740158, 370372870, 97014913, 28011421, 489017248, 492953261, 73530695, 27277034, 570013262, 81306939,
                519086053, 993680429, 599609256, 639477062, 677313848, 950497430, 672417749, 266140123, 601572332, 273157042, 777834449, 123586826
        ));
    }

    private static void print(int k, List<Integer> s) {
        int result = Result.nonDivisibleSubset(k, s);
//        System.out.print(" " + k + ": ");
//        for (Integer sElem : s) {
//            System.out.print(" " + sElem);
//        }
//        System.out.println();
        System.out.println(" = " + result);
    }
}
