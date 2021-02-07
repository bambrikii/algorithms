package org.bambrikii.examples.algorithms.incubator.strings.palindromeoutoftwostrings;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bambrikii.examples.algorithms.incubator.utils.ConsoleUtils.ANSI_BLACK_BACKGROUND;
import static org.bambrikii.examples.algorithms.incubator.utils.ConsoleUtils.ANSI_GREEN;
import static org.bambrikii.examples.algorithms.incubator.utils.ConsoleUtils.ANSI_RED;
import static org.bambrikii.examples.algorithms.incubator.utils.ConsoleUtils.ANSI_RESET;
import static org.bambrikii.examples.algorithms.incubator.utils.ConsoleUtils.ANSI_WHITE_BACKGROUND;

public class PalindromeOutOfTwoStrings {
    @Test
    public void should1() {
        assertThat(buildPalindrome("bac", "bac")).isEqualTo("aba");
    }

    @Test
    public void should2() {
        assertThat(buildPalindrome("jdfh", "fds")).isEqualTo("dfhfd");
    }

    @Test
    public void should4() {
        assertThat(buildPalindrome("imtpftgy", "tkfntylnflbfutsyovxmligoaiqz")).isEqualTo("fbf");
    }

    public static Stream<Object[]> inputParams() {
        List<Object[]> results = new ArrayList<>();
        results.addAll(extractInputParams("04"));
        return results.stream();
    }

    private static Collection<? extends String[]> extractInputParams(String code) {
        try (
                Scanner in = new Scanner(PalindromeOutOfTwoStrings.class.getResourceAsStream("input" + code + ".txt"));
                Scanner out = new Scanner(PalindromeOutOfTwoStrings.class.getResourceAsStream("output" + code + ".txt"));
        ) {
            int t = Integer.parseInt(in.nextLine().trim());
            List<String[]> results = new ArrayList<>();
            for (int tItr = 0; tItr < t; tItr++) {
                String a = in.nextLine();
                String b = in.nextLine();
                String expected = out.nextLine();
                results.add(new String[]{a, b, expected});
            }
            return results;
        }
    }

    @ParameterizedTest
    @MethodSource("inputParams")
    public void shouldPassInputs(String a, String b, String expected) {
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("e = " + expected);
        assertThat(buildPalindrome(a, b)).isEqualTo(expected);
    }

    static boolean debug = true;

    static void log(int l2, int pos1, int pos2, char char1, char char2, String a, String b) {
        if (!debug) {
            return;
        }
        String color = char1 == char2 ? ANSI_GREEN : ANSI_RED;
        StringBuilder sb = new StringBuilder();
        int lena = a.length();
        int lenb = b.length();
        String colorLeft = ANSI_BLACK_BACKGROUND;
        String colorRight = ANSI_WHITE_BACKGROUND;
        if (pos1 < lena) {
            sb
                    .append(colorLeft)
                    .append(a, 0, pos1)
                    .append(ANSI_RESET)
                    .append(color).append(a.charAt(pos1)).append(ANSI_RESET);
            if (pos2 < lena) {
                sb
                        .append(colorLeft)
                        .append(a, pos1 + 1, pos2)
                        .append(ANSI_RESET)
                        .append(color).append(a.charAt(pos2)).append(ANSI_RESET)
                        .append(colorLeft)
                        .append(a, pos2 + 1, lena)
                        .append(ANSI_RESET)
                        .append(colorRight)
                        .append(b)
                        .append(ANSI_RESET)
                ;
            } else {
                sb
                        .append(colorLeft)
                        .append(a, pos1 + 1, lena)
                        .append(ANSI_RESET)
                        .append(colorRight)
                        .append(b, 0, pos2 - lena)
                        .append(ANSI_RESET)
                        .append(color).append(b.charAt(pos2 - lena)).append(ANSI_RESET)
                        .append(colorRight)
                        .append(b, pos2 - lena + 1, lenb)
                        .append(ANSI_RESET)
                ;
            }
        } else {
            sb
                    .append(colorLeft)
                    .append(a)
                    .append(ANSI_RESET)
                    .append(colorRight)
                    .append(b, 0, pos1 - lena)
                    .append(ANSI_RESET)
                    .append(color).append(b.charAt(pos1 - lena)).append(ANSI_RESET)
                    .append(colorRight)
                    .append(b, pos1 + 1 - lena, pos2 - lena)
                    .append(ANSI_RESET)
                    .append(color).append(b.charAt(pos2 - lena)).append(ANSI_RESET)
                    .append(colorRight)
                    .append(b, pos2 + 1 - lena, lenb)
                    .append(ANSI_RESET)
            ;
        }
        String status = (char1 == char2) ? ANSI_GREEN + "continue" + ANSI_RESET : ANSI_RED + "break   " + ANSI_RESET;
        System.out.println("\tlen=" + l2 + ",\t[" + pos1 + ":" + pos2 + "]\t" + status + " : " + sb);
    }

    private static void log(String result) {
        if (!debug) {
            return;
        }
        System.out.println("\tmatch\t\t\t\t\t : " + ANSI_GREEN + result + ANSI_RESET);
    }

    static String buildPalindrome(String a, String b) {
        int lena = a.length();
        int lenb = b.length();
        String result = "";
        for (int pa2 = lena - 1; pa2 >= 0; pa2--) { // slide len a
            for (int pa1 = 0; pa1 <= pa2; pa1++) { // slide pos a
                for (int pb2 = lenb - 1; pb2 >= 0; pb2--) { // slide len b
                    for (int pb1 = 0; pb1 <= pb2; pb1++) { // slide pos b
                        int lm = pa2 - pa1 + 1 + pb2 - pb1 + 1; // match string len
                        boolean fnd = true;
                        for (int p1 = 0; p1 < lm / 2; p1++) { // find from pos
                            int p2 = lm - p1 - 1; // set pos
                            char char1 = pa1 + p1 <= pa2 ? a.charAt(pa1 + p1) : b.charAt(pb1 + p1 - (pa2 - pa1 + 1));
                            char char2 = pa1 + p2 <= pa2 ? a.charAt(pa1 + p2) : b.charAt(pb1 + p2 - (pa2 - pa1 + 1));
                            log(lm, p1, p2, char1, char2, a.substring(pa1, pa2 + 1), b.substring(pb1, pb2 + 1));
                            if (char1 != char2) {
                                fnd = false;
                                break;
                            }
                        }
                        if (fnd) {
                            if (result.length() <= lm) {
                                result = new StringBuilder()
                                        .append(a, pa1, pa2 + 1).append(b, pb1, pb2 + 1)
                                        .toString();
                                log(result);
                            }
                        }
                    }
                }
            }
        }
        return result.length() > 0 ? result : "-1";
    }
}
