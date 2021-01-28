package org.bambrikii.examples.algorithms.incubator.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://www.youtube.com/watch?v=qBbZ3tS0McI
 */
public class GenerateParentheses {
    public void generate(int max) {
        List<String> results = new ArrayList<>();
        gen(results, new ArrayList<>(), max, 0, 0);
        results.forEach(elem -> System.out.println(elem));
    }

    private void gen(List<String> results, List<String> arr, int max, int open, int closed) {
        if (arr.size() == max * 2) {
            results.add(arr.stream().collect(Collectors.joining()));
            return;
        }
        if (open < max) {
            ArrayList<String> arr1 = new ArrayList<>(arr);
            arr1.add("{");
            gen(results, arr1, max, open + 1, closed);
        }
        if (closed < open) {
            ArrayList<String> arr1 = new ArrayList<>(arr);
            arr1.add("}");
            gen(results, arr1, max, open, closed + 1);
        }
    }

    public static void main(String[] args) {
        new GenerateParentheses().generate(3);
    }
}
