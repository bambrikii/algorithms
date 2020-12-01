package org.bambrikii.examples.linear;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimplexAlgoTest {
    @Test
    public void shouldCalc() {
        double[][] input = {
                {3, 5, 78},
                {4, 1, 36},
                {-5, -4, 0}
        };
        SimplexAlgo algo = new SimplexAlgo();

        double[] result = algo.calc(input);

        assertThat(result).isEqualTo(new double[]{12, 6, 78});
    }
}
