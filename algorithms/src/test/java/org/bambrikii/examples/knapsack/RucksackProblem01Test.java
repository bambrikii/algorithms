package org.bambrikii.examples.knapsack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RucksackProblem01Test {
    private RucksackProblem01 algo;

    @BeforeEach
    public void before() {
        algo = new RucksackProblem01();
    }

    @Test
    public void shouldTest01() {
        int[][] result = algo.solve(new int[][]{
                {5, 3, 2}, // values
                {3, 2, 1} // weights
        }, 5);
        algo.print(result);
    }
}
