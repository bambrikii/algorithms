package org.bambrikii.examples.algorithms.incubator.regex.ast.impl;

import org.bambrikii.examples.algorithms.incubator.regex.ast.AstCtx;
import org.bambrikii.examples.algorithms.incubator.regex.ast.AstNode;

import java.util.List;

public class ExprNode implements AstNode {
    private final List<AstNode> children;

    public ExprNode(List<AstNode> children) {
        this.children = children;
    }

    public boolean test(AstCtx ctx) {
        ctx.start();
        for (AstNode child : children) {
            if (!child.test(ctx)) {
                ctx.rollback();
                return false;
            }
        }
        boolean b = children.size() > 0;
        if (b) {
            ctx.commit();
        } else {
            ctx.rollback();
        }
        return b;
    }
}
