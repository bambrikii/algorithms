package org.bambrikii.examples.algorithms.incubator.regex.ops.impl;

import org.bambrikii.examples.algorithms.incubator.regex.ast.AstNode;
import org.bambrikii.examples.algorithms.incubator.regex.ast.impl.TimesNode;
import org.bambrikii.examples.algorithms.incubator.regex.ops.Op;
import org.bambrikii.examples.algorithms.incubator.regex.ops.OpCtx;

public class TimesOp implements Op {
    private final AstNode child;

    public TimesOp(AstNode child) {
        this.child = child;
    }

    @Override
    public AstNode ast(OpCtx ctx) {
        ctx.start();
        if (child == null) {
            ctx.rollback();
            return null;
        }
        if (ctx.has('*')) {
            ctx.commit();
            return new TimesNode(child, 0, Integer.MAX_VALUE);
        }
        if (ctx.has('+')) {
            ctx.commit();
            return new TimesNode(child, 1, Integer.MAX_VALUE);
        }
        // TODO: {from,to}
        // return new TimesNode(child, 0, 0);
        ctx.rollback();
        return null;
    }
}
