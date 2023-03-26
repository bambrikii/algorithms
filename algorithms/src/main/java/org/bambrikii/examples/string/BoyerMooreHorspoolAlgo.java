package org.bambrikii.examples.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Substring search
 * <p>
 * https://www.encora.com/insights/the-boyer-moore-horspool-algorithm
 * <p>
 * 1. find min indexes per character in search string
 * 2. in text to go over starting from position 0:
 * 3. for search string starting from last to first position
 * 4. if search string character position is over text position, break with not found
 * 5. if current text character matches search string character, continue
 * 6. if for current text character min index found in search string,
 * 6.1. increment text position to current search position minus min index found
 * 7. if the loop 3 is complete, return text current position
 * 8. break with not found
 */
public class BoyerMooreHorspoolAlgo {
    public static void main(String[] args) {
        var algo = new BoyerMooreHorspoolAlgo();
        System.out.println(algo.find("eovadabcdftoy", "abcd")); // 5
        System.out.println(algo.find("longtextline", "text")); // 4
        System.out.println(algo.find("1text", "text")); // 1
        System.out.println(algo.find("text1", "text")); // 0
        System.out.println(algo.find("text", "k")); // -1
        System.out.println(algo.find("text1", "find")); // -1
    }

    public int find(String text, String match) {
        var matchLen = match.length();
        Map<Character, Integer> shifts = new HashMap<>();
        for (var i = 0; i < matchLen; i++) {
            var ch = match.charAt(i);
            if (!shifts.containsKey(ch)) {
                shifts.put(ch, i);
            }
        }

        var len = text.length();
        var pos = 0;
        w:
        while (pos < len) {
            for (int i = matchLen - 1; i >= 0; i--) {
                var posPlus = pos + i;
                if (posPlus >= text.length()) {
                    break w;
                }
                var textChar = text.charAt(posPlus);
                char findChar = match.charAt(i);
                if (textChar == findChar) {
                    continue;
                }
                var shift = shifts.get(textChar);
                pos += i + (shift == null ? 1 : -shift);
                continue w;
            }
            return pos;
        }
        return -1;
    }
}
