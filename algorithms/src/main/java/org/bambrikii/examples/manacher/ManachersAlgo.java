package org.bambrikii.examples.manacher;

/**
 * https://www.geeksforgeeks.org/manachers-algorithm-linear-time-longest-palindromic-substring-part-1/
 */
public class ManachersAlgo {
    public String find(String in) {
        if (in == null) {
            return null;
        }
        int len = in.length();
        StringBuilder result = new StringBuilder();
        int mid = (len + 1) / 2;
        for (int pos1 = 0; pos1 < mid; pos1++) {
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            int mid2 = (len - pos1 + 1) / 2;
            for (int pos2 = 0; pos2 < mid2; pos2++) {
                tryMatch(in, sb1, pos1 + pos2, len - 1 - pos2);
                tryMatch(in, sb2, pos2, len - 1 - pos1 - pos2);
            }
            if (sb1.length() > result.length()) {
                result = sb1;
            }
            if (sb2.length() > result.length()) {
                result = sb2;
            }
        }
        return result.toString();
    }

    private void tryMatch(String in, StringBuilder sb, int leftPos, int rightPos) {
        char left = in.charAt(leftPos);
        int sbMid = sb.length() / 2;
        if (leftPos == rightPos) {
            sb.insert(sbMid, left);
            return;
        }

        char right = in.charAt(rightPos);
        if (left == right) {
            sb.insert(sbMid, left).insert(sbMid, right);
            return;
        }

        sb.setLength(0);
    }
}
