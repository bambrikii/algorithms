package org.bambrikii.examples.algorithms.incubator.strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordBreak {
    public static void main(String[] args) {
        Set<String> dictionary = new HashSet<>(Arrays.asList(new String[]{"this", "is", "a", "famous", "problem"}));
        String string = "thisisafamousproblem";
        System.out.println(breakItBottomUp(dictionary, string));
    }

    public static boolean breakItBottomUp(Set<String> dictionary, String str) {
        boolean[] table = new boolean[str.length() + 1];
        table[0] = true;
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; table[i] && j <= str.length(); j++) {
                if (dictionary.contains(str.substring(i, j))) {
                    table[j] = true;
                }
            }
        }
        return table[str.length()];
    }
}
