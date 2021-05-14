package org.bambrikii.examples.combinatorics.factoradix;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class FactoradixTest {
    private Factoradix algo;

    @BeforeEach
    public void beforeEach() {
        algo = new Factoradix();
    }

    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[]{14, 2100, 10},
                new Object[]{349, 242010, 10}
        );
    }

    @ParameterizedTest(name = "to factoradic [{index}]: decimal={0}, expected factoradic={1}, radix={2}")
    @MethodSource("data")
    public void shouldConvertToFactoradic(int decimal, int factoradic, int radix) {
        assertThat(algo.toFactoradic(decimal, radix)).isEqualTo(factoradic);
    }

    @ParameterizedTest(name = "to decimal [{index}]: factoradic={1}, expected decimal={0}, radix={2}")
    @MethodSource("data")
    public void shouldConvertToDecimal(int decimal, int factoradic, int radix) {
        assertThat(algo.toNumber(factoradic, radix)).isEqualTo(decimal);
    }
}
