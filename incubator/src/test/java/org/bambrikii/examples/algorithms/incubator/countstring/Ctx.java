package org.bambrikii.examples.algorithms.incubator.countstring;

class Ctx {
    private CharSequence str;
    private int pos = 0;
    private int depth;

    private boolean enableLogging;
    private int rollbackPos = -1;

    public Ctx(CharSequence str) {
        this.str = str;
    }

    public char next() {
        pos++;
        return chr();
    }

    public char chr() {
        if (pos < 0 || str.length() <= pos) {
            return 0;
        }
        return str.charAt(pos);
    }

    public int remaining() {
        return str.length() - pos;
    }

    public int start() {
        return pos;
    }

    public void move(int pos) {
        this.pos = pos;
    }

    public int getRollbackPos() {
        return rollbackPos;
    }

    public void rollback(int pos) {
        if (pos > rollbackPos) {
//            System.out.println("*** " + this.pos + " " + pos);
            this.rollbackPos = pos;
        }
    }

    public char charAt(int i) {
        return str.charAt(i);
    }

    public boolean hasMore() {
        return pos >= 0 && pos < str.length();
    }

    public CharSequence getStr() {
        return str;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int incDepth() {
        return depth++;
    }

    public int decDepth() {
        return --depth;
    }

    public void enableLogging() {
        this.enableLogging = true;
    }

    public int getDepth() {
        return depth;
    }

    private int[] locals;

    public int locals(int localIndex) {
        int newLen = localIndex + 1;
        if (locals == null) {
            locals = new int[newLen];
        } else {
            int currLen = locals.length;
            if (newLen > currLen) {
                int[] tmp = new int[newLen];
                System.arraycopy(locals, 0, tmp, 0, currLen);
                locals = tmp;
            }
        }
        return locals[localIndex];
    }

    public void localsFromPos(int localIndex) {
        locals[localIndex] = pos;
    }

    public void locals(int localIndex, int val) {
        this.locals[localIndex] = val;
    }
}
