package org.bambrikii.examples.combinatorics.combinations;

import org.junit.jupiter.api.Test;

public class CombinationsForwardBackwardTest {
    @Test
    public void shouldGenerate() {
        CombinationsForwardBackward.combination(new Object[]{1, 2, 3, 4, 5}, 3);
    }
}
