package org.bambrikii.examples.numbers.prime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimeAlgoTest {
    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(3, true),
                Arguments.of(15, false),
                Arguments.of(27, false),
                Arguments.of(53, true),
                Arguments.of(131071, true),
                Arguments.of(2147483647, true)
        );
    }

    @ParameterizedTest(name = "[{index}] {displayName} {arguments}")
    @MethodSource("data")
    public void shouldTest(int val, boolean expected) {
        System.out.printf("validating " + val);
        assertEquals(expected, new PrimeAlgo().isPrime(val));
    }

    @Test
    public void shouldGetAllPrimes() {
        new PrimeAlgo()
                .getPrimesUntil(131071)
                .forEach(prime -> System.out.print(prime + " "));
        System.out.println();
    }
}
