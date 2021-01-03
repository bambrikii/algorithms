package org.bambrikii.examples.algorithms.incubator.countstring;

public class Prolog extends Node {
    private final Loop loop;

    public Prolog(Loop loop) {
        this.loop = loop;
    }

    @Override
    boolean match(Ctx ctx) {
        return loop.matchInit(ctx);
    }

    @Override
    void next(Node next) {
        super.next(next);
        loop.next(next);
    }
}
