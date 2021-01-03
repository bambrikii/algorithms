package org.bambrikii.examples.algorithms.incubator.countstring;

public class True extends Node {
    public static final True TRUE = new True();

    @Override
    boolean match(Ctx ctx) {
        return true;
    }
}
