package org.bambrikii.examples.algorithms.incubator.regex.ops.impl;

import org.bambrikii.examples.algorithms.incubator.regex.ast.AstNode;
import org.bambrikii.examples.algorithms.incubator.regex.ast.impl.CharsNode;
import org.bambrikii.examples.algorithms.incubator.regex.ops.Op;
import org.bambrikii.examples.algorithms.incubator.regex.ops.OpCtx;

import java.util.ArrayList;
import java.util.List;

public class CharsOp implements Op {
    @Override
    public AstNode ast(OpCtx ctx) {
        ctx.start();
        List<Character> chars = new ArrayList<>();
        while (true) {
            char ch = ctx.currChar();
            if (ch < 'a' || ch > 'z') {
                break;
            }
            chars.add(ch);
            if (!ctx.advance()) {
                break;
            }
        }
        if (chars.isEmpty()) {
            ctx.rollback();
            return null;
        }
        ctx.commit();
        return new CharsNode(chars);
    }
}
