package org.bambrikii.examples.algorithms.incubator.regex.ops.impl;

import org.bambrikii.examples.algorithms.incubator.regex.ast.AstNode;
import org.bambrikii.examples.algorithms.incubator.regex.ast.impl.ExprNode;
import org.bambrikii.examples.algorithms.incubator.regex.ops.Op;
import org.bambrikii.examples.algorithms.incubator.regex.ops.OpCtx;

import java.util.ArrayList;
import java.util.List;

public class ExprOp implements Op {
    @Override
    public AstNode ast(final OpCtx ctx) {
        ctx.start();
        if (!ctx.has('(')) {
            ctx.rollback();
            return null;
        }
        if (!ctx.advance()) {
            ctx.rollback();
            return null;
        }
        List<AstNode> children = new ArrayList<>();
        while (true) {
            AstNode child = new OrOp().ast(ctx);
            if (child == null) {
                child = new ExprOp().ast(ctx);
                if (child == null) {
                    child = new CharsOp().ast(ctx);
                    if (child == null) {
                        break;
                    }
                }
            }
            children.add(child);
        }
        if (!ctx.has(')')) {
            ctx.rollback();
            return null;
        }
        ctx.commit();
        AstNode node = new ExprNode(children);
        if (ctx.advance()) {
            AstNode times = new TimesOp(node).ast(ctx);
            if (times != null) {
                return times;
            }
        }
        return node;
    }
}
