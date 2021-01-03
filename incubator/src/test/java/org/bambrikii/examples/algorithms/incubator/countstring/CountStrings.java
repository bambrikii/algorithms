package org.bambrikii.examples.algorithms.incubator.countstring;

public class CountStrings {
    /*
     * Complete the countStrings function below.
     */
    static int countStrings(String r, int l) {
        /*
         * Write your code here.
         */
        char[] dict = new char[]{'a', 'b'};
        int n = dict.length;
        int[] pos = new int[l];
        Ctx patternCtx = new Ctx(r);
        Ast astBuilder = new Ast();
        Node ast = astBuilder.main(patternCtx);
        int count = 0;
        int startPos;
        do {
            StringBuilder str = buildString(pos, dict);
            Ctx ctx = new Ctx(str);
            ctx.enableLogging();
            tryLog("=== " + r + " -> " + str + " ===");
            boolean matched = ast.match(ctx);
            startPos = ctx.getRollbackPos();
            tryLog("matches: " + matched + ", rollbackPos: " + startPos);
            if (matched) {
                startPos = l - 1;
                tryLog(Node.ANSI_GREEN + " (match)" + Node.ANSI_RESET);
                count++;
            } else {
                if (startPos < 0) {
                    startPos = 0;
                } else if (startPos >= l) {
                    startPos = l - 1;
                }
                for (int i = startPos + 1; i < l; i++) {
                    pos[i] = 0;
                }
                tryLog(Node.ANSI_RED + " (no match)" + Node.ANSI_RESET);
            }
        } while (inc(pos, n, startPos));
        return count;
    }

    private static void tryLog(String msg) {
        if (Node.debug) {
            System.out.println(msg);
        }
    }

    static boolean inc(int[] pos, int n, int curr) {
        if (curr == -1) {
            return false;
        }
        if (pos[curr] >= n - 1) {
            pos[curr] = 0;
            return inc(pos, n, curr - 1);
        }
        pos[curr]++;
        return true;
    }

    static StringBuilder buildString(int[] pos, char[] dict) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pos.length; i++) {
            sb.append(dict[pos[i]]);
        }
        return sb;
    }
}
