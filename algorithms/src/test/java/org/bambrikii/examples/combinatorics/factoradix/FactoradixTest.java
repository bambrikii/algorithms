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
                new Object[]{14, new int[]{2, 1, 0, 0}},
                new Object[]{349, new int[]{2, 4, 2, 0, 1, 0}}
        );
    }

    @ParameterizedTest(name = "to factoradic [{index}]: decimal={0}, expected factoradic={1}")
    @MethodSource("data")
    public void shouldConvertToFactoradic(int number, int[] factoradic) {
        assertThat(algo.toFactoradic(number)).isEqualTo(factoradic);
    }

    @ParameterizedTest(name = "to decimal [{index}]: factoradic={1}, expected decimal={0}")
    @MethodSource("data")
    public void shouldConvertToNumber(int decimal, int[] factoradic) {
        assertThat(algo.toNumber(factoradic)).isEqualTo(decimal);
    }
}
