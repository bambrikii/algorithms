package org.bambrikii.examples.combinatorics.factoradix;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FactoradixTest {
    private Factoradix algo;

    @BeforeEach
    public void beforeEach() {
        algo = new Factoradix();
    }

    @Test
    public void shouldConvertToDecimal() {
        assertThat(algo.to(2100))
                .isEqualTo(14);
    }

    @Test
    public void shouldConvertToFactoradix() {
        assertThat(algo.from(14))
                .isEqualTo(2100);
    }
}
