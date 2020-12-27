package org.bambrikii.examples.algorithms.incubator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountStrings {
    static class MainNode extends CountNode {
        private final List<CountNode> children;

        public MainNode(List<CountNode> children) {
            this.children = children;
        }

        @Override
        boolean matches(CountCtx ctx) {
            int pos = ctx.start();
            int matches = 0;
            for (CountNode node : children) {
                if (!node.matches(ctx)) {
                    break;
                }
                matches++;
            }
            if (matches == 0 || ctx.str.length() != ctx.pos + 1) {
                ctx.rollback(pos);
                return false;
            }
            return true;
        }
    }

    static class OrNode extends CountNode {
        private final List<CountNode> children;

        public OrNode(List<CountNode> children) {
            this.children = children;
        }

        @Override
        boolean matches(CountCtx ctx) {
            int pos = ctx.start();
            if (children.size() > 1) {
                for (CountNode node : children) {
                    if (node.matches(ctx)) {
                        return true;
                    }
                }
                return false;
            }
            ctx.rollback(pos);
            return false;
        }
    }

    static class TimesNode extends CountNode {
        private final CountNode child;
        private final int min;
        private final int max;

        public TimesNode(CountNode child, int min, int max) {
            this.child = child;
            this.min = min;
            this.max = max;
        }

        @Override
        boolean matches(CountCtx ctx) {
            int pos = ctx.start();
            int added = 0;
            while (true) {
                if (!child.matches(ctx)) {
                    break;
                }
                added++;
            }
            boolean b = added >= min && added <= max;
            if (!b) {
                ctx.rollback(pos);
                return false;
            }
            return true;
        }
    }

    static class CharsNode extends CountNode {
        private List<Character> chars;

        public CharsNode(List<Character> chars) {
            this.chars = chars;
        }

        @Override
        boolean matches(CountCtx ctx) {
            int pos = ctx.start();
            for (int i = 0; i < chars.size(); i++) {
                if (ctx.nextChar() != chars.get(i)) {
                    ctx.rollback(pos);
                    return false;
                }
            }
            return true;
        }
    }

    static class ExprNode extends CountNode {
        private final List<CountNode> children;

        public ExprNode(List<CountNode> children) {
            this.children = children;
        }

        @Override
        boolean matches(CountCtx ctx) {
            int pos = ctx.start();
            int added = 0;
            for (CountNode child : children) {
                if (!child.matches(ctx)) {
                    break;
                }
                added++;
            }
            if (added == 0) {
                ctx.rollback(pos);
                return false;
            }
            return true;
        }
    }

    abstract static class CountNode {
        abstract boolean matches(CountCtx ctx);
    }

    static class CountCtx {
        private CharSequence str;
        private int pos = -1;

        public CountCtx(CharSequence str) {
            this.str = str;
        }

        public char nextChar() {
            if (pos + 1 >= str.length()) {
                return 0;
            }
            return str.charAt(++pos);
        }

        public int start() {
            return pos;
        }

        public void rollback(int pos) {
            this.pos = pos;
        }
    }


    private static CountNode matchesMain(CountCtx ctx) {
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

    public static CountNode matchesChars(CountCtx ctx) {
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
            chars.add(ctx.str.charAt(i));
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

    /*
     * Complete the countStrings function below.
     */
    static int countStrings(String r, int l) {
        /*
         * Write your code here.
         */
        char[] dict = {'a', 'b'};
        int n = dict.length;
        int[] pos = new int[l];
        char[] sb = new char[l];
        for (int i = 0; i < l; i++) {
            sb[i] = dict[0];
        }
        CountCtx patternCtx = new CountCtx(r);
        CountNode ast = matchesMain(patternCtx);
        int nn = 0;
        do {
            StringBuilder str = buildString(dict, pos);
            CountCtx ctx = new CountCtx(str);
            if (ast.matches(ctx)) {
                System.out.println(r + " -> " + str + " (match)");
                nn++;
            } else {
                System.out.println(r + " != " + str);
            }
        } while (inc(pos, n, 0));
        return nn;
    }

    private static boolean inc(int[] pos, int n, int curr) {
        if (curr == pos.length) {
            return false;
        }
        if (pos[curr] == n - 1) {
            pos[curr] = 0;
            return inc(pos, n, curr + 1);
        }
        pos[curr]++;
        return true;
    }

    private static StringBuilder buildString(char[] dict, int[] pos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pos.length; i++) {
            sb.append(dict[pos[i]]);
        }
        return sb;
    }

    @Test
    public void testConcat() {
        int n = countStrings("((ab)(ba))", 4);
        System.out.println(n);
        assertEquals(n, 1);
    }

    @Test
    public void testOr() {
        int n = countStrings("((ab)|(ba))", 2);
        System.out.println(n);
        assertEquals(2, n);
    }

    @Test
    public void testOrChar() {
        int n = countStrings("((aa|b))", 1);
        System.out.println(n);
        assertEquals(1, n);
    }

    @Test
    public void testAny() {
        int n = countStrings("(ab)*", 2);
        System.out.println(n);
        assertEquals(1, n);
    }

    @Test
    public void testCharAny() {
        int n = countStrings("(a*)", 2);
        System.out.println(n);
        assertEquals(1, n);
    }

    @Test
    public void testCharAnyOrdered() {
        int n = countStrings("((b*)a*)", 2);
        System.out.println(n);
        assertEquals(3, n);
    }

    @Test
    public void testAtLeastOnce() {
        int n = countStrings("(ab)+", 2);
        System.out.println(n);
        assertEquals(1, n);
    }

    @Test
    public void testCharAtLeastOnce() {
        int n = countStrings("(a+)", 2);
        System.out.println(n);
        assertEquals(1, n);
    }

    public static Stream<Object[]> params() {
        return Stream.of(
                new Object[]{"((ab)|(ba))", 2, 2},
                new Object[]{"((a|b)*)", 5, 32},
                new Object[]{"((a*)(b(a*)))", 100, 100}
        );
    }

    @ParameterizedTest
    @MethodSource("params")
    public void shouldPassBulk(String p, int len, int matches) {
        System.out.println("Test: pattern: " + p + ", sample length: " + len + ", expected matches: " + matches);

        int n = countStrings(p, len);
        System.out.println(n);
        assertEquals(matches, n);
    }
}
