package org.bambrikii.examples.combinatorics.combinations;

/**
 * https://www.ktbyte.com/java-tutorial/book/combinatorics
 */
public class Combinations {
    public static void main(String[] args) {
        comb3("ABCDE", 3);
    }

    //Helper function so we can use default arguments
    static void comb3(String x, int targetlength) {
        comb3(x, new boolean[x.length()], 0, 0, targetlength);
    }

    //The new arguments are included and targetlength
    //We keep track of how many characters long our subset is,
    //and we stop when we reach targetlength
    static void comb3(String x, boolean[] inc, int pos, int included, int targetlength) {
        if (included == targetlength) {
            //Rather than printing, we could calculate
            //how much happiness these friends give us:
            for (int i = 0; i < inc.length; i++) {
                if (inc[i]) System.out.print(x.charAt(i));
            }
            System.out.println();
            return;   //exit
        }
        if (pos >= inc.length) return;
        inc[pos] = true;
        comb3(x, inc, pos + 1, included + 1, targetlength); //+1 because we included another
        inc[pos] = false;
        comb3(x, inc, pos + 1, included, targetlength);
    }
}
