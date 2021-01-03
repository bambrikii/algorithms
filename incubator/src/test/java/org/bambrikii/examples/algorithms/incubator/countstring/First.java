package org.bambrikii.examples.algorithms.incubator.countstring;

class First extends Node {
    public boolean match(String str) {
        return match(new Ctx(str));
    }

    @Override
    boolean match(Ctx ctx) {
        return next().match(ctx);
    }
}
