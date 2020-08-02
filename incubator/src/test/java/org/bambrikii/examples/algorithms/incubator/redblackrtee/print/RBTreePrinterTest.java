package org.bambrikii.examples.algorithms.incubator.redblackrtee.print;

import org.junit.Test;

public class RBTreePrinterTest {
    @Test
    public void testPrint() {
        RBTreePrinter.print(RbTreeTestUtils.createSampleRbTree());
    }

    @Test
    public void testPrint2() {
        RBTreePrinter.print(RbTreeTestUtils.createSampleRbTree2());
    }
}
