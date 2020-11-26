package org.bambrikii.examples.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringMatchAlgoTest {
    private static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of("abcdef", "cd", 2),
                Arguments.of("abcdef", "cde", 2),
                Arguments.of("abcdef", "cdefg", -1),
                Arguments.of("ab", "cdefg", -1),
                Arguments.of("abc", "abc", 0),
                Arguments.of("abc", "abd", -1),
                Arguments.of("ababdabe", "abd", 2) // Skip to the next in Knuth-Morris-Pratt algo
        );
    }

    @ParameterizedTest
    @MethodSource("params")
    public void shouldMatchRabinKarp(String text, String match, int pos) {
        assertEquals(pos, new RabinKarpAlgo().pos(text, match));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void shouldMatchKnuthMorrisPratt(String text, String match, int pos) {
        assertEquals(pos, new KnuthMorrisPrattAlgo().pos(text, match));
    }
}
