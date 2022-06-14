package org.bambrikii.examples.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LongestCommonSubsequenceStrings {
    private String str = "";

    public String find(String str1, String str2) {
        find(str1, str2, 0, 0, new ArrayList<>());
        return str;
    }

    private void find(String str1, String str2, int pos1, int pos2, List<Character> seq) {
        if (pos1 >= str1.length() || pos2 >= str2.length()) {
            if (str.length() < seq.size()) {
                str = toString(seq);
            }
            return;
        }

        char ch1 = str1.charAt(pos1);
        char ch2 = str2.charAt(pos2);
        if (ch1 == ch2) {
            seq.add(ch1);
            find(str1, str2, pos1 + 1, pos2 + 1, seq);
            seq.remove(seq.size() - 1);
            return;
        }

        find(str1, str2, pos1 + 1, pos2, seq);
        find(str1, str2, pos1, pos2 + 1, seq);
    }

    private String toString(List<Character> seq) {
        StringBuilder sb = new StringBuilder();
        seq.forEach(ch -> sb.append(ch));
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(Objects.equals(new LongestCommonSubsequenceStrings().find("abc", "adc"), "ac"));
        System.out.println(Objects.equals(new LongestCommonSubsequenceStrings().find("abcdefghijk", "adcdefgak"), "acdefgk"));
    }
}
