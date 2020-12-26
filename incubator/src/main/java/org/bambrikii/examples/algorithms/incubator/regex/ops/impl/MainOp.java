package org.bambrikii.examples.algorithms.incubator.regex.ops.impl;

import org.bambrikii.examples.algorithms.incubator.regex.ast.AstNode;
import org.bambrikii.examples.algorithms.incubator.regex.ast.impl.MainNode;
import org.bambrikii.examples.algorithms.incubator.regex.ops.Op;
import org.bambrikii.examples.algorithms.incubator.regex.ops.OpCtx;

import java.util.ArrayList;
import java.util.List;

public class MainOp implements Op {
    @Override
    public AstNode ast(OpCtx ctx) {
        ctx.start();
        List<AstNode> children = new ArrayList<>();
        AstNode bolNode = new BolOp().ast(ctx);
        if (bolNode != null) {
            children.add(bolNode);
        }
        AstNode exprNode = new ExprOp().ast(ctx);
        if (exprNode != null) {
            children.add(exprNode);
        } else {
            ctx.rollback();
            return null;
        }
        AstNode eolNode = new EolOp().ast(ctx);
        if (eolNode != null) {
            children.add(eolNode);
        }
        ctx.commit();
        return new MainNode(children);
    }
}
