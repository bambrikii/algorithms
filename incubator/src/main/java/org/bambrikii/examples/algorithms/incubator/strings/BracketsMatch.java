package org.bambrikii.examples.algorithms.incubator.strings;

public class BracketsMatch {
    private boolean log = false;

    private void log(String msg) {
        if (!log) {
            return;
        }
        System.out.println(msg);
    }

    public static void main(String[] args) {
        BracketsMatch match = new BracketsMatch();
        System.out.println(match.isValid("([]){}") + " == " + true);
        System.out.println(match.isValid("([]){}}") + " == " + false);
        System.out.println(match.isValid("([])[}") + " == " + false);
        System.out.println(match.isValid("([])") + " == " + true);
        System.out.println(match.isValid("([})") + " == " + false);
    }

    private int pos2 = 0;
    private String s;

    public boolean isValid(String s) {
        this.pos2 = 0;
        this.s = s;
        while (!end(pos2)) {
            if (!anyExpr(pos2)) {
                return false;
            }
        }
        return pos2 == s.length();
    }

    private boolean anyExpr(int pos) {
        if (end(pos)) {
            pos2 = pos;
            return false;
        }
        if (rounded(pos)) {
            return true;
        }
        if (curly(pos)) {
            return true;
        }
        if (square(pos)) {
            return true;
        }
        pos2 = pos;
        return false;
    }

    private boolean rounded(int pos) {
        return expr(pos, '(', ')');
    }

    private boolean curly(int pos) {
        return expr(pos, '{', '}');
    }

    private boolean square(int pos) {
        return expr(pos, '[', ']');
    }

    private boolean end(int pos) {
        return pos >= s.length();
    }

    private boolean expr(int pos, char open, char close) {
        if (end(pos)) {
            return false;
        }
        char ch1 = s.charAt(pos);
        log("evaluating " + open + " ~ " + close + " " + ch1 + " " + pos + " " + pos2);
        if (ch1 != open) {
            return false;
        }
        int nextPos;
        if (anyExpr(pos + 1)) {
            nextPos = pos2;
        } else {
            nextPos = pos + 1;
        }
        if (end(nextPos)) {
            pos2 = pos;
            return false;
        }
        boolean b = s.charAt(nextPos) == close;
        if (b) {
            pos2 = nextPos + 1;
        } else {
            pos2 = pos;
        }
        log("ev " + open + " ~ " + close + " " + ch1 + " " + pos + " " + pos2 + " " + b);
        return b;
    }
}
