package org.bambrikii.examples.algorithms.incubator.countstring;

import static org.bambrikii.examples.algorithms.incubator.utils.ConsoleUtils.ANSI_BLUE;
import static org.bambrikii.examples.algorithms.incubator.utils.ConsoleUtils.ANSI_GREEN;
import static org.bambrikii.examples.algorithms.incubator.utils.ConsoleUtils.ANSI_RED;
import static org.bambrikii.examples.algorithms.incubator.utils.ConsoleUtils.ANSI_RESET;

public abstract class Node {
    private Node next = True.TRUE;
    public static boolean debug;

    void next(Node next) {
        this.next = next;
    }

    Node next() {
        return next;
    }

    abstract boolean match(Ctx ctx);

    protected void logStr(Ctx ctx) {
        logStr(ctx, 0);
    }

    private String cls() {
        return this.getClass().getSimpleName().replaceAll("Node$", "");
    }

    protected void logStr(Ctx ctx, int offset) {
        CharSequence seq = ctx.getStr();
        int pos = ctx.getPos() + offset;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seq.length(); i++) {
            char ch = seq.charAt(i);
            if (pos == i) {
                sb.append(ANSI_GREEN).append(ch).append(ANSI_RESET);
            } else {
                sb.append(ch);
            }
            if (sb.length() % 100 == 0) {
                System.out.print(sb);
                System.out.flush();
                sb.setLength(0);
            }
        }
        sb.append("[").append(pos).append("]");
        System.out.print(sb.toString());
    }

    protected void log(Ctx ctx, String comment) {
        if (debug) {
            printDepth(ctx.getDepth());
            System.out.print("? " + cls() + " ");
            logStr(ctx);
            System.out.print(" " + comment);
            System.out.println();
        }
    }

    private void printDepth(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print(".  ");
        }
    }

    private void tryDebug(Ctx ctx, String comment, int i, String s, int i2, String ansiRed, String s2) {
        if (debug) {
            printDepth(i);
            System.out.print(s + cls() + " ");
            logStr(ctx, i2);
            System.out.print(ansiRed + s2 + comment + ANSI_RESET);
            System.out.println();
            System.out.flush();
        }
    }

    protected void logStart(Ctx ctx, String comment) {
        tryDebug(ctx, comment, ctx.incDepth(), "> ", 0, ANSI_BLUE, " st. ");
    }

    protected void logComplete(Ctx ctx, String comment) {
        tryDebug(ctx, comment, ctx.decDepth(), "< ", -1, ANSI_GREEN, " ok. ");
    }

    protected void logRollback(Ctx ctx, String comment) {
        tryDebug(ctx, comment, ctx.decDepth(), "< ", -1, ANSI_RED, " rb. ");
    }
}
