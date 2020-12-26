package org.bambrikii.examples.algorithms.incubator.regex.ast.impl;

import org.bambrikii.examples.algorithms.incubator.regex.ast.AstCtx;
import org.bambrikii.examples.algorithms.incubator.regex.ast.AstNode;

import java.util.List;

public class OrNode implements AstNode {
    private final List<AstNode> children;

    public OrNode(List<AstNode> children) {
        this.children = children;
    }

    @Override
    public boolean test(AstCtx ctx) {
        ctx.start();
        for (AstNode child : children) {
            if (child.test(ctx)) {
                ctx.commit();
                return true;
            }
        }
        ctx.rollback();
        return false;
    }
}
