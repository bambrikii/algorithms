package org.bambrikii.examples.algorithms.incubator.countstring;

public class GroupTail extends Node {
    private final int localIndex;

    public GroupTail(int localIndex) {
        this.localIndex = localIndex;
    }

    @Override
    boolean match(Ctx ctx) {
        return next().match(ctx);
    }
}
