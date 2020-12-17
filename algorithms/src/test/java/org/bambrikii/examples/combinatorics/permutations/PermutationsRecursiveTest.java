package org.bambrikii.examples.combinatorics.permutations;

import org.junit.jupiter.api.Test;

public class PermutationsRecursiveTest {
    @Test
    public void shouldGenerate() {
        new PermutationsRecursive().permute(new int[]{1, 2, 3, 4});
    }
}
