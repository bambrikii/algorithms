package org.bambrikii.examples.algorithms.incubator.regex;

import org.bambrikii.examples.algorithms.incubator.regex.ast.AstCtx;
import org.bambrikii.examples.algorithms.incubator.regex.ast.AstNode;
import org.bambrikii.examples.algorithms.incubator.regex.ops.OpCtx;
import org.bambrikii.examples.algorithms.incubator.regex.ops.impl.MainOp;

public class Regex {
    private final AstNode ast;

    public Regex(String pattern) {
        OpCtx ctx = new OpCtx();
        ctx.setPattern(pattern);
        MainOp exprOp = new MainOp();
        ast = exprOp.ast(ctx);
    }

    public boolean test(String str) {
        AstCtx astCtx = new AstCtx();
        astCtx.setStr(str);
        return ast.test(astCtx);
    }
}
