package org.bambrikii.examples.sorting;

import org.bambrikii.examples.sorting.bubble.BubbleSort;
import org.bambrikii.examples.sorting.my.MyBubbleSort;
import org.bambrikii.examples.sorting.my.MySort;
import org.bambrikii.examples.sorting.quick.QuickSort;
import org.junit.Before;
import org.junit.Test;

public class SortAlgosTest {

	private int[] array = new int[] { 5, 8, 1, 0, 9, 4, 3, 2, 7, 6 };
	private int[] reversed = new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };

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
	public void testBubbleSortReversed() throws SortingException {
		Sortable algo = new BubbleSort();
		check(algo.sort(reversed));
	}

	@Test
	public void testQuickSort() throws SortingException {
		Sortable algo = new QuickSort();
		check(algo.sort(array));
	}

	@Test
	public void testQuickSortReversed() throws SortingException {
		Sortable algo = new QuickSort();
		check(algo.sort(reversed));
	}

	@Test
	public void testMySort() throws SortingException {
		Sortable algo = new MySort();
		check(algo.sort(array));
	}

	@Test
	public void testMySortReversed() throws SortingException {
		Sortable algo = new MySort();
		check(algo.sort(reversed));
	}

	@Test
	public void testMyBubbleSort() throws SortingException {
		Sortable algo = new MyBubbleSort();
		check(algo.sort(array));
	}

	@Test
	public void testMyBubbleSortReversed() throws SortingException {
		Sortable algo = new MyBubbleSort();
		check(algo.sort(reversed));
	}
}
