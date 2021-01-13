package org.bambrikii.examples.algorithms.incubator.countstring;

public class Str implements CharSequence {
    public static final int div = 60;
    private final int len;
    private final int elLen;
    private long[] els;

    public Str(int len) {
        this.len = len;
        elLen = len / div + 1;
        els = new long[elLen];
    }

    private boolean inc0(int curr) {
        if (curr == len) {
            return false;
        }
        int ix1 = curr / div;
        if (ix1 >= len) {
            return false;
        }
        int ix2 = curr % div;
        long el = els[ix1];
        long mapped = (el >> ix2) & 1;
        if (mapped == 1l) {
            els[ix1] = el - (1l << ix2);
            return inc0(curr + 1);
        } else {
            els[ix1] = el | (1l << ix2);
        }
        return true;
    }

    public boolean inc(int curr) {
        return inc0(curr);
    }

    @Override
    public int length() {
        return len;
    }

    public char charAt(int curr) {
        int ix1 = curr / div;
        int ix2 = curr % div;
        long el = els[ix1];
        long mapped = (el >> ix2) & 1l;
        if (mapped == 0) {
            return 'a';
        } else if (mapped == 1) {
            return 'b';
        } else {
            return 0;
        }
    }

    @Override
    public CharSequence subSequence(int start, int end) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = start; i < end; i++) {
//            sb.append(charAt(i));
//        }
//        return sb;
        return null;
    }

    public String toString() {
//        return subSequence(0, len).toString();
        return null;
    }

    protected void setCharAt(int curr, char ch) {
        int ix1 = curr / div;
        int ix2 = curr % div;
        long el = els[ix1];
        long mapped = (el >> ix2) & 1l;
        if (ch == 'a') {
            if (mapped == 1) {
                els[ix1] = el - (1l << ix2);
            }
        } else if (ch == 'b') {
            if (mapped == 0) {
                els[ix1] = el + (1l << ix2);
            }
        }
    }

    public void resetBefore(int pos) {
        for (int i = 0; i < pos; i++) {
            setCharAt(i, 'a');
        }
    }

    class StrReverse extends Str {
        public StrReverse() {
            super(Str.this.len);
        }

        @Override
        public boolean inc(int pos) {
            return super.inc(len - pos - 1);
        }

        @Override
        public char charAt(int pos) {
            return super.charAt(len - pos - 1);
        }

        @Override
        protected void setCharAt(int pos, char ch) {
            super.setCharAt(len - pos - 1, ch);
        }

        @Override
        public void resetBefore(int pos) {
            for (int i = pos + 1; i < len; i++) {
                setCharAt(i, 'a');
            }
        }
    }
}
