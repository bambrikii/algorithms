package org.bambrikii.examples.algorithms.incubator.regex.ops.impl;

import org.bambrikii.examples.algorithms.incubator.regex.ast.AstNode;
import org.bambrikii.examples.algorithms.incubator.regex.ast.impl.EolNode;
import org.bambrikii.examples.algorithms.incubator.regex.ops.Op;
import org.bambrikii.examples.algorithms.incubator.regex.ops.OpCtx;

public class EolOp implements Op {
    @Override
    public AstNode ast(OpCtx ctx) {
        ctx.start();
        if (!ctx.has('$')) {
            ctx.rollback();
            return null;
        }
        ctx.commit();
        return new EolNode();
    }
}
