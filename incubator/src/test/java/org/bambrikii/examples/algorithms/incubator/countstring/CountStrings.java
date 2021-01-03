package org.bambrikii.examples.algorithms.incubator.countstring;

import static org.bambrikii.examples.algorithms.incubator.countstring.AstBuilder.matchesMain;
import static org.bambrikii.examples.algorithms.incubator.countstring.CountNode.ANSI_GREEN;
import static org.bambrikii.examples.algorithms.incubator.countstring.CountNode.ANSI_RED;
import static org.bambrikii.examples.algorithms.incubator.countstring.CountNode.ANSI_RESET;

public class CountStrings {
    public static final int OK = -2;

    /*
     * Complete the countStrings function below.
     */
    static int countStrings(String r, int l) {
        /*
         * Write your code here.
         */
        char[] dict = {'a', 'b'};
        int n = dict.length;
        int[] pos = new int[l];
        char[] sb = new char[l];
        for (int i = 0; i < l; i++) {
            sb[i] = dict[0];
        }
        CountCtx patternCtx = new CountCtx(r);
        CountNode ast = matchesMain(patternCtx);
        int count = 0;
        int startPos;
        do {
            StringBuilder str = buildString(dict, pos);
            CountCtx ctx = new CountCtx(str);
            System.out.println("=== " + r + " -> " + str + " ===");
            ast.tryMatch(ctx);
            boolean matched = ((MainNode) ast).isMatched();
            int lastMatchPos = ctx.getRollbackPos();
            startPos = lastMatchPos < 1 ? 0 : lastMatchPos - 1;
            if (matched) {
                System.out.println(ANSI_GREEN + " (match)" + ANSI_RESET);
                count++;
            } else {
                for (int i = 0; i < startPos; i++) {
                    pos[i] = 0;
                }
                System.out.println(ANSI_RED + " (no match)" + ANSI_RESET);
            }
        } while (inc(pos, n, startPos));
        return count;
    }

    private static boolean inc(int[] pos, int n, int curr) {
        if (curr == pos.length) {
            return false;
        }
        if (pos[curr] == n - 1) {
            pos[curr] = 0;
            return inc(pos, n, curr + 1);
        }
        pos[curr]++;
        return true;
    }

    private static StringBuilder buildString(char[] dict, int[] pos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pos.length; i++) {
            sb.append(dict[pos[i]]);
        }
        return sb;
    }
}
