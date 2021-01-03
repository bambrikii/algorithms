package org.bambrikii.examples.algorithms.incubator.countstring;

abstract class CountNode {
    private CountNode parent;

    void setParent(CountNode parent) {
        this.parent = parent;
    }

    CountNode getParent() {
        return parent;
    }

    abstract boolean tryMatch(CountCtx ctx);

    boolean matchCallback(CountCtx ctx) {
        return parent.matchCallback(ctx);
    }

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

    protected String logStr(CountCtx ctx) {
        return logStr(ctx, 0);
    }

    private String cls() {
        return this.getClass().getSimpleName().replaceAll("Node$", "");
    }

    protected String logStr(CountCtx ctx, int offset) {
        CharSequence seq = ctx.getStr();
        int pos = ctx.getPos() + offset;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seq.length(); i++) {
            char ch = seq.charAt(i);
            if (pos == i) {
                sb.append(ANSI_GREEN + ch + ANSI_RESET);
            } else {
                sb.append(ch);
            }
        }
        sb.append("[").append(pos).append("]");
        return sb.toString();
    }

    protected void log(CountCtx ctx, String comment) {
        System.out.println("+ " + cls() + " " + logStr(ctx) + " " + comment);
    }

    private String printDepth(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append(".  ");
        }
        return sb.toString();
    }

    protected void logStart(CountCtx ctx, String comment) {
        System.out.println(printDepth(ctx.incDepth()) + "> " + cls() + " " + logStr(ctx, 1) + ANSI_BLUE + " start, " + comment + ANSI_RESET);
    }

    protected void logComplete(CountCtx ctx, String comment) {
        System.out.println(printDepth(ctx.decDepth()) + "< " + cls() + " " + logStr(ctx) + ANSI_GREEN + " complete, " + comment + ANSI_RESET);
    }

    protected void logRollback(CountCtx ctx, String comment) {
        System.out.println(printDepth(ctx.decDepth()) + "< " + cls() + " " + logStr(ctx) + ANSI_RED + " rollback, " + comment + ANSI_RESET);
    }
}
