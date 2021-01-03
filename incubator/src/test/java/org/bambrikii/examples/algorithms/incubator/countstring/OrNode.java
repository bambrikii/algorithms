package org.bambrikii.examples.algorithms.incubator.countstring;

import java.util.List;

class OrNode extends CountNode {
    private final List<CountNode> children;
    private int matches;

    public OrNode(List<CountNode> children) {
        this.children = children;
        for (CountNode child : children) {
            child.setParent(this);
        }
    }

    @Override
    boolean tryMatch(CountCtx ctx) {
        logStart(ctx, " children: " + children.size());
        if (children.size() == 1) {
            return false;
        }
        int pos = ctx.start();
        for (CountNode node : children) {
            if (node.tryMatch(ctx)) {
                matches++;
            }
            ctx.rollback(pos);
        }
        if (matches == 0) {
            logRollback(ctx, " n: " + matches);
            return false;
        }
        logComplete(ctx, " n: " + matches);
        matches = 0;
        return true;
    }

    @Override
    boolean matchCallback(CountCtx ctx) {
        return super.matchCallback(ctx);
    }
}
