package org.bambrikii.examples.algorithms.incubator.countstring;

abstract class Node {
    private Node next = True.TRUE;
    public static boolean debug;

    void next(Node next) {
        this.next = next;
    }

    Node next() {
        return next;
    }

    abstract boolean match(Ctx ctx);

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";

    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

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
