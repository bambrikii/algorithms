package org.bambrikii.examples.string;

public class LongestCommonSubsequence {
    public int find(String str1, String str2) {
        return find(str1, str2, 0, 0);
    }

    private int find(String str1, String str2, int pos1, int pos2) {
        if (pos1 >= str1.length() || pos2 >= str2.length()) {
            return 0;
        }

        if (str1.charAt(pos1) == str2.charAt(pos2)) {
            return find(str1, str2, pos1 + 1, pos2 + 1) + 1;
        }

        return Math.max(
                find(str1, str2, pos1 + 1, pos2),
                find(str1, str2, pos1, pos2 + 1)
        );
    }

    public static void main(String[] args) {
        System.out.println(new LongestCommonSubsequence().find("abc", "adc") == 2);
        System.out.println(new LongestCommonSubsequence().find("abcdefghijk", "adcdefgk") == 7);
    }
}
