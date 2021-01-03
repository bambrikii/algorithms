package org.bambrikii.examples.algorithms.incubator.countstring;

import java.util.List;

class MainNode extends CountNode {
    private final List<CountNode> children;
    private boolean matched;

    public MainNode(List<CountNode> children) {
        this.children = children;
        for (CountNode child : children) {
            child.setParent(this);
        }
    }

    @Override
    boolean tryMatch(CountCtx ctx) {
        matched = false;
        int pos = ctx.start();
        logStart(ctx, "");
        int matches = 0;
        for (CountNode node : children) {
            if (!node.tryMatch(ctx)) {
                break;
            }
            matches++;
        }
        if (matches != children.size() || ctx.charsLeft() != 0) {
            logComplete(ctx, ", matches = " + matches);
            ctx.rollback(pos);
            return false;
        }
        logComplete(ctx, "");
        return true;
    }

    @Override
    boolean matchCallback(CountCtx ctx) {
        if (ctx.charsLeft() != 0) {
            return false;
        }
        this.matched = true;
        return true;
    }

    public boolean isMatched() {
        return matched;
    }
}
