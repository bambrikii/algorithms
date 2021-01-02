package org.bambrikii.examples.algorithms.incubator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountStrings {
    public static final int OK = -2;

    static class MainNode extends CountNode {
        private final List<CountNode> children;

        public MainNode(List<CountNode> children) {
            this.children = children;
        }

        @Override
        int matches(CountCtx ctx) {
            int pos = ctx.start();
            int matches = 0;
            for (CountNode node : children) {
                if (node.matches(ctx) != OK) {
                    break;
                }
                matches++;
            }
            if (matches == 0 || ctx.str.length() != ctx.pos + 1) {
                return ctx.rollback(pos);
            }
            return OK;
        }
    }

    static class OrNode extends CountNode {
        private final List<CountNode> children;

        public OrNode(List<CountNode> children) {
            this.children = children;
        }

        @Override
        int matches(CountCtx ctx) {
            int pos = ctx.start();
            if (children.size() > 1) {
                for (CountNode node : children) {
                    if (node.matches(ctx) == OK) {
                        return OK;
                    }
                }
                return ctx.rollback(pos);
            }
            return ctx.rollback(pos);
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
        int matches(CountCtx ctx) {
            int pos = ctx.start();
            int added = 0;
            int lastPos;
            int charsLeft = ctx.charsLeft();
            while (true) {
                lastPos = child.matches(ctx);
                if ((lastPos != OK)) {
                    break;
                }
                added++;
                if (added >= charsLeft || added > max) {
                    break;
                }
            }
            boolean b = min <= added && added <= max;
            if (!b) {
                ctx.rollback(pos);
                return lastPos;
            }
            return OK;
        }
    }

    static class CharsNode extends CountNode {
        private List<Character> chars;

        public CharsNode(List<Character> chars) {
            this.chars = chars;
        }

        @Override
        int matches(CountCtx ctx) {
            int pos = ctx.start();
            for (int i = 0; i < chars.size(); i++) {
                if (ctx.nextChar() != chars.get(i)) {
                    return ctx.rollback(pos);
                }
            }
            return OK;
        }
    }

    static class ExprNode extends CountNode {
        private final List<CountNode> children;

        public ExprNode(List<CountNode> children) {
            this.children = children;
        }

        @Override
        int matches(CountCtx ctx) {
            int pos = ctx.start();
            int matches = 0;
            int lastPos = OK;
            for (CountNode child : children) {
                lastPos = child.matches(ctx);
                if (lastPos != OK) {
                    break;
                }
                matches++;
            }
            if (matches != children.size()) {
                ctx.rollback(pos);
                return lastPos;
            }
            return OK;
        }
    }

    abstract static class CountNode {
        abstract int matches(CountCtx ctx);
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

        public int charsLeft() {
            return str.length() - (pos + 1);
        }

        public int start() {
            return pos;
        }

        public int rollback(int pos) {
            int prevPos = this.pos;
            this.pos = pos;
            return prevPos;
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
        int count = 0;
        int startPos;
        do {
            StringBuilder str = buildString(dict, pos);
            CountCtx ctx = new CountCtx(str);
            int lastMatchPos = ast.matches(ctx);
            startPos = lastMatchPos < 0 ? 0 : lastMatchPos;
            if (lastMatchPos == OK) {
                System.out.println(r + " -> " + str + " (match)");
                count++;
            } else {
                for (int i = 0; i < startPos; i++) {
                    pos[i] = 0;
                }
                System.out.println(r + " != " + str);
            }
        } while (inc(pos, n, startPos));
        return count;
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

    @Test
    public void tryMatchLong() {
        CountCtx patternCtx = new CountCtx("((a*)(b(a*)))");
        CountNode ast = matchesMain(patternCtx);
        CountCtx ctx = new CountCtx("aa");

        int actual = ast.matches(ctx);

        assertThat(actual).isGreaterThanOrEqualTo(-1);
    }

    @Test
    public void shouldMatchOrOptional() {
        CountCtx patternCtx = new CountCtx("((((ab)|a)*)|(((aa)|(bb))*))");
        CountCtx ctx = new CountCtx("aabb");

        CountNode ast = matchesMain(patternCtx);
        int actual = ast.matches(ctx);

        assertThat(actual).isEqualTo(OK);
    }

    public static List<Object[]> extractInputParams(String name) {
        try (
                Scanner scanner = new Scanner(CountStrings.class.getResourceAsStream("input" + name + ".txt"));
                Scanner scanner2 = new Scanner(CountStrings.class.getResourceAsStream("expected" + name + ".txt"));
        ) {
            List<Object[]> results = new ArrayList<>();
            int t = Integer.parseInt(scanner.nextLine().trim());
            for (int tItr = 0; tItr < t; tItr++) {
                String[] rl = scanner.nextLine().split(" ");
                String r = rl[0];
                String expected = scanner2.nextLine();
                int l = Integer.parseInt(rl[1].trim());
                results.add(new Object[]{r, l, expected});
            }
            return results;
        }
    }

    public static Stream<Object[]> inputParams() {
        List<Object[]> results = new ArrayList<>();
        results.addAll(
                Arrays.asList(
//                new Object[]{"(((((((b|(((b|((a*)b))*)((b|((b*)*))a)))*)|a)*)|b)|(b|b))(a*))", 50, 750333556}//,
//                new Object[]{"(((((ba)|(((a|(((b|a)((ab)|(b*)))(ba)))(b|a))|a))(((a|a)*)|(((a|(b|(a(b|a))))(b*))(b|b))))*)*)", 50, 512127296}
                )
        );
        results.addAll(extractInputParams("00"));
        results.addAll(extractInputParams("01"));
        return results.stream();
    }

    @ParameterizedTest
    @MethodSource("inputParams")
    public void shouldPassInputs(String p, int len, int matches) {
        System.out.println("Test: pattern: " + p + ", sample length: " + len + ", expected matches: " + matches);

        int n = countStrings(p, len);
        System.out.println(n);
        assertEquals(matches, n);
    }
}
