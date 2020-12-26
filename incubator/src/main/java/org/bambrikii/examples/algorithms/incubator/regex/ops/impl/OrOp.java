package org.bambrikii.examples.algorithms.incubator.regex.ops.impl;

import org.bambrikii.examples.algorithms.incubator.regex.ast.AstNode;
import org.bambrikii.examples.algorithms.incubator.regex.ast.impl.OrNode;
import org.bambrikii.examples.algorithms.incubator.regex.ops.Op;
import org.bambrikii.examples.algorithms.incubator.regex.ops.OpCtx;

import java.util.ArrayList;
import java.util.List;

public class OrOp implements Op {
    @Override
    public AstNode ast(OpCtx ctx) {
        ctx.start();
        List<AstNode> children = new ArrayList<>();
        AstNode child = new ExprOp().ast(ctx);
        if (child == null) {
            ctx.rollback();
            return null;
        }
        children.add(child);
        while (true) {
            if (!ctx.has('|')) {
                break;
            }
            if (!ctx.advance()) {
                break;
            }
            child = new ExprOp().ast(ctx);
            if (child == null) {
                break;
            }
            children.add(child);
        }
        if (children.size() < 2) {
            ctx.rollback();
            return null;
        }
        ctx.commit();
        return new OrNode(children);
    }
}
