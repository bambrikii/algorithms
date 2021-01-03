package org.bambrikii.examples.algorithms.incubator.countstring;

import java.util.List;

class CharsNode extends CountNode {
    private List<Character> chars;

    public CharsNode(List<Character> chars) {
        this.chars = chars;
    }

    @Override
    boolean tryMatch(CountCtx ctx) {
        int pos = ctx.start();
        logStart(ctx, "chars: " + chars);
        for (int i = 0; i < chars.size(); i++) {
            char ch = ctx.nextChar();
            if (ch != chars.get(i)) {
                logRollback(ctx, "" + ch + "!=" + chars.get(i));
                ctx.rollback(pos);
                return false;
            }
        }
        logComplete(ctx, "");
        return getParent().matchCallback(ctx);
    }
}
