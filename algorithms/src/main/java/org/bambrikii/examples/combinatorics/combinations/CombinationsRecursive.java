package org.bambrikii.examples.combinatorics.combinations;

import java.util.List;

/**
 * https://hmkcode.com/calculate-find-all-possible-combinations-of-an-array-using-java/
 */
public class CombinationsRecursive {
    public static void combination(List<String> e, int k, String accumulated) {
        if (e.size() < k) { // 1. stop
            return;
        }

        if (k == 1) { // 2. add each element in e to accumulated
            for (String s : e) {
                print(accumulated + s);
            }
        } else if (e.size() == k) { // 3. add all elements in e to accumulated
            for (String s : e) {
                accumulated += s;
            }
            print(accumulated);
        } else if (e.size() > k) { // 4. for each element, call combination
            for (int i = 0; i < e.size(); i++) {
                combination(e.subList(i + 1, e.size()), k - 1, accumulated + e.get(i));
            }
        }

    }

    private static void print(String s) {
        System.out.println(s);
    }

}
