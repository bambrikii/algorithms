package org.bambrikii.examples.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Substring search
 * <p>
 * https://www.encora.com/insights/the-boyer-moore-horspool-algorithm
 */
public class BoyerMooreHorspoolAlgo {
    public static void main(String[] args) {
        System.out.println(new BoyerMooreHorspoolAlgo().find("eovadabcdftoy", "abcd"));
        System.out.println(new BoyerMooreHorspoolAlgo().find("longtextline", "text"));
    }

    public int find(String text, String str) {
        int strLen = str.length();
        Map<Character, Integer> shifts = new HashMap<>();
        for (int i = 0; i < strLen; i++) {
            char ch = str.charAt(i);
            Integer found = shifts.get(ch);
            int sh = i == strLen - 1 ? strLen : strLen - i - 1;
            if (found != null && sh < found) {
                shifts.put(ch, sh);
            }
        }

        int len = text.length();
        int pos = 0;
        w:
        while (pos < len) {
            for (int i = strLen - 1; i >= 0; i--) {
                int posPlus = pos + i;
                if (posPlus >= text.length()) {
                    break;
                }
                char textChar = text.charAt(posPlus);
                if (i >= str.length()) {
                    break;
                }
                char strChar = str.charAt(i);
                if (textChar == strChar) {
                    continue;
                }
                Integer shift = shifts.get(textChar);
                if (shift != null) {
                    pos += shift - 1;
                    continue w;
                }
                pos += strLen;
                continue w;
            }
            return pos;
        }
        return -1;
    }
}
