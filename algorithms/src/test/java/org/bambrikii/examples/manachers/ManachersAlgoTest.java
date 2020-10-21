package org.bambrikii.examples.manachers;

import org.bambrikii.examples.manacher.ManachersAlgo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * https://www.geeksforgeeks.org/manachers-algorithm-linear-time-longest-palindromic-substring-part-1/
 */
@RunWith(Parameterized.class)
public class ManachersAlgoTest {
    private ManachersAlgo algo;

    @Parameterized.Parameters(name = "{index}: {0} - {1}")
    public static List<Object[]> data() {
        return Arrays.asList(
                new Object[]{"forgeeksskeegfor", "geeksskeeg"},
                new Object[]{"abaaba", "abaaba"},
                new Object[]{"abababa", "abababa"},
//                new Object[]{"abcbabcbabcba", "abcbabcba"},
                new Object[]{"abcbabcbabcba", "abcbabcbabcba"}
        );
    }

    @Before
    public void before() {
        algo = new ManachersAlgo();
    }

    @Parameterized.Parameter
    public static String in;

    @Parameterized.Parameter(1)
    public static String expected;

    @Test
    public void findOne() {
        assertEquals(expected, algo.find(in));
    }
}
