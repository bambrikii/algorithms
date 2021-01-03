package org.bambrikii.examples.algorithms.incubator.countstring;

class TimesNode extends CountNode {
    private final CountNode child;
    private final int min;
    private final int max;
    private int added = 0;

    public TimesNode(CountNode child, int min, int max) {
        this.child = child;
        child.setParent(this);
        this.min = min;
        this.max = max;
    }

    @Override
    boolean tryMatch(CountCtx ctx) {
        logStart(ctx, min + ".." + max);
        if (min == 0) {
            getParent().matchCallback(ctx);
        }
        logComplete(ctx, " added: " + added);
        return tryMatch0(ctx);
    }

    private boolean tryMatch0(CountCtx ctx) {
        int pos = ctx.start();
        logStart(ctx, min + ".." + max);
        if (child.tryMatch(ctx)) {
            logRollback(ctx, " added: " + added);
            return true;
        }
        logRollback(ctx, " added: " + added);
        ctx.rollback(pos);
        added = 0;
        return false;
    }

    @Override
    boolean matchCallback(CountCtx ctx) {
        added++;
        if (min > added) {
            return tryMatch0(ctx);
        }
        if (added == max) {
            logComplete(ctx, " added: " + added + " == " + max);
            added = 0;
            return super.matchCallback(ctx);
        } else if (added > max) {
            logRollback(ctx, " added: " + added + " > " + max);
            return false;
        }
        if (ctx.charsLeft() == 0) {
            logComplete(ctx, " added: " + added + ", chars left == 0");
            added = 0;
            return super.matchCallback(ctx);
        }
        return tryMatch0(ctx);
    }
}
