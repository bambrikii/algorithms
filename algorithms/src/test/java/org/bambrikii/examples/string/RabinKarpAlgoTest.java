package org.bambrikii.examples.string;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RabinKarpAlgoTest {
    private RabinKarpAlgo rabinKarpAlgo;

    private static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of("abcdef", "cd", 2),
                Arguments.of("abcdef", "cde", 2),
                Arguments.of("abcdef", "cdefg", -1),
                Arguments.of("ab", "cdefg", -1),
                Arguments.of("abc", "abc", 0),
                Arguments.of("abc", "abd", -1)
        );
    }

    @BeforeEach
    public void before() {
        rabinKarpAlgo = new RabinKarpAlgo();
    }

    @ParameterizedTest
    @MethodSource("params")
    public void shouldMatch(String text, String match, int pos) {
        assertEquals(pos, rabinKarpAlgo.pos(text, match));
    }
}
