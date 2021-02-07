package org.bambrikii.examples.algorithms.incubator.strings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bambrikii.examples.algorithms.incubator.utils.ConsoleUtils.ANSI_GREEN;
import static org.bambrikii.examples.algorithms.incubator.utils.ConsoleUtils.ANSI_RED;
import static org.bambrikii.examples.algorithms.incubator.utils.ConsoleUtils.ANSI_RESET;

public class PalindromeOutOfTwoStrings {
    @Test
    public void should1() {
        assertThat(buildPalindrome("bac", "bac")).isEqualTo("aba");
    }

    @Test
    public void should2() {
        assertThat(buildPalindrome("jdfh", "fds")).isEqualTo("dfhfd");
    }

    static boolean debug = true;

    static void log(int p, int l2, int pos1, int pos2, char char1, char char2, String s) {
        if (!debug) {
            return;
        }
        String color = char1 == char2 ? ANSI_GREEN : ANSI_RED;
        System.out.println(" start=" + p + ", len=" + l2 + ", range=" + pos1 + "-" + pos2
                + " " +
                s.substring(0, pos1) + color + s.charAt(pos1) + ANSI_RESET + s.substring(pos1 + 1, pos2) + color + s.charAt(pos2) + ANSI_RESET + s.substring(pos2 + 1));
    }

    static String buildPalindrome(String a, String b) {
        int la = a.length();
        int lb = b.length();
        int l = la + lb;

        for (int p = 0; p < la; p++) {
            for (int l2 = l - p; l2 > 1 && p + l2 > la; l2--) {
                boolean found = true;
                for (int i = 0; i < l2 / 2; i++) {
                    int pos1 = p + i;
                    int pos2 = p + l2 - i - 1;
                    char char1 = pos1 < la ? a.charAt(pos1) : b.charAt(la - pos1);
                    char char2 = pos2 < la ? a.charAt(pos2) : b.charAt(pos2 - la);
                    log(p, l2, pos1, pos2, char1, char2, a + b);
                    if (char1 != char2) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    return new StringBuilder()
                            .append(a, p, la).append(b, 0, l2 - (la - p))
                            .toString();
                }
            }
        }
        return "-1";
    }
}
