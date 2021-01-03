package org.bambrikii.examples.algorithms.incubator.countstring;

class CountCtx {
    private CharSequence str;
    private int pos = -1;
    private int rollbackPos = pos;
    private int depth;

    public CountCtx(CharSequence str) {
        this.str = str;
    }

    public char nextChar() {
        if (pos + 1 >= str.length()) {
            return 0;
        }
        return str.charAt(++pos);
    }

    public int charsLeft() {
        return str.length() - (pos + 1);
    }

    public int start() {
        return pos;
    }

    public void rollback(int pos) {
        if (pos > -1 && pos > rollbackPos) {
            rollbackPos = this.pos;
        }
        this.pos = pos;
    }

    public char charAt(int i) {
        return str.charAt(i);
    }

    public CharSequence getStr() {
        return str;
    }

    public int getPos() {
        return pos;
    }

    public int getRollbackPos() {
        return rollbackPos;
    }

    public int incDepth() {
        return depth++;
    }

    public int decDepth() {
        return --depth;
    }
}
