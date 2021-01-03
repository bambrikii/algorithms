package org.bambrikii.examples.algorithms.incubator.countstring;

import java.util.ArrayList;
import java.util.List;

public class Ast {
    private int localCount;

    private Node next(Node prev, Node next) {
        if (prev == null) {
            return next;
        }
        prev.next(next);
        return next;
    }

    private boolean isGroup(char ch) {
        return ch == '(';
    }

    private boolean isChar(char ch) {
        return 'a' <= ch && ch <= 'b';
    }

    private boolean isOr(char ch) {
        return ch == '|';
    }

    static First main(String str) {
        return new Ast().main(new Ctx(str));
    }

    First main(Ctx ctx) {
        First head = new First();
        Node tail = new Last();
        Node last = head;
        while (ctx.hasMore()) {
            Node[] expr = expr(ctx);
            last.next(expr[0]);
            last = expr[1];
        }
        last.next(tail);
        return head;
    }

    private Node[] group(Ctx ctx) {
        if (ctx.chr() != '(') {
            throw new IllegalArgumentException();
        }
        int localIndex = localCount++;
        Node head = new GroupHead(localIndex);
        Node tail = new GroupTail(localIndex);
        Node prev = head;
        ctx.next();
        if (ctx.hasMore()) {
            Node[] expr = expr(ctx);
            prev.next(expr[0]);
            prev = expr[1];
        }
        prev.next(tail);
        prev = tail;
        char ch = ctx.chr();
        if (ch != ')') {
            throw new IllegalArgumentException();
        }
        ctx.next();
        ch = ctx.chr();
        if (ch == '*') {
            Loop loop = loop(head, localCount++, 0, Integer.MAX_VALUE);
            Prolog prolog = new Prolog(loop);
            prev.next(loop);
            ctx.next();
            return new Node[]{prolog, prolog};
        } else if (ch == '+') {
            Loop loop = loop(head, localCount++, 1, Integer.MAX_VALUE);
            Prolog prolog = new Prolog(loop);
            prev.next(loop);
            ctx.next();
            return new Node[]{prolog, prolog};
        }
        return new Node[]{head, tail};
    }

    private Node[] expr(Ctx ctx) {
        Or or = null;
        Node first = null;
        Node last = null;
        while (ctx.hasMore()) {
            char ch = ctx.chr();
            if (isGroup(ch)) {
                Node[] group = group(ctx);
                if (first == null) {
                    first = group[0];
                }
                if (last != null) {
                    last.next(group[0]);
                }
                last = group[1];
            } else if (isChar(ch)) {
                Node chars = chars(ctx);
                if (first == null) {
                    first = chars;
                }
                if (last != null) {
                    last.next(chars);
                }
                last = chars;
            } else if (isOr(ch)) {
                or = or(ctx, or, first, last);
                first = null;
                last = null;
            } else {
                break;
            }
        }
        if (or == null) {
            return new Node[]{first, last};
        }
        if (first != null) {
            or.add(first, last);
        }
        return new Node[]{or, or};
    }

    private Or or(Ctx ctx, Or or, Node first, Node last) {
        if (first == null || last == null) {
            throw new IllegalArgumentException();
        }
        char ch = ctx.chr();
        if (ch != '|') {
            throw new IllegalArgumentException();
        }
        ctx.next();
        if (or == null) {
            or = new Or();
        }
        or.add(first, last);
        return or;
    }

    private Loop loop(Node atom, int localIndex, int min, int max) {
        return new Loop(atom, localIndex, min, max);
    }

    private Node chars(Ctx ctx) {
        List<Character> chars = new ArrayList<>();
        if (!ctx.hasMore()) {
            throw new IllegalArgumentException();
        }
        while (ctx.hasMore()) {
            char chr = ctx.chr();
            if (!isChar(chr)) {
                break;
            }
            chars.add(chr);
            ctx.next();
        }
        if (chars.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Chars node = new Chars(chars);
        char ch = ctx.chr();
        if (ch == '*') {
            ctx.next();
            Loop loop = loop(node, localCount++, 0, Integer.MAX_VALUE);
            node.next(loop);
            Prolog prolog = new Prolog(loop);
            return prolog;
        } else if (ch == '+') {
            ctx.next();
            Loop loop = loop(node, localCount++, 1, Integer.MAX_VALUE);
            node.next(loop);
            Prolog prolog = new Prolog(loop);
            return prolog;
        }
        return node;
    }
}
