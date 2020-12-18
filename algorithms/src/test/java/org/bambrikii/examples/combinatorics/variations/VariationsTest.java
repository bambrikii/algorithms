package org.bambrikii.examples.combinatorics.variations;

import org.junit.jupiter.api.Test;

public class VariationsTest {
    @Test
    public void testAllVariations() {
        Variations.variations("12345", "", 3);
    }
}
