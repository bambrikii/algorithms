package org.bambrikii.examples.algorithms.incubator.countstring;

import java.util.List;

class ExprNode extends CountNode {
    private final List<CountNode> children;
    private int matches = 0;

    public ExprNode(List<CountNode> children) {
        this.children = children;
        for (CountNode child : children) {
            child.setParent(this);
        }
    }

    @Override
    boolean tryMatch(CountCtx ctx) {
        int pos = ctx.start();
        for (CountNode child : children) {
            if (!child.tryMatch(ctx)) {
                break;
            }
        }
        ctx.rollback(pos);
        boolean b = matches == children.size();
        matches = 0;
        return b;
    }

    @Override
    boolean matchCallback(CountCtx ctx) {
        matches++;
        if (matches == children.size()) {
            matches = 0;
            return super.matchCallback(ctx);
        }
        return true;
    }
}
