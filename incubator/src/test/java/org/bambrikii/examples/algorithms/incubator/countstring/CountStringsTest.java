package org.bambrikii.examples.algorithms.incubator.countstring;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bambrikii.examples.algorithms.incubator.countstring.AstBuilder.matchesMain;
import static org.bambrikii.examples.algorithms.incubator.countstring.CountStrings.countStrings;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountStringsTest {
    @Test
    public void testConcat() {
        int n = countStrings("((ab)(ba))", 4);
        System.out.println(n);
        assertEquals(1, n);
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

        ast.tryMatch(ctx);

        assertThat(((MainNode) ast).isMatched()).isTrue();
    }

    @Test
    public void shouldMatchOrOptional() {
        CountCtx patternCtx = new CountCtx("((((ab)|a)*)|(((aa)|(bb))*))");
        CountCtx ctx = new CountCtx("aabb");

        CountNode ast = matchesMain(patternCtx);
        ast.tryMatch(ctx);

        assertThat(((MainNode) ast).isMatched()).isTrue();
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
    @Disabled
    public void shouldPassInputs(String p, int len, int matches) {
        System.out.println("Test: pattern: " + p + ", sample length: " + len + ", expected matches: " + matches);

        int n = countStrings(p, len);
        System.out.println(n);
        assertEquals(matches, n);
    }
}
