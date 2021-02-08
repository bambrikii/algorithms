package org.bambrikii.examples.algorithms.incubator.countstring;

import org.bambrikii.examples.algorithms.incubator.countstring.Str.StrReverse;

import static org.bambrikii.examples.algorithms.incubator.utils.ConsoleUtils.ANSI_GREEN;
import static org.bambrikii.examples.algorithms.incubator.utils.ConsoleUtils.ANSI_RED;
import static org.bambrikii.examples.algorithms.incubator.utils.ConsoleUtils.ANSI_RESET;

public class CountStrings {
    /*
     * Complete the countStrings function below.
     */
    static int countStrings(String r, int l) {
        /*
         * Write your code here.
         */
        int pos;
        Ctx patternCtx = new Ctx(r);
        Ast astBuilder = new Ast();
        Node ast = astBuilder.main(patternCtx);
        StrReverse str = new Str(l).new StrReverse();
        int count = 0;
        do {
            Ctx ctx = new Ctx(str);
            tryLog("=== " + r + " -> " + str + " ===");
            boolean matched = ast.match(ctx);
            pos = ctx.getRollbackPos();
            tryLog("matches: " + matched + ", rollbackPos: " + pos);
            if (matched) {
                pos = l - 1;
                tryLog(ANSI_GREEN + " (match)" + ANSI_RESET);
                count++;
            } else {
                if (pos < 0) {
                    pos = 0;
                } else if (pos >= l) {
                    pos = l - 1;
                }
                str.resetBefore(pos);
                tryLog(ANSI_RED + " (no match)" + ANSI_RESET);
            }
        } while (str.inc(pos));
        return count;
    }

    private static void tryLog(String msg) {
        if (Node.debug) {
            System.out.println(msg);
        }
    }
}
