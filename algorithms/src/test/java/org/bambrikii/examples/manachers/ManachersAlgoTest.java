package org.bambrikii.examples.manachers;

import org.bambrikii.examples.manacher.ManachersAlgo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://www.geeksforgeeks.org/manachers-algorithm-linear-time-longest-palindromic-substring-part-1/
 */
public class ManachersAlgoTest {
    private ManachersAlgo algo;

    public static List<Object[]> data() {
        return Arrays.asList(
                new Object[]{"forgeeksskeegfor", "geeksskeeg"},
                new Object[]{"abaaba", "abaaba"},
                new Object[]{"abababa", "abababa"},
//                new Object[]{"abcbabcbabcba", "abcbabcba"},
                new Object[]{"abcbabcbabcba", "abcbabcbabcba"}
        );
    }

    @BeforeEach
    public void before() {
        algo = new ManachersAlgo();
    }

    @ParameterizedTest
    @MethodSource("data")
    public void findOne(String in, String expected) {
        assertEquals(expected, algo.find(in));
    }
}
