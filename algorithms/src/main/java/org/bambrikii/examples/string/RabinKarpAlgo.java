package org.bambrikii.examples.string;

/**
 *
 */
public class RabinKarpAlgo {
    public int pos(String text, String match) {
        int matchLen = match.length();
        int textLen = text.length();
        if (matchLen > textLen) {
            return -1;
        }
        int prime = 31;
        int matchHash = 0;
        for (int i = 0; i < matchLen; i++) {
            matchHash = matchHash * prime + match.charAt(i);
        }

        int textHash = 0;
        for (int i = 0; i < matchLen; i++) {
            textHash = textHash * prime + text.charAt(i);
        }
        if (textHash == matchHash) {
            return 0;
        }
        int powFirst = (int) Math.pow(prime, matchLen - 1);
        for (int i = 1; i < textLen - matchLen; i++) {
            textHash = textHash - text.charAt(i - 1) * powFirst;
            textHash = textHash * prime + text.charAt(i + matchLen - 1);
            if (matchHash == textHash) {
                return i;
            }
        }
        return -1;
    }
}
