package org.bambrikii.examples.algorithms.incubator.countstring;

import java.util.LinkedHashMap;

class Or extends Node {
    private LinkedHashMap<Node, Node> atoms = new LinkedHashMap<>();

    public void add(Node first, Node last) {
        this.atoms.put(first, last);
    }

    @Override
    boolean match(Ctx ctx) {
        logStart(ctx, "");
        for (Node atom : atoms.keySet()) {
            if (atom.match(ctx)) {
//                boolean n = next().match(ctx);
//                if (n) {
                logComplete(ctx, "atom matched");
                return true;
//                }
            }
        }
        logRollback(ctx, "no atom matched");
        return false;
    }

    @Override
    void next(Node next) {
        super.next(next);
        this.atoms.entrySet().forEach(entry -> entry.getValue().next(next));
    }
}
