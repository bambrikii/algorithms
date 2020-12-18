package org.bambrikii.examples.combinatorics.variations;

/**
 * https://www.ktbyte.com/java-tutorial/book/combinatorics
 */
public class Variations {
    public static void main(String[] args) {
        variations("ABC", "", 2);
    }

    static void variations(String x, String pre, int len) {
        if (len == 0) {
            System.out.println(pre);
            return;
        }
        for (int i = 0; i < x.length(); i++) {
            String p = pre + x.charAt(i);
            variations(x, p, len - 1);
        }
    } //note recursive does not remove items
    //outputs AA,AB,AC,BA,BB,BC,CA,CB,CC
}
