package org.bambrikii.examples.algorithms.incubator.countstring;

public class Last extends Node {
    @Override
    boolean match(Ctx ctx) {
        return ctx.remaining() == 0;
    }
}
