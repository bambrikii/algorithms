package org.bambrikii.examples.manacher;

/**
 * https://www.geeksforgeeks.org/manachers-algorithm-linear-time-longest-palindromic-substring-part-1/
 */
public class ManachersAlgo2 {
    public String find(String in) {
        if (in == null) {
            return null;
        }
        int len = in.length();
        StringBuilder result = new StringBuilder();
        for (int pos1 = 0; pos1 < len; pos1++) {
            StringBuilder sb = new StringBuilder();
            int leftPos = (pos1) / 2;
            int rightPos = (pos1 + 1) / 2;
            while ((leftPos >= 0 && rightPos < len)) {
                char left = in.charAt(leftPos);
                if (leftPos == rightPos) {
                    sb.insert(0, left);
                } else {
                    char right = in.charAt(rightPos);
                    if (left == right) {
                        sb.insert(0, left).append(right);
                        if (sb.length() > 1 && sb.length() > result.length()) {
                            result.setLength(0);
                            result.append(sb.toString());
                        }
                    } else {
                        sb.setLength(0);
                    }
                }
                leftPos -= 1;
                rightPos += 1;
            }
        }
        return result.toString();
    }
}
