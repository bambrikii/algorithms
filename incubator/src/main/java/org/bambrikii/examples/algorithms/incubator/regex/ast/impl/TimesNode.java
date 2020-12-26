package org.bambrikii.examples.algorithms.incubator.regex.ast.impl;

import org.bambrikii.examples.algorithms.incubator.regex.ast.AstCtx;
import org.bambrikii.examples.algorithms.incubator.regex.ast.AstNode;

public class TimesNode implements AstNode {
    private final AstNode child;
    private final int min;
    private final int max;

    public TimesNode(AstNode child, int min, int max) {
        this.child = child;
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean test(AstCtx ctx) {
        ctx.start();
        int n = 0;
        while (child.test(ctx)) {
            n++;
        }
        boolean b = min <= n && n <= max;
        if (b) {
            ctx.commit();
        } else {
            ctx.rollback();
        }
        return b;
    }
}
