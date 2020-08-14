package org.bambrikii.examples.matrix;

import org.assertj.core.api.Assertions;
import org.bambrikii.examples.martix.MartixOperations;
import org.junit.Test;

public class MatrixOperationsTest {
    @Test
    public void shouldMultiply() {
        // given
        int[][] a = new int[][] { //
                { 1, 2, 3 }, //
                { 4, 5, 6 }, //
                { 7, 8, 9 }, //
                { 10, 11, 12 } //
        };

        int[][] b = new int[][] { //
                { 1, 2 }, //
                { 4, 5 }, //
                { 7, 8 }, //
        };

        // when
        int[][] c = new MartixOperations().multiply(a, b);

        // then
        for (int i = 0; i < c.length; i++) {
            int[] ci = c[i];
            for (int j = 0; j < ci.length; j++) {
                System.out.print(c[i][j] + " ");
            }
            System.out.println();
        }

        Assertions.assertThat(c).isEqualTo(new int[][] { //
                { 3, 18, 45 }, //
                { 12, 45, 90 }, //
                { 21, 72, 135 }, //
                { 30, 99, 180 } //
        });
    }
}
