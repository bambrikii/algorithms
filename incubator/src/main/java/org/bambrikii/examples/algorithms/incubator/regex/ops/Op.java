package org.bambrikii.examples.algorithms.incubator.regex.ops;

import org.bambrikii.examples.algorithms.incubator.regex.ast.AstNode;

public interface Op {
    AstNode ast(OpCtx ctx);
}
