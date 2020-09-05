package org.bambrikii.examples.graphs.rbtree.print;

import org.bambrikii.examples.graphs.treeutils.RBTreePrinter;
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
