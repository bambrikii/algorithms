package org.bambrikii.examples.algorithms.incubator.regex;

import java.util.LinkedList;

public abstract class AbstractCtx {
    private int pos;
    private LinkedList<Integer> positions = new LinkedList<>();

    public boolean has(char ch) {
        return getStr().charAt(pos) == ch;
    }

    public char currChar() {
        if (getStr().length() - 1 <= pos) {
            return 0;
        }
        return getStr().charAt(pos);
    }

    public int currPos() {
        return pos;
    }

    public boolean advance() {
        if ((getStr().length() - 1 <= pos)) {
            return false;
        }
        pos++;
        return true;
    }

    public void start() {
        positions.push(pos);
    }

    public void commit() {
        positions.pop();
    }

    public Integer rollback() {
        Integer pop = positions.pop();
        pos = pop;
        return pop;
    }

    public abstract String getStr();
}
