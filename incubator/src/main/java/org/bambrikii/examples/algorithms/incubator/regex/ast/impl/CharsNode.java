package org.bambrikii.examples.algorithms.incubator.regex.ast.impl;

import org.bambrikii.examples.algorithms.incubator.regex.ast.AstCtx;
import org.bambrikii.examples.algorithms.incubator.regex.ast.AstNode;

import java.util.List;

public class CharsNode implements AstNode {
    private final List<Character> chars;

    public CharsNode(List<Character> chars) {
        this.chars = chars;
    }

    @Override
    public boolean test(AstCtx ctx) {
        ctx.start();
        for (int i = 0; i < chars.size(); i++) {
            if (!ctx.has(chars.get(i))) {
                ctx.rollback();
                return false;
            }
            if (!ctx.advance() && i < chars.size() - 1) {
                ctx.rollback();
                return false;
            }
        }
        boolean b = chars.size() > 0;
        if (b) {
            ctx.commit();
        } else {
            ctx.rollback();
        }
        return b;
    }
}
