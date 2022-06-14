package org.bambrikii.examples.string;

/**
 * https://en.wikipedia.org/wiki/Levenshtein_distance
 * <p>
 * Dynamic programming way of The Longest Common Subsequence problem
 */
public class LongestCommonSubsequenceDynamic {
    private static final int MATCH = 1;
    private static final int DIFF = 0;
    private String strX;
    private String strY;

    public static void main(String[] args) {
        LongestCommonSubsequenceDynamic algo = new LongestCommonSubsequenceDynamic();
        algo.find("string", "staaaink").print();
        algo.find("str", "svkr").print();
        algo.find("archeopterix", "plateozerixis").print();
    }

    private int[][] curr;
    private char[][] chars;

    public LongestCommonSubsequenceDynamic find(String strX, String strY) {
        int xLen = strX.length() + 1;
        int yLen = strY.length() + 1;
        curr = new int[xLen][yLen];
        chars = new char[xLen][yLen];
        this.strX = strX;
        this.strY = strY;
        for (int x = 0; x < xLen; x++) {
            curr[x][0] = 1;
        }
        for (int y = 0; y < yLen; y++) {
            curr[0][y] = 1;
        }
        for (int x = 1; x < xLen; x++) {
            int posX = x - 1;
            for (int y = 1; y < yLen; y++) {
                int posY = y - 1;
                int match = match(strX, posX, strY, posY);
                int val = curr[x - 1][y - 1] + match;
                int left = curr[x - 1][y];
                int right = curr[x][y - 1];
                if (val < left) {
                    val = left;
                    match = 0;
                }
                if (val < right) {
                    val = right;
                    match = 0;
                }
                curr[x][y] = val;
                if (match == 1) {
                    chars[x][y] = strX.charAt(posX);
                }
            }
        }
        return this;
    }

    public LongestCommonSubsequenceDynamic print() {
        System.out.println(strX);
        System.out.println(strY);
        StringBuilder sb = new StringBuilder();
        System.out.print("__|");
        for (int pos = 0; pos < strY.length(); pos++) {
            System.out.print(strY.charAt(pos) + "__");
        }
        System.out.println();
        for (int i = 1; i < curr.length; i++) {
            System.out.print(" " + strX.charAt(i - 1) + "|");
            for (int j = 1; j < curr[i].length; j++) {
                char ch = chars[i][j];
                System.out.print((ch == 0 ? ' ' : ch) + "" + curr[i][j] + " ");
                if (ch != 0) {
                    sb.append(ch);
                }
            }
            System.out.println();
        }
        System.out.println(sb);
        System.out.println();
        return this;
    }

    private int match(String strX, int posX, String strY, int posY) {
        if (posX < 0 || posX >= strX.length()) {
            return DIFF;
        }
        if (posY < 0 || posY >= strY.length()) {
            return DIFF;
        }
        return strX.charAt(posX) == strY.charAt(posY) ? MATCH : DIFF;
    }
}
