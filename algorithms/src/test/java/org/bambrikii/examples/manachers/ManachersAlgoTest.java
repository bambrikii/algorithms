package org.bambrikii.examples.manachers;

import org.bambrikii.examples.manacher.ManachersAlgo;
import org.bambrikii.examples.manacher.ManachersAlgo2;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://www.geeksforgeeks.org/manachers-algorithm-linear-time-longest-palindromic-substring-part-1/
 */
public class ManachersAlgoTest {
    public static List<Object[]> data() {
        return Arrays.asList(
                new Object[]{"forgeeksskeegfor", "geeksskeeg"},
                new Object[]{"abaaba", "abaaba"},
                new Object[]{"abababa", "abababa"},
//                new Object[]{"abcbabcbabcba", "abcbabcba"},
                new Object[]{"abcbabcbabcba", "abcbabcbabcba"}
        );
    }

    @ParameterizedTest(name = "{index}: {0} -> {1}")
    @MethodSource("data")
    public void findOne(String in, String expected) {
        assertEquals(expected, new ManachersAlgo().find(in));
    }

    @ParameterizedTest(name = "{index}: {0} -> {1}")
    @MethodSource("data")
    public void findTwo(String in, String expected) {
        assertEquals(expected, new ManachersAlgo2().find(in));
    }
}
