package org.bambrikii.examples.algorithms.incubator.countstring;

import java.util.List;

class Chars extends Node {
    private List<Character> chars;

    public Chars(List<Character> chars) {
        this.chars = chars;
    }

    @Override
    boolean match(Ctx ctx) {
        int pos = ctx.start();
        logStart(ctx, "chars: " + chars);
        for (int i = 0; i < chars.size(); i++) {
            char ch = ctx.chr();
            if (ch != chars.get(i)) {
                logRollback(ctx, "" + ch + "!=" + chars.get(i));
                ctx.move(pos);
                ctx.rollback(pos + i);
                return false;
            }
            ctx.next();
        }
        logComplete(ctx, "");
        return next().match(ctx);
    }
}
