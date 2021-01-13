package org.bambrikii.examples.algorithms.incubator.countstring;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bambrikii.examples.algorithms.incubator.countstring.CountStrings.countStrings;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountStringsTest {
    @Test
    public void testConcat() {
        assertEquals(1, countStrings("((ab)(ba))", 4));
    }

    @Test
    public void testConcatAbba() {
        assertThat(Ast.main("((ab)(ba))").match("abba")).isTrue();
    }

    @Test
    public void testOr() {
        assertEquals(2, countStrings("((ab)|(ba))", 2));
    }

    @Test
    public void testOrChar() {
        assertEquals(1, countStrings("((aa|b))", 1));
    }

    @Test
    public void testAny() {
        assertEquals(1, countStrings("(ab)*", 2));
    }

    @Test
    public void testOrAny() {
        Node.debug = true;
        assertEquals(32, countStrings("((a|b)*)", 5));
    }

    @Test
    public void testAnyNested() {
        assertThat(Ast.main("(ab*)*").match("ab")).isTrue();
    }

    @Test
    public void testCharAny() {
        assertEquals(1, countStrings("(a*)", 2));
    }

    @Test
    @Disabled
    public void testCharAny2() {
        assertThat(Pattern.compile("((a)*)b").matcher("aab").matches()).isTrue();
    }

    @Test
    public void testCharAnyOrdered() {
        Node.debug = true;
        assertEquals(3, countStrings("((b*)a*)", 2));
    }

    @Test
    public void testCharAnyOrderedBa() {
        assertThat(Ast.main("((b*)a*)").match("ba")).isTrue();
    }

    @Test
    public void testCharAnyOrderedAb() {
        assertThat(Ast.main("((b*)a*)").match("ab")).isFalse();
    }

    @Test
    public void testAtLeastOnce() {
        assertEquals(1, countStrings("(ab)+", 2));
    }

    @Test
    public void testCharAtLeastOnce() {
        assertEquals(1, countStrings("(a+)", 2));
    }

    @Test
    public void tryMatchLong() {
        assertThat(Ast.main("((a*)(b(a*)))").match("aba")).isTrue();
    }

    @Test
    public void shouldMatchOrOptional() {
        assertThat(Ast
//                .main("((((ab)|a)*)|(((aa)|(bb))*))")
                        .main("((ab|a)*|(aa|bb)*)")
                        .match("aabb")
        ).isTrue();
    }

    @Test
    public void shouldSkip() {
//        assertThat(Ast
//                .main("((((ab)|a)*)|(((aa)|(bb))*))")
//                .match("aabb")
//        ).isTrue();
        Node.debug = true;
        assertThat(countStrings("((((ab)|a)*)|(((aa)|(bb))*))", 5)).isEqualTo(8);
    }

    @Test
    @Disabled
    public void testCount100() {
        Node.debug = true;
        assertEquals(100, countStrings("((a*)(b(a*)))", 100));
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
//        results.add(new Object[]{"(((((((b|(((b|((a*)b))*)((b|((b*)*))a)))*)|a)*)|b)|(b|b))(a*))", 50, 750333556});
//        results.add(new Object[]{"(((((ba)|(((a|(((b|a)((ab)|(b*)))(ba)))(b|a))|a))(((a|a)*)|(((a|(b|(a(b|a))))(b*))(b|b))))*)*)", 50, 512127296});
//        results.add(new Object[]{"(((((((b|(((b|((a*)b))*)((b|((b*)*))a)))*)|a)*)|b)|(b|b))(a*))", 50, 750333556});
//        results.add(new Object[]{"((((b*)((b(a|a))a))(((a*)|a)a))((aa)((((((b*)*)(b|((b|(aa))|b)))(a|b))|(b*))*)))", 937477085, 971722885});

        results.addAll(extractInputParams("00"));
//        results.addAll(extractInputParams("01"));
//        results.addAll(extractInputParams("02"));
        return results.stream();
    }

    @ParameterizedTest
    @MethodSource("inputParams")
//    @Disabled
    @Timeout(value = 10)
    public void shouldPassInputs(String p, int len, int matches) {
        System.out.println("Test: pattern: " + p + ", sample length: " + len + ", expected matches: " + matches);
//        Node.debug = true;
        int n = countStrings(p, len);
        System.out.println(n);
        assertEquals(matches, n);
    }

    @Test
    public void shouldGenerate() {
        Str str = new Str(10);
        do {
            System.out.println(str.toString());
        } while (str.inc(0));
    }
}
