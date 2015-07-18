package org.bambrikii.examples.sorting;

import org.bambrikii.examples.sorting.bubble.BubbleSort;
import org.bambrikii.examples.sorting.quick.QuickSort;
import org.junit.Before;
import org.junit.Test;

public class BubbleSortTest {

	private int[] array = new int[] { 5, 8, 1, 0, 9, 4, 3, 2, 7, 6 };

	@Before
	public void before() {

	}

	private void check(int[] array) throws SortingException {
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] > array[i + 1]) {
				throw new SortingException(array, i);
			}
		}
	}

	@Test
	public void testBubbleSort() throws SortingException {
		Sortable algo = new BubbleSort();
		check(algo.sort(array));
	}

	@Test
	public void testQuickSort() throws SortingException {
		Sortable algo = new QuickSort();
		check(algo.sort(array));
	}
}
