package org.bambrikii.examples.algorithms.incubator.strings.lexiorder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LexiOrderTest {
    @Test
    public void should1() {
        assertThat(morganAndString("JACK", "DANIEL")).isEqualTo("DAJACKNIEL");
    }

    @Test
    public void should2() {
        assertThat(morganAndString("ABACABA", "ABACABA")).isEqualTo("AABABACABACABA");
    }

    @Test
    public void should3() {
        assertThat(morganAndString("", "ABACABA")).isEqualTo("ABACABA");
    }

    @Test
    public void should4() {
        assertThat(morganAndString("", "")).isEqualTo("");
    }

    @Test
    public void should5() {
        assertThat(morganAndString("ZQ", "ZB")).isEqualTo("ZBZQ");
    }

    static String morganAndString(String a, String b) {
        StringBuilder res = new StringBuilder();

        int aLen = a.length();
        int bLen = b.length();

        int len = aLen + bLen;

        int posA = 0;
        int posB = 0;

        while (posA + posB < len) {
            if (posA >= a.length()) {
                res.append(b.charAt(posB++));
                continue;
            }
            if (posB >= b.length()) {
                res.append(a.charAt(posA++));
                continue;
            }
            char aCh = a.charAt(posA);
            char bCh = b.charAt(posB);
            if (aCh < bCh) {
                res.append(a.charAt(posA++));
            } else if (aCh > bCh) {
                res.append(b.charAt(posB++));
            } else {
                int posA2 = posA;
                int posB2 = posB;
                while (true) {
                    if (posA2 >= a.length()) {
                        res.append(b.charAt(posB++));
                        break;
                    }
                    if (posB2 >= b.length()) {
                        res.append(a.charAt(posA++));
                        break;
                    }
                    char charA2 = a.charAt(posA2);
                    char charB2 = b.charAt(posB2);
                    if (charA2 < charB2) {
                        res.append(a.charAt(posA++));
                        break;
                    } else if (a.charAt(posA2) > charB2) {
                        res.append(b.charAt(posB++));
                        break;
                    }
                    posA2++;
                    posB2++;
                }
            }
        }
        return res.toString();
    }

    public static List<Object[]> extractInputParams(String input) {
        try (
                Scanner scanner = new Scanner(LexiOrderTest.class.getResourceAsStream("input" + input + ".txt"));
                Scanner scanner2 = new Scanner(LexiOrderTest.class.getResourceAsStream("output" + input + ".txt"));
        ) {
            List<Object[]> results = new ArrayList<>();

            int t = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int tItr = 0; tItr < t; tItr++) {
                String a = scanner.nextLine();
                String b = scanner.nextLine();
                String expected = scanner2.nextLine();

                results.add(new Object[]{a, b, expected});
            }
            return results;
        }
    }

    public static Stream<Object[]> inputParams() {
        List<Object[]> results = new ArrayList<>();
//        results.addAll(extractInputParams("01"));
        results.addAll(extractInputParams("05"));
        return results.stream();
    }

    @ParameterizedTest
    @MethodSource("inputParams")
    public void shouldPassInputs(String a, String b, String expected) {
        assertThat(morganAndString(a, b)).isEqualTo(expected);
    }
}
