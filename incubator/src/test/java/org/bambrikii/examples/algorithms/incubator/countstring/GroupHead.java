package org.bambrikii.examples.algorithms.incubator.countstring;

public class GroupHead extends Node {
    private final int localIndex;

    public GroupHead(int localIndex) {
        this.localIndex = localIndex;
    }

    @Override
    boolean match(Ctx ctx) {
        int save = ctx.locals(localIndex);
        ctx.localsFromPos(localIndex);
        boolean b = next().match(ctx);
        ctx.locals(localIndex, save);
        return b;
    }
}
