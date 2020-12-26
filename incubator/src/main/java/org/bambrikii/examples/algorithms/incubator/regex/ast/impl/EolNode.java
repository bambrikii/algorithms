package org.bambrikii.examples.algorithms.incubator.regex.ast.impl;

import org.bambrikii.examples.algorithms.incubator.regex.ast.AstCtx;
import org.bambrikii.examples.algorithms.incubator.regex.ast.AstNode;

public class EolNode implements AstNode {
    @Override
    public boolean test(AstCtx ctx) {
        ctx.start();
        if (ctx.advance()) {
            ctx.rollback();
            return false;
        }
        ctx.commit();
        return true;
    }
}
