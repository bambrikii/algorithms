package org.bambrikii.examples.knapsack;

import org.junit.jupiter.api.Test;

public class KnapsackDynamicAlgoTest {
    @Test
    public void shouldTest() {
        int result = new KnapsackDynamicAlgo().calc(
                new int[]{5, 3, 5, 3, 2},
                new int[]{1, 2, 4, 2, 5},
                5
        );

        System.out.println(result);
    }
}
