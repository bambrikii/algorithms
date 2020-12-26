package org.bambrikii.examples.algorithms.incubator.regex.ast.impl;

import org.bambrikii.examples.algorithms.incubator.regex.ast.AstCtx;
import org.bambrikii.examples.algorithms.incubator.regex.ast.AstNode;

public class BolNode implements AstNode {
    @Override
    public boolean test(AstCtx ctx) {
        ctx.start();
        if (ctx.currPos() != 0) {
            ctx.rollback();
            return false;
        }
        ctx.commit();
        return true;
    }
}
