package org.bambrikii.examples.algorithms.incubator.countstring;

class Loop extends Node {
    private final Node body;
    private final int localIndex;
    private final int min;
    private final int max;

    public Loop(Node body, int localIndex, int min, int max) {
        this.body = body;
        this.localIndex = localIndex;
        this.min = min;
        this.max = max;
    }

    @Override
    boolean match(Ctx ctx) {
        int count = ctx.locals(localIndex);
        int pos = ctx.start();
        logStart(ctx, min + ".." + max);
        Node next = next();
        if (count > pos) {
            return next.match(ctx);
        }
        if (count < min) {
            ctx.locals(localIndex, count + 1);
            boolean b = body.match(ctx);
            if (!b) {
                logRollback(ctx, "no atom match. stage 1");
                ctx.locals(localIndex, count);
            } else {
                logComplete(ctx, "atom added. stage 1");
            }
            return b;
        }
        if (count < max) {
            ctx.locals(localIndex, count + 1);
            boolean b = body.match(ctx);
            if (!b) {
                logRollback(ctx, "no atom match. stage 2");
                ctx.locals(localIndex, count);
                ctx.setPos(pos);
            } else {
                logComplete(ctx, "atom matched. stage 2");
                return b;
            }
        } else {
            logComplete(ctx, "max overflow. stage 2");
        }
        return next.match(ctx);
    }

    protected boolean matchInit(Ctx ctx) {
        return match(ctx);
    }
}
