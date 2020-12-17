package org.bambrikii.examples.combinatorics.combinations;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class CombinationsRecursiveTest {
    @Test
    public void shouldGenerate() {
        CombinationsRecursive.combination(Arrays.asList("1", "2", "3", "4", "5"), 3, "");
    }
}
