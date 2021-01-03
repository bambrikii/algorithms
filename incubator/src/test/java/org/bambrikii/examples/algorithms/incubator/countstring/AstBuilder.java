package org.bambrikii.examples.algorithms.incubator.countstring;

import java.util.ArrayList;
import java.util.List;

public class AstBuilder {
    static CountNode matchesMain(CountCtx ctx) {
        int pos = ctx.start();
        List<CountNode> children = new ArrayList<>();
        while (true) {
            int sz = children.size();
            CountNode orNode = matchesOr(ctx);
            if (orNode != null) {
                children.add(orNode);
            } else {
                CountNode exprNode = matchesExpr(ctx);
                if (exprNode != null) {
                    children.add(exprNode);
                }
                CountNode charsNode = matchesChars(ctx);
                if (charsNode != null) {
                    children.add(charsNode);
                }
                if (sz == children.size()) {
                    break;
                }
            }
        }
        if (children.size() == 0) {
            ctx.rollback(pos);
            return null;
        }
        return new MainNode(children);
    }

    private static CountNode matchesOr(CountCtx ctx) {
        int pos = ctx.start();
        List<CountNode> children = new ArrayList<>();
        CountNode child = matchesChars(ctx);
        if (child != null) {
            children.add(child);
        } else {
            child = matchesExpr(ctx);
            if (child != null) {
                children.add(child);
            } else {
                ctx.rollback(pos);
                return null;
            }
        }
        while (true) {
            int pos2 = ctx.start();
            if (ctx.nextChar() != '|') {
                ctx.rollback(pos2);
                break;
            }
            child = matchesChars(ctx);
            if (child != null) {
                children.add(child);
            } else {
                child = matchesExpr(ctx);
                if (child != null) {
                    children.add(child);
                } else {
                    break;
                }
            }
        }
        if (children.size() < 2) {
            ctx.rollback(pos);
            return null;
        }
        return new OrNode(children);
    }

    private static CountNode matchesExpr(CountCtx ctx) {
        int pos = ctx.start();
        if (ctx.nextChar() != '(') {
            ctx.rollback(pos);
            return null;
        }
        List<CountNode> children = new ArrayList<>();
        int added = 1;
        while (added > 0) {
            added = 0;
            CountNode orNode = matchesOr(ctx);
            if (orNode != null) {
                children.add(orNode);
                added++;
            } else {
                CountNode charsNode = matchesChars(ctx);
                if (charsNode != null) {
                    children.add(charsNode);
                    added++;
                }
                CountNode exprNode = matchesExpr(ctx);
                if (exprNode != null) {
                    children.add(exprNode);
                    added++;
                }
            }
        }
        if (ctx.nextChar() != ')') {
            ctx.rollback(pos);
            return null;
        }
        int pos2 = ctx.start();
        char ch = ctx.nextChar();
        if (ch == '*') {
            return new TimesNode(new ExprNode(children), 0, Integer.MAX_VALUE);
        } else if (ch == '+') {
            return new TimesNode(new ExprNode(children), 1, Integer.MAX_VALUE);
        } else {
            ctx.rollback(pos2);
        }
        return new ExprNode(children);
    }

    private static CountNode matchesChars(CountCtx ctx) {
        int pos = ctx.start();
        int from = pos + 1;
        int to = pos + 1;
        while (true) {
            int pos2 = ctx.start();
            char ch = ctx.nextChar();
            if (ch >= 'a' && ch <= 'b') {
                to++;
            } else {
                ctx.rollback(pos2);
                break;
            }
        }
        if (to - from == 0) {
            ctx.rollback(pos);
            return null;
        }
        List<Character> chars = new ArrayList<>();
        for (int i = from; i < to; i++) {
            chars.add(ctx.charAt(i));
        }
        int pos2 = ctx.start();
        char ch = ctx.nextChar();
        if (ch == '*') {
            return new TimesNode(new CharsNode(chars), 0, Integer.MAX_VALUE);
        } else if (ch == '+') {
            return new TimesNode(new CharsNode(chars), 1, Integer.MAX_VALUE);
        } else {
            ctx.rollback(pos2);
        }
        return new CharsNode(chars);
    }
}
