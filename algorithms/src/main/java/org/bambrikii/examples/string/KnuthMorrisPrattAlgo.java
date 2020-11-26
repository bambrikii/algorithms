package org.bambrikii.examples.string;

/**
 * https://brilliant.org/wiki/knuth-morris-pratt-algorithm/
 */
public class KnuthMorrisPrattAlgo {
    public int pos(String text, String match) {
        int textLen = text.length();
        int matchLen = match.length();
        if (textLen < matchLen) {
            return -1;
        }
        int textPos = 0;
        int maxPos = textLen - matchLen;
        int matchPos = 0;
        int nextPos = -1;
        while (textPos <= maxPos) {
            char textChar = text.charAt(textPos + matchPos);
            if (textChar == match.charAt(matchPos)) {
                if (matchPos + 1 == matchLen) {
                    return textPos;
                }
                matchPos++;
                if (textChar == match.charAt(nextPos + 1)) {
                    nextPos++;
                } else {
                    nextPos = -1;
                }
            } else if (nextPos > -1) {
                textPos += nextPos + 1;
                matchPos = nextPos + 1;
                nextPos = -1;
            } else {
                textPos++;
                matchPos = 0;
                nextPos = -1;
            }
        }
        return -1;
    }
}
